package se.group5.build;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.ast.ProgramInputStrategy;
import se.group5.ast.data.DataElement;
import se.group5.ast.data.Representation;
import se.group5.processor.Processor;

import java.io.IOException;

@Slf4j
public class AstPictureLikeTest {

    private static Processor processor;

    @BeforeClass
    public static void setup() {
        processor = new Processor();
    }


    @Test
    public void programRunTest_Ast_CompositeLike_Passes() throws IOException {
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
                      01 G LIKE Y.
                          03 W PICTURE IS 99.
                  PROCEDURE DIVISION.
                      MOVE HIGH-VALUES TO Y.
                      DISPLAY B OF Y.
           """;

        // Act
        Program program = processor.parse(source);
        System.out.println(program);

        String YFullpath = program.symbolTable.getFullyQualifiedIdentifier("Y");
        String WFullpath = program.symbolTable.getFullyQualifiedIdentifier("W");

        String Ypath = YFullpath.substring(0, YFullpath.lastIndexOf('.'));
        String Wpath = WFullpath.substring(0, WFullpath.lastIndexOf('.'));

        // Assert
        Assert.assertEquals(Ypath + ".G", Wpath);
    }

    @Test
    public void programRunTest_Ast_FieldLike_Passes() throws IOException {
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
                      01 G.
                          03 W LIKE A OF Y.
                  PROCEDURE DIVISION.
                      MOVE HIGH-VALUES TO Y.
                      DISPLAY B OF Y.
           """;

        // Act
        Program program = processor.parse(source);
        System.out.println(program);

        String AFullpath = program.symbolTable.getFullyQualifiedIdentifier("Y.A");
        String WFullpath = program.symbolTable.getFullyQualifiedIdentifier("W");


        Representation Wpicture = ((DataElement)program.symbolTable.resolve(WFullpath).get()).picture();
        Representation Apicture = ((DataElement)program.symbolTable.resolve(AFullpath).get()).picture();

        Assert.assertTrue(Wpicture.matches(Apicture.symbols()));
    }
}
