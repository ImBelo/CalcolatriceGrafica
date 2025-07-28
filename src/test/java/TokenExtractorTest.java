import org.junit.jupiter.api.Test;
import com.github.imbelo.calcolatricegrafica.model.interfaces.*;
import com.github.imbelo.calcolatricegrafica.model.parser.*;
import com.github.imbelo.calcolatricegrafica.model.token.AlphabetToken;
import com.github.imbelo.calcolatricegrafica.model.token.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
public class TokenExtractorTest{
    private TokenExtractor tokenExtractor;
    private Expression expr;

    @BeforeEach
    public void setUp(){
      tokenExtractor = new TokenExtractorImpl(new AlphabetToken());

    }
    @Test
    public void test() {
      expr = ExpressionImpl.of("log(x)");
      Optional<Token> token = tokenExtractor.extract(expr);
      assertTrue(token.isPresent());
      assertEquals(new Log(),token.get());
      assertEquals(expr,ExpressionImpl.of("(x)"));
    }
    @Test
    public void test1() {
      expr = ExpressionImpl.of("sog(x)");
      Optional<Token> token = tokenExtractor.extract(expr);
       
      assertTrue(!token.isPresent());
      assertEquals(expr,ExpressionImpl.of("sog(x)"));

    }
    @Test
    public void test2() {
      expr = ExpressionImpl.of("+sog(x)");
      Optional<Token> token = tokenExtractor.extract(expr);
      assertTrue(token.isPresent());
      assertEquals(new Sum(),token.get());
      assertEquals(expr,ExpressionImpl.of("sog(x)"));
    }

}
