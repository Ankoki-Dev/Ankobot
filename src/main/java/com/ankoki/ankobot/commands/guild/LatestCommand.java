package com.ankoki.ankobot.commands.guild;

import com.ankoki.ankobot.managers.GuildCommand;
import com.ankoki.ankobot.utilities.Embed;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class LatestCommand implements GuildCommand {

    @Override
    public void onCommand(Guild guild, User user, TextChannel channel, String[] args, Message message) {
        if (args.length != 1 || args[0].isEmpty()) {
            channel.sendMessage(Embed.simple(":no_entry_sign: This isn't the correct usage! You need to specify a plugin.", user)).queue();
            return;
        }
        switch (args[0].toLowerCase()) {
            case "elementals":
                String elementalsVer = getLatestTag("Ankoki-Dev", "Elementals");
                channel.sendMessage(Embed.simple(":white_check_mark: Elementals is currently on v" + elementalsVer + "!", user)).queue();
                break;
            case "beasttokensk":
                String beastVer = getLatestTag("Ankoki-Dev", "BeastTokenSk");
                channel.sendMessage(Embed.simple(":white_check_mark: BeastTokenSk is currently on v" + beastVer + "!", user)).queue();
                break;
            default:
                channel.sendMessage(Embed.simple(":no_entry_sign: This isn't one of my plugins!", user)).queue();
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{"latest", "version", "ver"};
    }

    @Override
    public @Nullable Permission[] getPermissions() {
        return null;
    }

    private String getLatestTag(String user, String repo) {
        String url = "https://www.github.com/" + user + "/" + repo + "/releases/latest";
        String redirect = "";
        try {
            URLConnection con = new URL(url).openConnection();
            con.connect();
            InputStream is = con.getInputStream();
            redirect = con.getURL().toString();
            is.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String split;
        try {
            split = redirect.split("releases/tag/")[1];
        } catch (ArrayIndexOutOfBoundsException ex) {
            return "UNKNOWN";
        }
        return split;
    }
}
