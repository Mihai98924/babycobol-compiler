package se.group5.build;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import se.group5.ast.Node;
import se.group5.ast.SymbolTable;
import se.group5.processor.Processor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class AstBuilderTest {

    @Test
    public void testParseAndBuildEmptyProgram() {


    }


    @Test
    public void parsesAndBuildsAst() throws Exception {
        Processor processor = new Processor(resourcePath);
        ParseTree tree = processor.parse();
        Assert.assertNotNull("ParseTree should not be null", tree);

        AstBuilder builder = new AstBuilder();
        Node rootNode = builder.visit(tree);
        SymbolTable symbolTable = builder.getSymbols();

        Assert.assertNotNull("AST should not be null", symbolTable);

        System.out.println(symbolTable.table);
    }
}
