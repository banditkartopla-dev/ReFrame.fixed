package me.eereeska.reframe.configs;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ItemFrame;
import me.eereeska.reframe.ReFrame;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ItemFrameConfig {

    private final ReFrame plugin;
    private final File file;
    private FileConfiguration config;

    public ItemFrameConfig(ReFrame plugin) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "frames.yml");
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    private String getKey(ItemFrame itemFrame) {
        return "items." + itemFrame.getLocation().toString();
    }

    public List<String> getFunctionsList(ItemFrame itemFrame) {
        return config.getStringList(getKey(itemFrame) + ".functions");
    }

    public boolean toggleFunction(ItemFrame itemFrame, String function) {
        String path = getKey(itemFrame) + ".functions";
        List<String> functions = config.getStringList(path);
        boolean added;
        if (functions.contains(function)) {
            functions.remove(function);
            added = false;
        } else {
            functions.add(function);
            added = true;
        }
        config.set(path, functions);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return added;
    }
}
