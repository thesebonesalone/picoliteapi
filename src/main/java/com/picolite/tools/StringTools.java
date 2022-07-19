package com.picolite.tools;

public class StringTools {

    public static String getRandom(String[] stringArr)
    {
        int index = (int)Math.floor(Math.random() * stringArr.length);
        return stringArr[index];
    }
}
