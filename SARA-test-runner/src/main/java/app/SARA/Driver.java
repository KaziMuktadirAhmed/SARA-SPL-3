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
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;

public class Driver {
    private int targetLineNumber = 15;
    private String srcFilePath;
    private int issueIndex = 0;

    public Driver(int lineNumber) {
        this.targetLineNumber = lineNumber;
    }

    public Driver(int lineNumber, String srcFilePath, int issueIndex) {
        this.targetLineNumber = lineNumber;
        this.srcFilePath = srcFilePath;
        this.issueIndex = issueIndex;
    }

    public void run() throws IOException {
        JavaLexer lexer = new JavaLexer(CharStreams.fromPath(Path.of(this.srcFilePath)));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        ParserRuleContext tree = parser.compilationUnit();

//        newTest(24, tree);
//        newTest2(24, tree);
//        newTest3(24, tree);
//        newTest4(31, tree);

        getTagsForMethodBody(this.targetLineNumber, tree);

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
