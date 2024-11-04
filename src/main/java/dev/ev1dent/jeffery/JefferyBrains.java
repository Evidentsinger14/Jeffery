package dev.ev1dent.jeffery;

import dev.ev1dent.jeffery.Commands.CommandServers;
import dev.ev1dent.jeffery.events.GuildMemberJoinListener;
import dev.ev1dent.jeffery.events.GuildMemberRoleProcessor;
import dev.ev1dent.jeffery.panels.PanelATSModlist;
import dev.ev1dent.jeffery.panels.PanelReactionRoles;
import dev.ev1dent.jeffery.events.MessageEventListener;
import dev.ev1dent.jeffery.events.ReadyEventListener;
import dev.ev1dent.jeffery.panels.PanelSunkenland;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class JefferyBrains {
    public static Dotenv config() {
        return Dotenv.configure().load();
    }

    public static void main(String[] args) {
        String BOT_TOKEN = JefferyBrains.config().get("BOT_TOKEN");
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
                        new PanelReactionRoles(),
                        new PanelSunkenland(),
                        new PanelATSModlist(),
                        new GuildMemberJoinListener(),
                        new GuildMemberRoleProcessor(),
                        new CommandServers()
                )
                // Build code into a bot.
                .build();

        // Create your slash commands to be used.
        jda.updateCommands().queue();
        jda.upsertCommand("reactionroles", "This is the panel for the reaction roles")
                .setGuildOnly(true).queue();

        jda.upsertCommand("skpanel", "This is the panel for the Sunkenland Server Manager")
                .setGuildOnly(true).queue();

        jda.upsertCommand("atsmods", "This is the panel for American Truck Simulator mods, and their order!")
                .setGuildOnly(true).queue();

        jda.upsertCommand("addserver", "Add servers to config (Admin)")
                .addOption(OptionType.STRING, "servername", "The name the server will be referred to as within the bot.")
                .addOption(OptionType.STRING, "pteroid", "The short ID for pterodactyl")
                .addOption(OptionType.ROLE, "role", "What role should be able to start/stop this server")
                .setGuildOnly(true).queue();
        jda.upsertCommand("rmserver", "Remove servers from config (Admin)")
                .addOption(OptionType.STRING, "servername", "The name the server will be referred to as within the bot.")
                .setGuildOnly(true).queue();
        jda.upsertCommand("listservers", "List all servers currently in the server configuration file.")
                .setGuildOnly(true).queue();
        jda.upsertCommand("startserver", "start the specified server")
                .addOption(OptionType.STRING, "servername", "Specify the name of the server you wish to start")
                .setGuildOnly(true).queue();
        jda.upsertCommand("stopserver", "stops the specified server")
                .addOption(OptionType.STRING, "servername", "Specify the name of the server you wish to stop")
                .setGuildOnly(true).queue();





    }
}
