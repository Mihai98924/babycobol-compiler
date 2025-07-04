package se.group5.build;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.ast.StringAccumulatingDisplayStrategy;
import se.group5.ast.call.Call;
import se.group5.ast.procedure.ProcedureList;
import se.group5.processor.Processor;

import java.util.stream.Collectors;

public class AstCallTest {
    private static Processor processor;

    @BeforeClass
    public static void setup() {
        processor = new Processor();
    }

    // CALL HELLO USING BY VALUE 5 BY REFERENCE A BY CONTENT 6.
    @Test
    public void CallOperation() throws Exception {
        String source = """
       IDENTIFICATION DIVISION.
          PROGRAM-ID. ADDTEST.
          AUTHOR. SUSPICIOUSLAWNMOWERS.
          DATE-WRITTEN. 2022-04-22.
       DATA DIVISION.
          01 A PICTURE IS 99.
       PROCEDURE DIVISION.
          CALL "src/test/resources/programs/test_0.baby".
""";
        Program program = processor.parse(source);
        var output = new StringAccumulatingDisplayStrategy();
        program.run(null, output);

        ProcedureList procedures = program.procedures;
        Assert.assertNotNull("Program should not be null", program);
        Assert.assertNotNull("Procedure list should not be null", procedures);
        Assert.assertEquals("42100054", output.getOutput());
    }

    @Test
    public void CallOperationUsingByReference() throws Exception {
        String source = """
       IDENTIFICATION DIVISION.
           PROGRAM-ID. ADDTEST.
           AUTHOR. SUSPICIOUSLAWNMOWERS.
           DATE-WRITTEN. 2022-04-22.
       DATA DIVISION.
           01 A PICTURE IS X(9).
           01 B PICTURE IS X(9).
           01 C PICTURE IS X(9).
       PROCEDURE DIVISION.
           CALL "MOVING" OF
      -"src/test/resources/test/target_function_program.baby"
      -USING BY REFERENCE A BY REFERENCE B BY REFERENCE C.
""";
        Program program = processor.parse(source);
        var output = new StringAccumulatingDisplayStrategy();
        program.run(null, output);
        ProcedureList procedures = program.procedures;
        Assert.assertNotNull("Program should not be null", program);
        Assert.assertNotNull("Procedure list should not be null", procedures);

        var symbols = program.symbolTable;
        Assert.assertTrue("A is present in the symbol table", symbols.resolve("A").isPresent());
        Assert.assertEquals("A has the correct value", "ITS ALIVE", symbols.resolve("A").get().getValue());
        Assert.assertTrue("B is present in the symbol table", symbols.resolve("B").isPresent());
        Assert.assertEquals("B has the correct value", "ITS ALIVE", symbols.resolve("B").get().getValue());
        Assert.assertTrue("C is present in the symbol table", symbols.resolve("C").isPresent());
        Assert.assertEquals("C has the correct value", "ITS ALIVE", symbols.resolve("C").get().getValue());
    }

    @Test
    public void CallOperationReturningByReference() throws Exception {
        String source = """
       IDENTIFICATION DIVISION.
           PROGRAM-ID. ADDTEST.
           AUTHOR. SUSPICIOUSLAWNMOWERS.
           DATE-WRITTEN. 2022-04-22.
       DATA DIVISION.
           01 A PICTURE IS X(9).
           01 B PICTURE IS X(9).
           01 C PICTURE IS X(9).
       PROCEDURE DIVISION.
           CALL "MOVING" OF
      -"src/test/resources/test/target_function_program.baby"
      -USING BY VALUE A BY VALUE B BY VALUE C
      -RETURNING BY REFERENCE A BY REFERENCE B BY REFERENCE C.
""";
        Program program = processor.parse(source);
        var output = new StringAccumulatingDisplayStrategy();
        program.run(null, output);
        ProcedureList procedures = program.procedures;
        Assert.assertNotNull("Program should not be null", program);
        Assert.assertNotNull("Procedure list should not be null", procedures);

        var symbols = program.symbolTable;
        var call = (Call) procedures.get(0).get();
        var calleeSymbols = call.getCallee().symbolTable;
        Assert.assertTrue("A is present in the symbol table", symbols.resolve("A").isPresent());
        Assert.assertTrue("A is present in the callee's symbol table", calleeSymbols.resolve("A").isPresent());
        Assert.assertEquals("A is passed by reference", symbols.resolve("A").get(), calleeSymbols.resolve("A").get());

        Assert.assertTrue("B is present in the callee's symbol table", calleeSymbols.resolve("B").isPresent());
        Assert.assertTrue("B is present in the symbol table", symbols.resolve("B").isPresent());
        Assert.assertEquals("B is passed by reference", symbols.resolve("B").get(), calleeSymbols.resolve("B").get());

        Assert.assertTrue("C is present in the callee's symbol table", calleeSymbols.resolve("C").isPresent());
        Assert.assertTrue("C is present in the symbol table", symbols.resolve("C").isPresent());
        Assert.assertEquals("C is passed by reference", symbols.resolve("C").get(), calleeSymbols.resolve("C").get());
    }


    @Test
    public void CallOperationReturningByContent() throws Exception {
        String source = """
       IDENTIFICATION DIVISION.
           PROGRAM-ID. ADDTEST.
           AUTHOR. SUSPICIOUSLAWNMOWERS.
           DATE-WRITTEN. 2022-04-22.
       DATA DIVISION.
           01 A PICTURE IS X(9).
           01 B PICTURE IS X(9).
           01 C PICTURE IS X(9).
       PROCEDURE DIVISION.
           CALL "MOVING" OF
      -"src/test/resources/test/target_function_program.baby"
      -USING BY VALUE A BY VALUE B BY VALUE C
      -RETURNING BY CONTENT A BY CONTENT B BY CONTENT C.
           CALL "MOVING" OF
      -"src/test/resources/test/target_function_program.baby"
      -USING BY REFERENCE A BY REFERENCE B BY REFERENCE C.
""";
        Program program = processor.parse(source);
        var output = new StringAccumulatingDisplayStrategy();
        Assert.assertThrows(IllegalArgumentException.class, () -> program.run(null, output));
    }

    @Test
    public void CallOperationUsingByValue() throws Exception {
        String source = """
       IDENTIFICATION DIVISION.
           PROGRAM-ID. ADDTEST.
           AUTHOR. SUSPICIOUSLAWNMOWERS.
           DATE-WRITTEN. 2022-04-22.
       DATA DIVISION.
           01 A PICTURE IS X(9).
           01 B PICTURE IS X(9).
           01 C PICTURE IS X(9).
       PROCEDURE DIVISION.
           CALL "MOVING" OF
      -"src/test/resources/test/target_function_program.baby"
      -USING BY VALUE A BY VALUE B BY VALUE C.
""";
        Program program = processor.parse(source);
        var output = new StringAccumulatingDisplayStrategy();
        program.run(null, output);
        ProcedureList procedures = program.procedures;
        Assert.assertNotNull("Program should not be null", program);
        Assert.assertNotNull("Procedure list should not be null", procedures);

        var symbols = program.symbolTable;
        Assert.assertTrue("A is present in the symbol table", symbols.resolve("A").isPresent());
        Assert.assertNull("A has the correct value", symbols.resolve("A").get().getValue());
        Assert.assertTrue("B is present in the symbol table", symbols.resolve("B").isPresent());
        Assert.assertNull("B has the correct value", symbols.resolve("B").get().getValue());
        Assert.assertTrue("C is present in the symbol table", symbols.resolve("C").isPresent());
        Assert.assertNull("C has the correct value", symbols.resolve("C").get().getValue());
    }

    @Test
    public void CallOperationUsingByContent() throws Exception {
        String source = """
       IDENTIFICATION DIVISION.
           PROGRAM-ID. ADDTEST.
           AUTHOR. SUSPICIOUSLAWNMOWERS.
           DATE-WRITTEN. 2022-04-22.
       DATA DIVISION.
           01 A PICTURE IS X(9).
           01 B PICTURE IS X(9).
           01 C PICTURE IS X(9).
       PROCEDURE DIVISION.
           CALL "MOVING" OF
      -"src/test/resources/test/target_function_program.baby"
      -USING BY CONTENT A BY CONTENT B BY CONTENT C.
""";
        Program program = processor.parse(source);
        var output = new StringAccumulatingDisplayStrategy();
        Assert.assertThrows(IllegalArgumentException.class, () -> program.run(null, output));
    }
}
