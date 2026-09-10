package com.estie.arcanecontent;

import com.estie.arcanecontent.common.item.BlockItemOfSilly;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(modid = ArcaneContent.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    
    // =========================================================
    // Definitions
    // =========================================================
    
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> BLOCK_SILLY_EFFECT_WEIGHT;
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> BLOCK_SILLY_POSITIVE_EFFECTS;
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> BLOCK_SILLY_NEGATIVE_EFFECTS;
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> BLOCK_SILLY_ITEMS;
    
    private static final ForgeConfigSpec.BooleanValue BLOCK_SILLY_ENABLED;

    private static final ForgeConfigSpec.DoubleValue BLOCK_SILLY_CHANCE;
    
    private static final ForgeConfigSpec.IntValue WORLDGEN_TYPE;
    private static final ForgeConfigSpec.IntValue PARRY_WIND_UP;
    private static final ForgeConfigSpec.IntValue PARRY_WINDOW;
    private static final ForgeConfigSpec.IntValue PARRY_COOLDOWN_FAIL;
    private static final ForgeConfigSpec.IntValue PARRY_COOLDOWN_SUCCESS;
    private static final ForgeConfigSpec.IntValue BLOCK_SILLY_GIVE_ITEMS_ROLLS;
    private static final ForgeConfigSpec.IntValue BLOCK_SILLY_STEAL_ITEMS_ROLLS;
    private static final ForgeConfigSpec.IntValue BLOCK_SILLY_VISUALS_RADIUS;
    private static final ForgeConfigSpec.IntValue BLOCK_SILLY_EFFECT_ROLLS;
    
    static {
        BUILDER.push("Parrying");
        BUILDER.comment("Affects Spartan Weaponry melee block trait");
        
        PARRY_WIND_UP = BUILDER.comment("Wind up (in ticks) after blocking before parrying can be performed").defineInRange("parryWindUp", 8, 0, 1000000);
        PARRY_WINDOW = BUILDER.comment("Window (in ticks) in which enemies can be parried").defineInRange("parryWindow", 20, 0, 1000000);
        PARRY_COOLDOWN_FAIL = BUILDER.comment("Cooldown (in ticks) after an unsuccessful parry before another one can be performed. Used to prevent spamming").defineInRange("parryCooldownFail", 30, 0, 1000000);
        PARRY_COOLDOWN_SUCCESS = BUILDER.comment("Cooldown (in ticks) after a successful parry before another one can be performed").defineInRange("parryCooldownSuccess", 200, 0, 1000000);
        
        BUILDER.pop();
        
        BUILDER.push("Block of Silly");
        
        BLOCK_SILLY_ENABLED = BUILDER.comment("Whether the Block of Silly can generate in the world").define("blockSillyEnabled", true);
        BLOCK_SILLY_CHANCE = BUILDER.comment("Chance for the Block of Silly to replace a random ore block during generation (in %)").defineInRange("blockSillyChance", 0.1D, 0.0D, 100.0D);
        BLOCK_SILLY_EFFECT_ROLLS = BUILDER.comment("How many effects are rolled per one use of the block").defineInRange("blockSillyEffectRolls", 1, 1, 50);
        BLOCK_SILLY_VISUALS_RADIUS = BUILDER.comment("Radius (in blocks) in which the Block of Silly can apply certain visual effects").defineInRange("blockSillyVisualsRadius", 15, 0, 100);
        
        BLOCK_SILLY_EFFECT_WEIGHT = BUILDER
                .comment("Weight for each Block of Silly effect. Bigger = more common. 0 = off",
                        "This option automatically lists all available effects on first load",
                        "You can delete this option and restart the game to generate them again (if more appear with updates or just to restore them)")
                .defineListAllowEmpty(
                        List.of("blockSillyEffectWeight"),
                        Arrays.stream(BlockItemOfSilly.SillyEffect.values())
                                .map(e -> e.getId() + "=" + e.getDefaultWeight())
                                .collect(Collectors.toList()),
                        o -> o instanceof String s && s.matches("[a-z_]+=\\d+")
                );
        
        BLOCK_SILLY_POSITIVE_EFFECTS = BUILDER
                .comment("Potion effects applied when the 'positive potion effects' effect is rolled",
                        "Format: effect;amplifier;duration ticks",
                        "Example: minecraft:strength;1;300")
                .defineListAllowEmpty(
                        List.of("blockSillyPositiveEffects"),
                        List.of("minecraft:strength;1;300",
                                "minecraft:haste;2;300",
                                "minecraft:regeneration;5;300"),
                        o -> o instanceof String
                );
        
        BLOCK_SILLY_NEGATIVE_EFFECTS = BUILDER
                .comment("Potion effects applied when the 'negative potion effects' effect is rolled",
                        "Format: effect;amplifier;duration ticks",
                        "Example: minecraft:poison;1;300")
                .defineListAllowEmpty(
                        List.of("blockSillyNegativeEffects"),
                        List.of("minecraft:poison;1;300",
                                "minecraft:wither;2;300",
                                "minecraft:slowness;5;300"),
                        o -> o instanceof String
                );
        
        BLOCK_SILLY_ITEMS = BUILDER
                .comment("List from which items will be rolled when the 'give items' effect is rolled",
                        "Format: item;count",
                        "Example: minecraft:diamond;7")
                .defineListAllowEmpty(
                        List.of("blockSillyItems"),
                        List.of("minecraft:diamond;7",
                                "minecraft:gold_ingot;15",
                                "minecraft:ender_pearl;5"),
                        o -> o instanceof String
                );
        
        BLOCK_SILLY_GIVE_ITEMS_ROLLS = BUILDER.comment("Number of rolls for the 'give items' effect").defineInRange("blockSillyGiveItemRolls", 3, 1, 100);
        BLOCK_SILLY_STEAL_ITEMS_ROLLS = BUILDER.comment("Number of rolls for the 'steal items' effect").defineInRange("blockSillyStealItemRolls", 3, 1, 100);
        
        BUILDER.pop();
        
        BUILDER.push("Misc");
        
        WORLDGEN_TYPE = BUILDER
                .comment("World generation type",
                        "0: Default (vanilla), 1: Arcane, 2: Biome Blend")
                .defineInRange("worldgenType", 1, 0, 2);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
    
    // =========================================================
    // Runtime values
    // =========================================================
  
    public static Map<BlockItemOfSilly.SillyEffect, Integer> blockSillyEffectWeight;

    public static List<String> blockSillyPositiveEffects;
    public static List<String> blockSillyNegativeEffects;
    public static List<String> blockSillyItems;

    public static boolean blockSillyEnabled;

    public static double blockSillyChance;

    public static int worldgenType;
    public static int parryWindUp;
    public static int parryWindow;
    public static int parryCooldownFail;
    public static int parryCooldownSuccess;
    public static int blockSillyGiveItemsRolls;
    public static int blockSillyStealItemsRolls;
    public static int blockSillyVisualsRadius;
    public static int blockSillyEffectRolls;
    
    // =========================================================
    // Sync
    // =========================================================
    
    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        if (event.getConfig().getSpec() != SPEC) return;

        worldgenType = Mth.clamp(WORLDGEN_TYPE.get(), 0, 2);
        parryWindUp = PARRY_WIND_UP.get();
        parryWindow = PARRY_WINDOW.get();
        parryCooldownFail = PARRY_COOLDOWN_FAIL.get();
        parryCooldownSuccess = PARRY_COOLDOWN_SUCCESS.get();
        blockSillyEffectWeight = parseBlockSillyEffectWeight();
        blockSillyPositiveEffects = new ArrayList<>(BLOCK_SILLY_POSITIVE_EFFECTS.get());
        blockSillyNegativeEffects = new ArrayList<>(BLOCK_SILLY_NEGATIVE_EFFECTS.get());
        blockSillyEnabled = BLOCK_SILLY_ENABLED.get();
        blockSillyChance = BLOCK_SILLY_CHANCE.get();
        blockSillyItems = new ArrayList<>(BLOCK_SILLY_ITEMS.get());
        blockSillyGiveItemsRolls = BLOCK_SILLY_GIVE_ITEMS_ROLLS.get();
        blockSillyStealItemsRolls = BLOCK_SILLY_STEAL_ITEMS_ROLLS.get();
        blockSillyVisualsRadius =  BLOCK_SILLY_VISUALS_RADIUS.get();
        blockSillyEffectRolls = BLOCK_SILLY_EFFECT_ROLLS.get();
    }
    
    // =========================================================
    // Helpers
    // =========================================================
    
    public static Map<BlockItemOfSilly.SillyEffect, Integer> parseBlockSillyEffectWeight() {
        return BLOCK_SILLY_EFFECT_WEIGHT.get().stream()
                .map(s -> s.split("="))
                .filter(parts -> parts.length == 2)
                .flatMap(parts -> {
                    try {
                        int weight = Integer.parseInt(parts[1].trim());
                        return Arrays.stream(BlockItemOfSilly.SillyEffect.values())
                                .filter(e -> e.getId().equals(parts[0].trim()))
                                .map(e -> Map.entry(e, weight));
                    } catch (NumberFormatException e) {
                        ArcaneContent.LOGGER.warn("Invalid weight in blockSillyEffectWeight: {}", parts[1]);
                        return Stream.empty();
                    }
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    
    public record SillyEffects(ResourceLocation effect, int amplifier, int duration) {
        public static SillyEffects parse(String s) {
            String[] parts = s.split(";");
            return new SillyEffects(
                    new ResourceLocation(parts[0]),
                    Integer.parseInt(parts[1]),
                    Integer.parseInt(parts[2])
            );
        }
        
        public static List<SillyEffects> getPositiveEffects() {
            return blockSillyPositiveEffects.stream()
                    .map(s -> parse((String) s))
                    .toList();
        }
        
        public static List<SillyEffects> getNegativeEffects() {
            return blockSillyNegativeEffects.stream()
                    .map(s -> parse((String) s))
                    .toList();
        }
    }
    public record SillyItems(ResourceLocation item, int count) {
        public static SillyItems parse(String s) {
            String[] parts = s.split(";");
            return new SillyItems(
                    new ResourceLocation(parts[0]),
                    Integer.parseInt(parts[1])
            );
        }
        
        public static List<SillyItems> getItems() {
            return Config.blockSillyItems.stream()
                    .map(s -> SillyItems.parse((String) s))
                    .toList();
        }
    }
}