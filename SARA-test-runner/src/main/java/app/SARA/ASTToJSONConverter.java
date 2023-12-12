package app.SARA;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.ParserRuleContext;

import com.google.gson.*;

public class ASTToJSONConverter {
    public static JsonObject parseTreeToJson(ParseTree tree) {
        System.out.println("jus for demo");
        JsonObject json = new JsonObject();

        if (tree instanceof ParserRuleContext) {
            json.addProperty("type", tree.getClass().getSimpleName());
            json.addProperty("text", ((ParserRuleContext) tree).getText());

            Token startSymbol = ((ParserRuleContext) tree).getStart();
            Token stopSymbol = ((ParserRuleContext) tree).getStop();
            if (startSymbol != null && stopSymbol != null) {
                json.addProperty("startLine", startSymbol.getLine());
                json.addProperty("startColumn", startSymbol.getCharPositionInLine());
                json.addProperty("endLine", stopSymbol.getLine());
                json.addProperty("endColumn", stopSymbol.getCharPositionInLine() + stopSymbol.getText().length());
            }

            JsonArray children = new JsonArray();
            for (int i = 0; i < tree.getChildCount(); i++) {
                children.add(parseTreeToJson(tree.getChild(i)));
            }
            json.add("children", children);
        } else {
            json.addProperty("type", "TerminalNode");
            json.addProperty("text", tree.getText());

            Token symbol = (Token) tree.getPayload();
            json.addProperty("startLine", symbol.getLine());
            json.addProperty("startColumn", symbol.getCharPositionInLine());
            json.addProperty("endLine", symbol.getLine());
            json.addProperty("endColumn", symbol.getCharPositionInLine() + tree.getText().length());
        }

        return json;
    }

}
