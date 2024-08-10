package dev.aucorg.armourstandmodeler.eventlistener;

import dev.aucorg.armourstandmodeler.ArmourStandModeler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRemoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.UUID;

public class ArmourStandRemoveEvent implements Listener {
    @EventHandler
    public void onArmourStandRemove(EntityRemoveEvent event) {
        if (event.getEntity() instanceof ArmorStand armourStand) {
            NamespacedKey leadAttachmentKey = new NamespacedKey(ArmourStandModeler.getPlugin(ArmourStandModeler.class), "ArmourStandModelerLeadAttachment");
            if (armourStand.getPersistentDataContainer().has(leadAttachmentKey, PersistentDataType.STRING)) {
                UUID uuid = UUID.fromString(armourStand.getPersistentDataContainer().get(leadAttachmentKey, PersistentDataType.STRING));
                Entity leadEntity = Bukkit.getEntity(uuid);
                if (leadEntity != null) {
                    if (leadEntity instanceof LivingEntity livingLeadEntity) {
                        if (livingLeadEntity.isLeashed()) {
                            livingLeadEntity.getWorld().dropItemNaturally(livingLeadEntity.getLocation(), new ItemStack(Material.LEAD));
                        }
                    }
                    leadEntity.remove();
                }
            }
        }
    }
}
