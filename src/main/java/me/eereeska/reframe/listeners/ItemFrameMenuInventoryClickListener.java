package me.eereeska.reframe.listeners;

import me.eereeska.reframe.ReFrame;
import me.eereeska.reframe.gui.menu.ItemFrameMenuInventoryHolder;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class ItemFrameMenuInventoryClickListener implements Listener {

    private final ReFrame plugin;

    public ItemFrameMenuInventoryClickListener(ReFrame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onIconClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        Inventory topInventory = event.getView().getTopInventory();
        InventoryHolder holder = topInventory.getHolder();
        if (!(holder instanceof ItemFrameMenuInventoryHolder menuHolder)) return;

        event.setCancelled(true);

        ItemStack clicked = event.getCurrentItem();
        if (clicked == null) return;

        ItemFrame itemFrame = menuHolder.getItemFrame();

        // Slot 2 = visibility toggle
        if (event.getSlot() == 2 && menuHolder.hasVisibilityPermission()) {
            String visPerm = plugin.getConfig().getString("permissions.visibility", "reframe.toggle.visibility");
            if (player.hasPermission(visPerm)) {
                itemFrame.setVisible(!itemFrame.isVisible());
                menuHolder.updateIcons();
            }
        }

        // Slot 6 = fixation toggle
        if (event.getSlot() == 6 && menuHolder.hasFixationPermission()) {
            String fixPerm = plugin.getConfig().getString("permissions.fixation", "reframe.toggle.fixation");
            if (player.hasPermission(fixPerm)) {
                itemFrame.setFixed(!itemFrame.isFixed());
                menuHolder.updateIcons();
            }
        }
    }
}
