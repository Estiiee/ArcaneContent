package com.estie.arcanecontent.init;

import com.estie.arcanecontent.ArcaneContent;
import com.estie.arcanecontent.world.level.feature.DropleafFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ArcaneFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, ArcaneContent.MODID);
    public static final DeferredRegister<Feature<?>> FEATURES_RU = DeferredRegister.create(ForgeRegistries.FEATURES, "regions_unexplored");
    
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> DROPLEAF =
            FEATURES_RU.register("dropleaf", () -> new DropleafFeature(NoneFeatureConfiguration.CODEC));
    
}