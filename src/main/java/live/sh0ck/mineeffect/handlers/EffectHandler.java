package live.sh0ck.mineeffect.handlers;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EffectHandler implements Listener {
  
  public EffectHandler(JavaPlugin plugin) {
    plugin.getServer().getPluginManager().registerEvents(this, plugin);
  }
  
  @EventHandler(priority = EventPriority.MONITOR)
  public void onBlockBreak(BlockBreakEvent event) {
  }
}
