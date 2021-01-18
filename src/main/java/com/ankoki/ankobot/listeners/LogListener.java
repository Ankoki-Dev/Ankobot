package com.ankoki.ankobot.listeners;

import com.ankoki.ankobot.gitignore.Secrets;
import com.ankoki.ankobot.utilities.Const;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;

public class LogListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent e) {
        if (e.getAuthor().isBot()) return;
        e.getGuild().getTextChannelsByName("message-logs", true).get(0).sendMessage(new MessageEmbed(null,
                "**New Message**",
                null,
                null,
                null,
                Secrets.COLOUR.getRGB(),
                null,
                null,
                null,
                null,
                new MessageEmbed.Footer(e.getMember().getAsMention() + " | " + Const.formattedNow(), null, null),
                null,
                Collections.singletonList(new MessageEmbed.Field(e.getMember().getEffectiveName() + " said:",
                        e.getMessage().getContentRaw() + "\n**in the channel ** " + e.getChannel().getAsMention(),
                        false)))).queue();
    }
}
