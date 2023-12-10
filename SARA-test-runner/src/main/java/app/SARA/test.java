package app.SARA;

import antlr.java.JavaLexer;
import antlr.java.JavaParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.FileWriter;
import java.io.IOException;

public class test {
    public void run()  {
        String javaCodeStr = "package var.var.sealed;\n" +
                "\n" +
                "import java.lang.annotation.ElementType;\n" +
                "import java.lang.annotation.Target;\n" +
                "import java.util.function.BiFunction;\n" +
                "import java.util.function.Consumer;\n" +
                "import java.util.function.Function;\n" +
                "\n" +
                "@interface Dummy {\n" +
                "}\n" +
                "\n" +
                "@interface Dummy2 {\n" +
                "}\n" +
                "\n" +
                "@Target({ElementType.TYPE, ElementType.TYPE_USE})\n" +
                "@interface Dummy3 {\n" +
                "}\n" +
                "\n" +
                "/**\n" +
                " * https://openjdk.java.net/jeps/361\n" +
                " */\n" +
                "class SwitchExpressions {\n" +
                "\n" +
                "    final static private int C = 10;\n" +
                "\n" +
                "    static class SC1 {\n" +
                "        final static int C = 100;\n" +
                "    }\n" +
                "\n" +
                "    enum E1 {\n" +
                "        ONE;\n" +
                "    }\n" +
                "\n" +
                "    int fn1(int n) {\n" +
                "        final int k = 4;\n" +
                "        var r = switch (n) {\n" +
                "            case 1, 2, 3 + 3, k, C, SC1.C -> 3 + SC1.C;\n" +
                "            case 20 -> 3 + 4 + C - k;\n" +
                "            case 21 -> {\n" +
                "                int ff = 222;\n" +
                "                yield ff;\n" +
                "            }\n" +
                "            case 22 -> {\n" +
                "                yield 33 + 3;\n" +
                "            }\n" +
                "            case 99 -> {\n" +
                "                throw new RuntimeException(\"\");\n" +
                "            }\n" +
                "            default -> 0;\n" +
                "        };\n" +
                "        return r;\n" +
                "    }\n" +
                "\n" +
                "    String fn2(String s) {\n" +
                "        return switch (s) {\n" +
                "            //case null -> \"n\";\n" +
                "            case \"a\" -> \"\";\n" +
                "            case \"b\", \"c\" -> \"a\";\n" +
                "            default -> \"o\";\n" +
                "        };\n" +
                "    }\n" +
                "\n" +
                "    int fn3(final int var) {\n" +
                "        return switch (var) {\n" +
                "            case 1 -> 2;\n" +
                "            default -> var;\n" +
                "        };\n" +
                "    }\n" +
                "\n" +
                "    void fn4() {\n" +
                "\n" +
                "        fn1(switch (1) {\n" +
                "            case 1 -> 0;\n" +
                "            case 2 -> 2;\n" +
                "            default -> 1;\n" +
                "        });\n" +
                "    }\n" +
                "\n" +
                "    int fn5() {\n" +
                "        E1 e = E1.ONE;\n" +
                "        return switch (e) {\n" +
                "            case ONE -> 0;\n" +
                "            //default -> 1;\n" +
                "        };\n" +
                "    }\n" +
                "\n" +
                "    void fn6() {\n" +
                "        switch (1) {\n" +
                "            case 1 -> {\n" +
                "\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    void fn7() {\n" +
                "        switch (1) {\n" +
                "            case 1 -> {\n" +
                "            }\n" +
                "            case 2 -> {\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    void fn8() {\n" +
                "        var i = 1;\n" +
                "        switch (1) {\n" +
                "\n" +
                "        }\n" +
                "        var f = 2;\n" +
                "        switch (2) {\n" +
                "            case 2 -> {\n" +
                "                f = 3;\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    void fn9(String s) {\n" +
                "        switch (s) {\n" +
                "            case \"\" -> {\n" +
                "            }\n" +
                "            default -> {\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    void fn10() {\n" +
                "        var i = switch (1) {\n" +
                "            case 1 -> switch (2) {\n" +
                "                case 2 -> 0;\n" +
                "                default -> 2;\n" +
                "            };\n" +
                "            default -> 2;\n" +
                "        };\n" +
                "    }\n" +
                "\n" +
                "    void fn11() {\n" +
                "        switch (1) {\n" +
                "            case 1 -> throw new RuntimeException(\"\");\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    int fn12() {\n" +
                "        var v = 1;\n" +
                "        int n = switch (1) {\n" +
                "            case 1:\n" +
                "                var g = 1;\n" +
                "                System.out.println();\n" +
                "                yield v;\n" +
                "            default:\n" +
                "                yield 3;\n" +
                "        };\n" +
                "        return n;\n" +
                "    }\n" +
                "\n" +
                "    void fn13() {\n" +
                "        int n;\n" +
                "        switch (1) {\n" +
                "            case 1 -> n = 1;\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    void fn14() {\n" +
                "        switch (1) {\n" +
                "            default -> {\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        var n = 1;\n" +
                "        var m = switch (n) {\n" +
                "            case 1 -> 2;\n" +
                "            case 2 -> 2;\n" +
                "            default -> 1;\n" +
                "        };\n" +
                "\n" +
                "        m = switch (n) {\n" +
                "            case 2:\n" +
                "                yield 2;\n" +
                "            default:\n" +
                "                yield 3;\n" +
                "        };\n" +
                "\n" +
                "\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "/**\n" +
                " * https://openjdk.java.net/jeps/394\n" +
                " */\n" +
                "class PatternMatching4instanceof {\n" +
                "\n" +
                "    void fn1(Number n) {\n" +
                "        if (n instanceof Long var) {\n" +
                "            var v = var;\n" +
                "        } else if (n instanceof Integer open) {\n" +
                "            var v = open;\n" +
                "        } else if (n instanceof Byte) {\n" +
                "            //\n" +
                "        } else {\n" +
                "            throw new RuntimeException(\"\");\n" +
                "        }\n" +
                "\n" +
                "        if (!(n instanceof Long l)) ;\n" +
                "\n" +
                "        if (n instanceof final @Dummy @Dummy2 Long l && l.byteValue() == 1\n" +
                "                || n instanceof @Dummy @Dummy2 final Byte b && b.intValue() == 1) ;\n" +
                "\n" +
                "        if (n instanceof Long) ;\n" +
                "        if (n instanceof Long var) ;\n" +
                "        if (n instanceof Long l) ;\n" +
                "        if (n instanceof final Long l) ;\n" +
                "        if (n instanceof @Dummy Long l) ;\n" +
                "        if (n instanceof @Dummy @Dummy2 Long l) ;\n" +
                "        if (n instanceof final @Dummy Long l) ;\n" +
                "        if (n instanceof final @Dummy @Dummy2 Long l) ;\n" +
                "        if (n instanceof @Dummy final Long l) ;\n" +
                "        if (n instanceof @Dummy @Dummy2 final Long l) ;\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "/**\n" +
                " * https://openjdk.java.net/jeps/406\n" +
                " */\n" +
                "class PatternMatching4switchExp {\n" +
                "\n" +
                "    void f(int i) {\n" +
                "    }\n" +
                "\n" +
                "    void f1(Object obj) {\n" +
                "        switch (obj) {\n" +
                "            case null -> f(0);\n" +
                "            case String s -> f(1);\n" +
                "            case int[] a -> f(2);\n" +
                "            default -> f(-1);\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    void f2(Object obj) {\n" +
                "        switch (obj) {\n" +
                "            case null -> f(0);\n" +
                "            case Long l -> f(1);\n" +
                "            case Integer i -> f(1);\n" +
                "            case int[] a -> f(2);\n" +
                "            default -> f(-1);\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    void f3(Object o) {\n" +
                "        switch (o) {\n" +
                "            case null:\n" +
                "            case Long l:\n" +
                "                f(0);\n" +
                "                break;\n" +
                "            default:\n" +
                "                break;\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    enum E1 {\n" +
                "        var;\n" +
                "    }\n" +
                "\n" +
                "    void f4() {\n" +
                "        var var = E1.var;\n" +
                "        switch (var) {\n" +
                "            case var:\n" +
                "                return;\n" +
                "            default:\n" +
                "                break;\n" +
                "        }\n" +
                "\n" +
                "        switch (var) {\n" +
                "            case var -> {\n" +
                "            }\n" +
                "            default -> {\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    int f5(Number n) {\n" +
                "        return switch (n) {\n" +
                "            case Long l && l.intValue() == 1 && l.byteValue() == 1 -> l.byteValue();\n" +
                "            case Long var -> var.byteValue();\n" +
                "            case Integer i -> i.byteValue();\n" +
                "            default -> throw new RuntimeException(\"\");\n" +
                "        };\n" +
                "    }\n" +
                "\n" +
                "    Function<Integer, String> f6(Object obj) {\n" +
                "        boolean b = true;\n" +
                "        return switch (obj) {\n" +
                "            case String var && b -> t -> var;\n" +
                "            default -> t -> \"Default string\";\n" +
                "        };\n" +
                "    }\n" +
                "\n" +
                "    int dummy() {\n" +
                "        return 0;\n" +
                "    }\n" +
                "\n" +
                "    Function<Integer, String> f7(Object obj) {\n" +
                "        boolean b = true;\n" +
                "        boolean b2 = true;\n" +
                "        boolean b3 = true;\n" +
                "        return switch (obj) {\n" +
                "            case (((String s) && (b && b2)) && s.length() > 0 && dummy() == 1) -> t -> s;\n" +
                "            case (((Integer i && b && b2) && (b && b2)) && b3 && (b && b2)) -> t -> \"\";\n" +
                "            case (((Integer i && b && b2) && (b && b2)) && b3 && (b && b2 && !b3)) -> {\n" +
                "                yield t -> \"\";\n" +
                "            }\n" +
                "            case final Long l && (b ? b2 : b3) -> {\n" +
                "                yield t -> \"\";\n" +
                "            }\n" +
                "            default -> t -> \"Default string\";\n" +
                "        };\n" +
                "    }\n" +
                "\n" +
                "    void f8(Object o, int i) {\n" +
                "        switch (i) {\n" +
                "            case 1, 2:\n" +
                "            case 3, 4: {\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        switch (o) {\n" +
                "            case Number b: {\n" +
                "            }\n" +
                "            default: {\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        var f = switch (o) {\n" +
                "            case final I2 l: {\n" +
                "                yield switch (o) {\n" +
                "                    case Byte b -> 1;\n" +
                "                    default -> 0;\n" +
                "                };\n" +
                "            }\n" +
                "            default: {\n" +
                "                yield 1;\n" +
                "            }\n" +
                "        };\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "/**\n" +
                " * https://openjdk.java.net/jeps/395\n" +
                " */\n" +
                "class Records {\n" +
                "\n" +
                "    interface I1 {\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    static record R0(int x) {\n" +
                "        R0 {\n" +
                "            if (x > 3) throw Exception(\"new\", null);\n" +
                "            x *= 3;\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    final record R1(@Dummy2 @Dummy int x) {\n" +
                "\n" +
                "        R1(int x) {\n" +
                "            this.x = x;\n" +
                "        }\n" +
                "\n" +
                "        enum E {\n" +
                "            ONE;\n" +
                "\n" +
                "            record ER() {\n" +
                "\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        class C {\n" +
                "            record CR() {\n" +
                "\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        interface I {\n" +
                "            record IR() {\n" +
                "\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        final static private record R() implements I1 {\n" +
                "        }\n" +
                "\n" +
                "        final static protected record R2() implements I1 {\n" +
                "        }\n" +
                "\n" +
                "        final static public record R3() implements I1 {\n" +
                "        }\n" +
                "\n" +
                "        final static record R4() implements I1 {\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    record R2() {\n" +
                "        public @interface TM1 {\n" +
                "            record AR() {\n" +
                "\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    record R3<T>(int x, T y) {\n" +
                "    }\n" +
                "\n" +
                "    record R4<T>(int x, T y) implements I1 {\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    void fn1() {\n" +
                "        final record Pt<T, G extends Number>(int x, int y) implements I1, R1.I {\n" +
                "            void fn(T t) {\n" +
                "            }\n" +
                "\n" +
                "            <TT> void f() {\n" +
                "            }\n" +
                "\n" +
                "            //final int x; implicitly defined\n" +
                "\n" +
                "            Pt(int x, int y) {\n" +
                "                this.x = x;\n" +
                "                this.y = y;\n" +
                "            }\n" +
                "\n" +
                "            //private int c = 1; not allowed\n" +
                "            private final static int C = 1; //allowed\n" +
                "\n" +
                "            static class C {\n" +
                "\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        Pt<Long, Long> p = new Pt<>(1, 2);\n" +
                "        p.fn(1L);\n" +
                "\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "}\n" +
                "\n" +
                "/**\n" +
                " * https://openjdk.java.net/jeps/378\n" +
                " */\n" +
                "class TextBlocks {\n" +
                "\n" +
                "    void f(String s) {\n" +
                "    }\n" +
                "\n" +
                "    void fn() {\n" +
                "        var s = \"\"\"\n" +
                "                a \\t\n" +
                "                \\r\"\"\";\n" +
                "\n" +
                "        var s2 = \"\"\"\n" +
                "                a\"\"\" + \"\"\"\n" +
                "                b\"\"\";\n" +
                "\n" +
                "        var s3 = \"\"\"\n" +
                "                \"\"\";\n" +
                "\n" +
                "        f(\"\"\"\n" +
                "                a\"\"\");\n" +
                "\n" +
                "        f(\"\"\"\n" +
                "                \"\"\");\n" +
                "    }\n" +
                "\n" +
                "}\n" +
                "\n" +
                "/**\n" +
                " * https://openjdk.java.net/jeps/409\n" +
                " */\n" +
                "class SealedClasses {\n" +
                "\n" +
                "    interface I1 {\n" +
                "    }\n" +
                "\n" +
                "    class C0 {\n" +
                "    }\n" +
                "\n" +
                "    sealed class SC1 extends C0 implements I1 permits FC1, FC2 {\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    sealed class SC2 {\n" +
                "        void f() {\n" +
                "            var non = 1;\n" +
                "            var sealed = 2;\n" +
                "            var ns = non - sealed;\n" +
                "            var permits = 1;\n" +
                "            var record = 1;\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    final class FC1 extends SC1 {\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    final class FC2 extends SC1 {\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    non-sealed class NSC1 extends SC2 {\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    class C1 extends NSC1 {\n" +
                "\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "class Ids {\n" +
                "    class oo {\n" +
                "\n" +
                "        class opens<T> {\n" +
                "\n" +
                "            enum E {\n" +
                "                provides;\n" +
                "            }\n" +
                "\n" +
                "            class provides<S> {\n" +
                "\n" +
                "                void f() {\n" +
                "\n" +
                "                    opens<Byte>.provides<Long> b1 = new opens<>().new provides<>() {\n" +
                "                    };\n" +
                "                    opens<Byte>.provides<Long> b2 = new opens().new provides() {\n" +
                "                    };\n" +
                "                }\n" +
                "\n" +
                "                void g() {\n" +
                "                    E e = E.provides;\n" +
                "                    switch (e) {\n" +
                "                        case provides:\n" +
                "                            break;\n" +
                "                    }\n" +
                "                }\n" +
                "\n" +
                "                <T> Object var() {\n" +
                "                    return null;\n" +
                "                }\n" +
                "\n" +
                "                provides<Long> get() {\n" +
                "                    return null;\n" +
                "                }\n" +
                "\n" +
                "                class with<S> {\n" +
                "\n" +
                "                }\n" +
                "\n" +
                "                static class SS<R> {\n" +
                "                    interface Sup<T> {\n" +
                "                        T get();\n" +
                "                    }\n" +
                "                }\n" +
                "\n" +
                "                void h() {\n" +
                "                    var o = get().<Long>var();\n" +
                "\n" +
                "                    SS.Sup<provides<Long>.with<Long>> s = @Issue1897.Dum1 provides<Long>.with<Long>::new;\n" +
                "                }\n" +
                "\n" +
                "                class R {\n" +
                "\n" +
                "                    <to> void f() {\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "\n" +
                "\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    static class opens {\n" +
                "        enum requires {\n" +
                "            opens;\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        public static <T> void with(String s) {\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        interface with {\n" +
                "            default void f() {\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        class exports implements with {\n" +
                "            void g() {\n" +
                "                with.super.f();\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        @interface to {\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        class module {\n" +
                "            public static <T> void with(String s) {\n" +
                "                try {\n" +
                "\n" +
                "                } catch (Exception var) {\n" +
                "\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        record provides(int to) {\n" +
                "\n" +
                "            void f() {\n" +
                "\n" +
                "                opens o = new opens();\n" +
                "                BiFunction<Long, Long, Long> b = (opens, with) -> 1L;\n" +
                "                Consumer<String> c = opens.module::<Byte>with;\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "}\n" +
                "\n" +
                "class Yield {\n" +
                "\n" +
                "    int f(Object o) {\n" +
                "\n" +
                "        final var yield = 1;\n" +
                "        return switch (o) {\n" +
                "            case Long l -> {\n" +
                "                //var yield = 1;\n" +
                "                yield yield;\n" +
                "            }\n" +
                "            default -> {\n" +
                "                yield yield;\n" +
                "            }\n" +
                "        };\n" +
                "    }\n" +
                "\n" +
                "    int yield(int yield){\n" +
                "        return yield;\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "\n" +
                "class IF_PERMITS {\n" +
                "    final class T1 implements I1 {\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    final class T2 implements I1 {\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    interface I2 {\n" +
                "    }\n" +
                "\n" +
                "    sealed interface I1 extends I2 permits T1, T2 {\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "}";

        JavaLexer lexer = new JavaLexer(CharStreams.fromString(javaCodeStr));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        ParserRuleContext tree = parser.compilationUnit();

        newTest(37, tree);
        newTest2(37, tree);

//        AST to json conversion
        JsonObject astJson = ASTToJSONConverter.parseTreeToJson(tree);

//        Json string builder
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(astJson);
        writeToFile(jsonString);

        System.out.println("finished");
    }

    private void newTest(int lineNumber, ParserRuleContext parseTree) {
// Extract function body for the specified line number
        ParserRuleContext methodContext = MethodFinder.findMethodForLine(lineNumber, parseTree);
        if (methodContext != null) {
            System.out.println("Found method for line " + lineNumber + ": " + methodContext.getText());
            System.out.println("Method starts at " + methodContext.getStart().getLine() + " ends at line " + methodContext.getStop().getLine());
        } else {
            System.out.println("No method found for line " + lineNumber);
        }
    }

    private void newTest2(int lineNumber, ParserRuleContext parseTree) {
        ParserRuleContext methodSubtree = MethodFinder.findMethodForLine(lineNumber, parseTree);
        if (methodSubtree != null) {
            DataFlowGraphGenerator dfgGenerator = new DataFlowGraphGenerator();

// Generate the Data Flow Graph for a specific method subtree
            DataFlowGraphGenerator.DataFlowGraphNode startNode = dfgGenerator.generateDFGForFunction((JavaParser.MethodDeclarationContext) methodSubtree);
            System.out.println("DFG done ???");
        } else {
            System.out.println("No method found for line " + lineNumber);
        }
    }

    private void writeToFile(String input) {
        System.out.println("writing");
        try (FileWriter fileWriter = new FileWriter("ast_output.json")) {
            fileWriter.write(input);
            System.out.println("AST JSON written to ast_output.json successfully.");
        } catch (IOException e) {
            System.err.println("Error writing JSON to file: " + e.getMessage());
        }
    }
}
