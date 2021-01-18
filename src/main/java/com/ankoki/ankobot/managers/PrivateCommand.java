package com.ankoki.ankobot.managers;

import net.dv8tion.jda.api.entities.*;

public interface PrivateCommand {
    void onCommand(User user, PrivateChannel channel, String[] args, String alias);
    String[] getAliases();
}