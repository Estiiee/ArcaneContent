package com.estie.arcanecontent.init;

import com.estie.arcanecontent.ArcaneContent;
import com.estie.arcanecontent.common.item.BlockItemOfSilly;
import com.estie.arcanecontent.common.item.blooded.BloodedDragonType;
import com.estie.arcanecontent.common.item.blooded.ItemBloodedArmor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ArcaneItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ArcaneContent.MODID);
    
    public static final Map<BloodedDragonType, BloodedArmorSet> BLOODED_ARMOR_SETS = new EnumMap<>(
            BloodedDragonType.class);
    
    public record BloodedArmorSet(
            RegistryObject<Item> helmet, RegistryObject<Item> chestplate,
            RegistryObject<Item> leggings, RegistryObject<Item> boots) {
    }
    
    public static final RegistryObject<BlockItemOfSilly> BLOCK_OF_SILLY = ITEMS.register("block_of_silly", () -> new BlockItemOfSilly(ArcaneBlocks.BLOCK_OF_SILLY.get(), new Item.Properties()));
    
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