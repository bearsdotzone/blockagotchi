package zone.bears.blockagotchi;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
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

    @Override
    public void inventoryTick(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        super.inventoryTick(p_77663_1_, p_77663_2_, p_77663_3_, p_77663_4_, p_77663_5_);
        if(p_77663_2_.isClientSide())
        {
            Blockagotchi.LOGGER.log(Level.DEBUG, p_77663_4_ + " " + p_77663_5_);
        }
    }
}
