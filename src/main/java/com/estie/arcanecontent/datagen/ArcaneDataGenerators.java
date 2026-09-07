package com.estie.arcanecontent.datagen;

import com.estie.arcanecontent.ArcaneContent;
import com.estie.arcanecontent.datagen.loot.ArcaneLootTableProvider;
import com.estie.arcanecontent.datagen.tags.ArcaneBlockTagsProvider;
import com.estie.arcanecontent.datagen.tags.ArcaneDamageTypeTagsProvider;
import com.estie.arcanecontent.datagen.tags.ArcaneEntityTypeTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = ArcaneContent.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ArcaneDataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        boolean client = event.includeClient();
        boolean server = event.includeServer();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        gen.addProvider(server, new ArcaneBlockTagsProvider(output, lookupProvider, existingFileHelper));
        //gen.addProvider(server, new ArcaneItemTagsProvider(output, lookupProvider, blockTags.contentsGetter(), existingFileHelper));
        gen.addProvider(server, new ArcaneEntityTypeTagsProvider(output, lookupProvider, existingFileHelper));
        
        gen.addProvider(server, new ArcaneLootTableProvider(output));
        
        DatapackBuiltinEntriesProvider registryProvider = new ArcaneRegistryProvider(output, lookupProvider);
        CompletableFuture<HolderLookup.Provider> updatedLookupProvider = registryProvider.getRegistryProvider();
        gen.addProvider(event.includeServer(), registryProvider);
        
        gen.addProvider(event.includeServer(), new ArcaneDamageTypeTagsProvider(output, updatedLookupProvider, existingFileHelper));
        
        
        //gen.addProvider(client, new ArcaneBlockStateProvider(output, ArcaneContent.MODID, existingFileHelper));
        //gen.addProvider(client, new ArcaneItemModelProvider(output, ArcaneContent.MODID, existingFileHelper));
        //gen.addProvider(client, new ArcaneLanguageProvider(output, ArcaneContent.MODID, "en_us"));
    }
}