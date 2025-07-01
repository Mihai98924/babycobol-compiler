package se.group5.processor;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import se.group5.ast.Node;
import se.group5.ast.Program;
import se.group5.ast.SymbolTable;
import se.group5.build.AstBuilder;
import se.group5.parser.CoBabyBoL;
import se.group5.parser.CoBabyBoLLexer;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Processor {

    /**
     * Parse COBOL from a raw string and build the AST + symbol table in one go.
     */
    public Program parse(String source) throws IOException {
        String cleaned = stripCobolComments(source);
        return buildResult(parseFromCharStream(CharStreams.fromString(cleaned, "string-input")));
    }

    /**
     * Parse COBOL from a class-path resource and build the AST + symbol table in one go.
     */
    public Program parseFile(String filePath) throws IOException {
        FileInputStream in = new FileInputStream(filePath);
        if(in == null)
            throw new FileNotFoundException("File not found: " + filePath);
        String cleaned = stripCobolComments(in);
        return buildResult(parseFromCharStream(CharStreams.fromString(cleaned, filePath)));
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

    /**
     * Strip COBOL-style comments from a raw string.
     */
    public static String stripCobolComments(String input) throws IOException {
        return stripCobolComments(new BufferedReader(new StringReader(input)));
    }

    /**
     * Strip COBOL-style comments from an input stream.
     */
    public static String stripCobolComments(InputStream in) throws IOException {
        return stripCobolComments(new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8)));
    }

    /**
     * Shared internal logic for COBOL comment stripping.
     */
    private static String stripCobolComments(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder(4096);
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.length() >  80) {
                continue;
            }
            sb.append(line).append('\n');
        }
        return sb.toString();
    }
}

