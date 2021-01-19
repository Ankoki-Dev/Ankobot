package com.ankoki.ankobot.commands.suggestion;

import com.ankoki.ankobot.Ankobot;
import com.ankoki.ankobot.gitignore.Secrets;
import com.ankoki.ankobot.managers.PrivateCommand;
import com.ankoki.ankobot.utilities.Const;
import com.ankoki.ankobot.utilities.Embed;
import com.ankoki.ankobot.utilities.StringUtils;
import net.dv8tion.jda.api.entities.*;

import java.util.Collections;

public class SuggestCommand implements PrivateCommand {
    private static final Guild guild = Ankobot.getJda().getGuildById(747818545466966176L);

    @Override
    public void onCommand(User user, PrivateChannel channel, String[] args, String alias) {
        if (args.length < 1 || args[0].isEmpty()) {
            channel.sendMessage(Embed.simple(":no_entry_sign: You need to suggest something!", user)).queue();
            return;
        }
        channel.sendMessage(Embed.simple(":white_check_mark: Thank you for your suggestion!", user)).queue();
        assert guild != null;
        TextChannel guildChannel = guild.getTextChannelsByName("pending-suggestions", true).get(0);
        guildChannel.sendMessage(new MessageEmbed(null,
                "**New Suggestion**",
                null,
                null,
                null,
                Secrets.COLOUR.getRGB(),
                null,
                null,
                null,
                null,
                new MessageEmbed.Footer(user.getAsTag() + " | " + Const.formattedNow(),
                        null,
                        null),
                null,
                Collections.singletonList(new MessageEmbed.Field("***New Suggestion from ***" + user.getName() + ":",
                        StringUtils.arrayAsString(args), false)))).queue(message -> {
            message.addReaction("\uD83D\uDC4D").queue();
            message.addReaction("\uD83D\uDC4E").queue();
        });
    }

    @Override
    public String[] getAliases() {
        return new String[]{"suggest", "suggestion"};
    }
}
