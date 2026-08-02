package com.example.nopauseinject.mixin;

import com.example.nopauseinject.NoPauseConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(method = "setScreen", at = @At("HEAD"), cancellable = true)
    private void nopauseinject$onSetScreen(Screen screen, CallbackInfo ci) {
        if (NoPauseConfig.blockPause && screen instanceof GameMenuScreen) {
            ci.cancel();
        }
    }
}
