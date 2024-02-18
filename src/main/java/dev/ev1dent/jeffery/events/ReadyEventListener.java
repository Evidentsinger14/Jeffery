package dev.ev1dent.jeffery.events;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ReadyEventListener extends ListenerAdapter {
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        super.onReady(event);
        System.out.println("Bot Ready...");
    }
}
