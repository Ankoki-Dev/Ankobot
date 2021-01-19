package com.ankoki.ankobot.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Const {

    private Const() throws IllegalAccessException {
        throw new IllegalAccessException("No instances of this class can be made!");
    }
    private static final DateFormat format = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

    public static String formattedNow() {
        return format.format(new Date()) + " GMT";
    }
}
