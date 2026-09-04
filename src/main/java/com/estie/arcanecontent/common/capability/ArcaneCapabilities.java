package com.estie.arcanecontent.common.capability;

import com.estie.arcanecontent.common.capability.bossminion.BossMinionData;
import com.estie.arcanecontent.common.capability.foodbonuses.FoodBonusesData;
import com.estie.arcanecontent.common.capability.parry.ParryData;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class ArcaneCapabilities {
    public static final Capability<BossMinionData> BOSS_MINION =
            CapabilityManager.get(new CapabilityToken<>() {});
    
    public static final Capability<FoodBonusesData> FOOD_BONUSES =
            CapabilityManager.get(new CapabilityToken<>() {});
    
    public static final Capability<ParryData> PARRY =
            CapabilityManager.get(new CapabilityToken<>() {});
}