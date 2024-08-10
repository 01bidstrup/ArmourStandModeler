package dev.aucorg.armourstandmodeler.eventlistener;


import org.bukkit.GameMode;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPlaceEvent;
import org.bukkit.util.EulerAngle;

public class PlaceArmourStandEventListener implements Listener {
    @EventHandler
    public void onPlaceArmourStand(EntityPlaceEvent event) {
        if (!event.getEntityType().equals(EntityType.ARMOR_STAND)) {
            return;
        }

        ArmorStand armourStand = (ArmorStand) event.getEntity();

        armourStand.setArms(true);


        armourStand.setHeadPose(EulerAngle.ZERO);
        armourStand.setBodyPose(EulerAngle.ZERO);
        armourStand.setLeftArmPose(EulerAngle.ZERO);
        armourStand.setRightArmPose(EulerAngle.ZERO);
        armourStand.setLeftLegPose(EulerAngle.ZERO);
        armourStand.setRightLegPose(EulerAngle.ZERO);
        armourStand.setGravity(false);

        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            armourStand.setInvulnerable(true);
        }

    }
}

