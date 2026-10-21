package com.estie.arcanecontent.init;

import com.estie.arcanecontent.ArcaneContent;
import com.estie.arcanecontent.common.block.BlockOfSilly;
import com.estie.arcanecontent.common.block.FrenzyFireBlock;
import com.estie.arcanecontent.common.block.regions_unexplored.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ArcaneBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ArcaneContent.MODID);
    public static final DeferredRegister<Block> BLOCKS_RU = DeferredRegister.create(ForgeRegistries.BLOCKS, "regions_unexplored");
    
    public static final RegistryObject<BlockOfSilly> BLOCK_OF_SILLY = BLOCKS.register("block_of_silly",
            () -> new BlockOfSilly(BlockBehaviour.Properties.copy(Blocks.STONE).sound(ArcaneSounds.BLOCK_OF_SILLY.get())));
    
    public static final RegistryObject<FrenzyFireBlock> FRENZY_FIRE_BLOCK = BLOCKS.register("frenzy_fire_block",
            () -> new FrenzyFireBlock(BlockBehaviour.Properties.copy(Blocks.FIRE), 4));
    
    public static final RegistryObject<LeavesBlock> FROSTBITTEN_LEAVES = BLOCKS.register("frostbitten_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_LEAVES)));
    
    
    //Regions Unexplored
    public static RegistryObject<Block> ARGILLITE_GRASS_BLOCK = BLOCKS_RU.register("argillite_grass_block",
            () -> new ArgilliteGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GRASS).sound(SoundType.STONE).randomTicks().strength(1.5F, 6.0F).requiresCorrectToolForDrops()));
    
    public static RegistryObject<Block> ARGILLITE = BLOCKS_RU.register("argillite",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.TERRACOTTA_ORANGE).sound(SoundType.CALCITE)));
    
    public static RegistryObject<Block> BLADED_GRASS = BLOCKS_RU.register("bladed_grass",
            () -> new RuPlantBlock(BlockBehaviour.Properties.copy(Blocks.GRASS).sound(SoundType.AZALEA)));
    
    public static RegistryObject<Block> BLADED_TALL_GRASS = BLOCKS_RU.register("bladed_tall_grass",
            () -> new RuDoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS).sound(SoundType.AZALEA)));
    
    public static RegistryObject<Block> CORPSE_FLOWER = BLOCKS_RU.register("corpse_flower",
            () -> new CorpseFlowerBlock(BlockBehaviour.Properties.copy(Blocks.SUNFLOWER).sound(SoundType.FLOWERING_AZALEA)));
    
    public static RegistryObject<Block> DUSKMELON = BLOCKS_RU.register("duskmelon",
            () -> new DuskmelonBlock(BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.AZALEA)));
    
    public static RegistryObject<Block> DUSKTRAP = BLOCKS_RU.register("dusktrap",
            () -> new DuskTrapBlock(DuskTrapBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).noCollission().instabreak().sound(SoundType.TWISTING_VINES)));
    
    public static RegistryObject<Block> DROPLEAF_PLANT = BLOCKS_RU.register("dropleaf_plant",
            () -> new DropleafPlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).noCollission().instabreak().sound(SoundType.WEEPING_VINES).offsetType(BlockBehaviour.OffsetType.XZ)));
    
    public static RegistryObject<Block> DROPLEAF = BLOCKS_RU.register("dropleaf",
            () -> new DropleafBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).randomTicks().noCollission().instabreak().sound(SoundType.WEEPING_VINES).offsetType(BlockBehaviour.OffsetType.XZ).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).lightLevel((s) -> 14)));
}