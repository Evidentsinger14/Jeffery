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
                    .setDescription("List of all games there are Text Channels for. You can select games you're interested in here - This will unlock a category + Channel for you to access, and chat with others in. For other games to be added, please post them in <#795787619782623293> ")
                    .addField("Games", "Grand Theft Auto V, American Truck Simulator, DayZ, Overwatch", false);

            /* CODE TO ACTUALLY EXECUTE */
            event.reply("Sending panel").setEphemeral(true).queue();

            event.getChannel().sendMessageEmbeds(panelEmbed.build())
                    .addActionRow(
                            Button.success("ALL", "ALL"),
                            Button.success("ATS", "ATS"),
                            Button.success("GTA", "GTA V"),
                            Button.success("DayZ", "DayZ"),
                            Button.success("Overwatch", "Overwatch")
                    ).queue();
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


        if (gameList.contains(ID)) {
            Role role = Objects.requireNonNull(event.getGuild()).getRolesByName(ID, true).get(0);

            try {
                assert member != null;
                boolean hasRole = member.getRoles().contains(role);
                if(hasRole){
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

