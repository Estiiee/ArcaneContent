package com.estie.arcanecontent.datagen.providers;

import com.estie.arcanecontent.ArcaneContent;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

public class ArcaneSoundDefinitionsProvider extends SoundDefinitionsProvider {
    public ArcaneSoundDefinitionsProvider(PackOutput output, ExistingFileHelper efh) {
        super(output, ArcaneContent.MODID, efh);
    }
    
    private ResourceLocation modLoc(String s) {
        return new ResourceLocation(ArcaneContent.MODID, s);
    }
    
    @Override
    public void registerSounds() {
        add("dragon_nuke_charge_fire", definition().with(sound(modLoc("dragon_nuke_charge_fire"))));
        add("dragon_nuke_charge_ice", definition().with(sound(modLoc("dragon_nuke_charge_ice"))));
        add("dragon_nuke_charge_lightning", definition().with(sound(modLoc("dragon_nuke_charge_lightning"))));
        add("dragon_nuke_explode", definition().with(sound(modLoc("dragon_nuke_explode"))));
        add("mrkrabs_sadge", definition().with(sound(modLoc("mrkrabs_sadge"))));
        add("melee_block_break", definition().with(sound(modLoc("melee_block_break"))));
        add("melee_parry", definition().with(sound(modLoc("melee_parry"))));
        add("funnehsound", definition().with(sound(modLoc("funnehsound"))));
        add("block_of_silly_mine", definition().with(sound(modLoc("block_of_silly_mine"))));
        add("wither_boss_phase_1", definition().with(sound(modLoc("wither_boss_phase_1"))));
        add("wither_boss_phase_2", definition().with(sound(modLoc("wither_boss_phase_2"))));
        add("wither_boss_phase_3", definition().with(sound(modLoc("wither_boss_phase_3"))));
    }
}
