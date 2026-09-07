package com.estie.arcanecontent.init;

import com.estie.arcanecontent.ArcaneContent;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public final class ArcaneDamageSources {
    
    public static final ResourceKey<DamageType> VAPORIZED = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(ArcaneContent.MODID, "vaporized"));
    public static final ResourceKey<DamageType> IN_FRENZY_FIRE = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(ArcaneContent.MODID, "in_frenzy_fire"));
    
    public static DamageSource vaporized(Level level) {
        Holder<DamageType> type = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(VAPORIZED);
        
        return new DamageSource(type) {
            
            @Override
            public Component getLocalizedDeathMessage(LivingEntity entity) {
                int i = entity.getRandom().nextInt(4);
               
                return Component.translatable(
                        "death.attack.vaporized." + i,
                        entity.getDisplayName()
                );
            }
        };
    }
    
    public static DamageSource inFrenzyFire(Level level) {
        Holder<DamageType> type = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(IN_FRENZY_FIRE);
        
        return new DamageSource(type) {
            
            @Override
            public Component getLocalizedDeathMessage(LivingEntity entity) {
                int i = entity.getRandom().nextInt(3);
                
                return Component.translatable(
                        "death.attack.in_frenzy_fire." + i,
                        entity.getDisplayName()
                );
            }
        };
    }
    
    public static void bootstrap(BootstapContext<DamageType> context) {
        context.register(IN_FRENZY_FIRE, new DamageType("in_frenzy_fire", DamageScaling.NEVER, 0.1f));
        context.register(VAPORIZED, new DamageType("vaporized", DamageScaling.NEVER, 0.1f));
    }
}