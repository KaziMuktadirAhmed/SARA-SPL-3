package app.SARA;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        if(args.length > 0) {
            String filePath = args[0];
            String targetLineNo = args[1];
            String issueIndex = args[2];
            new Driver(Integer.parseInt(targetLineNo), filePath, Integer.parseInt(issueIndex)).run();
        }
//        new Driver(10).run();
//        hi
    }

    private static void hi() {
        System.out.println("hi");
    }

    private static void hi2() {
        System.out.println("hi2");
    }

    private static void hi3() {
        System.out.println("hi3");
    }
}