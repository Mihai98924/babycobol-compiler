package ut.pp;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import ut.pp.codeGen.Code;
import ut.pp.exceptions.CodeError;
import ut.pp.exceptions.CompilerException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static boolean DEBUG_MODE = false;
    private static final boolean GENERATE_GRAPH = true;

    private static final boolean TEST_ALL_BROKEN = true;

    private static final boolean TEST_ALL_WORKING = true;

    public static boolean DEBUG_MODE() {
        return DEBUG_MODE;
    }

    private static boolean UNROLL_ARRAY_ACCESS = true;

    public static boolean UNROLL_ARRAY_ACCESS() {
        return UNROLL_ARRAY_ACCESS;
    }

    private static final Compiler COMPILER = new Compiler();


    private static void compile(String filepath) {
        CharStream stream = readFile(filepath);
        if (stream == null)
            return;

        try {
            Code code = COMPILER.compile(stream);
            if (filepath.endsWith(".pot")) {
                filepath = filepath.substring(0, filepath.length() - 4);
            }
            writeFile(filepath, code);
            if (GENERATE_GRAPH) {
                StringBuilder graphString = COMPILER.generateGraph();
                writeFile(filepath, graphString);
            }
        } catch (CompilerException e) {
            for (CodeError error : e.getErrors())
                System.err.println(error.getMessage());
        }
    }

    private static CharStream readFile(String filepath) {
        try {
            return CharStreams.fromFileName(filepath);
        } catch (IOException e) {
            System.err.printf("Could not read from file: '%s'\n", filepath);
            System.err.println(e.getMessage());
            return null;
        }
    }

    private static void writeFile(String filepath, Code code) {
        filepath += ".hs";

        try (FileWriter out = new FileWriter(filepath)) {
            out.write(code.toString());
        } catch (IOException e) {
            System.err.printf("Could not write to file: '%s'\n", filepath);
            System.err.println(e.getMessage());
        }

    }

    private static void writeFile(String filepath, StringBuilder graphString) {
        filepath += ".dot";

        try (FileWriter out = new FileWriter(filepath)) {
            out.write(graphString.toString());
        } catch (IOException e) {
            System.err.printf("Could not write to file: '%s'\n", filepath);
            System.err.println(e.getMessage());
        }

    }

    private static void toggleFlags(String[] args) {
        System.out.print("Compiler flags: ");
        for (String flag : args) {
            if (flag.equals("-DEBUG")) {
                DEBUG_MODE = true;
                System.out.print("-DEBUG ");
            } else if (flag.equals("-SMALLER")) {
                UNROLL_ARRAY_ACCESS = false;
                System.out.print("-SMALLER");
            }
        }
        System.out.print("\n\n");
    }

    public static void main(String[] args) {
        toggleFlags(args);
        for (String filepath : args) {
            if (filepath.charAt(0) == '-') {
                continue;
            }    // compiler flag instead of filepath.
            compileProgram(filepath);
        }
        if (TEST_ALL_BROKEN) {
            String brokenProgramsRoot = "src/main/resources/testPrograms/broken_programs/";
            compilePrograms(brokenProgramsRoot);
        }

        if (TEST_ALL_WORKING) {
            String workingProgramsRoot = "src/main/resources/testPrograms/";
            compilePrograms(workingProgramsRoot);
        }
    }

    private static void compilePrograms(String programsRoot) {
        System.out.println(programsRoot);
        File folder = new File(programsRoot);
        File[] brokenPrograms = folder.listFiles();
        assert brokenPrograms != null;
        for (File program : brokenPrograms) {
            if (program.isFile() && program.toString().endsWith(".pot")) {
                String path = String.valueOf(program);
                compileProgram(path);
            }
        }
    }

    public static void compileProgram(String filepath) {
        System.out.printf("Compiling %s\n", filepath);
        compile(filepath);
    }

}

