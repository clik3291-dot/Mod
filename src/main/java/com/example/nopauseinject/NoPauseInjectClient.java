package com.example.nopauseinject;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class NoPauseInjectClient implements ClientModInitializer {

    private static final KeyBinding TOGGLE_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.nopauseinject.toggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_N,
            "category.nopauseinject"
    ));

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (TOGGLE_KEY.wasPressed()) {
                NoPauseConfig.blockPause = !NoPauseConfig.blockPause;
                sendFeedback(client, NoPauseConfig.blockPause);
            }
        });
    }

    private void sendFeedback(MinecraftClient client, boolean enabled) {
        if (client.player != null) {
            String msg = enabled
                    ? "[No Pause] Блокировка меню паузы: ВКЛ"
                    : "[No Pause] Блокировка меню паузы: ВЫКЛ";
            client.player.sendMessage(Text.literal(msg), true);
        }
    }
}
