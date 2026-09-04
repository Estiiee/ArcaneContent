package com.estie.arcanecontent.init;

import com.estie.arcanecontent.ArcaneContent;
import com.estie.arcanecontent.common.item.BlockItemOfSilly;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ArcaneItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ArcaneContent.MODID);
    public static final RegistryObject<BlockItemOfSilly> BLOCK_OF_SILLY = ITEMS.register("block_of_silly", () -> new BlockItemOfSilly(ArcaneBlocks.BLOCK_OF_SILLY.get(), new Item.Properties()));
}