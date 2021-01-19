package com.ankoki.ankobot.commands.guild;

import com.ankoki.ankobot.managers.GuildCommand;
import com.ankoki.ankobot.utilities.Embed;
import com.ankoki.ankobot.utilities.StringUtils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PurgeCommand implements GuildCommand {

    private boolean isWorking;

    @Override
    public void onCommand(Guild guild, User user, TextChannel channel, String[] args, Message message) {
        if (this.isWorking) {
            channel.sendMessage(Embed.simple(":no_entry_sign: We are already working! Please wait...", user)).queue();
            return;
        }
        if (args.length != 1) {
            channel.sendMessage(Embed.simple(":no_entry_sign: This isn't the correct usage!", user)).queue();
            return;
        }
        this.purge(channel, StringUtils.arrayAsString(args), user);
    }

    @Override
    public String[] getAliases() {
        return new String[]{"purge"};
    }

    @Override
    public @Nullable Permission[] getPermissions() {
        return new Permission[]{Permission.ADMINISTRATOR, Permission.MESSAGE_MANAGE};
    }

    private void purge(TextChannel channel, String contents, User user) {
        isWorking = true;
        int i;
        String[] stupidWorkaround = contents.split(" ");
        try {
            i = Integer.parseInt(stupidWorkaround[0]) + 1;
        } catch (NumberFormatException ex) {
            channel.sendMessage(Embed.simple(":no_entry_sign: Use this command with the format `.purge <number>`!", user)).queue();
            isWorking = false;
            return;
        }
        if (2 > i || i > 100) {
            channel.sendMessage(Embed.simple(":no_entry_sign: Invalid number! Please use a number between `1 and 99`!", user)).queue();
            isWorking = false;
            return;
        }
        new Thread(() -> {
            List<Message> messages = channel.getHistory().retrievePast(i).complete();
            messages.remove(0);
            if (messages.isEmpty()) {
                isWorking = false;
                channel.sendMessage(Embed.simple(":no_entry_sign: No messages to delete!", user));
            } else {
                try {
                    channel.deleteMessages(messages).complete();
                } catch (IllegalArgumentException ex) {
                    channel.sendMessage(Embed.simple(":no_entry_sign: Some messages are older than 2 weeks and cannot be deleted!", user)).queue();
                    isWorking = false;
                    return;
                }
                isWorking = false;
                channel.sendMessage(Embed.simple(":white_check_mark: " + (i - 1) + " messages were purged successfully!", user)).queue();
            }
        }).start();
        isWorking = false;
    }
}
