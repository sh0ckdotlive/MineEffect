package live.sh0ck.mineeffect;

import live.sh0ck.mineeffect.handlers.EffectHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class MineEffect extends JavaPlugin {
  
  @Override
  public void onEnable() {
    registerHandlers();
  }
  
  private void registerHandlers() {
    new EffectHandler(this);
  }
}
