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
                "        System.out.println(\"Hello world!\");\n" +
                "    }\n" +
                "}";

        JavaLexer lexer = new JavaLexer(CharStreams.fromString(javaCodeStr));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        ParseTree tree = parser.compilationUnit();

//        AST to json conversion
        JsonObject astJson = ASTToJSONConverter.parseTreeToJson(tree);

//        Json string builder
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(astJson);
        writeToFile(jsonString);

        System.out.println(jsonString);

        System.out.println("finished");
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
