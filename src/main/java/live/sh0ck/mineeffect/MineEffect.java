package live.sh0ck.mineeffect;

import live.sh0ck.mineeffect.handlers.EffectHandler;
import live.sh0ck.mineeffect.managers.ConfigManager;
import live.sh0ck.mineeffect.managers.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

public class MineEffect extends JavaPlugin {
  
  @Override
  public void onEnable() {
    registerHandlers();
    registerConfigs();
  }
  
  private void registerHandlers() {
    new EffectHandler(this);
  }
  
  private void registerConfigs() {
    // Load and cache all configs
    ConfigManager.loadAllConfigs(this);
    
    // Setup config.yml defaults
    ConfigManager.createIfNotPresent("config", this);
    Configuration config = ConfigManager.getConfig("config");
    config.yamlConfig.addDefault("effects.amplifier.min", 0);
    config.yamlConfig.addDefault("effects.amplifier.max", 9);
    config.yamlConfig.addDefault("effects.duration.min", 0);
    config.yamlConfig.addDefault("effects.duration.max", 15);
    config.yamlConfig.options().copyDefaults(true);
    ConfigManager.saveConfig(config);
    
    // Reload after setting defaults
    ConfigManager.reloadConfigs(this);
  }
}
