package com.ankoki.ankobot.commands;

import com.ankoki.ankobot.gitignore.Secrets;
import com.ankoki.ankobot.managers.GuildCommand;
import com.ankoki.ankobot.utilities.Const;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;

public class BotCommand implements GuildCommand {

    @Override
    public void onCommand(Guild guild, User user, TextChannel channel, String[] args, Message message) {
        channel.sendMessage(new MessageEmbed(null,
                "**Ankobot!**",
                null,
                null,
                null,
                Secrets.COLOUR.getRGB(),
                null,
                null,
                null,
                null,
                new MessageEmbed.Footer(user.getName() + " | " + Const.formattedNow() + " GMT", null, null),
                null,
                Collections.singletonList(new MessageEmbed.Field("The source code of Ankobot is public!",
                        "If you want to know how this bot works and see the code, you can visit " +
                                "https://www.github.com/Ankoki-Dev/Ankobot/ and browse the source code! " +
                                "This bot was coded in Java using the JDA API!", false)))).queue();
    }

    @Override
    public String[] getAliases() {
        return new String[]{"bot", "bots", "sc", "source"};
    }

    @Override
    public @Nullable Permission[] getPermissions() {
        return null;
    }
}
