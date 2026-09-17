package com.estie.arcanecontent.init;

import com.estie.arcanecontent.ArcaneContent;
import com.estie.arcanecontent.common.block.BlockOfSilly;
import com.estie.arcanecontent.common.block.FrenzyFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ArcaneBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ArcaneContent.MODID);
    
    public static final RegistryObject<BlockOfSilly> BLOCK_OF_SILLY = BLOCKS.register("block_of_silly",
            () -> new BlockOfSilly(BlockBehaviour.Properties.copy(Blocks.STONE).sound(ArcaneSounds.BLOCK_OF_SILLY.get())));
    
    public static final RegistryObject<FrenzyFireBlock> FRENZY_FIRE_BLOCK = BLOCKS.register("frenzy_fire_block",
            () -> new FrenzyFireBlock(BlockBehaviour.Properties.copy(Blocks.FIRE), 4));
    
    public static final RegistryObject<LeavesBlock> FROSTBITTEN_LEAVES = BLOCKS.register("frostbitten_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_LEAVES)));
}