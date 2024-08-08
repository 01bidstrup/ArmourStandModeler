package dev.aucorg.armourstandmodeler;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class ArmourStandInteractionMap {
    private static final BiMap<Player, ArmorStand> ARMOUR_STAND_INTERACTION_MAP = HashBiMap.create();

    public static ArmorStand getArmourStand(Player p) {
        return ARMOUR_STAND_INTERACTION_MAP.get(p);
    }

    public static void addInteraction(Player p, ArmorStand as) {
        ARMOUR_STAND_INTERACTION_MAP.put(p, as);
    }

    public static void removeInteraction(Player p) {
        ARMOUR_STAND_INTERACTION_MAP.remove(p);
    }

}
