import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import com.github.imbelo.calcolatricegrafica.model.interfaces.*;
import com.github.imbelo.calcolatricegrafica.model.token.*;
import com.github.imbelo.calcolatricegrafica.model.parser.*;
import java.util.LinkedList;
import java.util.Optional;

public class LexerTest {
    
    private Lexer lexer;
    private AlphabetToken alphabet;
    
    @BeforeEach
    void setUp() {
        // Create a simple alphabet for testing
        alphabet = new AlphabetToken();
        // Assuming AlphabetToken has methods to add variables, operators, etc.

        lexer = LexerImpl.builder()
            .extractor(new TokenExtractorImpl())
            .errorFinder(new ErrorFinderSyntax())
            .alphabet(alphabet)
            .build();
    }
  @Test
  void test() {
    Expression expr = ExpressionImpl.of("2+x+cos(x+3.34)^4");
    List<Token> expected = new LinkedList<>();
		expected.add(new MyNumber(2.0));
		expected.add(new Sum());
		expected.add(new VariableX());
		expected.add(new Sum());
		expected.add(new Cos());
		expected.add(new OpenParentheses());
		expected.add(new VariableX());
		expected.add(new Sum());
		expected.add(new MyNumber(3.34));
		expected.add(new ClosedParentheses());
		expected.add(new Pow());
		expected.add(new MyNumber(4.0));
		
		List<Token> actual = lexer.tokenize(expr).get();
		assertEquals(expected,actual);
  }
   void testBasicArithmetic() {
        Expression expr = ExpressionImpl.of("2+x+3");
        List<Token> expected = new LinkedList<>();
        expected.add(new MyNumber(2.0));
        expected.add(new Sum());
        expected.add(new VariableX());
        expected.add(new Sum());
        expected.add(new MyNumber(3.0));
        
        List<Token> actual = lexer.tokenize(expr).get();
        assertEquals(expected, actual);
    }

    @Test
    void testComplexExpression() {
        Expression expr = ExpressionImpl.of("2+y+cos(x+3.34)^4");
        List<Token> expected = new LinkedList<>();
        expected.add(new MyNumber(2.0));
        expected.add(new Sum());
        expected.add(new VariableY());
        expected.add(new Sum());
        expected.add(new Cos());
        expected.add(new OpenParentheses());
        expected.add(new VariableX());
        expected.add(new Sum());
        expected.add(new MyNumber(3.34));
        expected.add(new ClosedParentheses());
        expected.add(new Pow());
        expected.add(new MyNumber(4.0));
        
        List<Token> actual = lexer.tokenize(expr).get();
        assertEquals(expected, actual);
    }

    @Test
    void testWithMultipleVariables() {
        Expression expr = ExpressionImpl.of("x*y+sin(y)-y/2");
        List<Token> expected = new LinkedList<>();
        expected.add(new VariableX());
        expected.add(new Product());
        expected.add(new VariableY());
        expected.add(new Sum());
        expected.add(new Sin());
        expected.add(new OpenParentheses());
        expected.add(new VariableY());
        expected.add(new ClosedParentheses());
        expected.add(new Difference());
        expected.add(new VariableY());
        expected.add(new Division());
        expected.add(new MyNumber(2.0));
        
        List<Token> actual = lexer.tokenize(expr).get();
        assertEquals(expected, actual);
    }
    @Test
    void testNestedFunctions() {
        Expression expr = ExpressionImpl.of("cos(sin(x)) + tan(2*x)");
        List<Token> expected = new LinkedList<>();
        expected.add(new Cos());
        expected.add(new OpenParentheses());
        expected.add(new Sin());
        expected.add(new OpenParentheses());
        expected.add(new VariableX());
        expected.add(new ClosedParentheses());
        expected.add(new ClosedParentheses());
        expected.add(new Sum());
        expected.add(new Tan());
        expected.add(new OpenParentheses());
        expected.add(new MyNumber(2.0));
        expected.add(new Product());
        expected.add(new VariableX());
        expected.add(new ClosedParentheses());
        
        List<Token> actual = lexer.tokenize(expr).get();
        assertEquals(expected, actual);
    }

    @Test
    void testDecimalNumbers() {
        Expression expr = ExpressionImpl.of("1.5 + 0.003 * 100.0");
        List<Token> expected = new LinkedList<>();
        expected.add(new MyNumber(1.5));
        expected.add(new Sum());
        expected.add(new MyNumber(0.003));
        expected.add(new Product());
        expected.add(new MyNumber(100.0));
        
        List<Token> actual = lexer.tokenize(expr).get();
        assertEquals(expected, actual);
    }

    @Test
    void testEmptyExpression() {
        Expression expr = ExpressionImpl.of("");
        Optional<List<Token>> result = lexer.tokenize(expr);
        assertTrue(!result.isEmpty(), "Empty expression should return non empty optional");
    }

    @Test
    void testInvalidExpression() {
        Expression expr = ExpressionImpl.of("x @ y # z");
        Optional<List<Token>> result = lexer.tokenize(expr);
        assertTrue(result.isEmpty(), "Expression with invalid characters should return empty optional");
    }

    @Test
    void testComplexParentheses() {
        Expression expr = ExpressionImpl.of("((x + y) * (x - y)) / (x ^ y)");
        List<Token> expected = new LinkedList<>();
        expected.add(new OpenParentheses());
        expected.add(new OpenParentheses());
        expected.add(new VariableX());
        expected.add(new Sum());
        expected.add(new VariableY());
        expected.add(new ClosedParentheses());
        expected.add(new Product());
        expected.add(new OpenParentheses());
        expected.add(new VariableX());
        expected.add(new Difference());
        expected.add(new VariableY());
        expected.add(new ClosedParentheses());
        expected.add(new ClosedParentheses());
        expected.add(new Division());
        expected.add(new OpenParentheses());
        expected.add(new VariableX());
        expected.add(new Pow());
        expected.add(new VariableY());
        expected.add(new ClosedParentheses());
        
        List<Token> actual = lexer.tokenize(expr).get();
        assertEquals(expected, actual);
    }
}
