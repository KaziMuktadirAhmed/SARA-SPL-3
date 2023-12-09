package app.SARA;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import antlr.java.JavaParser;

public class DataFlowGraphGenerator {

    private DataFlowGraphNode startNode;

    public DataFlowGraphNode generateDFGForFunction(JavaParser.MethodDeclarationContext methodSubtree) {
        startNode = null;
        traverseAndConstructDFG(methodSubtree, -1, -1); // Initial start and end line numbers
        return startNode;
    }

    private void traverseAndConstructDFG(ParseTree node, int startLine, int endLine) {
        String codeSnippet = node.getText();

        int nodeStartLine = -1;
        int nodeEndLine = -1;
        if (node instanceof TerminalNode) {
            TerminalNode terminalNode = (TerminalNode) node;
            nodeStartLine = terminalNode.getSymbol().getLine();
            nodeEndLine = terminalNode.getSymbol().getLine();
        } else if (node instanceof ParserRuleContext) {
            ParserRuleContext ctx = (ParserRuleContext) node;
            nodeStartLine = ctx.getStart().getLine();
            nodeEndLine = ctx.getStop().getLine();
        }

        DataFlowGraphNode dfgNode = new DataFlowGraphNode(nodeStartLine, nodeEndLine, codeSnippet);

        if (startNode == null) {
            startNode = dfgNode;
        } else {
            DataFlowGraphNode tempNode = startNode;
            while (tempNode.getNext() != null) {
                tempNode = tempNode.getNext();
            }
            tempNode.setNext(dfgNode);
            dfgNode.setParent(tempNode);
        }

        for (int i = 0; i < node.getChildCount(); ++i) {
            ParseTree child = node.getChild(i);
            if (child instanceof ParserRuleContext) {
                traverseAndConstructDFG(child, nodeStartLine, nodeEndLine);
            }
        }
    }

    // Placeholder class for DataFlowGraphNode
    static class DataFlowGraphNode {
        private int startLine;
        private int endLine;
        private String codeSnippet;
        private DataFlowGraphNode parent;
        private DataFlowGraphNode next;

        public DataFlowGraphNode(int startLine, int endLine, String codeSnippet) {
            this.startLine = startLine;
            this.endLine = endLine;
            this.codeSnippet = codeSnippet;
        }

        public void setParent(DataFlowGraphNode parent) {
            this.parent = parent;
        }

        public void setNext(DataFlowGraphNode next) {
            this.next = next;
        }

        public DataFlowGraphNode getNext() {
            return next;
        }
    }
}
