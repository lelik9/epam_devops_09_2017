package com.epam.Philippov;

import com.epam.Philippov.selfCollection.CharArray;
import org.jetbrains.annotations.Contract;

import java.util.Arrays;

public class FourthTask {
    public static void main(String[] args) {
        int[] charCounts = new int[args.length];

//        for (int i = 0; i < args.length; i++) {
//            charCounts[i] = getCharCount(args[i]);
//        }
        System.out.println(args.length);
        Character[] chars = new Character[args[0].toCharArray().length];
        for (int i = 0; i < args[0].toCharArray().length; i++) {
            chars[i] = args[0].toCharArray()[i];
        }
        CharArray inputString = new CharArray(chars);
        System.out.println(Arrays.toString(inputString.getSet()));
    }

    @Contract(pure = true)
    private static int getCharCount(String str) {
        char[] inputString = str.toCharArray();

        return 0;
    }
}
