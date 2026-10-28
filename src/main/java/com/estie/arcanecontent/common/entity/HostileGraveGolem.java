package com.estie.arcanecontent.common.entity;

import com.Polarice3.Goety.common.entities.ally.undead.GraveGolem;
import com.Polarice3.Goety.common.entities.neutral.Owned;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;

public class HostileGraveGolem extends GraveGolem {
    
    public HostileGraveGolem(EntityType<? extends Owned> type, Level worldIn) {
        super(type, worldIn);
        this.setHostile(true);
    }
    
    public static AttributeSupplier.Builder createAttributes() {
        return GraveGolem.setCustomAttributes();
    }
}