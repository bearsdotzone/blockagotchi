package zone.bears.blockagotchi;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.audio.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class WatchBlock extends Block {

    public WatchBlock()
    {
        super(AbstractBlock.Properties.of(Material.WOOD).harvestLevel(0).harvestTool(ToolType.PICKAXE).strength(1.0f));
    }

    @Override
    public void onPlace(BlockState p_220082_1_, World p_220082_2_, BlockPos p_220082_3_, BlockState p_220082_4_, boolean p_220082_5_) {
        super.onPlace(p_220082_1_, p_220082_2_, p_220082_3_, p_220082_4_, p_220082_5_);
//        p_220082_2_.playSound(null, p_220082_3_, SoundEvents.NOTE_BLOCK_GUITAR, SoundCategory.NEUTRAL, 0.5f, 0.5f);

    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {

        return super.createTileEntity(state, world);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
