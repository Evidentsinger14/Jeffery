package dev.ev1dent.jeffery.events;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class GuildMemberJoinListener extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        super.onGuildMemberJoin(event);
        Member member = event.getMember();
        Role role = event.getGuild().getRolesByName("Civilian", true).get(0);
        System.out.print(role);

        try {
            event.getGuild().addRoleToMember(member, role).queue();
        } catch (Exception e){
            System.out.printf("Unable to add role: %s", e.getMessage());
            e.printStackTrace();
        }
    }
}
