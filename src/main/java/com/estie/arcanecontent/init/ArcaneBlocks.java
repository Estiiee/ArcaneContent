package com.estie.arcanecontent.init;

import com.estie.arcanecontent.ArcaneContent;
import com.estie.arcanecontent.common.block.BlockOfSilly;
import com.estie.arcanecontent.common.block.FrenzyFireBlock;
import com.estie.arcanecontent.common.block.regions_unexplored.*;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

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
    
    public static RegistryObject<Block> BRIMSPROUT = BLOCKS_RU.register("brimsprout",
            () -> new BrimPlantBlock(BlockBehaviour.Properties.of().replaceable().noCollission().instabreak().sound(SoundType.SCULK).offsetType(BlockBehaviour.OffsetType.XYZ).lightLevel(s -> 5).emissiveRendering((s, l, p) -> true)));
   
    public static final WoodSet BLACKWOOD = new WoodSet("blackwood", SoundType.NETHER_WOOD,
            MapColor.TERRACOTTA_BLACK, MapColor.TERRACOTTA_BROWN, false, UnaryOperator.identity());
    public static final WoodSet BRIMWOOD = new WoodSet("brimwood", SoundType.NETHER_WOOD,
            MapColor.COLOR_BROWN, MapColor.COLOR_ORANGE, true, ArcaneBlocks::glow);
    
    public static final RegistryObject<Block> BLACKWOOD_LEAVES = leaves("blackwood_leaves", MapColor.TERRACOTTA_GREEN);
    public static final RegistryObject<Block> BRIMWOOD_LEAVES  = leaves("brimwood_leaves", MapColor.COLOR_BROWN);
    public static final RegistryObject<Block> BLACKWOOD_SHRUB  = shrub("blackwood_shrub");
    
    public static final RegistryObject<Block> BRIMWOOD_LOG_MAGMA = reg("brimwood_log_magma",
            () -> new MagmaLogBlock(BlockBehaviour.Properties.copy(Blocks.CRIMSON_STEM)
                    .mapColor(MapColor.COLOR_ORANGE)
                    .lightLevel(s -> 5)
                    .hasPostProcess((s, l, p) -> true)
                    .emissiveRendering((s, l, p) -> true)
            ));
            
    
    private static RegistryObject<Block> leaves(String id, MapColor c) {
        return reg(id, () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).mapColor(c)) {
            @Override
            public boolean isRandomlyTicking(BlockState state) {
                return false;
            }
        });
    }
    
    private static RegistryObject<Block> shrub(String id) {
        return reg(id, () -> new BushBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)
                .sound(SoundType.AZALEA).offsetType(BlockBehaviour.OffsetType.XZ)));
    }
    
    static RegistryObject<Block> reg(String id, Supplier<Block> s) {
        RegistryObject<Block> b = ArcaneBlocks.BLOCKS_RU.register(id, s);
        ArcaneItems.ITEMS_RU.register(id, () -> new BlockItem(b.get(), new Item.Properties()));
        return b;
    }
    
    static RegistryObject<Block> regNoItem(String id, Supplier<Block> s) {
        return ArcaneBlocks.BLOCKS_RU.register(id, s);
    }
    
    static BlockBehaviour.Properties glow(BlockBehaviour.Properties p) {
        return p.lightLevel(s -> 5).emissiveRendering((s, l, pos) -> true);
    }
    
    public static class WoodSet {
        public final BlockSetType setType;
        public final WoodType woodType;
        public final RegistryObject<Block> log, wood, strippedLog, strippedWood, planks, stairs, slab,
                fence, fenceGate, door, trapdoor, pressurePlate, button,
                sign, wallSign, hangingSign, wallHangingSign;
        
        WoodSet(String n, SoundType sound, MapColor plankColor, MapColor logColor, boolean fireproof, UnaryOperator<BlockBehaviour.Properties> fx) {
            Block planksBase = fireproof ? Blocks.CRIMSON_PLANKS : Blocks.OAK_PLANKS;
            Block logBase    = fireproof ? Blocks.CRIMSON_STEM   : Blocks.OAK_LOG;
            
            setType  = BlockSetType.register(new BlockSetType("regions_unexplored:" + n));
            woodType = WoodType.register(new WoodType("regions_unexplored:" + n, setType));
            
            Supplier<BlockBehaviour.Properties> plankProps =
                    () -> fx.apply(BlockBehaviour.Properties.copy(planksBase).mapColor(plankColor).sound(sound));
            
            planks = reg(n + "_planks", () -> new Block(plankProps.get()));
            stairs = reg(n + "_stairs", () -> new StairBlock(() -> planks.get().defaultBlockState(), plankProps.get()));
            slab   = reg(n + "_slab",   () -> new SlabBlock(plankProps.get()));
            fence  = reg(n + "_fence",  () -> new FenceBlock(plankProps.get()));
            fenceGate = reg(n + "_fence_gate", () -> new FenceGateBlock(plankProps.get(), woodType));
            door = reg(n + "_door", () -> new DoorBlock(
                    fx.apply(BlockBehaviour.Properties.copy(fireproof ? Blocks.CRIMSON_DOOR : Blocks.OAK_DOOR)
                            .mapColor(plankColor)), setType));
            trapdoor = reg(n + "_trapdoor", () -> new TrapDoorBlock(
                    fx.apply(BlockBehaviour.Properties.copy(fireproof ? Blocks.CRIMSON_TRAPDOOR : Blocks.OAK_TRAPDOOR)
                            .mapColor(plankColor)), setType));
            pressurePlate = reg(n + "_pressure_plate", () -> new PressurePlateBlock(
                    PressurePlateBlock.Sensitivity.EVERYTHING,
                    fx.apply(BlockBehaviour.Properties.copy(fireproof ? Blocks.CRIMSON_PRESSURE_PLATE : Blocks.OAK_PRESSURE_PLATE)
                            .mapColor(plankColor)), setType));
            button = reg(n + "_button", () -> new ButtonBlock(
                    fx.apply(BlockBehaviour.Properties.copy(fireproof ? Blocks.CRIMSON_BUTTON : Blocks.OAK_BUTTON)),
                    setType, 30, true));
            
            log = reg(n + "_log", () -> new StrippableLog(this, fx.apply(logProps(logBase, plankColor, logColor, sound))));
            wood = reg(n + "_wood", () -> new StrippableLog(this, fx.apply(BlockBehaviour.Properties.copy(logBase).mapColor(logColor).sound(sound))));
            strippedLog = reg("stripped_" + n + "_log", () -> new RotatedPillarBlock(fx.apply(logProps(logBase, plankColor, plankColor, sound))));
            strippedWood = reg("stripped_" + n + "_wood", () -> new RotatedPillarBlock(fx.apply(BlockBehaviour.Properties.copy(logBase).mapColor(plankColor).sound(sound))));
            
            // Signs: blocks have no item of their own, one shared SignItem
            RegistryObject<Block> standing = regNoItem(n + "_sign", () -> new StandingSignBlock(
                    fx.apply(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN).mapColor(plankColor).sound(sound)), woodType));
            RegistryObject<Block> wall = regNoItem(n + "_wall_sign", () -> new WallSignBlock(
                    fx.apply(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN).mapColor(plankColor).sound(sound).dropsLike(standing.get())), woodType));
            RegistryObject<Block> hanging = regNoItem(n + "_hanging_sign", () -> new CeilingHangingSignBlock(
                    fx.apply(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN).mapColor(plankColor).sound(sound)), woodType));
            RegistryObject<Block> wallHanging = regNoItem(n + "_wall_hanging_sign", () -> new WallHangingSignBlock(
                    fx.apply(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN).mapColor(plankColor).sound(sound).dropsLike(hanging.get())), woodType));
            sign = standing; wallSign = wall; hangingSign = hanging; wallHangingSign = wallHanging;
            ArcaneItems.ITEMS_RU.register(n + "_sign", () -> new SignItem(new Item.Properties().stacksTo(16), standing.get(), wall.get()));
            ArcaneItems.ITEMS_RU.register(n + "_hanging_sign", () -> new HangingSignItem(hanging.get(), wallHanging.get(), new Item.Properties().stacksTo(16)));
        }
        
        private static BlockBehaviour.Properties logProps(Block base, MapColor top, MapColor side, SoundType sound) {
            return BlockBehaviour.Properties.copy(base)
                    .mapColor(s -> s.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? top : side)
                    .sound(sound);
        }
    }

    static class StrippableLog extends RotatedPillarBlock {
        private final WoodSet set;
        StrippableLog(WoodSet set, Properties p) { super(p); this.set = set; }
        
        @Override
        public BlockState getToolModifiedState(BlockState state, UseOnContext ctx, ToolAction action, boolean simulate) {
            if (action == ToolActions.AXE_STRIP) {
                Block target = state.is(set.log.get()) ? set.strippedLog.get() : set.strippedWood.get();
                return target.defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            return super.getToolModifiedState(state, ctx, action, simulate);
        }
    }
}