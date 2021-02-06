package com.ankoki.ankobot.utilities;

import java.util.Arrays;

public class StringUtils {

    public static String arrayAsString(String[] args) {
        StringBuilder builder = new StringBuilder();
        Arrays.stream(args).forEach(arg -> builder.append(arg).append(" "));
        builder.setLength(builder.length() - 1);
        return builder.toString();
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