import org.junit.Test;
import asm.AsmGenerator;
import ast.Expr;
import org.junit.Assert;
import java.util.ArrayList;

public class AsmGeneratorTest {
    @Test
    public void testAsmInstructionsGeneration() {
        SimpleExpr expr = new SimpleExpr("IM 5\nPU\nIM 6\nAD\nPO");

        AsmGenerator asmGenerator = new AsmGenerator(expr);
        ArrayList<String> instructions = asmGenerator.getAsmInstructions();

        Assert.assertNotNull("Instructions list should not be null", instructions);
        Assert.assertEquals("Instructions list should have 5 entries", 5, instructions.size());
        Assert.assertEquals("First instruction should be 'IM 5'", "IM 5", instructions.get(0));
        Assert.assertEquals("Second instruction should be 'PU'", "PU", instructions.get(1));
        Assert.assertEquals("Third instruction should be 'IM 6'", "IM 6", instructions.get(2));
        Assert.assertEquals("Fourth instruction should be 'AD'", "AD", instructions.get(3));
        Assert.assertEquals("Fifth instruction should be 'PO'", "PO", instructions.get(4));
    }
}

class SimpleExpr extends Expr {
    private final String asmOutput;

    public SimpleExpr(String asmOutput) {
        this.asmOutput = asmOutput;
    }

    @Override
    public String toAsm() {
        return asmOutput;
    }
}