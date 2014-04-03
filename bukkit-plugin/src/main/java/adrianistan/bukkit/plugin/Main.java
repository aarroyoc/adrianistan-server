package adrianistan.bukkit.plugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.*;

public final class Main extends JavaPlugin{
	@Override
	public void onEnable(){
		getLogger().info("Adrianistan Main Plugin has been enabled");
	}
	@Override
	public void onDisable(){
		getLogger().info("Adrianistan Main Plugin has been disabled");
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("adrianistan")){
			sender.sendMessage("Adrianistan Server running CraftBukkit");
			return true;
		}
		return false;
	}
}
