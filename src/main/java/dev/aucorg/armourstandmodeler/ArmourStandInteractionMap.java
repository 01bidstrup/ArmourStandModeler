package dev.aucorg.armourstandmodeler;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.Map;
import java.util.Set;

public class ArmourStandInteractionMap {
    private static final BiMap<Player, ArmorStand> ARMOUR_STAND_INTERACTION_MAP = HashBiMap.create();

    public static ArmorStand getArmourStand(Player p) {
        return ARMOUR_STAND_INTERACTION_MAP.get(p);
    }

    public static Player getPlayer(ArmorStand as) {
        return ARMOUR_STAND_INTERACTION_MAP.inverse().get(as);
    }

    public static void addInteraction(Player p, ArmorStand as) {
        ARMOUR_STAND_INTERACTION_MAP.put(p, as);
    }

    public static void removeInteraction(Player p) {
        ARMOUR_STAND_INTERACTION_MAP.remove(p);
    }

    public static boolean isPlayerInteracting(Player p) {
        return ARMOUR_STAND_INTERACTION_MAP.containsKey(p);
    }

    public static Set<Player> getPlayers() {
        return ARMOUR_STAND_INTERACTION_MAP.keySet();
    }

    public static Set<ArmorStand> getArmourStands() {
        return ARMOUR_STAND_INTERACTION_MAP.values();
    }

    public static Set<Map.Entry<Player, ArmorStand>> getInteractionPairs() {
        return ARMOUR_STAND_INTERACTION_MAP.entrySet();
    }
}
