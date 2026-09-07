package com.estie.arcanecontent.datagen.tags;

import com.estie.arcanecontent.ArcaneContent;
import com.estie.arcanecontent.init.ArcaneTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ArcaneBlockTagsProvider extends BlockTagsProvider {
    public ArcaneBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ArcaneContent.MODID, existingFileHelper);
    }
    
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ArcaneTags.HYDROTHERMAL_VENT_SURFACE).add(Blocks.GRASS_BLOCK);
    }
}