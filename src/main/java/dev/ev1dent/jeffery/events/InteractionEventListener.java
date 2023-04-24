package dev.ev1dent.jeffery.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class InteractionEventListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);

        switch (event.getName()) {
            case "panel" -> {
                            /* EMBEDS */
                EmbedBuilder panelEmbed = new EmbedBuilder()
                        .setTitle("Title", null)
                        .setColor(Color.RED)
                        .setDescription("List of all games there are Text Channels for.")
                        .addField("Games", "American Truck Simulator /n DayZ, Grand Theft Auto V", false);

                        /* CODE TO ACTUALLY EXECUTE */
                event.reply("Sending panel").queue();
                event.getChannel()
                        .sendMessageEmbeds(panelEmbed.build())
                        .queue();
            }

            case "mute" -> {

                event.getChannel().sendMessage("Mute Command").queue();
            }
            default -> {
            }
        }
    }
}
