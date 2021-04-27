package zone.bears.blockagotchi;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class WatchScreen extends Screen {

    private static final int HEIGHT = 256;
    private static final int WIDTH = 128;


    protected WatchScreen(ITextComponent p_i51108_1_) {
        super(p_i51108_1_);
    }

    public WatchScreen()
    {
        // TODO Add this string to the resource pack
        super(new TranslationTextComponent("screen.blockagotchi.watchscreen"));
    }

    @Override
    protected void init() {
        // TODO When IPressable is null, clicking the button crashes the game
        addButton(new Button(40,40,20,20,new StringTextComponent("L"), null));
        super.init();
    }

    @Override
    public void render(MatrixStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        // TODO There's some sort of blit business here. https://github.com/McJty/YouTubeModding14/blob/1.16/src/main/java/com/mcjty/mytutorial/gui/SpawnerScreen.java
        super.render(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public static void open() {
        Minecraft.getInstance().setScreen(new WatchScreen());
    }
}
