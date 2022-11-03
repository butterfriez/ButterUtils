package com.butterutil.features;

import com.butterutil.config.Config;
import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.RichPresence;
import com.jagrosh.discordipc.exceptions.NoDiscordClientException;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

import java.time.OffsetDateTime;

public class DiscordRichPresence {
    IPCClient client = new IPCClient(Long.parseLong(Config.INSTANCE.getClientID()));
    private OffsetDateTime offsetDateTime = OffsetDateTime.now();

    @SubscribeEvent
    public void onServerConnect(FMLNetworkEvent.ClientConnectedToServerEvent event) throws NoDiscordClientException {
        String network = Minecraft.getMinecraft().getCurrentServerData().serverIP;
        if(network.contains("hypixel") && Config.INSTANCE.getDRP() && Config.INSTANCE.getClientID().length() > 1) {
            updateDiscord();
        }
    }

    @SubscribeEvent
    public void onServerDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        resetOffsetTime();
        client.close();
    }
    private void updateDiscord() throws NoDiscordClientException {
        client.setListener(new IPCListener() {
            @Override
            public void onReady(IPCClient client) {
                RichPresence.Builder builder = new RichPresence.Builder();

                builder.setState("Minecraft: Butter Utils")
                        .setDetails("")
                        .setStartTimestamp(offsetDateTime)
                        .setLargeImage("canary-large", "Discord Canary")
                        .setSmallImage("ptb-small", "Discord PTB");
                client.sendRichPresence(builder.build());
            }
        });
        client.connect();
    }

    public void resetOffsetTime() {
        this.offsetDateTime = OffsetDateTime.now();
    }
}

/* example
IPCClient client = new IPCClient(345229890980937739L);
client.setListener(new IPCListener(){
    @Override
    public void onReady(IPCClient client)
    {
        RichPresence.Builder builder = new RichPresence.Builder();
        builder.setState("West of House")
            .setDetails("Frustration level: Over 9000")
            .setStartTimestamp(OffsetDateTime.now())
            .setLargeImage("canary-large", "Discord Canary")
            .setSmallImage("ptb-small", "Discord PTB")
            .setParty("party1234", 1, 6)
            .setMatchSecret("xyzzy")
            .setJoinSecret("join")
            .setSpectateSecret("look");
        client.sendRichPresence(builder.build());
    }
});
client.connect();
 */