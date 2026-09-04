package com.estie;

import com.github.alexmodguy.alexscaves.server.entity.ACEntityRegistry;
import com.github.alexmodguy.alexscaves.server.entity.item.NuclearBombEntity;
import com.oblivioussp.spartanweaponry.api.ModToolActions;
import net.miauczel.legendary_monsters.effect.ModEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;

//it is common and expected for this class to crash when loaded, this mod is expected to run with ArcaneTweaks
//and running it independently is meant only for temporary testing purposes
public class CompatUtils {
    
    public static class AlexsCaves {
        public static void createNuke(ServerLevel level, Player player) {
            if (!ModList.get().isLoaded("alexscaves")) return;
           
            NuclearBombEntity nuke = new NuclearBombEntity(ACEntityRegistry.NUCLEAR_BOMB.get(), level);
            nuke.setPos(player.getPosition(1));
            level.addFreshEntity(nuke);
        }
    }
    
    public static class SpartanWeaponry {
        public static boolean canMeleeBlock(ItemStack stack) {
            return ModList.get().isLoaded("spartanweaponry") && stack.getItem().canPerformAction(stack, ModToolActions.MELEE_BLOCK);
        }
    }
    
    public static class LegendaryMonsters {
        public static MobEffect getSoulFracture() {
            return ModList.get().isLoaded("legendary_monsters") ? ModEffects.SOUL_FRACTURE.get() : null;
        }
    }
}