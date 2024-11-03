package dev.ev1dent.jeffery.Commands;

import dev.ev1dent.jeffery.utils.ServerConfigManager;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Set;

public class CommandServers extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);
        ServerConfigManager scm = new ServerConfigManager();
        switch (event.getName()) {
            case "addserver" -> {
                if (event.getOptions().isEmpty()) {
                    event.reply("Please specify the required data").queue();
                    return;
                }
                String serverName = event.getOptions().get(0).getAsString();
                String pteroID = event.getOptions().get(1).getAsString();
                String roleID = event.getOptions().get(2).getAsString();
                if (scm.hasServer(serverName)) {
                    event.reply("This server already exists").queue();
                    return;
                }
                scm.addServerConfig(serverName, pteroID, roleID);
                event.reply("Added server config").queue();

            }
            case "rmserver" -> {
                if (event.getOptions().isEmpty()) {
                    event.reply("Please specify the required data").queue();
                    return;
                }
                String serverName = event.getOptions().get(0).getAsString();
                if (!scm.hasServer(serverName)) {
                    event.reply("This server does not exist").queue();
                    return;
                }
                scm.removeServerConfig(serverName);
                event.reply("Removed server config").queue();

            }
            case "listservers" -> {
                Set<String> servers = scm.getAllServerNames();
                if(servers.isEmpty()){
                    event.reply("No servers found").queue();
                    return;
                }

                StringBuilder serverList = new StringBuilder();
                for (String server : servers) {
                    serverList.append(server + ", ");
                }
                event.reply(serverList.toString()).queue();

            }
        }
    }
}
