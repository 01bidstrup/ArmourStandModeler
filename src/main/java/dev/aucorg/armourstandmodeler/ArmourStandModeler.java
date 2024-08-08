package dev.aucorg.armourstandmodeler;

import dev.aucorg.armourstandmodeler.debug.DebugInventoryClickListener;
import dev.aucorg.armourstandmodeler.debug.DebugPrintInteractionListCommand;
import dev.aucorg.armourstandmodeler.eventlistener.*;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArmourStandModeler extends JavaPlugin {
    @Override
    public void onEnable() {
        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new DebugInventoryClickListener(), this);
        this.getCommand("printinteractions").setExecutor(new DebugPrintInteractionListCommand());


        pluginManager.registerEvents(new ArmourStandInteractEventListener(), this);
        pluginManager.registerEvents(new PlaceArmourStandEventListener(), this);
        pluginManager.registerEvents(new InventoryClickEventListener(), this);
        pluginManager.registerEvents(new InventoryDragEventListener(), this);
        pluginManager.registerEvents(new InventoryCloseEventListener(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
