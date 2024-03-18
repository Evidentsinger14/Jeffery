package dev.ev1dent.jeffery.panels;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class PanelReactionRoles extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);
        if(event.getName().contains("reactionroles")){
            /* EMBEDS */
            EmbedBuilder panelEmbed = new EmbedBuilder()
                    .setTitle("Role Reactions", null)
                    .setColor(Color.RED)
                    .setDescription("List of all games there are Text Channels for. You can select games you're interested in here - This will unlock a category + Channel for you to access, and chat with others in. For other games to be added, please post them in <#795787619782623293> ");

            /* CODE TO ACTUALLY EXECUTE */
            event.reply("Sending panel").setEphemeral(true).queue();

            event.getChannel().sendMessageEmbeds(panelEmbed.build())
                    .addActionRow(
                            Button.success("ALL", "ALL"),
                            Button.success("ATS", "ATS"),
                            Button.success("GTA", "GTA V"),
                            Button.success("DayZ", "DayZ"),
                            Button.success("Overwatch", "Overwatch")
                    )
                    .addActionRow(
                            Button.success("Sunkenland", "Sunkenland")
                    )
                    .queue();
        }
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        super.onButtonInteraction(event);
        String ID = event.getComponentId();
        Member member = event.getMember();
        ArrayList<String> gameList = new ArrayList<>();
        gameList.add("ALL");
        gameList.add("ATS");
        gameList.add("GTA");
        gameList.add("DayZ");
        gameList.add("Overwatch");
        gameList.add("Sunkenland");

        if (gameList.contains(ID)) {
            Role role = Objects.requireNonNull(event.getGuild()).getRolesByName(ID, true).get(0);
            System.out.printf("Role Added: %s", role.toString());

            try {
                assert member != null;
                boolean hasRole = member.getRoles().contains(role);
                switch (String.valueOf(hasRole)){
                    case "true":
                        event.getGuild().removeRoleFromMember(event.getInteraction().getUser(), role).queue();
                        event.reply(String.format("Removed %s", ID)).setEphemeral(true).queue();
                        break;
                    case "false":
                        event.getGuild().addRoleToMember(event.getInteraction().getUser(), role).queue();
                        event.reply(String.format("Added %s", ID)).setEphemeral(true).queue();
                        break;
                }
            } catch (Exception e) {
                EmbedBuilder errorEmbed = new EmbedBuilder()
                        .setTitle("Error Embed", null)
                        .setColor(Color.RED)
                        .addField("Exception", e.toString(), false);
                event.getGuild().getTextChannelsByName("bot-logs", true).get(0).sendMessageEmbeds(errorEmbed.build()).queue();

                event.reply("Could not add role " + ID + ". Please contact <@412070526081695744>.").setEphemeral(true).queue();
                System.out.println(e);
            }

        }
    }

}

