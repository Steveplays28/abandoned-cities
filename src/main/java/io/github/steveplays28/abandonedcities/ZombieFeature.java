package io.github.steveplays28.abandonedcities;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class ZombieFeature extends Feature<ZombieFeature.ZombieFeatureConfig> {
	public ZombieFeature(Codec<ZombieFeatureConfig> configCodec) {
		super(configCodec);
	}

	@Override
	public boolean generate(FeatureContext<ZombieFeatureConfig> context) {
		for (int i = 0; i < context.getConfig().amount(); i++) {
			var zombie = EntityType.ZOMBIE.create(context.getWorld().toServerWorld());
			if (zombie == null) return false;
			zombie.setPosition(context.getOrigin().toCenterPos());
			context.getWorld().spawnEntity(zombie);

			AbandonedCities.LOGGER.info("spawned zombie at {}", context.getOrigin().toCenterPos());
		}

		return true;
	}

	public record ZombieFeatureConfig(int amount) implements FeatureConfig {
		public static Codec<ZombieFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(Codecs.POSITIVE_INT.fieldOf("amount").forGetter(ZombieFeatureConfig::amount)).apply(instance, ZombieFeatureConfig::new));

		public int amount() {
			return amount;
		}
	}
}
