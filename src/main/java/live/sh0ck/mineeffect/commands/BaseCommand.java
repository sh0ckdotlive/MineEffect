package live.sh0ck.mineeffect.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class BaseCommand implements CommandExecutor {
  
  protected JavaPlugin plugin;
  protected String name;
  
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
  
  protected abstract void execute(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args);
}
