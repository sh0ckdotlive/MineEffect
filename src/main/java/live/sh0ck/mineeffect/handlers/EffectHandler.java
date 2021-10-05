package live.sh0ck.mineeffect.handlers;

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
    
    player.addPotionEffect(new PotionEffect(effects.get(random.nextInt(effects.size())), 5 * 20, 0, true, false, true));
  }
}
