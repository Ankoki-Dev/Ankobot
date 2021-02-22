package com.ankoki.ankobot.commands.guild;

import com.ankoki.ankobot.managers.GuildCommand;
import com.ankoki.ankobot.utilities.Embed;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.Nullable;

public class DownloadCommand implements GuildCommand {

    @Override
    public void onCommand(Guild guild, User user, TextChannel channel, String[] args, Message message) {
        if (args.length != 1 || args[0].isEmpty()) {
            channel.sendMessage(Embed.simple(":no_entry_sign: This isn't the correct usage! You need to specify a plugin.", user)).queue();
            return;
        }
        switch (args[0].toLowerCase()) {
            case "skjade":
                channel.sendMessage(Embed.simple(":white_check_mark: Here is the download link to SkJade!", user)).queue();
                channel.sendMessage("https://www.spigotmc.org/resources/skjade.89127/").queue();
                break;
            case "elementals":
                channel.sendMessage(Embed.simple(":white_check_mark: Here is the download link to Elementals!", user)).queue();
                channel.sendMessage("https://www.spigotmc.org/resources/elementals.87962/").queue();
                break;
            case "beasttokensk":
                channel.sendMessage(Embed.simple(":white_check_mark: Here is the download link to BeastTokenSk!", user)).queue();
                channel.sendMessage("https://www.spigotmc.org/resources/skript-addon-beasttokensk.86157/").queue();
                break;
            default:
                channel.sendMessage(Embed.simple(":no_entry_sign: This isn't one of my plugins!", user)).queue();
                channel.sendMessage("If this plugin exists, it'll be here -> https://www.github.com/Ankoki-Dev/" + args[0].toLowerCase()).queue();
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{"down", "download"};
    }

    @Override
    public @Nullable Permission[] getPermissions() {
        return null;
    }
}
