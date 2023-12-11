package app.SARA;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

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

    public List<CFGNode> generateCFG(ParseTree methodSubtree) {
        // Logic to convert AST subtree to CFG nodes
        // ...

        // Extract code snippet using DFS traversal
        String codeSnippet = buildCodeSnippet(methodSubtree);

        // Creating a CFG node with the extracted code snippet
        CFGNode cfgNode = new CFGNode(0, 0, codeSnippet);
        // Add cfgNode to the list of CFG nodes

        // Implement further logic to traverse and extract CFG nodes

        return List.of(cfgNode); // Return the list of CFG nodes
    }

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
