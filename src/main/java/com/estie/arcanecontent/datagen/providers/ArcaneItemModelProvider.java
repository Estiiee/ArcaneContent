package com.estie.arcanecontent.datagen.providers;

import com.estie.arcanecontent.ArcaneContent;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ArcaneItemModelProvider extends ItemModelProvider {
    public ArcaneItemModelProvider(PackOutput output, ExistingFileHelper efh) {
        super(output, ArcaneContent.MODID, efh);
    }
    
    @Override
    protected void registerModels() {
        withExistingParent("block_of_silly", modLoc("block/block_of_silly"));
    }
}
