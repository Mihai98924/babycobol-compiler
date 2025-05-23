package se.group5.processor;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import se.group5.ast.Program;
import se.group5.build.AstBuilder;
import se.group5.parser.CoBabyBoL;
import se.group5.parser.CoBabyBoLLexer;

import java.io.*;

public class Processor {

    /**
     * Parse COBOL from a raw string and build the AST + symbol table in one go.
     */
    public Program parse(String source) throws IOException {
        return buildResult(parseFromCharStream(CharStreams.fromString(source, "string-input")));
    }

    /**
     * Parse COBOL from a class-path resource and build the AST + symbol table in one go.
     */
    public Program parseFile(String resourcePath) throws IOException {
        InputStream in = getClass().getResourceAsStream(resourcePath);
        if (in == null) throw new IOException("Resource not found on class-path: " + resourcePath);

        CharStream charStream = CharStreams.fromStream(in);
        return buildResult(parseFromCharStream(charStream));
    }

    /**
     * Convert the ANTLR parse tree into a {@link ParseResult}.
     */
    private Program buildResult(ParseTree tree) {
        AstBuilder builder = new AstBuilder();
        return (Program) builder.visit(tree);
    }

    /**
     * Core parser build logic reused by both string and file entry points.
     */
    private ParseTree parseFromCharStream(CharStream cs) {
        CoBabyBoLLexer lexer = new CoBabyBoLLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CoBabyBoL parser = new CoBabyBoL(tokens);

        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer,
                                    Object offendingSymbol,
                                    int line, int charPosInLine,
                                    String msg,
                                    RecognitionException e) {
                throw new ParseCancellationException(String.format(
                        "%s:%d:%d %s",
                        cs.getSourceName(), line, charPosInLine, msg));
            }
        });

        ParseTree tree = parser.program();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            throw new ParseCancellationException(
                    "Parser reported " + parser.getNumberOfSyntaxErrors() + " syntax error(s)");
        }
        return tree;
    }
}

