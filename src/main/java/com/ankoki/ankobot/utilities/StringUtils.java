package com.ankoki.ankobot.utilities;

public class StringUtils {

    public static String arrayAsString(String[] args) {
        String argsAsString = "";
        int i = 1;
        for (String s : args) {
            if (i == args.length) {
                argsAsString += s;
            } else {
                argsAsString += s + " ";
            }
            i++;
        }
        return argsAsString;
    }

    public static String getCommandName(String args, String prefix) {
        String[] splitArgs = args.split(" ");
        return splitArgs[0].substring(prefix.length());
    }


    public static String withoutCommand(String args) {
        try {
            return args.substring((args.split(" ")[0].length() + 1));
        } catch (StringIndexOutOfBoundsException ex) {
            return args.substring((args.split(" ")[0].length()));
        }
    }
}