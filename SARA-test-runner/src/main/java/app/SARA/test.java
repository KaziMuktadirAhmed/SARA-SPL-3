package app.SARA;

import antlr.java.JavaLexer;
import antlr.java.JavaParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.FileWriter;
import java.io.IOException;

public class test {
    public void run()  {
        String javaCodeStr = "package app.SARA;\n" +
                "\n" +
                "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        int i = 0;" +
                "        System.out.println(\"Hello world!\");\n" +
                "        new test().run();\n" +
                "//        hi\n" +
                "    }\n" +
                "\n" +
                "    private static void hi() {\n" +
                "        System.out.println(\"hi\");\n" +
                "    }\n" +
                "\n" +
                "    private static void hi2() {\n" +
                "        System.out.println(\"hi2\");\n" +
                "    }\n" +
                "\n" +
                "    private static void hi3() {\n" +
                "        System.out.println(\"hi3\");\n" +
                "    }\n" +
                "}";

        JavaLexer lexer = new JavaLexer(CharStreams.fromString(javaCodeStr));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        ParserRuleContext tree = parser.compilationUnit();

        newTest(12, tree);
        newTest2(12, tree);

//        AST to json conversion
        JsonObject astJson = ASTToJSONConverter.parseTreeToJson(tree);

//        Json string builder
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(astJson);
        writeToFile(jsonString);

        System.out.println("finished");
    }

    private void newTest(int lineNumber, ParserRuleContext parseTree) {
// Extract function body for the specified line number
        ParserRuleContext methodContext = MethodFinder.findMethodForLine(lineNumber, parseTree);
        if (methodContext != null) {
            System.out.println("Found method for line " + lineNumber + ": " + methodContext.getText());
            System.out.println("Method starts at " + methodContext.getStart().getLine() + " ends at line " + methodContext.getStop().getLine());
        } else {
            System.out.println("No method found for line " + lineNumber);
        }
    }

    private void newTest2(int lineNumber, ParserRuleContext parseTree) {
        ParserRuleContext methodSubtree = MethodFinder.findMethodForLine(lineNumber, parseTree);
        if (methodSubtree != null) {
            DataFlowGraphGenerator dfgGenerator = new DataFlowGraphGenerator();

// Generate the Data Flow Graph for a specific method subtree
            DataFlowGraphGenerator.DataFlowGraphNode startNode = dfgGenerator.generateDFGForFunction((JavaParser.MethodDeclarationContext) methodSubtree);
            System.out.println("DFG done ???");
        } else {
            System.out.println("No method found for line " + lineNumber);
        }
    }

    private void writeToFile(String input) {
        System.out.println("writing");
        try (FileWriter fileWriter = new FileWriter("ast_output.json")) {
            fileWriter.write(input);
            System.out.println("AST JSON written to ast_output.json successfully.");
        } catch (IOException e) {
            System.err.println("Error writing JSON to file: " + e.getMessage());
        }
    }
}
