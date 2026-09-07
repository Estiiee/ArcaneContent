package com.estie.arcanecontent.datagen.providers.loot;

import com.estie.arcanecontent.init.ArcaneBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ArcaneBlockLootSubProvider extends BlockLootSubProvider {
    protected ArcaneBlockLootSubProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }
    
    @Override
    protected void generate() {
        dropSelf(ArcaneBlocks.BLOCK_OF_SILLY.get());
        add(ArcaneBlocks.FRENZY_FIRE_BLOCK.get(), noDrop());
    }
    
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ArcaneBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}