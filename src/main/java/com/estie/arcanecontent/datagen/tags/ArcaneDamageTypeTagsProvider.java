package com.estie.arcanecontent.datagen.tags;

import com.estie.arcanecontent.ArcaneContent;
import com.estie.arcanecontent.init.ArcaneDamageSources;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ArcaneDamageTypeTagsProvider extends TagsProvider<DamageType> {
    public ArcaneDamageTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, Registries.DAMAGE_TYPE, lookupProvider, ArcaneContent.MODID, existingFileHelper);
    }
    
    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {
        tag(DamageTypeTags.BYPASSES_ARMOR)
                .add(
                        ArcaneDamageSources.IN_FRENZY_FIRE
                );
        tag(DamageTypeTags.BYPASSES_EFFECTS)
                .add(
                        ArcaneDamageSources.IN_FRENZY_FIRE
                );
        tag(DamageTypeTags.BYPASSES_ENCHANTMENTS)
                .add(
                        ArcaneDamageSources.IN_FRENZY_FIRE
                );
    }
}