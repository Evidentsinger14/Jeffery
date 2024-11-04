package dev.ev1dent.jeffery.Commands;

import dev.ev1dent.jeffery.utils.PteroUtil;
import dev.ev1dent.jeffery.utils.ServerConfigManager;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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
                event.reply(String.format("Added Server:\nServer Name: %s\nPterodactyl ID: %s\nRequired Role: %s", serverName, pteroID, roleID)).queue();

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
                event.reply(String.format("Removed Server:\nServer Name: %s", serverName)).queue();

            }
            case "listservers" -> {
                Set<String> servers = scm.getAllServerNames();
                if (servers.isEmpty()) {
                    event.reply("No servers found").queue();
                    return;
                }

                StringBuilder serverList = new StringBuilder();
                for (String server : servers) {
                    serverList.append(server + "\n");
                }
                event.reply(serverList.toString()).queue();

            }

            case "startserver" -> {
                if (!scm.hasServer(event.getOptions().get(0).getAsString())) {
                    event.reply("This server does not exist").queue();
                    return;
                }
                String requiredRole = scm.getRoleId(event.getOptions().get(0).getAsString());
                Role role = event.getGuild().getRoleById(requiredRole);
                System.out.println(event.getGuild().getMember(event.getUser()).getRoles().contains(role));
                System.out.println(scm.isAuthorizedUser(event));
                if (!event.getGuild().getMember(event.getUser()).getRoles().contains(role) || !scm.isAuthorizedUser(event)) {
                    event.reply("You do not have permission to modify this server").queue();
                    return;
                }
                PteroUtil.postToPanel("start", scm.getServerId(event.getOptions().get(0).getAsString()));
                event.reply("Started server").queue();
            }

            case "stopserver" -> {
                if (!scm.hasServer(event.getOptions().get(0).getAsString())) {
                    event.reply("This server does not exist").queue();
                    return;
                }
                String requiredRole = scm.getRoleId(event.getOptions().get(0).getAsString());
                Role role = event.getGuild().getRoleById(requiredRole);
                System.out.println(event.getGuild().getMember(event.getUser()).getRoles().contains(role));
                System.out.println(scm.isAuthorizedUser(event));
                if (!event.getGuild().getMember(event.getUser()).getRoles().contains(role) || !scm.isAuthorizedUser(event)) {
                    event.reply("You do not have permission to modify this server").queue();
                    return;
                }
                PteroUtil.postToPanel("stop", scm.getServerId(event.getOptions().get(0).getAsString()));
                event.reply("Stopped server").queue();
            }

        }
    }
}