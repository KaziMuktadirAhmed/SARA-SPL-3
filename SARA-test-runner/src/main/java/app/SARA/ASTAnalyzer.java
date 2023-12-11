package app.SARA;

import antlr.java.JavaParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class ASTAnalyzer {
    ParseTree targetStatement;
    ParseTree methodBody;
    ArrayList<ParseTree> statements;

    public ASTAnalyzer(int targetLineNo, ParserRuleContext methodSubtree) {
        this.methodBody = getMethodBody(methodSubtree);
        this.targetStatement = findStatementAtLine(targetLineNo, methodSubtree);
    }

    private String getMethodIdentifier(ParseTree methodSubtree) {
        String methodIdentifier = "";
        for(int i=0; i< methodSubtree.getChildCount(); i++) {
            ParseTree child = methodSubtree.getChild(i);
            if(child instanceof JavaParser.IdentifierContext) {
                methodIdentifier = child.getText();
                break;
            }
        }
        return methodIdentifier;
    }

    private ParseTree getMethodBody(ParseTree methodSubtree) {
        ParseTree context = null;
        for(int i=0; i< methodSubtree.getChildCount(); i++) {
            ParseTree child = methodSubtree.getChild(i);
            if(child instanceof JavaParser.MethodBodyContext) {
                context = child;
                break;
            }
        }
        return context;
    }

    private ArrayList<ParseTree> getMethodBodyBlockStatements(ParseTree methodBodyContext) {
        ArrayList<ParseTree> blocks = new ArrayList<>();
        for(int i=0; i<methodBodyContext.getChildCount(); i++) {
            ParseTree child = methodBodyContext.getChild(i);
            if(child instanceof JavaParser.BlockStatementContext)
                blocks.add(child);
        }
        return blocks;
    }

    public static ParseTree findStatementAtLine(int lineNumber, ParserRuleContext methodBodyContext) {
        return traverseForLine(lineNumber, methodBodyContext);
    }
    private static ParseTree traverseForLine(int lineNumber, ParserRuleContext context) {
        for (int i = 0; i < context.getChildCount(); i++) {
            ParseTree child = context.getChild(i);
            if (isLineInRange(child, lineNumber)) {
                if (child instanceof ParserRuleContext) {
                    ParseTree node = traverseForLine(lineNumber, (ParserRuleContext) child);
                    if (node != null) {
                        return node;
                    }
                } else if (child instanceof JavaParser.StatementContext) {
                    if (!hasChildStatements(child)) {
                        return child;
                    }
                }
            }
        }
        return null;
    }
    private static boolean isLineInRange(ParseTree node, int lineNumber) {
        if (node instanceof ParserRuleContext) {
            ParserRuleContext ctx = (ParserRuleContext) node;
            int startLine = ctx.start.getLine();
            int stopLine = ctx.stop.getLine();
            return lineNumber >= startLine && lineNumber <= stopLine;
        }
        return false;
    }
    private static boolean hasChildStatements(ParseTree node) {
        for (int i = 0; i < node.getChildCount(); i++) {
            ParseTree child = node.getChild(i);
            if (child instanceof JavaParser.BlockContext || child instanceof JavaParser.BlockStatementContext || child instanceof JavaParser.StatementContext) {
                return true;
            }
        }
        return false;
    }
}
