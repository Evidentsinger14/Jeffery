package dev.ev1dent.jeffery.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class GuildMemberRoleProcessor extends ListenerAdapter {

    String timeStamp = new SimpleDateFormat("MM/dd/yy HH:mm").format(Calendar.getInstance().getTime());

    @Override
    public void onGuildMemberRoleAdd(@NotNull GuildMemberRoleAddEvent event){
        super.onGuildMemberRoleAdd(event);
        Member member = event.getMember();
        ArrayList<String> roleList = new ArrayList<>();
        event.getRoles().forEach(role -> roleList.add(role.getName()));
        String descMessage = String.format("%s was given the `%s` role", event.getMember().getAsMention(), roleList);
        String footerMessage = String.format("ID: %s • %s", event.getMember().getId(), timeStamp);
        TextChannel channel = event.getGuild().getTextChannelsByName("bot-logs", true).get(0);
        Color color = Color.green;
        sendEmbed(member, descMessage, footerMessage, channel, color);
    }

    @Override
    public void onGuildMemberRoleRemove(@NotNull GuildMemberRoleRemoveEvent event){
        super.onGuildMemberRoleRemove(event);
        Member member = event.getMember();
        ArrayList<String> roleList = new ArrayList<>();
        event.getRoles().forEach(role -> roleList.add(role.getName()));
        String descMessage = String.format("%s was remove from the `%s` role", event.getMember().getAsMention(), roleList);
        String footerMessage = String.format("ID: %s • %s", event.getMember().getId(), timeStamp);
        TextChannel channel = event.getGuild().getTextChannelsByName("bot-logs", true).get(0);
        Color color = Color.red;
        sendEmbed(member, descMessage, footerMessage, channel, color);
    }

    private void sendEmbed(Member member, String descMessage, String footerMessage, TextChannel channel, Color color){
        EmbedBuilder embed = new EmbedBuilder()
                .setAuthor(member.getUser().getName(), null, member.getEffectiveAvatarUrl())
                .setColor(color)
                .setDescription(descMessage)
                .setFooter(footerMessage);
        channel.sendMessageEmbeds(embed.build()).queue();
    }
}
