package zone.bears.blockagotchi;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class WatchTile extends TileEntity implements ITickableTileEntity {

    public WatchTile()
    {
        super(Registration.watch_tile.get());
    }


    @Override
    public void tick() {

    }

    @Override
    public CompoundNBT save(CompoundNBT p_189515_1_) {
        return super.save(p_189515_1_);
    }

    @Override
    public void load(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
        super.load(p_230337_1_, p_230337_2_);
    }

}
