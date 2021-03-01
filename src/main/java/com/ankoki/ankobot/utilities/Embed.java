package com.ankoki.ankobot.utilities;

import com.ankoki.ankobot.gitignore.Secrets;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.entities.User;

import java.util.Arrays;

public class Embed {

    public static MessageEmbed simple(String message, User user) {
        return new MessageEmbed(null, null, message, null, null,
                Secrets.COLOUR.getRGB(), null, null, null,
                null, new Footer(user.getName() + " | " + Const.formattedNow(),
                null, null), null, null);
    }

    public static MessageEmbed noPermission(User user) {
        return new MessageEmbed(null, null,
                null, null, null, Secrets.COLOUR.getRGB(),
                null, null, null, null,
                new Footer(user.getName() + " | " + Const.formattedNow(),
                        null, null), null,
                Arrays.asList(new Field("No Permission!",
                        "Sorry " + user.getAsTag() + ", you do not " +
                                "have the correct perrmision to use this command!", false)));
    }
}