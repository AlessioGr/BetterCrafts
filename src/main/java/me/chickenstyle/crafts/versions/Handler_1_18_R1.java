package me.chickenstyle.crafts.versions;

import net.minecraft.nbt.CompoundTag;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_18_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import me.chickenstyle.crafts.NMSHandler;

public class Handler_1_18_R1 implements NMSHandler {
	
	@Override
	public ItemStack addIDTag(ItemStack item,int id) {
		net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		CompoundTag itemCompound = (nmsItem.hasTag()) ? nmsItem.getTag() : new CompoundTag();
		itemCompound.putInt("IDTag", id);
		nmsItem.setTag(itemCompound);
		return CraftItemStack.asBukkitCopy(nmsItem);
	}
	
	@Override
	public boolean hasIDTag(ItemStack item) {
		net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		CompoundTag itemCompound = (nmsItem.hasTag()) ? nmsItem.getTag() : new CompoundTag();
		return itemCompound.contains("IDTag");
	}

	
	
	@Override
	public int getID(ItemStack item) {
		net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		CompoundTag itemCompound = (nmsItem.hasTag()) ? nmsItem.getTag() : new CompoundTag();
		return itemCompound.getInt("IDTag");
	}
	
	@Override
	public void playParticles(Location loc, String particle, int amount) {
		loc.getWorld().spawnParticle(Particle.valueOf(particle.toUpperCase()),loc,amount,0,0,0,0.2);
	}
	
}
