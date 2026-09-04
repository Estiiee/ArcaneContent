package com.estie.arcanecontent.init;

import com.estie.arcanecontent.ArcaneContent;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ArcaneEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ArcaneContent.MODID);
    
    /*
    public static final RegistryObject<EntityType<TestBoss>> TEST_BOSS =
            ENTITY_TYPES.register("test_boss",
                    () -> EntityType.Builder.of(TestBoss::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.95F)
                            .build(ArcaneContent.MODID + ":test_boss"));
                            
     */
    
    public static void onRegisterAttributes(EntityAttributeCreationEvent event) {
        //event.put(TEST_BOSS.get(), TestBoss.createAttributes().build());
    }
    
}