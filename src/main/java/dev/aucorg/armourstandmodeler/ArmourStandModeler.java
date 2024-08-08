package dev.aucorg.armourstandmodeler;

import org.bukkit.plugin.java.JavaPlugin;

public final class ArmourStandModeler extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ArmourStandInteractEventListener(), this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
