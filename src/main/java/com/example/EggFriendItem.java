package com.example;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class EggFriendItem extends Item implements ExtendedScreenHandlerFactory {
    public EggFriendItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient)
        {
            user.openHandledScreen(this);
        }

        return super.use(world, user, hand);
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack working = super.getDefaultStack();

        NbtCompound status = new NbtCompound();
        status.putLong("age", System.currentTimeMillis());

        working.setNbt(status);

        return working;
    }

    LibEggFriend ef;

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);

        if (!world.isClient) {
            if(ef == null)
            {
                if(stack.hasNbt() && stack.getNbt().contains("egg/currentState"))
                {
                    try {
                        ef = new LibEggFriend(stack.getNbt());
                    } catch (Exception e) {
                        // TODO this is not forward compatible with egg api changes
                        throw new RuntimeException(e);
                    }
                }
                else
                {
                    ef = new LibEggFriend();
                    try {
                        stack.setNbt(ef.saveData());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }


            if(ef.shouldSimulate())
            {
                ef.simulate();
                try {
                    stack.setNbt(ef.saveData());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if(entity.isPlayer() && ((PlayerEntity) entity).currentScreenHandler instanceof EggScreenHandler)
                {
                    PacketByteBuf toSend = PacketByteBufs.create();
                    toSend.writeNbt(stack.getNbt());
                    ServerPlayNetworking.send((ServerPlayerEntity) entity, Identifier.of("modid", "pikachu.png"), toSend);
                }

            }

//            NbtCompound existing = stack.getNbt();
//            long age = existing.getLong("age");
//
//            if (System.currentTimeMillis() - age > 3000) {
//
//                world.playSound(
//                        null, // Player - if non-null, will play sound for every nearby player *except* the specified player
//                        entity.getBlockPos(), // The position of where the sound will come from
//                        SoundEvents.BLOCK_ANVIL_LAND, // The sound that will play, in this case, the sound the anvil plays when it lands.
//                        SoundCategory.BLOCKS, // This determines which of the volume sliders affect this sound
//                        1f, //Volume multiplier, 1 is normal, 0.5 is half volume, etc
//                        1f // Pitch multiplier, 1 is normal, 0.5 is half pitch, etc
//                );
//
////                ExampleMod.LOGGER.info("age increment");
//                existing.putLong("age", System.currentTimeMillis());
//                stack.setNbt(existing);
//
//                if(entity.isPlayer())
//                {
//                    if (((PlayerEntity) entity).currentScreenHandler instanceof EggScreenHandler)
//                    {
//                        ServerPlayNetworking.send((ServerPlayerEntity) entity, Identifier.of("modid", "pikachu.png"), PacketByteBufs.empty());
//                    }
//                    else
//                    {
//                        ExampleMod.LOGGER.info("screen not open");
//                    }
//                }
//
//            }



        }
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        if(player.getMainHandStack().getItem() instanceof EggFriendItem)
        {
            buf.writeNbt(player.getMainHandStack().getNbt());
        }
        else if (player.getOffHandStack().getItem() instanceof EggFriendItem)
        {
            buf.writeNbt(player.getOffHandStack().getNbt());
        }
    }

    @Override
    public Text getDisplayName() {
        return Text.of("Bears");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new EggScreenHandler(syncId, playerInventory, new SimpleInventory(9));
    }
}
