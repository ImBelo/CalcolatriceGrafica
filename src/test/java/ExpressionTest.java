import org.junit.jupiter.api.Test;
import com.github.imbelo.calcolatricegrafica.model.interfaces.*;
import com.github.imbelo.calcolatricegrafica.model.parser.*;
import com.github.imbelo.calcolatricegrafica.model.token.Arity;

import static org.junit.jupiter.api.Assertions.*;
public class ExpressionTest{

    @Test
    public void test() {
      Expression test = ExpressionImpl.of("2+x+y");
      assertEquals(Arity.Binary, test.getArity());
    }
    @Test
    public void test1() {
      Expression test = ExpressionImpl.of("2+x+x*y");
      assertEquals(Arity.Binary, test.getArity());
    }
    @Test
    public void test2() {
      Expression test = ExpressionImpl.of("");
      assertEquals(Arity.Unary, test.getArity());
      assertTrue(test.isEmpty());
    }
    @Test
    public void test3() {
      Expression test = ExpressionImpl.of("2+x");
      assertEquals(Arity.Unary, test.getArity());
    }

}
