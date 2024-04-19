package asm;

import java.util.ArrayList;
import java.util.Arrays;
import ast.Expr;

/*
 * AsmGenerator class generates asm instructions from AST
 */
public class AsmGenerator {
    private ArrayList<String> asmInstructions;

    /*
     * Create new instance of AsmGenerator from an AST.
     * 
     * @param ast AST to be translated into asm instructions.
     */
    public AsmGenerator(Expr ast) {
        String asmString = ast.toAsm();
        this.asmInstructions = new ArrayList<>(Arrays.asList(asmString.split("\n")));
    }

    /*
     * Retrieves the list of assembly instructions generated from the AST.
     *
     * @return ArrayList<String> An ArrayList of strings, each representing an individual assembly
     * instruction.
     */
    public ArrayList<String> getAsmInstructions() {
        return asmInstructions;
    }
}
