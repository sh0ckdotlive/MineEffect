package live.sh0ck.mineeffect.handlers;

import live.sh0ck.mineeffect.managers.ConfigManager;
import live.sh0ck.mineeffect.managers.Configuration;
import live.sh0ck.mineeffect.utils.MathUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * {@link Listener} for the Effect system
 *
 * @author sh0ckR6
 * @since 2021.1007.1
 */
public class EffectHandler implements Listener {
  
  /**
   * Cached list of all {@link PotionEffectType}s
   *
   * @since 2021.1007.1
   */
  private final List<PotionEffectType> effects = List.of(PotionEffectType.values());
  
  /**
   * Constructor
   * @param plugin The {@link JavaPlugin} this listener will be registered to
   *
   * @author sh0ckR6
   * @since 2021.1007.1
   */
  public EffectHandler(JavaPlugin plugin) {
    plugin.getServer().getPluginManager().registerEvents(this, plugin);
  }
  
  /**
   * Handle {@link BlockBreakEvent}
   *
   * @param event The {@link BlockBreakEvent} passed to this listener
   *
   * @author sh0ckR6
   * @since 2021.1007.1
   */
  @EventHandler(priority = EventPriority.MONITOR)
  public void onBlockBreak(BlockBreakEvent event) {
    Configuration config = ConfigManager.getConfig("config");
  
    Player player = event.getPlayer();
  
    ThreadLocalRandom random = ThreadLocalRandom.current();
  
    PotionEffectType type = effects.get(random.nextInt(effects.size()));
    
    int maxDuration = config.yamlConfig.getInt("effects.duration.max");
    int minDuration = config.yamlConfig.getInt("effects.duration.min");
    int maxAmplifier = config.yamlConfig.getInt("effects.amplifier.max");
    int minAmplifier = config.yamlConfig.getInt("effects.amplifier.min");
  
    int duration = random.nextInt(minDuration, maxDuration + 1) + 1;
    int amplifier = random.nextInt(minAmplifier, maxAmplifier + 1);
  
    // Ensure the player is unable to instantly die from potion effects
    // These calculations are assuming the player is at full health
    if (type.equals(PotionEffectType.HARM)) {
      amplifier = MathUtils.clamp(amplifier, 0, 1);
      duration = 0;
    } else if (type.equals(PotionEffectType.WITHER) && amplifier >= 3) {
      duration = 9;
    }
    
    player.addPotionEffect(new PotionEffect(type,
            (duration * 20) + 1,
            amplifier,
            true,
            false,
            true));
  }
}
