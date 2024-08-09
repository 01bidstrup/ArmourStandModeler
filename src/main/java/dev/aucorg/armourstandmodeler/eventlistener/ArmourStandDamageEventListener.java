package dev.aucorg.armourstandmodeler.eventlistener;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class ArmourStandDamageEventListener implements Listener {
    @EventHandler
    public void onArmourStandDamage(EntityDamageEvent event) {
        if (event.getEntityType().equals(EntityType.ARMOR_STAND)) {
            if (event.getEntity().isInvulnerable()) {
                event.setCancelled(true);
            }
        }
    }
}
