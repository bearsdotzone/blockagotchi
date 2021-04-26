package zone.bears.blockagotchi;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static zone.bears.blockagotchi.Blockagotchi.MODID;

public class Registration {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
//  private static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MODID);
//  private static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);
//  private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);
//  private static final DeferredRegister<ModDimension> DIMENSIONS = DeferredRegister.create(ForgeRegistries.MOD_DIMENSIONS, MODID);

    public static final RegistryObject<Block> watch_block = BLOCKS.register("watch_block", WatchBlock::new);
    public static final RegistryObject<Item> watch_item = ITEMS.register("watch_item", WatchItem::new);

    public static void init()
    {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
//        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
//        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
//        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
//        DIMENSIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
