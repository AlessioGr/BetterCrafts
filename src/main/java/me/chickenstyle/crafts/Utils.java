package me.chickenstyle.crafts;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {
	public static String color(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}
	
	public static Material getSignMaterial() {
		return Material.OAK_SIGN;
		
	}
	
	public static Material getRedstoneTorchMaterial() {
		return Material.REDSTONE_TORCH;
		
	}
	
	public static Material getCraftTableMaterial() {
		return Material.CRAFTING_TABLE;
		
	}
	
	public static Material getEnchantingTableMaterial() {
		return Material.ENCHANTING_TABLE;
		
	}
	
	
	
    @SuppressWarnings("deprecation")
	public static ItemStack getGreenVersionGlass(String title) {
    	ItemStack glass = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);

        ItemMeta meta = glass.getItemMeta();
        meta.setDisplayName(color(title));
        glass.setItemMeta(meta);
        return glass;
    }
    
    @SuppressWarnings("deprecation")
	public static ItemStack getGrayVersionGlass() {
    	ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        
        ItemMeta meta = glass.getItemMeta();
        meta.setDisplayName(" ");
        glass.setItemMeta(meta);
        return glass;
    }
    
	@SuppressWarnings("deprecation")
	public static ItemStack getRedVersionGlass() {
    	ItemStack glass = new ItemStack(Material.RED_STAINED_GLASS_PANE);

        
        ItemMeta meta = glass.getItemMeta();
        meta.setDisplayName(color("&cNo available pages!"));
        glass.setItemMeta(meta);
        
        return glass;
    }
    
    
    public static YamlConfiguration getLanguageYML() {
    	File lang = new File(Main.getInstance().getDataFolder()+"/lang.yml");
    	return YamlConfiguration.loadConfiguration(lang);
    }
	
    
    @SuppressWarnings("unchecked")
	public static ItemStack loadItemStack(ItemStack item,String name,Recipe recipe) {
    	YamlConfiguration lang = getLanguageYML();
    	String path = "items." + name + ".";
    	
    	ItemMeta meta = item.getItemMeta();
    	meta.setDisplayName(color(lang.getString(path + ".itemName")));
    	
    	ArrayList<String> lore = new ArrayList<>();
    	
    	for (String line:(ArrayList<String>) lang.get(path + "itemLore")) {
    		String loreLine = line;
    		
    		loreLine = loreLine.replace("{id}", recipe.getId() + "");
    		
    		loreLine = loreLine.replace("{animation}", recipe.getAnimationType().toString());
    		
    		if (recipe.getResult() != null) {
    			if (recipe.getResult().getItemMeta().hasDisplayName()) {
    				loreLine = loreLine.replace("{resultItem}", recipe.getResult().getItemMeta().getDisplayName());
    			} else {
    				loreLine = loreLine.replace("{resultItem}", getName(recipe.getResult().getType()));
    			}
    			
    			loreLine = loreLine.replace("{resultAmount}", recipe.getResult().getAmount() + "");
    		} else {
    			loreLine = loreLine.replace("{resultItem}", "none");
    			loreLine = loreLine.replace("{resultAmount}", 0 + "");
    		}
    		
    		
    		
    		if (recipe.getTriggerItem() != null) {
    			if (recipe.getTriggerItem().getItemMeta().hasDisplayName()) {
    				loreLine = loreLine.replace("{triggerItem}", recipe.getTriggerItem().getItemMeta().getDisplayName());
    			} else {
    				loreLine = loreLine.replace("{triggerItem}", getName(recipe.getTriggerItem().getType()));
    			}
    			loreLine = loreLine.replace("{triggerAmount}", recipe.getTriggerItem().getAmount() + "");
    		} else {
    			loreLine = loreLine.replace("{triggerItem}", "none");
    			loreLine = loreLine.replace("{triggerAmount}", 0 + "");
    		}
    		
    		lore.add(color(loreLine));
    		
    	} 
    	
    	meta.setLore(lore);
    	
    	item.setItemMeta(meta);
    	return item;
    }
    
    
    public static String getName(Material mat) {
		String name = mat.name().replace('_',' ').toLowerCase();
		String[] data = name.split("");
		
		for (int i = 0;i < data.length;i++) {
			if (i != 0) {
				if (data[i - 1].equals(" ")) {
					data[i] = data[i].toUpperCase();
				}
			} else {
				data[i] = data[i].toUpperCase();
			}
		}
		
		name = arrayToString(data);
		return name;
	}
    
	public static String arrayToString(String[] arr) {
		String str = "";
		for (String chr:arr) {
			str = str + chr;
		}
		return str;
	}
    
}
