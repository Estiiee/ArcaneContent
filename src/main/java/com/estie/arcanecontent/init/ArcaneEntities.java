package com.estie.arcanecontent.init;

import com.estie.arcanecontent.ArcaneContent;
import com.estie.arcanecontent.common.entity.HostileGraveGolem;
import com.estie.arcanecontent.common.entity.HostileGraveMonstrosity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ArcaneEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ArcaneContent.MODID);
    
    public static final RegistryObject<EntityType<HostileGraveGolem>> HOSTILE_GRAVE_GOLEM =
            ModList.get().isLoaded("goety") ? ENTITY_TYPES.register("hostile_grave_golem",
                    () -> EntityType.Builder.of(HostileGraveGolem::new, MobCategory.MONSTER)
                            .sized(2.7F, 3.9F).clientTrackingRange(10).fireImmune()
                            .build(ArcaneContent.MODID + ":hostile_grave_golem")) : null;
    
    public static final RegistryObject<EntityType<HostileGraveMonstrosity>> HOSTILE_GRAVE_MONSTROSITY =
            ModList.get().isLoaded("goety") ? ENTITY_TYPES.register("hostile_grave_monstrosity",
                    () -> EntityType.Builder.of(HostileGraveMonstrosity::new, MobCategory.MONSTER)
                            .sized(4.0F, 5.4F).clientTrackingRange(10).fireImmune()
                            .build(ArcaneContent.MODID + ":hostile_grave_monstrosity")) : null;
    
    public static void onRegisterAttributes(EntityAttributeCreationEvent event) {
        if (HOSTILE_GRAVE_GOLEM != null) event.put(HOSTILE_GRAVE_GOLEM.get(), HostileGraveGolem.createAttributes().build());
        if (HOSTILE_GRAVE_MONSTROSITY != null) event.put(HOSTILE_GRAVE_MONSTROSITY.get(), HostileGraveGolem.createAttributes().build());
    }
}