package dev.aucorg.armourstandmodeler.inventory;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GUIItemBuilder {
    private final ItemStack item;

    public GUIItemBuilder(Material material) {
        this.item = new ItemStack(material, 1);
    }

    public GUIItemBuilder withName(String name) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setItemName(name);
            this.item.setItemMeta(meta);
        }
        return this;
    }

    public GUIItemBuilder addLore(String lore) {
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            List<String> l = meta.hasLore() ? meta.getLore() : new ArrayList<>();
            l.add(lore);
            meta.setLore(l);
        }
        this.item.setItemMeta(meta);

        return this;
    }

    public GUIItemBuilder hideAttributes() {
        ItemMeta meta = this.item.getItemMeta();
        if (meta != null) {
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            this.item.setItemMeta(meta);
        }
        return this;
    }

    public ItemStack build() {
        return item;
    }
}
