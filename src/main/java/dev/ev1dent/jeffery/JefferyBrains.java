package dev.ev1dent.jeffery;

import dev.ev1dent.jeffery.events.InteractionEventListener;
import dev.ev1dent.jeffery.events.MessageEventListener;
import dev.ev1dent.jeffery.events.ReadyEventListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class JefferyBrains {
    public static void main(String[] args) throws LoginException {
        final String BOT_TOKEN = "DONT-PUSH-TOKEN";
        JDABuilder jdaBuilder = JDABuilder.createDefault(BOT_TOKEN);

        JDA jda = jdaBuilder
                // Start Intents
                .enableIntents(
                        GatewayIntent.DIRECT_MESSAGE_REACTIONS,
                        GatewayIntent.DIRECT_MESSAGE_TYPING,
                        GatewayIntent.DIRECT_MESSAGES,
                        GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
                        GatewayIntent.GUILD_INVITES,
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS,
                        GatewayIntent.GUILD_MESSAGE_TYPING,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_PRESENCES,
                        GatewayIntent.GUILD_VOICE_STATES,
                        GatewayIntent.GUILD_WEBHOOKS,
                        GatewayIntent.MESSAGE_CONTENT
                )
                // Start add event listeners
                .addEventListeners(
                        new ReadyEventListener(),
                        new MessageEventListener(),
                        new InteractionEventListener()
                )
                // Build code into a bot.
                .build();

        // Create your slash commands to be used.
        jda.updateCommands().queue();
        jda.upsertCommand("panel", "this is a slash command").setGuildOnly(true).queue();
        jda.upsertCommand("ping", "this is a slash command").setGuildOnly(true).queue();
        jda.upsertCommand("ban", "this is a slash command").setGuildOnly(true).queue();
        jda.upsertCommand("kick", "this is a slash command").setGuildOnly(true).queue();
        jda.upsertCommand("mute", "this is a slash command").setGuildOnly(true).queue();


    }
}
