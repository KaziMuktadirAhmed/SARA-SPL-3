package app.SARA;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.misc.Interval;
import antlr.java.JavaParser;

public class FunctionBodyExtractor {
    public static String getFunctionBodyAsString(int lineNumber, ParserRuleContext parseTree) {
        Token startToken = getStartTokenAtLine(lineNumber, parseTree);
        if (startToken != null) {
            ParserRuleContext functionNode = findParentFunction(parseTree, startToken.getTokenIndex());
            if (functionNode != null) {
                int startIndex = startToken.getStartIndex();
                int stopIndex = functionNode.getStop().getStopIndex();

                if (startIndex != -1 && stopIndex != -1) {
                    String functionText = startToken.getInputStream().getText(new Interval(startIndex, stopIndex));
                    int openBracketIndex = functionText.indexOf('{');
                    int closeBracketIndex = functionText.lastIndexOf('}');

                    if (openBracketIndex != -1 && closeBracketIndex != -1) {
                        return functionText.substring(openBracketIndex + 1, closeBracketIndex);
                    }
                }
            }
        }
        return null; // Indicates that the function body couldn't be extracted
    }

    private static Token getStartTokenAtLine(int lineNumber, ParseTree context) {
        if (context instanceof TerminalNode) {
            Token token = ((TerminalNode) context).getSymbol();
            if (token != null && token.getLine() == lineNumber) {
                return token;
            }
        }

        int numChildren = context.getChildCount();
        for (int i = 0; i < numChildren; i++) {
            Token token = getStartTokenAtLine(lineNumber, context.getChild(i));
            if (token != null) {
                return token;
            }
        }
        return null;
    }

    private static ParserRuleContext findParentFunction(ParseTree tree, int tokenIndex) {
        while (tree != null) {
            if (tree instanceof JavaParser.MethodDeclarationContext || tree instanceof JavaParser.ConstructorDeclarationContext) {
                Token startToken = ((ParserRuleContext) tree).getStart();
                Token stopToken = ((ParserRuleContext) tree).getStop();
                if (startToken != null && stopToken != null &&
                        tokenIndex >= startToken.getTokenIndex() && tokenIndex <= stopToken.getTokenIndex()) {
                    return (ParserRuleContext) tree;
                }
            }
            tree = tree.getParent();
        }
        return null;
    }
}
