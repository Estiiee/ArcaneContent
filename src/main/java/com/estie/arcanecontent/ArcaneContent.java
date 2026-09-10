package com.estie.arcanecontent;

import com.estie.arcanecontent.client.render.entity.layer.LayerBloodedArmorOverlay;
import com.estie.arcanecontent.init.*;
import com.mojang.logging.LogUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.function.Supplier;

@Mod(ArcaneContent.MODID)
public class ArcaneContent {
    public static final String MODID = "arcanecontent";
    public static final String NAME = "ArcaneContent";
    public static final Logger LOGGER = LogUtils.getLogger();
    
    public ArcaneContent(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::onRegister);
        forgeEventBus.addListener(this::reloadListener);
        
        ArcaneEntities.ENTITY_TYPES.register(modEventBus);
        modEventBus.addListener(ArcaneEntities::onRegisterAttributes);
        ArcaneSounds.SOUNDS.register(modEventBus);
        ArcaneBlocks.BLOCKS.register(modEventBus);
        ArcaneItems.ITEMS.register(modEventBus);
        ArcaneAttributes.ATTRIBUTES.register(modEventBus);
        ArcaneCreativeTabs.TABS.register(modEventBus);
        
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC, "arcanecontent.toml");
    }
    
    public static boolean tweaksLoaded() {
        return ModList.get().isLoaded("arcanetweaks");
    }
    
    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
        
        });
    }
    
    private void reloadListener(AddReloadListenerEvent event) {
        //event.addListener(new ResourceReloadHandler());
    }
    
    private void onRegister(RegisterEvent event) {
        //event.register(Registries.BIOME_SOURCE, ArcaneBiomeSources::register);
        //event.register(Registries.LOOT_CONDITION_TYPE, helper -> {helper.register(new ResourceLocation(ArcaneTweaks.MODID, "game_stage_per_player"), ModLootConditions.GAME_STAGE_PER_PLAYER);});
    }
    
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        
        @SubscribeEvent
        public static void registerDimensionEffects(RegisterDimensionSpecialEffectsEvent event) {
        
        }
        
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                ItemBlockRenderTypes.setRenderLayer(
                        ArcaneBlocks.FRENZY_FIRE_BLOCK.get(),
                        RenderType.cutout()
                );
            });
        }
        
        @SubscribeEvent
        public static void buildContents(BuildCreativeModeTabContentsEvent event) {
            if (event.getTabKey() != ArcaneCreativeTabs.ARCANE.getKey()) return;
            
            for (RegistryObject<Item> item : ArcaneItems.ITEMS.getEntries()) {
                event.accept(item.get());
            }
        }
        
        @SubscribeEvent
        public static void addLayers(EntityRenderersEvent.AddLayers event) {
            for (EntityType<?> entityType : ForgeRegistries.ENTITY_TYPES.getValues()) {
                addBloodedArmorLayer(event, entityType);
            }
            
            for (String skinName : event.getSkins()) {
                PlayerRenderer renderer = event.getSkin(skinName);
                if (renderer != null) {
                    renderer.addLayer(new LayerBloodedArmorOverlay<>(renderer));
                }
            }
        }
        
        @SuppressWarnings("unchecked")
        private static <T extends LivingEntity> void addBloodedArmorLayer(EntityRenderersEvent.AddLayers event,
                                                                          EntityType<?> entityType) {
            try {
                EntityRenderer<?> renderer = event.getRenderer((EntityType<T>) entityType);
                if (renderer instanceof LivingEntityRenderer<?, ?> livingRenderer
                        && livingRenderer.getModel() instanceof HumanoidModel<?>) {
                    addBloodedArmorLayer(livingRenderer);
                }
            } catch (ClassCastException ignored) {
                // Non-living entity renderers cannot wear armor and are intentionally skipped.
            }
        }
        
        @SuppressWarnings({"rawtypes", "unchecked"})
        private static void addBloodedArmorLayer(LivingEntityRenderer renderer) {
            renderer.addLayer(new LayerBloodedArmorOverlay(renderer));
        }
    }
}