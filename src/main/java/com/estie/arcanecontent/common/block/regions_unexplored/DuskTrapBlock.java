package com.estie.arcanecontent.common.block.regions_unexplored;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.common.PlantType;

public class DuskTrapBlock extends BaseDuskTrapBlock {
    public static final BooleanProperty POWERED;
    private final Sensitivity sensitivity;
    
    public DuskTrapBlock(Sensitivity p_273523_, BlockBehaviour.Properties p_273571_) {
        super(p_273571_);
        this.registerDefaultState(this.stateDefinition.any().setValue(POWERED, false).setValue(HALF, DoubleBlockHalf.LOWER));
        this.sensitivity = p_273523_;
    }
    
    protected int getSignalForState(BlockState p_55270_) {
        return p_55270_.getValue(POWERED) ? 15 : 0;
    }
    
    protected BlockState setSignalForState(BlockState p_55259_, int p_55260_) {
        return p_55259_.setValue(POWERED, p_55260_ > 0);
    }
    
    public PlantType getPlantType(BlockGetter getter, BlockPos pos) {
        return PlantType.CAVE;
    }
    
    protected int getSignalStrength(Level p_55264_, BlockPos p_55265_) {
        Class<? extends Entity> oclass1;
        switch (this.sensitivity) {
            case EVERYTHING -> oclass1 = Entity.class;
            case MOBS -> oclass1 = LivingEntity.class;
            default -> throw new IncompatibleClassChangeError();
        }
        
        return getEntityCount(p_55264_, TOUCH_AABB.move(p_55265_), oclass1) > 0 ? 15 : 0;
    }
    
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_55262_) {
        p_55262_.add(new Property[]{POWERED}).add(HALF);
    }
    
    static {
        POWERED = RuBlockStateProperties.CLOSED;
    }
    
    public static enum Sensitivity {
        EVERYTHING,
        MOBS;
    }
}
