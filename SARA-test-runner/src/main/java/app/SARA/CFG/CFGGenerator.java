package app.SARA.CFG;

import app.SARA.MethodFinder;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.HashMap;
import java.util.Map;

public class CFGGenerator {
    private static Map<ParserRuleContext, CFGNode> visitedNodes = new HashMap<>();

    public static CFGNode generateCFGForLine(int lineNumber, ParserRuleContext parseTree) {
        visitedNodes.clear(); // Reset visited nodes

        // Find the method context covering the line number
        ParserRuleContext methodContext = MethodFinder.findMethodForLine(lineNumber, parseTree);

        if (methodContext != null) {
            return traverseForCFG(methodContext);
        }

        return null; // Method context not found for the line number
    }

    private static CFGNode traverseForCFG(ParserRuleContext context) {
        if (visitedNodes.containsKey(context)) {
            return visitedNodes.get(context); // Return if already visited
        }

        String sourceCode = ""; // Get source code representation of the context
        int startLine = context.getStart().getLine();
        int endLine = context.getStop().getLine();

        CFGNode currentNode = new CFGNode(sourceCode, startLine, endLine);
        visitedNodes.put(context, currentNode);

        for (int i = 0; i < context.getChildCount(); i++) {
            ParserRuleContext childContext = (ParserRuleContext) context.getChild(i);

            CFGNode childNode = traverseForCFG(childContext);
            if (childNode != null) {
                currentNode.addNextNode(childNode);
            }
        }

        return currentNode;
    }
}
