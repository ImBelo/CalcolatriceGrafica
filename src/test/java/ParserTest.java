import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import com.github.imbelo.calcolatricegrafica.model.interfaces.*;
import com.github.imbelo.calcolatricegrafica.model.token.*;
import com.github.imbelo.calcolatricegrafica.model.parser.*;
import com.github.imbelo.calcolatricegrafica.model.factories.*;
import java.util.LinkedList;
import java.util.Optional;

public class ParserTest {
  private Parser<Function> parser;
  @BeforeEach
  void init() {
    Lexer lexer = LexerImpl.builder()
        .alphabet(new AlphabetToken())
        .extractor(new TokenExtractorImpl())
        .errorFinder(new ErrorFinderSyntax())
        .build();
    Expression expr = ExpressionImpl.of("cos(x)");
    this.parser = ParserImpl.builder()
        .lexer(lexer)
        .alphabet(new AlphabetToken())
        .errorFinderSemantic(new ErrorFinderSemantic())
        .treeFactory(new TokenTreeFactory(new TokenFinder(new AlphabetToken())))
        .build();
		}

  @Test
  void operationsPriorityCheck() {
    Expression expr = ExpressionImpl.of("2+2*2+2^3");
    var result = parser.parse(expr);
    assertTrue(!result.isEmpty());
    var tree = result.get();
    // 2+2*2+2^3
    // 2+2*2+8
    // 2+4+8
    // 14
    assertEquals(14 , tree.evaluateAt());
  }
		@Test
  void operationsPriorityCheckParentheses() {
    Expression expr = ExpressionImpl.of("(2+2*2+2)^3");
    var result = parser.parse(expr);
    assertTrue(!result.isEmpty());
    var tree = result.get();
    // (2+2*2+2)^3
    // (2+4+2)^3
    // 8^3
    // 512
    assertEquals(512 , tree.evaluateAt());
  }
		@Test
  void logCheck() {
    Expression expr = ExpressionImpl.of("2+log(5)");
    var result = parser.parse(expr);
    assertTrue(!result.isEmpty());
    var tree = result.get();

    assertEquals(2+Math.log(5.0) , tree.evaluateAt(0),1E-3);
		}
  @Test
  void sinCheck() {
    Expression expr = ExpressionImpl.of("sin(6.3)*4");
    var result = parser.parse(expr);
    assertTrue(!result.isEmpty());
    var tree = result.get();

			assertEquals(Math.sin(6.3)*4 , tree.evaluateAt(0),1E-3);
  }
  @Test
  void cosCheck() {
    Expression expr = ExpressionImpl.of("cos(3+2/3)");
    var result = parser.parse(expr);
    assertTrue(!result.isEmpty());
    var tree = result.get();
		
    assertEquals(Math.cos(3+2/3.0) , tree.evaluateAt(0),1E-3);
		}
  @Test
  void cosCheck2() {
    Expression expr = ExpressionImpl.of("cos(x+2/x)");
    var result = parser.parse(expr);
    assertTrue(!result.isEmpty());
    var tree = result.get();
		
    assertEquals(Math.cos(3+2/3.0) , tree.evaluateAt(3),1E-3);
		}
  @Test
  void errorTest(){
    Expression expr = ExpressionImpl.of("2+");
    var result = parser.parse(expr);
    assertTrue(result.isEmpty());
    assertTrue(parser.getError() != null);
  }

}
