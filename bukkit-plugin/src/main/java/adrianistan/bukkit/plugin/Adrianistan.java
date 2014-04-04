package adrianistan.bukkit.plugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.*;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public final class Adrianistan extends JavaPlugin{
	@Override
	public void onEnable(){
		getLogger().info("Adrianistan Main Plugin has been enabled");
		getServer().getPluginManager().registerEvents(new AdrianistanLogin(),this);
	}
	@Override
	public void onDisable(){
		getLogger().info("Adrianistan Main Plugin has been disabled");
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("adrianistan")){
			if(!(sender instanceof Player))
			{
				sender.sendMessage("Adrianistan Server running CraftBukkit");
			}else{
					Player player=(Player)sender;
					PlayerInventory inventory=player.getInventory();
					ItemStack is=new ItemStack(Material.WRITTEN_BOOK);
					BookMeta book=(BookMeta)is.getItemMeta();
					book.setTitle("Adrianistán");
					book.setAuthor("Adrián Arroyo");
					book.addPage("BIENVENIDO A ADRIANISTAN\nQuizá el mejor server");
					book.addEnchant(Enchantment.SILK_TOUCH,100,true);
					is.setItemMeta(book);
					inventory.addItem(is);

			}
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("internet"))
		{
			sender.sendMessage("Estas conectado a INTERNET");
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("tren"))
		{
			if(!(sender instanceof Player))
			{
				sender.sendMessage("Solo funciona para jugadores");
			}else{
				Player player=(Player)sender;
				PlayerInventory inventory=player.getInventory();
				ItemStack kart=new ItemStack(Material.MINECART);
				ItemMeta meta=(ItemMeta)kart.getItemMeta();
				meta.setDisplayName("Tren - Ministerio de Transportes");
				kart.setItemMeta(meta);
				inventory.addItem(kart);
			}
			return true;
		}
		return false;
	}
}
