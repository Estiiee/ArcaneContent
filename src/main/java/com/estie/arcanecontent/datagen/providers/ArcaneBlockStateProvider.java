package com.estie.arcanecontent.datagen.providers;

import com.estie.arcanecontent.ArcaneContent;
import com.estie.arcanecontent.init.ArcaneBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ArcaneBlockStateProvider extends BlockStateProvider {
    public ArcaneBlockStateProvider(PackOutput output, ExistingFileHelper efh) {
        super(output, ArcaneContent.MODID, efh);
    }
    
    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ArcaneBlocks.BLOCK_OF_SILLY.get(), models().cubeAll("block_of_silly", modLoc("block/block_of_silly")));
        simpleBlock(ArcaneBlocks.FROSTBITTEN_LEAVES.get(), models().cubeAll("frostbitten_leaves", modLoc("block/frostbitten_leaves")));
    }
}