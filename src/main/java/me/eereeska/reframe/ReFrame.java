package me.eereeska.reframe;

import me.eereeska.reframe.configs.ItemFrameConfig;
import me.eereeska.reframe.listeners.ItemFrameMenuInventoryClickListener;
import me.eereeska.reframe.listeners.PlayerInteractAtEntityEventListener;
import org.bukkit.plugin.java.JavaPlugin;

public class ReFrame extends JavaPlugin {

    private ItemFrameConfig itemFrameConfig;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.itemFrameConfig = new ItemFrameConfig(this);
        getServer().getPluginManager().registerEvents(new PlayerInteractAtEntityEventListener(this), this);
        getServer().getPluginManager().registerEvents(new ItemFrameMenuInventoryClickListener(this), this);
        getLogger().info("Enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled");
    }

    public ItemFrameConfig getItemFrameConfig() {
        return itemFrameConfig;
    }
}
