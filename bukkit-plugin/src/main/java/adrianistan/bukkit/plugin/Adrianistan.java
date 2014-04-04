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
					book.setTitle("Manual Gratuito de Adrianistán");
					book.setAuthor("Gobierno de Adrianistán");
					book.addPage("BIENVENIDO A ADRIANISTAN\nQuizá el mejor server de la historia\n\nPor Adrián Arroyo");
					book.addPage("En el principio hubo escasez, y hambrunas, y se fundó una ciudad semi-subterránea llamada Génesis. Pero gracias al esfuerzo de los fundadores y sobre todo de Adrián Arroyo, Adrianistán creció");
					book.addPage("TURISMO\n\nCiudad Génesis, Montes Daros, Aldea de los 4 vientos, Mar Raulántico,... muchos sitios por visitar");
					book.addPage("ECONOMIA\n\nLa moneda oficial es el DivCoin y el Banco Nacional es el Banco Tobías. Puedes crear una cuenta con un cofre y un cartel con [vault]. /money, /money pay, /money deposit y /money withdraw");
					book.addPage("NATURALEZA\n\nLa naturaleza en Adrianistán está muy presente. La Reserva Nacional, el Castillo de Adrián, el faro, la cueva-casa de Mario, etc");
					book.addPage("CIUDAD GÉNESIS\n\nVisita el Mercado de la Luz, la Biblioteca Nacional, el Arena, el Gobierno de Adrianistán o el Auditorio Nacional. Sin olvidar el magistral Éxodo");
					book.addPage("TRANSPORTES\n\nAdrianistán cuenta con caminos vías férreas. El Ministerio de Transportes pone a su disposición Trenes gratuitos, debe poner /tren");
					book.addPage("MAIL y TPA\n\nCompruebe su mail con /mail y transportese con /tpa");
					book.addPage("TRABAJOS\n\nAdrianistán cuenta con una amplia variedad de trabajos. Usa /jobs browse");
					book.addPage("TIENDAS\n\nAdrianistán tiene una variedad comercial. Puedes crear tus propias tiendas con cofres");
					book.addPage("CONTACTO\n\nSi tienes alguna duda contacta con el admin");
					book.addEnchant(Enchantment.SILK_TOUCH,1,true);
					book.setDisplayName("Manual Gratuito de Adrianistán - Gobierno de Adrianistán");
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
