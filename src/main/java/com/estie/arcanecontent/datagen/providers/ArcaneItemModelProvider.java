package com.estie.arcanecontent.datagen.providers;

import com.estie.arcanecontent.ArcaneContent;
import com.estie.arcanecontent.init.ArcaneItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ArcaneItemModelProvider extends ItemModelProvider {
    public ArcaneItemModelProvider(PackOutput output, ExistingFileHelper efh) {
        super(output, ArcaneContent.MODID, efh);
    }
    
    @Override
    protected void registerModels() {
        withExistingParent("block_of_silly", modLoc("block/block_of_silly"));
        withExistingParent("frostbitten_leaves", modLoc("block/frostbitten_leaves"));
        
        for (ArcaneItems.BloodedArmorSet set : ArcaneItems.BLOODED_ARMOR_SETS.values()) {
            registerBloodedPiece(set.helmet().get());
            registerBloodedPiece(set.chestplate().get());
            registerBloodedPiece(set.leggings().get());
            registerBloodedPiece(set.boots().get());
        }
    }
    
    private void registerBloodedPiece(Item item) {
        ResourceLocation texture = new ResourceLocation("iceandfire",
                "item/" + ForgeRegistries.ITEMS.getKey(item).getPath());
        withExistingParent(ForgeRegistries.ITEMS.getKey(item).getPath(), mcLoc("item/generated"))
                .texture("layer0", texture);
    }
}
