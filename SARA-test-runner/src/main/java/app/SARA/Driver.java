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
import java.util.ArrayList;
import java.util.HashSet;

public class Driver {
    private int targetLineNumber;
    private String javaCodeStr;
    private int issueIndex = 0;

    public Driver(int lineNumber) {
        this.targetLineNumber = lineNumber;
    }

    public Driver(int lineNumber, String javaCodeStr, int issueIndex) {
        this.targetLineNumber = lineNumber;
        this.javaCodeStr = javaCodeStr;
        this.issueIndex = issueIndex;
    }

    public void run()  {
        String javaCodeStr = "package app.SARA;\n" +
                "\n" +
                "import antlr.java.JavaParser;\n" +
                "import org.antlr.v4.runtime.ParserRuleContext;\n" +
                "import org.antlr.v4.runtime.Token;\n" +
                "import org.antlr.v4.runtime.tree.ParseTree;\n" +
                "\n" +
                "class MethodFinder {\n" +
                "    public static ParserRuleContext findMethodForLine(int lineNumber, ParserRuleContext parseTree) {\n" +
                "        return traverseForLine(lineNumber, parseTree);\n" +
                "    }\n" +
                "\n" +
                "    private static ParserRuleContext traverseForLine(int lineNumber, ParserRuleContext context) {\n" +
                "        for (int i = 0; i < context.getChildCount(); i++) {\n" +
                "            ParseTree child = context.getChild(i);\n" +
                "\n" +
                "            if (child instanceof ParserRuleContext) {\n" +
                "                ParserRuleContext childContext = (ParserRuleContext) child;\n" +
                "\n" +
                "                if (childContext instanceof JavaParser.MethodDeclarationContext) {\n" +
                "                    Token startToken = childContext.getStart();\n" +
                "                    Token stopToken = childContext.getStop();\n" +
                "\n" +
                "                    if (startToken.getLine() <= lineNumber && stopToken.getLine() >= lineNumber) {\n" +
                "                        return childContext; // Found the MethodDeclarationContext covering the line\n" +
                "                    }\n" +
                "                }\n" +
                "\n" +
                "                // Recursively traverse into child contexts\n" +
                "                ParserRuleContext result = traverseForLine(lineNumber, childContext);\n" +
                "                if (result != null) {\n" +
                "                    return result; // Return if found within this branch\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "        return null; // Not found in this branch\n" +
                "    }\n" +
                "}\n";

        JavaLexer lexer = new JavaLexer(CharStreams.fromString(javaCodeStr));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        ParserRuleContext tree = parser.compilationUnit();

        newTest(24, tree);
        newTest2(24, tree);
        newTest3(24, tree);
        newTest4(31, tree);

        getTagsForMethodBody(10, tree);

        printCodeSnippet(tree);

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

    private void newTest3(int lineNumber, ParserRuleContext parseTree) {
        ParserRuleContext methodSubtree = MethodFinder.findMethodForLine(lineNumber, parseTree);
        new CFGGenerator().generateCFG(methodSubtree);
    }

    private void newTest4(int lineNumber, ParserRuleContext parseTree) {
        ParserRuleContext methodSubtree = MethodFinder.findMethodForLine(lineNumber, parseTree);
        ASTAnalyzer analyzer = new ASTAnalyzer(lineNumber, methodSubtree);
        ParseTree methodBody = analyzer.getMethodBody();
        ParseTree statement = analyzer.findStatementAtLine(lineNumber, methodBody);
        System.out.println("statement : " + analyzer.buildCodeSnippet(statement));
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

    // Code snippet builder test
    public static void printCodeSnippet(ParseTree methodSubtree) {
        ParseTree blockStatement = findBlockStatement(methodSubtree);

        if (blockStatement != null) {
            CFGGenerator generator = new CFGGenerator();
            String codeSnippet = generator.buildCodeSnippet(blockStatement);
            System.out.println("Code Snippet:");
            System.out.println(codeSnippet);
        } else {
            System.out.println("No BlockStatementContext found in the method subtree.");
        }
    }
    private static ParseTree findBlockStatement(ParseTree node) {
        if (node.getClass().getSimpleName().equals("BlockStatementContext")) {
            return node;
        }

        for (int i = 0; i < node.getChildCount(); i++) {
            ParseTree child = node.getChild(i);
            ParseTree blockStatement = findBlockStatement(child);
            if (blockStatement != null) {
                return blockStatement;
            }
        }
        return null;
    }

    private void getTagsForMethodBody(int lineNumber, ParserRuleContext parseTree) {
        ParserRuleContext methodSubtree = MethodFinder.findMethodForLine(lineNumber, parseTree);
        ASTAnalyzer analyzer = new ASTAnalyzer(lineNumber, methodSubtree);
        ParseTree methodBody = analyzer.getMethodBody();
        ArrayList<String> tokens = analyzer.buildTokenCollection(methodBody);
        HashSet<String> tags = new TagGenerator().getTags(tokens);
        JsonBuilder.writeHashSetToJsonFile(tags, "./saraTags/" + this.issueIndex + ".json");
    }
}
