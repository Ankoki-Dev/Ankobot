package com.ankoki.ankobot;

import com.ankoki.ankobot.commands.guild.*;
import com.ankoki.ankobot.commands.suggestion.SuggestCommand;
import com.ankoki.ankobot.listeners.*;
import com.ankoki.ankobot.managers.GuildCommand;
import com.ankoki.ankobot.managers.PrivateCommand;
import com.ankoki.ankobot.gitignore.Secrets;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ankobot extends ListenerAdapter {

    private final List<GuildCommand> GUILD_COMMANDS = new ArrayList<>();
    private final List<PrivateCommand> PRIVATE_COMMANDS = new ArrayList<>();
    private static Ankobot instance;
    private static JDA jda;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("Bot Starting!");

        instance = new Ankobot();

        String token = Secrets.TOKEN;

        try {
            jda = JDABuilder.createDefault(token).build();
        } catch (LoginException ex) {
            ex.printStackTrace();
            return;
        }

        try {
            jda = jda.awaitReady();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            return;
        }

        try {
            JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.DIRECT_MESSAGE_REACTIONS, GatewayIntent.GUILD_BANS, GatewayIntent.GUILD_MEMBERS)
                    .addEventListeners(new CommandListener(jda),
                            new PingListener(),
                            new LogListener(),
                            new JoinListener(),
                            new SuggestionHandler())
                    .setActivity(Activity.listening("Jay's clacky ass keyboard"))
                    .setMemberCachePolicy(MemberCachePolicy.ALL)
                    .enableIntents(GatewayIntent.GUILD_MEMBERS)
                    .setStatus(OnlineStatus.DO_NOT_DISTURB)
                    .build();
        } catch (LoginException ex) {
            ex.printStackTrace();
        }

        instance.registerGuildCommands(new RulesCommand(),
                new BotCommand(),
                new DownloadCommand(),
                new SucksCommand(),
                new PurgeCommand(),
                new EchoCommand());
        instance.registerPrivateCommands(new SuggestCommand());
        System.out.printf("Bot was enabled in %.2f seconds!%n", (float) System.currentTimeMillis() - start);
        System.out.println("Invite Ankobot through this link: " + Secrets.BOT_INVITE);
    }

    private void registerGuildCommands(GuildCommand... commands) {
        GUILD_COMMANDS.addAll(Arrays.asList(commands));
    }

    private void registerPrivateCommands(PrivateCommand... commands) {
        Collections.addAll(PRIVATE_COMMANDS, commands);
    }

    public static Ankobot instance() {
        return instance;
    }

    public List<GuildCommand> getGuildCommands() {
        return GUILD_COMMANDS;
    }

    public List<PrivateCommand> getPrivateCommands() {
        return PRIVATE_COMMANDS;
    }

    public static JDA getJda() {
        return jda;
    }
}