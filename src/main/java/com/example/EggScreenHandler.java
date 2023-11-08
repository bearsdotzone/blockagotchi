package com.example;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;

public class EggScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    public LibEggFriend ef;
    public EggScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory, new SimpleInventory(9));

        NbtCompound readIn = buf.readNbt();
        if(readIn != null)
        {
            ExampleMod.LOGGER.info("bears");
            try {
                ef = new LibEggFriend(readIn);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }

    public EggScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ExampleMod.EGG_SCREEN_HANDLER, syncId);
        checkSize(inventory, 9);
        this.inventory = inventory;
        //some inventories do custom logic when a player opens it.
        inventory.onOpen(playerInventory.player);

        ef = new LibEggFriend();

//        //This will place the slot in the correct locations for a 3x3 Grid. The slots exist on both server and client!
//        //This will not render the background of the slots however, this is the Screens job
        int m;
        int l;
//        //Our inventory
//        for (m = 0; m < 3; ++m) {
//            for (l = 0; l < 3; ++l) {
//                this.addSlot(new Slot(inventory, l + m * 3, 62 + l * 18, 17 + m * 18));
//            }
//        }

        //The player inventory
//        for (m = 0; m < 3; ++m) {
//            for (l = 0; l < 9; ++l) {
//                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
//            }
//        }
//        //The player Hotbar
//        for (m = 0; m < 9; ++m) {
//            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
//        }

    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
