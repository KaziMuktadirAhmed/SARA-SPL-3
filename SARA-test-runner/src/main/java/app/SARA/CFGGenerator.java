package app.SARA;

import antlr.java.JavaParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class CFGGenerator {

    public static class CFGNode {
        private final int startLine;
        private final int endLine;
        private final String codeSnippet;

        public CFGNode(int startLine, int endLine, String codeSnippet) {
            this.startLine = startLine;
            this.endLine = endLine;
            this.codeSnippet = codeSnippet;
        }

        // Getters for startLine, endLine, and codeSnippet
    }

    public List<String> generateCFG(ParseTree methodSubtree) {
        List<String> cfg = new ArrayList<>();
        List<ParseTree> statements = new ArrayList<>();

        // Assume the methodSubtree contains MethodDeclarationContext with children
        ParseTree methodBody = getMethodBody(methodSubtree);
        collectStatements(methodBody, statements);

        for (ParseTree statement : statements) {
            String codeSnippet = buildCodeSnippet(statement);
            cfg.add(codeSnippet);
        }
        return cfg;
    }

    private ParseTree getMethodBody(ParseTree node) {
        for (int i = 0; i < node.getChildCount(); i++) {
            ParseTree child = node.getChild(i);
            if (child instanceof JavaParser.MethodBodyContext) {
                return child;
            }
        }
        return null; // MethodBodyContext not found
    }

    private void collectStatements(ParseTree node, List<ParseTree> statements) {
        for (int i = 0; i < node.getChildCount(); i++) {
            ParseTree child = node.getChild(i);
            if (child.getClass().getSimpleName().equals("BlockStatementContext")) {
                statements.add(child);
            }
            collectStatements(child, statements);
        }
    }

//    Build out code snippet from BlockStatementContext
    public String buildCodeSnippet(ParseTree node) {
        StringBuilder snippet = new StringBuilder();
        buildCodeSnippetDFS(node, snippet);
        return snippet.toString();
    }
    private void buildCodeSnippetDFS(ParseTree node, StringBuilder snippet) {
        if (node instanceof TerminalNode) {
            snippet.append(node.getText()).append(" ");
        } else {
            for (int i = 0; i < node.getChildCount(); i++) {
                ParseTree child = node.getChild(i);
                buildCodeSnippetDFS(child, snippet);
            }
        }
    }

    // Other methods for CFG traversal, path extraction, etc.
}
