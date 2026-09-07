package com.estie.arcanecontent.datagen.tags;

import com.estie.arcanecontent.ArcaneContent;
import com.estie.arcanecontent.init.ArcaneTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ArcaneEntityTypeTagsProvider extends EntityTypeTagsProvider {
    public ArcaneEntityTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ArcaneContent.MODID, existingFileHelper);
    }
    
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ArcaneTags.DISABLES_MELEE_BLOCK)
                .add(EntityType.VINDICATOR, EntityType.WARDEN);
        
        tag(ArcaneTags.FRENZY_FIRE_IMMUNE)
                .add(EntityType.BLAZE, EntityType.MAGMA_CUBE);
    }
}