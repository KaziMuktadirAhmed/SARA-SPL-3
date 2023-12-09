package app.SARA.CFG;

import java.util.ArrayList;
import java.util.List;

class CFGNode {
    private String sourceCode;
    private int startLine;
    private int endLine;
    private List<CFGNode> nextNodes;

    public CFGNode(String sourceCode, int startLine, int endLine) {
        this.sourceCode = sourceCode;
        this.startLine = startLine;
        this.endLine = endLine;
        this.nextNodes = new ArrayList<>();
    }

    public void addNextNode(CFGNode node) {
        nextNodes.add(node);
    }

    // Getters for sourceCode, startLine, endLine, nextNodes
    public String getSourceCode() {
        return sourceCode;
    }

    public int getStartLine() {
        return startLine;
    }

    public int getEndLine() {
        return endLine;
    }

    public List<CFGNode> getNextNodes() {
        return nextNodes;
    }
}

