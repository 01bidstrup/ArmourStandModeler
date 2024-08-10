package dev.aucorg.armourstandmodeler;

import dev.aucorg.armourstandmodeler.inventory.GUIState;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum ArmourStandInteractionMap {
    INSTANCE;
    ArmourStandInteractionMap() {
    };

    public static ArmourStandInteractionMap getInstance() {
        return INSTANCE;
    }

    private final BiMap<Player, ArmorStand> ARMOUR_STAND_INTERACTION_MAP = HashBiMap.create();
    private final Map<Player, GUIState> PLAYER_GUI_STATE_MAP = new HashMap<>();

    public ArmorStand getArmourStand(Player p) {
        return ARMOUR_STAND_INTERACTION_MAP.get(p);
    }

    public Player getPlayer(ArmorStand as) {
        return ARMOUR_STAND_INTERACTION_MAP.inverse().get(as);
    }

    public void addInteraction(Player p, ArmorStand as, GUIState guiType) {
        ARMOUR_STAND_INTERACTION_MAP.put(p, as);
        PLAYER_GUI_STATE_MAP.put(p, guiType);
    }

    public void removeInteraction(Player p) {
        ARMOUR_STAND_INTERACTION_MAP.remove(p);
        PLAYER_GUI_STATE_MAP.remove(p);
    }

    public boolean isPlayerInteracting(Player p) {
        return ARMOUR_STAND_INTERACTION_MAP.containsKey(p);
    }

    public Set<Player> getPlayers() {
        return ARMOUR_STAND_INTERACTION_MAP.keySet();
    }

    public Set<ArmorStand> getArmourStands() {
        return ARMOUR_STAND_INTERACTION_MAP.values();
    }

    public Set<Map.Entry<Player, ArmorStand>> getInteractionPairs() {
        return ARMOUR_STAND_INTERACTION_MAP.entrySet();
    }
}
