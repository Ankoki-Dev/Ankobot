package com.ankoki.ankobot.managers;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.Nullable;

public interface GuildCommand {
    void onCommand(Guild guild, User user, TextChannel channel, String[] args, Message message);
    String[] getAliases();
    @Nullable Permission[] getPermissions();
}