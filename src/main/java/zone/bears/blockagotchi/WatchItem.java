package zone.bears.blockagotchi;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.Logging;
import org.apache.logging.log4j.Level;

public class WatchItem extends BlockItem {

    public WatchItem()
    {
        super(Registration.watch_block.get(), new Properties().tab(ItemGroup.TAB_DECORATIONS));
    }

    public WatchItem(Block p_i48527_1_, Properties p_i48527_2_) {
        super(p_i48527_1_, p_i48527_2_);
    }

    // Int is inventory slot what this item stack is in
    // bool is whether or not the item is currently in hand
    // bool is also true when you're hovering over first inv slot and item is in offhand. weird stuff

    @Override
    public void inventoryTick(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        super.inventoryTick(p_77663_1_, p_77663_2_, p_77663_3_, p_77663_4_, p_77663_5_);
        if(p_77663_2_.isClientSide())
        {
//            Blockagotchi.LOGGER.log(Level.DEBUG, p_77663_4_ + " " + p_77663_5_);
        }
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }

    @Override
    public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {
        // TODO make this properly networked
        WatchScreen.open();
        return super.use(p_77659_1_, p_77659_2_, p_77659_3_);
    }

    @Override
    public ActionResultType place(BlockItemUseContext p_195942_1_) {
        if(!p_195942_1_.getPlayer().isCrouching())
        {
            return ActionResultType.PASS;
        }
        return super.place(p_195942_1_);
    }
}
