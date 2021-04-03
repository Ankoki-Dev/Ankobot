package com.ankoki.ankobot.commands.guild;

import com.ankoki.ankobot.gitignore.Secrets;
import com.ankoki.ankobot.managers.GuildCommand;
import com.ankoki.ankobot.utilities.Const;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class WelcomeCommand implements GuildCommand {

    private static final MessageEmbed RULES_EMBED(User user) {
        return new MessageEmbed(null, "Server Rules",
                null, null, null, Secrets.COLOUR.getRGB(),
                null, null, null, null,
                new MessageEmbed.Footer(user.getName() + " | " + Const.formattedNow(), null, null), null,
                Arrays.asList(new MessageEmbed.Field("[Rules]", "These are the rules you need to follow " +
                                "to be able to stay in this discord and this community " +
                                "as we want to keep this a safe space.", false),
                        new MessageEmbed.Field("**1.** Use Channels Correctly.",
                                "If you have a certain issue, go to the channel for that. If you want " +
                                        "to just talk generally, go to general. It's simple c:", false),
                        new MessageEmbed.Field("**2.** No Toxic Chat.",
                                "If you are toxic to anyone in a harmful or intended " +
                                        "manner, you will be muted as this is not tolerated. " +
                                        "As stated before, this is a safe space for people.", false),
                        new MessageEmbed.Field("**3.** No Slurs.",
                                "If you are found to use any slurs here, you will be " +
                                        "removed. Even if you are able to reclaim this slur, we " +
                                        "are unable to be sure of this, so people will be " +
                                        "punished regardless. We apologise for this.", false),
                        new MessageEmbed.Field("**4.** No Spamming.",
                                "Spamming is just annoying. Nobody enjoys seeing a giant " +
                                        "block of shit. Literally nobody. So please don't. " +
                                        "Again, this will result in a mute.", false),
                        new MessageEmbed.Field("**5.** In Regards to Help.",
                                "Of course people here will help you with whatever issues " +
                                        "you are having, no matter what they are related to. " +
                                        "However, please understand the basics of what you are doing. " +
                                        "We cannot assist people who do not understand the very basics " +
                                        "of their subject.", false),
                        new MessageEmbed.Field("**Punishments**",
                                "Actions have consequences, and if you break any of the " +
                                        "rules stated above, you could be muted, banned, or kicked " +
                                        "from the server, and we don't want to have to do that, so " +
                                        "please follow the rules and listen to what we say plskthx<3", false),
                        new MessageEmbed.Field("**Common Sense**",
                                "Don't be a dumb cunt, we all know theres gonna be a few rules missing " +
                                        "from here where people'll just say \"oh but it's not in the rules \", use " +
                                        "your common sense, if it's most likely going to be frowned upon, ask or " +
                                        "just don't do it:)", false),
                        new MessageEmbed.Field("***Hi<3***",
                                "My DM's are always open if anyone has any questions, " +
                                        "and I'm quite active, so if you ever have a problem, don't be " +
                                        "afraid to message me:) - Ankoki#0001", false)));
    }

    private static final MessageEmbed WELCOME_EMBED(User user) {
        return new MessageEmbed(null, "Channel Usage",
                null, null, null, Secrets.COLOUR.getRGB(), null, null,
                null, null, new MessageEmbed.Footer(user.getName() + " | " + Const.formattedNow(), null, null),
                null,
                Arrays.asList(new MessageEmbed.Field("",
                       "<#747818545466966179>\n" +
                               "You are here! Here you can familiarise yourself with the rules and what the " +
                         "channnels are used for.", false),
                new MessageEmbed.Field("",
                        "<#747824179067486339>\n" +
                                "Important updates about plugins or updates will be released here.", false),
                new MessageEmbed.Field("",
                        "<#747824237674233877>\n" +
                                "Links to all my social platforms that regard to development, such as GitHub and Spigot.", false),
                new MessageEmbed.Field("",
                        "<#826868366316666891>\n" +
                                "This is where we have our GitHub webhook. Any pushes, issues, pull requests, or stars added to a " +
                        "repo will appear here!", false),
                new MessageEmbed.Field("",
                        "<#800828243757498448>\n" +
                                "This is our channel for general speaking! Talk with everyone else here.", false),
                new MessageEmbed.Field("",
                        "<#747825144696799252>\n" +
                                "This is the channel for suggesting new plugins I should develop, things to add to existing ones, " +
                        "or things to add to this discord!", false),
                new MessageEmbed.Field("",
                        "<#801117926127697931>\n" +
                                "If you want to use any of the bot commands (hi! i'm the bot ^-^), you can use them here.", false),
                new MessageEmbed.Field("",
                        "**HELP CHANNELS**\n" +
                                "My released plugins will have help channels. Ask in the correct channel for that plugin. " +
                        "If there is no channel for it, please ask me to add it! Any other help please visit <#747825127516799056>", false)));
    }

    @Override
    public void onCommand(Guild guild, User user, TextChannel channel, String[] args, Message message) {
        channel.sendMessage(RULES_EMBED(user)).queue();
        channel.sendMessage(WELCOME_EMBED(user)).queue();
    }

    @Override
    public String[] getAliases() {
        return new String[]{"welcome", "guild"};
    }

    @Override
    public @Nullable Permission[] getPermissions() {
        return new Permission[]{Permission.ADMINISTRATOR};
    }
}
