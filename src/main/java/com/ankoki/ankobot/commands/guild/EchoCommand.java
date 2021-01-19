package com.ankoki.ankobot.commands.guild;

import com.ankoki.ankobot.managers.GuildCommand;
import com.ankoki.ankobot.utilities.StringUtils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.Nullable;

public class EchoCommand implements GuildCommand {

    @Override
    public void onCommand(Guild guild, User user, TextChannel channel, String[] args, Message message) {
        channel.sendMessage(StringUtils.arrayAsString(args)).queue();
    }

    @Override
    public String[] getAliases() {
        return new String[]{"copy", "echo"};
    }

    @Override
    public @Nullable Permission[] getPermissions() {
        return new Permission[]{Permission.ADMINISTRATOR};
    }
}
