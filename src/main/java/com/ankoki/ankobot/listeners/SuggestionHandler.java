package com.ankoki.ankobot.listeners;

import com.ankoki.ankobot.Ankobot;
import com.ankoki.ankobot.utilities.Embed;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.react.GenericGuildMessageReactionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class SuggestionHandler extends ListenerAdapter {
    private static Guild guild = Ankobot.getJda().getGuildById(747818545466966176L);

    @Override
    public void onGenericGuildMessageReaction(@NotNull GenericGuildMessageReactionEvent e) {
        try {
            if (e.getUser().isBot()) return;
        } catch (Exception ex) {return;}
        if (!e.getChannel().getId().equalsIgnoreCase(guild.getTextChannelsByName("pending-suggestions", true).get(0).getId())) return;
        if (e.getReaction().toString().equalsIgnoreCase("MR:(M:(801130677764489216) / RE:U+1f44d)")) {
            e.getChannel().sendMessage(Embed.simple(":white_check_mark: Suggestion approved!", e.getUser())).queue();
            Message message = e.getChannel().getHistory().getMessageById(e.getMessageId());
            if (message == null) System.out.println("message null");
            guild.getTextChannelsByName("〔\uD83D\uDCAD〕suggestions", true).get(0).sendMessage(message).queue(message1 -> {
                message1.addReaction("\uD83D\uDC4D").queue();
                message1.addReaction("\uD83D\uDC4E").queue();
            });
            return;
        }
        e.getChannel().sendMessage(Embed.simple(":no_entry_sign: Suggestion denied!", e.getUser())).queue();
    }
}
