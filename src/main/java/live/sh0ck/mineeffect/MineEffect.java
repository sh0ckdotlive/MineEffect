package live.sh0ck.mineeffect;

import live.sh0ck.mineeffect.commands.ConfigCommand;
import live.sh0ck.mineeffect.handlers.EffectHandler;
import live.sh0ck.mineeffect.managers.ConfigManager;
import live.sh0ck.mineeffect.managers.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Class that represents the {@link JavaPlugin} that will be loaded.
 *
 * @author sh0ckR6
 * @since 2021.1007.1
 */
public class MineEffect extends JavaPlugin {
  
  /**
   * Run when the plugin is enabled. All initialization happens here.
   *
   * @author sh0ckR6
   * @since 2021.1007.1
   */
  @Override
  public void onEnable() {
    registerHandlers();
    registerConfigs();
    registerCommands();
  }
  
  /**
   * Register all {@link org.bukkit.event.Listener}s.
   *
   * @author sh0ckR6
   * @since 2021.1007.1
   */
  private void registerHandlers() {
    new EffectHandler(this);
  }
  
  /**
   * Register and set defaults for all configuration files.
   *
   * @author sh0ckR6
   * @since 2021.1007.1
   */
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
  
  /**
   * Register all commands defined in the plugin.yml file.
   *
   * @since 2021.1007.1
   * @author sh0ckR6
   */
  private void registerCommands() {
    new ConfigCommand(this);
  }
}
