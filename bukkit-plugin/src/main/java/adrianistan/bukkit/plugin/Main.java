package adrianistan.bukkit.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin{
	@Override
	public void onEnable(){
		getLogger().info("Adrianistan Main Plugin has been enabled");
	}
	@Override
	public void onDisable(){
		getLogger().info("Adrianistan Main Plugin has been disabled");
	}
}
