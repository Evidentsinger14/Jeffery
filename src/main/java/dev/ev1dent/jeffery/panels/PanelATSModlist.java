package dev.ev1dent.jeffery.panels;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class PanelATSModlist extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);
        if (event.getName().contains("atsmods")) {
            /* EMBEDS */
            EmbedBuilder panelEmbed = new EmbedBuilder()
                    .setTitle("American Truck Simulator Modlist", null)
                    .setColor(Color.RED)
                    .setDescription(
                            """
                                                        [Smoke in my trucks](https://steamcommunity.com/sharedfiles/filedetails/?id=2326826291)
                                                        [Mortal & Cash Cummins M11 v3.2](https://steamcommunity.com/sharedfiles/filedetails/?id=2595128788)
                                                        [Rezbilt Added Smoke Public](https://steamcommunity.com/sharedfiles/filedetails/?id=2964376899)
                                                        [Realistic Rain](https://steamcommunity.com/sharedfiles/filedetails/?id=1787294046)
                                                        [Realistic Headlights](https://steamcommunity.com/sharedfiles/filedetails/?id=2112203913)
                                                        [BigT Britax LED Beacons Pack](https://steamcommunity.com/sharedfiles/filedetails/?id=916489351)
                                                        [Boreman LED Marker Lights Pack](https://steamcommunity.com/sharedfiles/filedetails/?id=1317972379)
                                                        [Mack R Series](https://steamcommunity.com/sharedfiles/filedetails/?id=1463066263)
                                                        [Smokey and the Bandit Truck & Trailer Skin](https://steamcommunity.com/sharedfiles/filedetails/?id=1504577645)
                                                        [Decepticon Truck and Trailer skin + parts](https://steamcommunity.com/sharedfiles/filedetails/?id=1512687603)
                                                        [Mack Chu613](https://steamcommunity.com/sharedfiles/filedetails/?id=1943894280)
                                                        [Non-Flared Vehicle Lights mod](https://steamcommunity.com/sharedfiles/filedetails/?id=2202821990)
                                                        [Custom Parts for Viper2's Peterbilt 389](https://steamcommunity.com/sharedfiles/filedetails/?id=2376620893)
                                                        [Peterbilt 352/362 Project](https://steamcommunity.com/sharedfiles/filedetails/?id=2435980341)
                                                        [Interior Cabin Lights](https://steamcommunity.com/sharedfiles/filedetails/?id=2566384806)
                                                        [Rezbilt 389](https://steamcommunity.com/sharedfiles/filedetails/?id=2626143691)
                                                        [Fenders and Grill for Vipers 389](https://steamcommunity.com/sharedfiles/filedetails/?id=2676844333)
                                                        [Peterbilt 389 Black Stripes Metallic](https://steamcommunity.com/sharedfiles/filedetails/?id=694059095)
                                                        [Flamed Peterbilt 389](https://steamcommunity.com/sharedfiles/filedetails/?id=1620977993)
                                                        [Custom SCS Kenworth W900](https://steamcommunity.com/sharedfiles/filedetails/?id=2448009922)
                                                        [Kenworth W990 edited by Harven](https://steamcommunity.com/sharedfiles/filedetails/?id=1781104022)
                                                        [Kenworth W990 light pack compatability](https://steamcommunity.com/sharedfiles/filedetails/?id=2758820454)
                                                        [Kenworth K100-E](https://steamcommunity.com/sharedfiles/filedetails/?id=1815959194)
                                                        [Seogi ATS HUD (heads up display) All Truck](https://steamcommunity.com/sharedfiles/filedetails/?id=2967470039)
                                                        [Stickers for your Cabin's Refrigerator!!](https://steamcommunity.com/sharedfiles/filedetails/?id=2857486452)
                                                        """
                    )
                    .setFooter("Count: 25");

            /* CODE TO ACTUALLY EXECUTE */
            event.reply("Sending panel").setEphemeral(true).queue();

            event.getChannel().sendMessageEmbeds(panelEmbed.build()).queue();
        }
    }
}