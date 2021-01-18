package com.ankoki.ankobot.listeners;

import com.ankoki.ankobot.Ankobot;
import com.ankoki.ankobot.gitignore.Secrets;
import com.ankoki.ankobot.managers.GuildCommand;
import com.ankoki.ankobot.managers.PrivateCommand;
import com.ankoki.ankobot.utilities.Embed;
import com.ankoki.ankobot.utilities.StringUtils;
import com.ankoki.ankobot.utilities.UserUtils;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;

@RequiredArgsConstructor
public class CommandListener extends ListenerAdapter {
    private final JDA jda;

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String message = e.getMessage().getContentRaw();
        if (e.getAuthor().isBot()) return;
        if (!message.startsWith(Secrets.PREFIX)) return;

        String commandName = StringUtils.getCommandName(e.getMessage().getContentRaw(), Secrets.PREFIX);
        for (GuildCommand command : Ankobot.instance().getGuildCommands()) {
            if (Arrays.asList(command.getAliases()).contains(commandName.toLowerCase())) {
                if (UserUtils.hasPerm(e.getMember(), command.getPermissions())) {
                    command.onCommand(e.getGuild(), e.getAuthor(), e.getChannel(), StringUtils.withoutCommand(message).split(" "), e.getMessage());
                    break;
                } else {
                    e.getChannel().sendMessage(Embed.noPermission(e.getAuthor())).queue();
                }
                break;
            }
        }
    }

    @Override
    public void onPrivateMessageReceived(PrivateMessageReceivedEvent e) {
        String message = e.getMessage().getContentRaw();
        if (e.getAuthor().isBot()) return;
        if (!message.startsWith(Secrets.PREFIX)) return;

        String commandName = StringUtils.getCommandName(e.getMessage().getContentRaw(), Secrets.PREFIX);
        for (PrivateCommand command : Ankobot.instance().getPrivateCommands()) {
            if (Arrays.asList(command.getAliases()).contains(commandName.toLowerCase())) {
                command.onCommand(e.getAuthor(), e.getChannel(), StringUtils.withoutCommand(message).split(" "), commandName);
                break;
            }
        }
    }
}