package app.SARA;

import antlr.java.JavaParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

public class MethodFinder {
    public static ParserRuleContext findMethodForLine(int lineNumber, ParserRuleContext parseTree) {
        return traverseForLine(lineNumber, parseTree);
    }
    private static ParserRuleContext traverseForLine(int lineNumber, ParserRuleContext context) {
        for (int i = 0; i < context.getChildCount(); i++) {
            ParseTree child = context.getChild(i);

            if (child instanceof ParserRuleContext) {
                ParserRuleContext childContext = (ParserRuleContext) child;

                if (childContext instanceof JavaParser.MethodDeclarationContext) {
                    Token startToken = childContext.getStart();
                    Token stopToken = childContext.getStop();

                    if (startToken.getLine() <= lineNumber && stopToken.getLine() >= lineNumber) {
                        return childContext; // Found the MethodDeclarationContext covering the line
                    }
                }

                // Recursively traverse into child contexts
                ParserRuleContext result = traverseForLine(lineNumber, childContext);
                if (result != null) {
                    return result; // Return if found within this branch
                }
            }
        }
        return null; // Not found in this branch
    }
}
