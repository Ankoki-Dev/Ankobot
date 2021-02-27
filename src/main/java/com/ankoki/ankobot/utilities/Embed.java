package com.ankoki.ankobot.utilities;

import com.ankoki.ankobot.gitignore.Secrets;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.entities.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Embed {

    public static MessageEmbed simple(String message, User user) {
        Date date = new Date();
        return new MessageEmbed(null, null, message, null, null,
                Secrets.COLOUR.getRGB(), null, null, null,
                null, new Footer(user.getName() + " | " + Const.formattedNow(),
                null, null), null, null);
    }

    public static MessageEmbed noPermission(User user) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yy");
        Date date = new Date();
        return new MessageEmbed(null, null,
                null, null, null, Secrets.COLOUR.getRGB(),
                null, null, null, null,
                new Footer(user.getName() + " | " + dateFormat.format(date) + " GMT",
                        null, null), null,
                Arrays.asList(new Field("No Permission!",
                        "Sorry " + user.getAsTag() + ", you do not " +
                                "have the correct perrmision to use this command!", false)));
    }
}