package com.epam.Philippov;

public class FirstTask {
    public static void main(String[] args) {
        Integer argsCount = args.length;
        Double totalArgsLength = 0.0;

        for (String arg : args) {
            totalArgsLength += arg.length();
        }

        System.out.println("Args count: " + argsCount);
        System.out.println("Avg args length: " + totalArgsLength/argsCount);
        String s = System.lineSeparator();
        System.out.println(s);
    }
}
