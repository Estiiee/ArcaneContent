package com.estie.arcanecontent.init;

import com.estie.arcanecontent.common.item.blooded.BloodedDragonType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public class ArcaneArmorMaterials {
    
    public static final ArmorMaterial BLOODED_ARMOR_FIRE = new ArcaneArmorMaterial(
            "blooded_armor_fire",
            25,
            new int[]{5, 7, 9, 5},
            35,
            () -> SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(Items.SHULKER_SHELL),
            3.0F,
            0F
    );
    
    public static final ArmorMaterial BLOODED_ARMOR_ICE = new ArcaneArmorMaterial(
            "blooded_armor_ice",
            25,
            new int[]{5, 7, 9, 5},
            35,
            () -> SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(Items.SHULKER_SHELL),
            3.0F,
            0F
    );
    
    public static final ArmorMaterial BLOODED_ARMOR_LIGHTNING = new ArcaneArmorMaterial(
            "blooded_armor_lightning",
            25,
            new int[]{5, 7, 9, 5},
            35,
            () -> SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(Items.SHULKER_SHELL),
            3.0F,
            0F
    );
    
    public static ArmorMaterial getBloodedArmorFromElement(BloodedDragonType.DragonElement element) {
        return switch (element) {
            case FIRE -> BLOODED_ARMOR_FIRE;
            case ICE -> BLOODED_ARMOR_ICE;
            case LIGHTNING -> BLOODED_ARMOR_LIGHTNING;
        };
    }
    
    public record ArcaneArmorMaterial(
            String name,
            int durabilityMultiplier,
            int[] slotProtections,
            int enchantmentValue,
            Supplier<SoundEvent> equipSoundSupplier,
            Supplier<Ingredient> repairIngredient,
            float toughness,
            float knockbackResistance
    ) implements ArmorMaterial {
        
        private static final int[] BASE_DURABILITY = {13, 15, 16, 11};
        
        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return BASE_DURABILITY[type.getSlot().getIndex()] * durabilityMultiplier;
        }
        
        @Override
        public int getDefenseForType(ArmorItem.Type type)
        {
            return slotProtections[type.getSlot().getIndex()];
        }
        
        @Override
        public int getEnchantmentValue()
        {
            return enchantmentValue;
        }
        
        @Override
        public SoundEvent getEquipSound()
        {
            return equipSoundSupplier.get();
        }
        
        @Override
        public Ingredient getRepairIngredient()
        {
            return repairIngredient.get();
        }
        
        @Override
        public String getName()
        {
            return name;
        }
        
        @Override
        public float getToughness()
        {
            return toughness;
        }
        
        @Override
        public float getKnockbackResistance()
        {
            return knockbackResistance;
        }
    }
    
}
