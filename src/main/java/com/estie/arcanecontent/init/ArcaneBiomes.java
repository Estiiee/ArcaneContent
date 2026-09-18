package com.estie.arcanecontent.init;

import com.estie.arcanecontent.ArcaneContent;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

import java.util.Set;

public final class ArcaneBiomes {
    private ArcaneBiomes() {}
    
    public static final ResourceKey<Biome> HOWLING_GLACIERS = key("howling_glaciers");
    
    public static final Set<ResourceKey<Biome>> BIOMES = Set.of(
            HOWLING_GLACIERS
    );
    
    //who decided to make adding non-fully-custom biomes so cancer
    public static void bootstrap(BootstapContext<Biome> context) {
        /*
        context.register(
                HOWLING_GLACIERS,
                OverworldBiomes.beach(context.lookup(Registries.PLACED_FEATURE),
                        context.lookup(Registries.CONFIGURED_CARVER),
                        true, true)
        );
         */
    }
    
    private static ResourceKey<Biome> key(String name) {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(ArcaneContent.MODID, name));
    }
}