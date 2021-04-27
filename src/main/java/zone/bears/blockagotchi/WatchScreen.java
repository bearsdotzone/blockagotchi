package zone.bears.blockagotchi;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class WatchScreen extends Screen {

    private static final int HEIGHT = 256;
    private static final int WIDTH = 256;

    private ResourceLocation GUI = new ResourceLocation(Blockagotchi.MODID, "textures/gui/watch_gui.png");


    protected WatchScreen(ITextComponent p_i51108_1_) {
        super(p_i51108_1_);
    }

    public WatchScreen()
    {
        // TODO Add this string to the resource pack
        super(new TranslationTextComponent("screen.blockagotchi.watch_screen"));
    }

    @Override
    protected void init() {
        int centerX = (this.width - WIDTH) / 2;
        int centerY = (this.height - HEIGHT) / 2;

        // TODO When IPressable is null, clicking the button crashes the game
        addButton(new Button(centerX + 108,centerY + 10,40,40,new StringTextComponent("L"), button -> test()));
        addButton(new Button(centerX + 108,centerY + 60,40,40,new StringTextComponent("M"), button -> test()));
        addButton(new Button(centerX + 108,centerY + 110,40,40,new StringTextComponent("R"), button -> test()));
    }

    private void test()
    {

    }


    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(GUI);
        int relX = (this.width - WIDTH) / 2;
        int relY = (this.height - HEIGHT) / 2;
        this.blit(matrixStack, relX, relY, 0, 0, WIDTH, HEIGHT);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public static void open() {
        Minecraft.getInstance().setScreen(new WatchScreen());
    }
}
