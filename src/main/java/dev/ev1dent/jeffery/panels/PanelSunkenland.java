package dev.ev1dent.jeffery.panels;

import dev.ev1dent.jeffery.JefferyBrains;
import dev.ev1dent.jeffery.utils.PteroUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;


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
        ArrayList<String> authorizedUsers = new ArrayList<>();
        authorizedUsers.add("412070526081695744");
        authorizedUsers.add("464783803110391818");
        authorizedUsers.add("303886787313401856");

        super.onButtonInteraction(event);
        User user = event.getUser();
        String status = event.getComponentId();
        if(!authorizedUsers.contains(user.getId())){
            return;
        }
        switch (status) {
            case "sunkenland-Start" -> startServer(event, user);
            case "sunkenland-Stop" -> stopServer(event, user);
        }

    }

    String ServerID = JefferyBrains.config().get("SUNKENLAND_SERVERID");


    private void startServer(ButtonInteractionEvent event, User user){
        event.reply("Starting Server...").setEphemeral(true).queue();
        PteroUtil.postToPanel("start", ServerID);
        TextChannel logChannel = event.getGuild().getTextChannelById(455843507618316308L);
        logChannel.sendMessage(user.getName() + " Started the server").queue();

    }

    private void stopServer(ButtonInteractionEvent event, User user){
        event.reply("Stopping Server...").setEphemeral(true).queue();
        PteroUtil.postToPanel("stop", ServerID);
        TextChannel logChannel = event.getGuild().getTextChannelById(455843507618316308L);
        logChannel.sendMessage(user.getName() + " Stopped the server").queue();
    }

}
