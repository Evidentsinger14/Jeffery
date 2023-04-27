package dev.ev1dent.jeffery.events;

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

public class InteractionEventListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);

        switch (event.getName()) {
            case "panel" -> {
                /* EMBEDS */
                EmbedBuilder panelEmbed = new EmbedBuilder()
                        .setTitle("Role Reactions", null)
                        .setColor(Color.RED)
                        .setDescription("List of all games there are Text Channels for.")
                        .addField("Games", "Grand Theft Auto V, American Truck Simulator, DayZ", false);

                /* CODE TO ACTUALLY EXECUTE */
                event.reply("Sending panel").setEphemeral(true).queue();

                event.getChannel().sendMessageEmbeds(panelEmbed.build()).addActionRow(Button.success("ALL", "ALL"), Button.success("ATS", "ATS"), Button.success("GTA", "GTA V"), Button.success("DayZ", "DayZ")).queue();
            }

            case "mute" -> event.getChannel().sendMessage("Mute Command").queue();

            default -> {
            }
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


        if (gameList.contains(ID)) {
            Role role = Objects.requireNonNull(event.getGuild()).getRolesByName(ID, true).get(0);

            try {
                assert member != null;
                if (!member.getRoles().contains(role)) {
                    event.getGuild().addRoleToMember(event.getInteraction().getUser(), role).queue();
                    event.reply("+ " + ID).setEphemeral(true).queue();
                } else {
                    event.getGuild().removeRoleFromMember(event.getInteraction().getUser(), role).queue();
                    event.reply("- " + ID).setEphemeral(true).queue();

                }
            } catch (Exception exception) {
                EmbedBuilder errorEmbed = new EmbedBuilder()
                        .setTitle("Error Embed", null)
                        .setColor(Color.RED)
                        .addField("Exception", exception.toString(), false);
                event.getGuild().getTextChannelsByName("bot-logs", true).get(0).sendMessageEmbeds(errorEmbed.build()).queue();

                event.reply("Could not add role " + ID + ". Please contact <@412070526081695744>.").setEphemeral(true).queue();
                System.out.println(exception);
            }
        }
    }

}

