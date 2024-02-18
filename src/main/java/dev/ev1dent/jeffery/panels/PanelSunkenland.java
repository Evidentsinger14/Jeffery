package dev.ev1dent.jeffery.panels;

import dev.ev1dent.jeffery.JefferyBrains;
import dev.ev1dent.jeffery.utils.PteroUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;


public class PanelSunkenland extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);
        if(event.getName().contains("skpanel")){
            /* EMBEDS */
            EmbedBuilder panelEmbed = new EmbedBuilder()
                    .setTitle("Sunkenland", null)
                    .setColor(Color.GREEN)
                    .setDescription("Start / Stop Sunkenland Server.")
                    .addField("Note", " (Please turn server off when nobody is online to preserve day)", false);

            /* CODE TO ACTUALLY EXECUTE */
            event.reply("Sending panel").setEphemeral(true).queue();

            event.getChannel().sendMessageEmbeds(panelEmbed.build())
                    .addActionRow(
                            Button.success("sunkenland-Start", "Start"),
                            Button.danger("sunkenland-Stop", "Stop")

                    ).queue();
        }
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {

        super.onButtonInteraction(event);
        String status = event.getComponentId();
        switch (status) {
            case "sunkenland-Start" -> startServer(event);
            case "sunkenland-Stop" -> stopServer(event);
        }

    }

    String APIKey = JefferyBrains.config().get("SUNKENLAND_APIKEY"),
            ServerID = JefferyBrains.config().get("SUNKENLAND_SERVERID");


    private void startServer(ButtonInteractionEvent event){
        event.reply("Starting Server...").setEphemeral(true).queue();
        PteroUtil.postToPanel("{\"signal\": \"start\"}", ServerID, APIKey);
    }
    private void stopServer(ButtonInteractionEvent event){
        event.reply("Stopping Server...").setEphemeral(true).queue();
        PteroUtil.postToPanel("{\"signal\": \"stop\"}", ServerID, APIKey);
    }

}
