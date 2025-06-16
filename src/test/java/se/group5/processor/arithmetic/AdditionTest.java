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
public class AdditionTest {
    private Processor processor;
    private ProgramInputStrategy strategy, compositesStrategy;

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
                      01 C PICTURE IS XX.
                      01 D PICTURE IS XXXX.
                      01 E PICTURE IS XXXXXX.
                  PROCEDURE DIVISION.
                      ADD "TE" TO "ST" GIVING C D E.
           """;
        String expectedOutput = "TEST";

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(expectedOutput.substring(0, 2), program.symbolTable.table.get("C").getValue().toString());
        Assert.assertEquals(expectedOutput, program.symbolTable.table.get("D").getValue().toString());
        Assert.assertEquals(expectedOutput, program.symbolTable.table.get("E").getValue().toString());
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
                      01 C PICTURE IS XX.
                      01 D PICTURE IS XXXXXXXX.
                      01 E PICTURE IS XXXXXXXXXXX.
                  PROCEDURE DIVISION.
                      ADD "TE" "EE" "AU" TO "ST" GIVING C D E. 
           """;
        String expectedOutput = "TEEEAUST";

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(expectedOutput.substring(0, 2), program.symbolTable.table.get("C").getValue().toString());
        Assert.assertEquals(expectedOutput, program.symbolTable.table.get("D").getValue().toString());
        Assert.assertEquals(expectedOutput, program.symbolTable.table.get("E").getValue().toString());
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

    @Test(expected = IllegalArgumentException.class)
    public void programRunTest_AddIdentifiers_ExpectedNumericPicture_Fail() throws IOException {
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
                      ADD A OF X TO B OF Y.
                      DISPLAY C.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void programRunTest_AddComposites_HasPictureClauseX_Fail() throws IOException {
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
                      ADD X TO Y.
                      DISPLAY C.
           """;

        // Act
        Program program = processor.parse(source);
        try {
            program.run(compositesStrategy);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void programRunTest_AddIdentifiers_HasPictureClauseA_Fail() throws IOException {
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
                      ADD B OF X TO B OF Y.
                      DISPLAY C.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void programRunTest_AddComposites_HasPictureClauseA_Fail() throws IOException {
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
                      ADD X TO Y.
                      DISPLAY C.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);
    }

    @Test
    public void programRunTest_AddComposites_Giving_Fail() throws IOException {
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
                          03 UC.
                              05 Z.
                                  07 A PICTURE IS 99.
                                  07 B PICTURE IS 99.
                                  07 C PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A OF X A OF Y B OF UA B OF UB.
                      ADD X TO Y GIVING Z.
                      DISPLAY C OF Z.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);

        // Assert
        Assert.assertEquals(3.0, (double)program.symbolTable.table.get("CONTAINER.UC.Z.A").getValue(), 0.001);
        Assert.assertEquals(7.0, (double)program.symbolTable.table.get("CONTAINER.UC.Z.B").getValue(), 0.001);

        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("CONTAINER.UA.X.A").getValue(), 0.001);
        Assert.assertEquals(3.0, (double)program.symbolTable.table.get("CONTAINER.UA.X.B").getValue(), 0.001);

        Assert.assertEquals(2.0, (double)program.symbolTable.table.get("CONTAINER.UB.Y.A").getValue(), 0.001);
        Assert.assertEquals(4.0, (double)program.symbolTable.table.get("CONTAINER.UB.Y.B").getValue(), 0.001);
    }

    @Test
    public void programRunTest_Add_Picture_Giving_Fail() throws IOException {
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
                      01 E PICTURE IS 9.
                      01 F PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C D.
                      ADD A B C TO D GIVING E F.
                      DISPLAY C.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);

        // Assert
        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("E").getValue(), 0.001);
        Assert.assertEquals(10.0, (double)program.symbolTable.table.get("F").getValue(), 0.001);
    }

    @Test
    public void programRunTest_AddMultipleComposites_Giving_Fail() throws IOException {
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
                          03 UC.
                              05 Z.
                                  07 A PICTURE IS 99.
                                  07 B PICTURE IS 99.
                                  07 C PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A OF X A OF Y B OF UA B OF UB.
                      ADD X Y X TO Y GIVING Z.
                      DISPLAY C OF Z.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);

        // Assert
        Assert.assertEquals(6.0, (double)program.symbolTable.table.get("CONTAINER.UC.Z.A").getValue(), 0.001);
        Assert.assertEquals(14.0, (double)program.symbolTable.table.get("CONTAINER.UC.Z.B").getValue(), 0.001);

        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("CONTAINER.UA.X.A").getValue(), 0.001);
        Assert.assertEquals(3.0, (double)program.symbolTable.table.get("CONTAINER.UA.X.B").getValue(), 0.001);

        Assert.assertEquals(2.0, (double)program.symbolTable.table.get("CONTAINER.UB.Y.A").getValue(), 0.001);
        Assert.assertEquals(4.0, (double)program.symbolTable.table.get("CONTAINER.UB.Y.B").getValue(), 0.001);
    }
}
