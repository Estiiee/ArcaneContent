package com.estie.arcanecontent.world.district;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

import java.util.*;

//replaces the climate conditions in which certain biomes can generate by substituting them with a different biome
//for example can prevent biome X from generating in a cold climate by replacing it with biome Y
public final class ClimateReplacementManager {
    private static final List<ClimateReplacement> REPLACEMENTS = new ArrayList<>();
    
    private ClimateReplacementManager() {}
    
    public static void register(ClimateReplacement replacement) {
        REPLACEMENTS.add(replacement);
    }
    
    public static ResourceKey<Biome> replace(
            ResourceKey<Biome> original,
            Climate.TargetPoint point,
            RandomSource random
    ) {
        for (ClimateReplacement replacement : REPLACEMENTS) {
            if (!replacement.appliesTo(original)) continue;
            if (!replacement.climateMatches(point)) continue;
            
            List<ResourceKey<Biome>> replacements = replacement.replacements();
            if (replacements.isEmpty()) return original;
            
            return replacements.get(random.nextInt(replacements.size()));
        }
        
        return original;
    }
    
    public static final class ClimateReplacement {
        private final Set<ResourceKey<Biome>> targets;
        private final List<ResourceKey<Biome>> replacements;
        private final float minTemperature, maxTemperature;
        private final float minHumidity, maxHumidity;
        private final float minContinentalness, maxContinentalness;
        private final float minErosion, maxErosion;
        private final float minWeirdness, maxWeirdness;
        private final float minDepth, maxDepth;
        
        private ClimateReplacement(Builder b) {
            this.targets = b.targets;
            this.replacements = b.replacements;
            this.minTemperature = b.minTemperature; this.maxTemperature = b.maxTemperature;
            this.minHumidity = b.minHumidity; this.maxHumidity = b.maxHumidity;
            this.minContinentalness = b.minContinentalness; this.maxContinentalness = b.maxContinentalness;
            this.minErosion = b.minErosion; this.maxErosion = b.maxErosion;
            this.minWeirdness = b.minWeirdness; this.maxWeirdness = b.maxWeirdness;
            this.minDepth = b.minDepth; this.maxDepth = b.maxDepth;
        }
        
        boolean appliesTo(ResourceKey<Biome> key) { return targets.contains(key); }
        
        boolean climateMatches(Climate.TargetPoint p) {
            float temperature = Climate.unquantizeCoord(p.temperature());
            float humidity = Climate.unquantizeCoord(p.humidity());
            float continentalness = Climate.unquantizeCoord(p.continentalness());
            float erosion = Climate.unquantizeCoord(p.erosion());
            float weirdness = Climate.unquantizeCoord(p.weirdness());
            float depth = Climate.unquantizeCoord(p.depth());
            
            return inRange(temperature, minTemperature, maxTemperature)
                    && inRange(humidity, minHumidity, maxHumidity)
                    && inRange(continentalness, minContinentalness, maxContinentalness)
                    && inRange(erosion, minErosion, maxErosion)
                    && inRange(weirdness, minWeirdness, maxWeirdness)
                    && inRange(depth, minDepth, maxDepth);
        }
        
        private static boolean inRange(float v, float min, float max) { return v >= min && v <= max; }
        
        List<ResourceKey<Biome>> replacements() { return replacements; }
        
        @SafeVarargs
        public static Builder builder(ResourceKey<Biome> first, ResourceKey<Biome>... more) {
            return new Builder(first, more);
        }
        
        public static final class Builder {
            private final Set<ResourceKey<Biome>> targets;
            private List<ResourceKey<Biome>> replacements = List.of();
            private float minTemperature = -Float.MAX_VALUE, maxTemperature = Float.MAX_VALUE;
            private float minHumidity = -Float.MAX_VALUE, maxHumidity = Float.MAX_VALUE;
            private float minContinentalness = -Float.MAX_VALUE, maxContinentalness = Float.MAX_VALUE;
            private float minErosion = -Float.MAX_VALUE, maxErosion = Float.MAX_VALUE;
            private float minWeirdness = -Float.MAX_VALUE, maxWeirdness = Float.MAX_VALUE;
            private float minDepth = -Float.MAX_VALUE, maxDepth = Float.MAX_VALUE;
            
            private Builder(ResourceKey<Biome> first, ResourceKey<Biome>[] more) {
                Set<ResourceKey<Biome>> set = new HashSet<>();
                set.add(first);
                set.addAll(Arrays.asList(more));
                this.targets = Set.copyOf(set);
            }
            
            public Builder temperature(float min, float max) { minTemperature = min; maxTemperature = max; return this; }
            public Builder humidity(float min, float max) { minHumidity = min; maxHumidity = max; return this; }
            public Builder continentalness(float min, float max) { minContinentalness = min; maxContinentalness = max; return this; }
            public Builder erosion(float min, float max) { minErosion = min; maxErosion = max; return this; }
            public Builder weirdness(float min, float max) { minWeirdness = min; maxWeirdness = max; return this; }
            public Builder depth(float min, float max) { minDepth = min; maxDepth = max; return this; }
            
            @SafeVarargs
            public final Builder replaceWith(ResourceKey<Biome>... replacements) {
                this.replacements = List.of(replacements);
                return this;
            }
            
            public ClimateReplacement build() { return new ClimateReplacement(this); }
        }
    }
}