package live.sh0ck.mineeffect.managers;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * Represents a {@link YamlConfiguration}, it's corresponding {@link File}, and the name of the configuration
 *
 * @author sh0ckR6
 * @since 2021.1007.1
 */
public class Configuration {
  
  /**
   * The current {@link YamlConfiguration}
   *
   * @since 2021.1007.1
   */
  public YamlConfiguration yamlConfig;
  
  /**
   * The current {@link File}
   *
   * @since 2021.1007.1
   */
  public File file;
  
  /**
   * This configuration's name
   *
   * @since 2021.1007.1
   */
  public String name;
  
  /**
   * Constructor to create a new Configuration
   *
   * @param yamlConfig The {@link YamlConfiguration} this will represent
   * @param file The {@link File} this will represent
   * @param name The name of this configuration
   * @author sh0ckR6
   * @since 2021.1007.1
   */
  public Configuration(YamlConfiguration yamlConfig, File file, String name) {
    this.yamlConfig = yamlConfig;
    this.file = file;
    this.name = name;
  }
}
