package com.estie.arcanecontent.init;

import com.estie.arcanecontent.ArcaneContent;
import com.estie.arcanecontent.common.item.blooded.BloodedDragonType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ArcaneCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ArcaneContent.MODID);
    
    public static final RegistryObject<CreativeModeTab> ARCANE = TABS.register("arcane",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + ArcaneContent.MODID + ".arcane"))
                    .icon(() -> new ItemStack(
                            ArcaneItems.BLOODED_ARMOR_SETS.get(BloodedDragonType.FIRE_RED).chestplate().get()))
                    .displayItems((parameters, output) -> {
                        for (ArcaneItems.BloodedArmorSet set : ArcaneItems.BLOODED_ARMOR_SETS.values()) {
                            output.accept(set.helmet().get());
                            output.accept(set.chestplate().get());
                            output.accept(set.leggings().get());
                            output.accept(set.boots().get());
                        }
                    })
                    .build());
}