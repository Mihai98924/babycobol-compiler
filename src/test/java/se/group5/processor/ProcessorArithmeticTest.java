package se.group5.processor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.ast.ProgramDisplayStrategy;
import se.group5.ast.ProgramInputStrategy;

import java.io.IOException;


@Slf4j
public class ProcessorArithmeticTest {
    private Processor processor;
    private ProgramInputStrategy strategy, divisionStrategy, compositesStrategy;

    @Before
    public void setup() {
        processor = new Processor();
        strategy = new ProgramInputStrategy() {
            final String[] inputs = {"1", "2", "3"};
            int inputIndex = 0;
            @Override
            public String getInput() {
                return inputs[(inputIndex++) % inputs.length];
            }
        };

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

    // ADDITION

    @Test
    public void programRunTest_Add_Returns3_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      ADD A TO B.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(3.0, (double)program.symbolTable.table.get("B").getValue(), 0.001);
    }

    @Test
    public void programRunTest_AddManyToOne_Returns3_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      ADD A B C TO B.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(8.0, (double)program.symbolTable.table.get("B").getValue(), 0.001);
    }

    @Test
    public void programRunTest_AddNumericLiteral_Returns3_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      ADD 5 TO B.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(7.0, (double)program.symbolTable.table.get("B").getValue(), 0.001);
    }

    @Test
    public void programRunTest_AddToNumericLiteral_Returns3_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      ADD A TO 5 GIVING C.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(6.0, (double)program.symbolTable.table.get("C").getValue(), 0.001);
        Assert.assertEquals(2.0, (double)program.symbolTable.table.get("B").getValue(), 0.001);
        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
    }

    @Test
    public void programRunTest_AddTwoNumericLiterals_Returns11_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      ADD 5 TO 6 GIVING C D.
                      DISPLAY C.
           """;
        double expectedOutput = 11.0;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(expectedOutput, (double)program.symbolTable.table.get("C").getValue(), 0.001);
        Assert.assertEquals(expectedOutput, (double)program.symbolTable.table.get("D").getValue(), 0.001);
    }

    @Test
    public void programRunTest_AddManyNumericLiterals_Returns11_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      ADD 5 5 6 TO 6 GIVING C D.
                      DISPLAY C.
           """;
        double expectedOutput = 22.0;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(expectedOutput, (double)program.symbolTable.table.get("C").getValue(), 0.001);
        Assert.assertEquals(expectedOutput, (double)program.symbolTable.table.get("D").getValue(), 0.001);
    }

    @Test
    public void programRunTest_AddManyNumericLiteralsAndIdentifiers_Returns11_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      ADD 5 B 6 A TO 10 GIVING C D.
                      DISPLAY C.
           """;
        double expectedOutput = 24.0;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(expectedOutput, (double)program.symbolTable.table.get("C").getValue(), 0.001);
        Assert.assertEquals(expectedOutput, (double)program.symbolTable.table.get("D").getValue(), 0.001);
    }

    @Test
    public void programRunTest_AddTwoStringLiterals_ReturnsTEST_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      ADD "TE" TO "ST" GIVING C D.
                      DISPLAY C.
           """;
        String expectedOutput = "TEST";

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(expectedOutput, program.symbolTable.table.get("C").getValue().toString());
        Assert.assertEquals(expectedOutput, program.symbolTable.table.get("D").getValue().toString());
    }

    @Test
    public void programRunTest_AddManyStringLiterals_ReturnsTEST_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      ADD "TE" "EE" "AU" TO "ST" GIVING C D.
                      DISPLAY C.
           """;
        String expectedOutput = "TEEEAUST";

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(expectedOutput, program.symbolTable.table.get("C").getValue().toString());
        Assert.assertEquals(expectedOutput, program.symbolTable.table.get("D").getValue().toString());
    }

    @Test
    public void programRunTest_AddComposites_Returns_Passes() throws IOException {
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
                                  07 B PICTURE IS 99.
                          03 UB.
                              05 Y.
                                  07 A PICTURE IS 99.
                                  07 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A OF X A OF Y B D.
                      ADD X TO Y.
                      DISPLAY D.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);

        // Assert
        Assert.assertEquals(3.0, (double)program.symbolTable.table.get("CONTAINER.UB.Y.A").getValue(), 0.001);
        Assert.assertEquals(3.0, (double)program.symbolTable.table.get("B").getValue(), 0.001);
    }

    @Test
    public void programRunTest_AddMultipleCompositeElements_Returns_Passes() throws IOException {
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
                                  07 B PICTURE IS 99.
                          03 UB.
                              05 Y.
                                  07 A PICTURE IS 99.
                                  07 B PICTURE IS 99.
                                  07 C PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A OF X A OF Y B OF UA B OF UB.
                      ADD X TO Y.
                      DISPLAY C.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);

        // Assert
        Assert.assertEquals(3.0, (double)program.symbolTable.table.get("CONTAINER.UB.Y.A").getValue(), 0.001);
        Assert.assertEquals(7.0, (double)program.symbolTable.table.get("CONTAINER.UB.Y.B").getValue(), 0.001);
    }

    // SUBTRACTION

    @Test
    public void programRunTest_Subtract_ReturnsMinus1_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      SUBTRACT B FROM A.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(-1.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
    }

    @Test
    public void programRunTest_SubtractWithGIVING_ReturnsMinus1_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      SUBTRACT B FROM A GIVING C D.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
        Assert.assertEquals(-1.0, (double)program.symbolTable.table.get("C").getValue(), 0.001);
        Assert.assertEquals(-1.0, (double)program.symbolTable.table.get("D").getValue(), 0.001);
    }

    @Test
    public void programRunTest_SubtractLiteral_Returns1_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      SUBTRACT 10 FROM 11 GIVING C D.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("C").getValue(), 0.001);
        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("D").getValue(), 0.001);
    }

    @Test
    public void programRunTest_SubtractMultipleLiterals_ReturnsMinus69_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      SUBTRACT 10 10 60 FROM 11 GIVING C D.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(-69.0, (double)program.symbolTable.table.get("C").getValue(), 0.001);
        Assert.assertEquals(-69.0, (double)program.symbolTable.table.get("D").getValue(), 0.001);
    }

    @Test
    public void programRunTest_SubtractMultipleLiteralsWithIdentifiers_Returns2_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      SUBTRACT 10 B 6 FROM 20 GIVING C D.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(2.0, (double)program.symbolTable.table.get("C").getValue(), 0.001);
        Assert.assertEquals(2.0, (double)program.symbolTable.table.get("D").getValue(), 0.001);
    }

    @Test
    public void programRunTest_SubtractMultipleLiteralsWithAndFromIdentifiers_NoGiving_ReturnsMinus5_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      SUBTRACT 4 B 2 FROM C.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(-5.0, (double)program.symbolTable.table.get("C").getValue(), 0.001);
    }

    @Test
    public void programRunTest_SubtractComposites_Returns_Passes() throws IOException {
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
                                  07 B PICTURE IS 99.
                          03 UB.
                              05 Y.
                                  07 A PICTURE IS 99.
                                  07 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A OF X A OF Y B.
                      SUBTRACT X FROM Y.
                      DISPLAY D.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);

        // Assert
        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("CONTAINER.UB.Y.A").getValue(), 0.001);
        Assert.assertEquals(3.0, (double)program.symbolTable.table.get("B").getValue(), 0.001);
    }

    @Test
    public void programRunTest_SubtractMultipleCompositeElements_Returns_Passes() throws IOException {
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
                                  07 B PICTURE IS 99.
                          03 UB.
                              05 Y.
                                  07 A PICTURE IS 99.
                                  07 B PICTURE IS 99.
                                  07 C PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A OF X A OF Y B OF UA B OF UB.
                      SUBTRACT X FROM Y.
                      DISPLAY C.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);

        // Assert
        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("CONTAINER.UB.Y.A").getValue(), 0.001);
        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("CONTAINER.UB.Y.B").getValue(), 0.001);
    }

    // MULTIPLICATION

    @Test
    public void programRunTest_Multiply_Returns6_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      MULTIPLY B BY C GIVING A.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(6.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
    }

    @Test
    public void programRunTest_MultiplyLiterals_Returns30_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      MULTIPLY 6 BY 5 GIVING A.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(30.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
    }

    @Test
    public void programRunTest_MultiplyMultipleIdentifiers_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      MULTIPLY 6 BY A B C.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(6.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
        Assert.assertEquals(12.0, (double)program.symbolTable.table.get("B").getValue(), 0.001);
        Assert.assertEquals(18.0, (double)program.symbolTable.table.get("C").getValue(), 0.001);
    }

    @Test
    public void programRunTest_MultiplyLiteralByIdentifier_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      MULTIPLY 6 BY B GIVING A.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(2.0, (double)program.symbolTable.table.get("B").getValue(), 0.001);
        Assert.assertEquals(12.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
    }

    @Test
    public void programRunTest_MultiplyLiteralByMultipleIdentifiers_WithGiving_Fails() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      MULTIPLY 6 BY B C GIVING A.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(2.0, (double)program.symbolTable.table.get("B").getValue(), 0.001);
        Assert.assertEquals(12.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
    }

    // DIVISION

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
}
