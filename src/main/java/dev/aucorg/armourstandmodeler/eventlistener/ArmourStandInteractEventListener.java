package dev.aucorg.armourstandmodeler.eventlistener;

import dev.aucorg.armourstandmodeler.ArmourStandInteractionMap;
import dev.aucorg.armourstandmodeler.ArmourStandModeler;
import dev.aucorg.armourstandmodeler.inventory.ArmourStandMainGUI;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class ArmourStandInteractEventListener implements Listener {
    @EventHandler
    public void onPlayerInteractArmourStand(PlayerInteractAtEntityEvent event) {
        Entity entity = event.getRightClicked();
        if (entity.getType() != EntityType.ARMOR_STAND) {
            return;
        }

        Player player = event.getPlayer();
        ArmorStand armourStand = (ArmorStand) entity;

        event.setCancelled(true);

        ArmourStandInteractionMap.addInteraction(player, armourStand);
        player.openInventory(ArmourStandMainGUI.createGUI(player, armourStand));
    }
}
