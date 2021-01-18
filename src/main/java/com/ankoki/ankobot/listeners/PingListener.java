package com.ankoki.ankobot.listeners;

import com.ankoki.ankobot.gitignore.Secrets;
import com.ankoki.ankobot.utilities.Const;
import com.ankoki.ankobot.utilities.Embed;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PingListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getAuthor().isBot()/* || e.getMember().isOwner()*/) return;
        for (Member member : e.getMessage().getMentionedMembers()) {
            if (member.getIdLong() == 722898458020937769L) {
                e.getMessage().delete().queue();
                e.getChannel().sendMessage(Embed.simple(e.getMember().getAsMention() + " please don't tag " +
                        member.getNickname() + "!", e.getAuthor())).queue();
                e.getGuild().getTextChannelsByName("illegal-pings", true)
                        .get(0)
                        .sendMessage(new MessageEmbed(null,
                                "Illegal Ping!",
                                member.getAsMention() + " was pinged in "
                                        + e.getChannel().getAsMention() + "!\n" +
                                        "The member who pinged " + member.getNickname() + " was " + e.getMember().getAsMention() + "!",
                                null,
                                null,
                                Secrets.COLOUR.getRGB(),
                                null,
                                null,
                                null,
                                null,
                                new MessageEmbed.Footer(
                                        member.getEffectiveName() + " | " + Const.formattedNow(),
                                        null, null),
                                null,
                                null))
                        .queue();
            } else if (member.getIdLong() == 800767264370458644L) {
                e.getMessage().delete();
                e.getChannel().sendMessage(Embed.simple("Don't ping me cunt", e.getAuthor())).queue();
            }
        }
    }
}
