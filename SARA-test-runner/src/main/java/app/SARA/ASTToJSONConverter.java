package app.SARA;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.google.gson.*;

public class ASTToJSONConverter {
    public static JsonObject parseTreeToJson(ParseTree tree) {
        JsonObject json = new JsonObject();
        if (tree instanceof TerminalNode) {
            json.addProperty("type", "TerminalNode");
            json.addProperty("text", tree.getText());
        } else {
            json.addProperty("type", tree.getClass().getSimpleName());
            JsonArray children = new JsonArray();
            for (int i = 0; i < tree.getChildCount(); i++) {
                children.add(parseTreeToJson(tree.getChild(i)));
            }
            json.add("children", children);
        }
        return json;
    }

}
