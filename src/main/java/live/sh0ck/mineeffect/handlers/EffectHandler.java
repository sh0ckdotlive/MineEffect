package live.sh0ck.mineeffect.handlers;

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
import java.util.Random;

public class EffectHandler implements Listener {
  
  private final List<PotionEffectType> effects = List.of(PotionEffectType.values());
  
  public EffectHandler(JavaPlugin plugin) {
    plugin.getServer().getPluginManager().registerEvents(this, plugin);
  }
  
  @EventHandler(priority = EventPriority.MONITOR)
  public void onBlockBreak(BlockBreakEvent event) {
    Player player = event.getPlayer();
    Random random = new Random(System.currentTimeMillis());
    
    PotionEffectType type = effects.get(random.nextInt(effects.size()));
    int duration = random.nextInt(15) + 1;
    int amplifier = random.nextInt(9);
  
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
