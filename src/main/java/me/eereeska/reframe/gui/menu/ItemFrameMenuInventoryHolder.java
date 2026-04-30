package me.eereeska.reframe.gui.menu;

import me.eereeska.reframe.ReFrame;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class ItemFrameMenuInventoryHolder implements InventoryHolder {

    private final ReFrame plugin;
    private final Player player;
    private final ItemFrame itemFrame;
    private final Inventory inventory;

    public ItemFrameMenuInventoryHolder(ReFrame plugin, Player player, ItemFrame itemFrame) {
        this.plugin = plugin;
        this.player = player;
        this.itemFrame = itemFrame;

        String title = plugin.getConfig().getString("phrases.menu", "Menu");
        this.inventory = Bukkit.createInventory(this, 9, title);
        updateIcons();
    }

    public void updateIcons() {
        if (hasVisibilityPermission()) {
            inventory.setItem(2, buildVisibilityIcon());
        }
        if (hasFixationPermission()) {
            inventory.setItem(6, buildFixationIcon());
        }
    }

    private ItemStack buildVisibilityIcon() {
        boolean visible = itemFrame.isVisible();
        Material mat = visible ? Material.ENDER_EYE : Material.ENDER_PEARL;
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(plugin.getConfig().getString("phrases.visibility", "§fVisibility"));
            String state = visible
                    ? plugin.getConfig().getString("phrases.visible", "§aVisible")
                    : plugin.getConfig().getString("phrases.invisible", "§cInvisible");
            meta.setLore(Collections.singletonList(state));
            item.setItemMeta(meta);
        }
        return item;
    }

    private ItemStack buildFixationIcon() {
        boolean fixed = itemFrame.isFixed();
        Material mat = fixed ? Material.BEDROCK : Material.GRASS_BLOCK;
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(plugin.getConfig().getString("phrases.fixation", "§fFixation"));
            String state = fixed
                    ? plugin.getConfig().getString("phrases.fixed", "§aFixed")
                    : plugin.getConfig().getString("phrases.unfixed", "§cUnfixed");
            meta.setLore(Collections.singletonList(state));
            item.setItemMeta(meta);
        }
        return item;
    }

    public boolean hasVisibilityPermission() {
        String perm = plugin.getConfig().getString("permissions.visibility", "reframe.toggle.visibility");
        return player.hasPermission(perm);
    }

    public boolean hasFixationPermission() {
        String perm = plugin.getConfig().getString("permissions.fixation", "reframe.toggle.fixation");
        return player.hasPermission(perm);
    }

    public ItemStack getToggleVisibilityIcon() {
        return buildVisibilityIcon();
    }

    public ItemStack getToggleFixationIcon() {
        return buildFixationIcon();
    }

    public ItemFrame getItemFrame() {
        return itemFrame;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
