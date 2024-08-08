package dev.aucorg.armourstandmodeler;

import dev.aucorg.armourstandmodeler.eventlistener.ArmourStandInteractEventListener;
import dev.aucorg.armourstandmodeler.eventlistener.InventoryClickEventListener;
import dev.aucorg.armourstandmodeler.eventlistener.InventoryDragEventListener;
import dev.aucorg.armourstandmodeler.eventlistener.PlaceArmourStandEventListener;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class ArmourStandModeler extends JavaPlugin {
    @Override
    public void onEnable() {
        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new DebugInventoryClickListener(), this);


        pluginManager.registerEvents(new ArmourStandInteractEventListener(), this);
        pluginManager.registerEvents(new PlaceArmourStandEventListener(), this);
        pluginManager.registerEvents(new InventoryClickEventListener(), this);
        pluginManager.registerEvents(new InventoryDragEventListener(), this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
