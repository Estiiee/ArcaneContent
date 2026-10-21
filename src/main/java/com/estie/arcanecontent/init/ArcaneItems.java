package com.estie.arcanecontent.init;

import com.estie.arcanecontent.ArcaneContent;
import com.estie.arcanecontent.common.item.BlockItemOfSilly;
import com.estie.arcanecontent.common.item.blooded.BloodedDragonType;
import com.estie.arcanecontent.common.item.blooded.ItemBloodedArmor;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.EnumMap;
import java.util.Map;

public class ArcaneItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ArcaneContent.MODID);
    public static final DeferredRegister<Item> ITEMS_RU = DeferredRegister.create(ForgeRegistries.ITEMS, "regions_unexplored");
    
    public static final Map<BloodedDragonType, BloodedArmorSet> BLOODED_ARMOR_SETS = new EnumMap<>(
            BloodedDragonType.class);
    
    public record BloodedArmorSet(
            RegistryObject<Item> helmet, RegistryObject<Item> chestplate,
            RegistryObject<Item> leggings, RegistryObject<Item> boots) {
    }
    
    public static final RegistryObject<BlockItemOfSilly> BLOCK_OF_SILLY = ITEMS.register("block_of_silly", () -> new BlockItemOfSilly(ArcaneBlocks.BLOCK_OF_SILLY.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> FROSTBITTEN_LEAVES = ITEMS.register("frostbitten_leaves", () -> new BlockItem(ArcaneBlocks.FROSTBITTEN_LEAVES.get(), new Item.Properties()));
    
    //Regions Unexplored
    public static final RegistryObject<BlockItem> ARGILLITE_GRASS_BLOCK = ITEMS_RU.register(
            "argillite_grass_block", () -> new BlockItem(ArcaneBlocks.ARGILLITE_GRASS_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> ARGILLITE = ITEMS_RU.register(
            "argillite", () -> new BlockItem(ArcaneBlocks.ARGILLITE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> BLADED_GRASS = ITEMS_RU.register(
            "bladed_grass", () -> new BlockItem(ArcaneBlocks.BLADED_GRASS.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> BLADED_TALL_GRASS = ITEMS_RU.register(
            "bladed_tall_grass", () -> new BlockItem(ArcaneBlocks.BLADED_TALL_GRASS.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> CORPSE_FLOWER = ITEMS_RU.register(
            "corpse_flower", () -> new BlockItem(ArcaneBlocks.CORPSE_FLOWER.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> DUSKTRAP = ITEMS_RU.register(
            "dusktrap", () -> new BlockItem(ArcaneBlocks.DUSKTRAP.get(), new Item.Properties()));
    
    
    static {
        for (BloodedDragonType type : BloodedDragonType.values()) {
            String prefix = "blooded_" + type.getId();
            RegistryObject<Item> helmet = ITEMS.register(prefix + "_helmet",
                    () -> new ItemBloodedArmor(type, ArmorItem.Type.HELMET));
            RegistryObject<Item> chestplate = ITEMS.register(prefix + "_chestplate",
                    () -> new ItemBloodedArmor(type, ArmorItem.Type.CHESTPLATE));
            RegistryObject<Item> leggings = ITEMS.register(prefix + "_leggings",
                    () -> new ItemBloodedArmor(type, ArmorItem.Type.LEGGINGS));
            RegistryObject<Item> boots = ITEMS.register(prefix + "_boots",
                    () -> new ItemBloodedArmor(type, ArmorItem.Type.BOOTS));
            BLOODED_ARMOR_SETS.put(type, new BloodedArmorSet(helmet, chestplate, leggings, boots));
        }
    }
}