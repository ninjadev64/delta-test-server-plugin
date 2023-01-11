package com.amansprojects.dtsp;

import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements CommandExecutor, Listener {
    @Override public void onEnable() {
        getLogger().info("Delta Test Server: Main plugin successfully enabled");
        getServer().getPluginManager().registerEvents(this, this);
    }

    private boolean shouldCancel(Player player) {
        if (!(player.hasPermission("delta.bypass") || player.isOp())) {
            player.sendMessage("§cSorry, but you can't do that!");
            return true;
        } else return false;
    }

    @EventHandler public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            if (shouldCancel((Player) event.getEntity())) event.setFoodLevel(20);
        }
    }

    @EventHandler public void onBlockBreak(BlockBreakEvent event) {
        if (shouldCancel(event.getPlayer())) event.setCancelled(true);
    }

    @EventHandler public void onBlockPlace(BlockPlaceEvent event) {
        if (shouldCancel(event.getPlayer())) event.setCancelled(true);
    }

    @EventHandler public void onEntityPickupItem(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player) {
            if (shouldCancel((Player) event.getEntity())) event.setCancelled(true);
        }
    }

    @EventHandler public void onPlayerInteract(PlayerInteractEvent event) {
        if (shouldCancel(event.getPlayer())) event.setCancelled(true);
    }

    @EventHandler public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            if (shouldCancel((Player) event.getDamager())) event.setCancelled(true);
        }
    }

    @EventHandler public void onEntityTarget(EntityTargetEvent event) {
        if (event.getEntity() instanceof Monster) {
            event.setCancelled(true);
        }
    }
}
