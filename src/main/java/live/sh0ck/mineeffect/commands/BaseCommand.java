package live.sh0ck.mineeffect.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Class representing an in-game command.<br><br>
 *
 * Permissions for this command can be added with the marker interfaces {@link IPlayerCommand} and {@link IServerCommand}.
 * By default, a command cannot be run by anybody.
 *
 * @author sh0ckR6
 * @since 2021.1007.1
 */
public abstract class BaseCommand implements CommandExecutor {
  
  /**
   * The {@link JavaPlugin} this command is registered to
   *
   * @since 2021.1007.1
   */
  protected JavaPlugin plugin;
  /**
   * The name of this command
   *
   * @since 2021.1007.1
   */
  protected String name;
  
  /**
   * Constructor
   *
   * @param name The name of the command
   * @param aliases A list of aliases of the command
   * @param plugin The plugin to register this command to
   *
   * @author sh0ckR6
   * @since 2021.1007.1
   */
  protected BaseCommand(String name, List<String> aliases, JavaPlugin plugin) {
    if (plugin.getCommand(name) == null) {
      plugin.getLogger().severe("The command " + name + " could not be found! Maybe you're missing the definition in the plugin.yml file?");
      return;
    }
    
    this.name = name;
    this.plugin = plugin;
    
    plugin.getCommand(name).setExecutor(this);
    plugin.getCommand(name).setAliases(aliases);
    if (this instanceof TabCompleter completer) plugin.getCommand(name).setTabCompleter(completer);
  }
  
  /**
   * Default handler overridden from {@link CommandExecutor}. Checks if the {@link CommandSender} has permission to run
   * this command before sending command execution to the inheriting class.
   *
   * @param sender The {@link CommandSender} that sent the command
   * @param command The {@link Command} representation of this command
   * @param label The name of the command
   * @param args Array of all arguments passed to the command
   * @return If command execution was successful
   *
   * @author sh0ckR6
   * @since 2021.1007.1
   */
  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if ((!(this instanceof IPlayerCommand) || !(sender instanceof Player)) &&
        (!(this instanceof IServerCommand) || !(sender instanceof ConsoleCommandSender))) {
      sender.sendMessage(ChatColor.RED + "You are not allowed to run this command!");
      return true;
    }
    
    execute(sender, command, label, args);
    return true;
  }
  
  /**
   * Handles command execution and is automatically called if all checks pass in {@link BaseCommand#onCommand}.<br><br>
   *
   * Inheritors should not worry about checking permission to run this command as that is handled in
   * {@link BaseCommand#onCommand} and instead should only handle execution.
   *
   * @param sender The {@link CommandSender} that sent the command
   * @param command The {@link Command} representation of this command
   * @param label The name of the command
   * @param args Array of all arguments passed to the command
   *
   * @author sh0ckR6
   * @since 2021.1007.1
   */
  protected abstract void execute(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args);
}
