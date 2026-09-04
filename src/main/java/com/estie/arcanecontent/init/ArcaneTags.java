package com.estie.arcanecontent.init;

import com.estie.arcanecontent.ArcaneContent;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;

public class ArcaneTags {
 
    public static final TagKey<EntityType<?>> DISABLES_MELEE_BLOCK =
            TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(ArcaneContent.MODID, "disables_melee_block"));
    
    public static final TagKey<EntityType<?>> FRENZY_FIRE_IMMUNE =
            TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(ArcaneContent.MODID, "frenzy_fire_immune"));
    
    public static final TagKey<Block> HYDROTHERMAL_VENT_SURFACE =
            TagKey.create(Registries.BLOCK, new ResourceLocation(ArcaneContent.MODID, "hydrothermal_vent_surface"));
}