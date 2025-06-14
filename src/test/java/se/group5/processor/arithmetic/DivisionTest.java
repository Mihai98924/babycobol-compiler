package se.group5.processor.arithmetic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.ast.ProgramInputStrategy;
import se.group5.processor.Processor;

import java.io.IOException;

@Slf4j
public class DivisionTest {
    private Processor processor;
    private ProgramInputStrategy divisionStrategy, compositesStrategy;

    @Before
    public void setup() {
        processor = new Processor();

        divisionStrategy = new ProgramInputStrategy() {
            final String[] inputs = {"2", "6", "3"};
            int inputIndex = 0;
            @Override
            public String getInput() {
                return inputs[(inputIndex++) % inputs.length];
            }
        };

        compositesStrategy = new ProgramInputStrategy() {
            final String[] inputs = {"1", "2", "3", "4"};
            int inputIndex = 0;
            @Override
            public String getInput() {
                return inputs[(inputIndex++) % inputs.length];
            }
        };
    }

    @Test
    public void programRunTest_Divide_Returns3_Passes() throws IOException {
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 9V9.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      DISPLAY A DELIMITED BY SIZE.
                      DIVIDE A INTO B GIVING C.
                      DISPLAY C.
           """;

        Program program = processor.parse(source);
        program.run(divisionStrategy, System.out::print);

        Assert.assertEquals(3.0, (double)program.symbolTable.table.get("C").getValue(), 0.001);
    }

    @Test
    public void programRunTest_DivideMultipleIdentifiers_Returns3And1_5_Passes() throws IOException {
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 9V9.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      DISPLAY A DELIMITED BY SIZE.
                      DIVIDE A INTO B C.
                      DISPLAY C.
           """;

        Program program = processor.parse(source);
        program.run(divisionStrategy, System.out::print);

        Assert.assertEquals(3.0, (double)program.symbolTable.table.get("B").getValue(), 0.001);
        Assert.assertEquals(1.5, (double)program.symbolTable.table.get("C").getValue(), 0.001);
    }

    @Test
    public void programRunTest_DivideLiteralIntoMultipleIdentifiers_Returns1_5And0_375_Passes() throws IOException {
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 9V9.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      DISPLAY A DELIMITED BY SIZE.
                      DIVIDE 4 INTO B C.
                      DISPLAY C.
           """;

        Program program = processor.parse(source);
        program.run(divisionStrategy, System.out::print);

        Assert.assertEquals(1.5, (double)program.symbolTable.table.get("B").getValue(), 0.001);
        Assert.assertEquals(0.75, (double)program.symbolTable.table.get("C").getValue(), 0.001);
    }

    @Test
    public void programRunTest_Divide_Remainder_1_Passes() throws IOException {
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 9V9.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      DISPLAY A DELIMITED BY SIZE.
                      DIVIDE 4 INTO 5 GIVING C REMAINDER D.
                      DISPLAY C.
           """;

        Program program = processor.parse(source);
        program.run(divisionStrategy, System.out::print);

        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("C").getValue(), 0.001);
        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("D").getValue(), 0.001);
    }

    @Test
    public void programRunTest_DivideLiterals_Returns3_Passes() throws IOException {
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 9V9.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      DISPLAY A DELIMITED BY SIZE.
                      DIVIDE 3 INTO 9 GIVING C.
                      DISPLAY C.
           """;

        Program program = processor.parse(source);
        program.run(divisionStrategy, System.out::print);

        Assert.assertEquals(3.0, (double)program.symbolTable.table.get("C").getValue(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void programRunTest_DivisionIdentifiers_HasPictureClauseX_Fail() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 CONTAINER.
                          03 UA.
                              05 X.
                                  07 A PICTURE IS 9X.
                                  07 B PICTURE IS 99.
                          03 UB.
                              05 Y.
                                  07 A PICTURE IS 99.
                                  07 B PICTURE IS 99.
                                  07 C PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A OF X A OF Y B OF UA B OF UB.
                      DIVIDE A OF X INTO B OF Y.
                      DISPLAY C.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void programRunTest_DivisionIdentifiers_HasPictureClauseA_Fail() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 CONTAINER.
                          03 UA.
                              05 X.
                                  07 A PICTURE IS 99.
                                  07 B PICTURE IS 9A.
                          03 UB.
                              05 Y.
                                  07 A PICTURE IS 99.
                                  07 B PICTURE IS 99.
                                  07 C PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A OF X A OF Y B OF UA B OF UB.
                      DIVIDE B OF X INTO B OF Y.
                      DISPLAY C.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);
    }
}
