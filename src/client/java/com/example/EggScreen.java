package com.example;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.DirectionalLayoutWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.OrderedText;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EggScreen extends HandledScreen<EggScreenHandler> {

    private ButtonWidget ButtonA, ButtonB, ButtonC;
    private static final Identifier TEXTURE = new Identifier("modid", "eggfriend.png");
    private static final Identifier pikachu = new Identifier("modid", "pikachu.png");

    private PlayerInventory pi;
    public EggScreen(EggScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        pi = inventory;
        if (handler instanceof EggScreenHandler)
        {
            if(handler.ef != null)
            {
                this.ef = handler.ef;
            }
        }
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

    }

    private float animationTimer = 0f;
    private int selectedSprite = 0;
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        int spriteSize = 54;

        animationTimer += delta;


        if(animationTimer >= 4f)
        {
            animationTimer = 0f;
//            selectedSprite ^= 1;
//            ExampleMod.LOGGER.info("" + (System.currentTimeMillis() / 1000 % 60));
        }
        String test = "";

        if(ef != null)
        {
            NbtCompound efData;
            try {
                efData = ef.saveData();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            for(String key : efData.getKeys())
            {
                test += key + " " + efData.get(key).asString() + "\n";
            }

            List<OrderedText> ot = this.textRenderer.wrapLines(StringVisitable.plain(test), 144);
            for (int i = 0; i < ot.size(); i++)
            {
                context.drawText(this.textRenderer, ot.get(i), 15, y + i*10, 0, false);
            }
        }


//        context.drawText(this.textRenderer, "Debug: " + String.join("\nDebug: ", text.values()), 15, y,0,false);

        if(ef != null && ef.currentState == LibEggFriend.States.EGG)
        {
            context.drawTexture(pikachu, x+16, y+16, spriteSize, spriteSize, 10, 45, spriteSize, spriteSize, 242, 2068);
        }
        else
        {
            context.drawTexture(pikachu, x+16, y+16, spriteSize, spriteSize, 66, 45, spriteSize, spriteSize, 242, 2068);
        }



//        ExampleMod.LOGGER.info("render called");
    }

    LibEggFriend ef;

    private void channelHandler(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {
        client.execute(() -> {
            // Everything in this lambda is run on the render thread
            NbtCompound input = buf.readNbt();
            if(input != null)
            {
                try {
                    ef = new LibEggFriend(input);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
//            selectedSprite ^= 1;
//            ExampleMod.LOGGER.info("Received packet");
        });
    }

    @Override
    protected void init() {
        int farLeft = (width - backgroundWidth) / 2;
        int bottomLeft = ((height - backgroundHeight) / 2) + backgroundHeight;
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
        ButtonA = this.addDrawableChild(ButtonWidget.builder(Text.of("A"), ctx -> {}).position(farLeft + 10, bottomLeft - 26).width(16).build());
        ButtonB = this.addDrawableChild(ButtonWidget.builder(Text.of("B"), ctx -> {}).position((farLeft + backgroundWidth / 2) - 8, bottomLeft - 26).width(16).build());
        ButtonC = this.addDrawableChild(ButtonWidget.builder(Text.of("C"), ctx -> {}).position(farLeft + backgroundWidth - 26, bottomLeft - 26).width(16).build());
        ExampleMod.LOGGER.info("Screen Init");

        ClientPlayNetworking.registerGlobalReceiver(pikachu, this::channelHandler);
    }

    @Override
    public void close() {
        super.close();
        ClientPlayNetworking.unregisterGlobalReceiver(pikachu);
    }
}
