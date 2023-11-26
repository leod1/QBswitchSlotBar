package fr.leod1;

import fr.leod1.Db.ProjectSerializationManager;
import fr.leod1.Db.fileUtils;
import fr.leod1.events.SwitchItem;
import fr.leod1.events.eventJoin;
import fr.leod1.events.eventLeave;
import fr.leod1.stockageSlots.SlotsBar;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public final class QBswitchSlotBar extends JavaPlugin {
    public HashMap<Player, SlotsBar> playerCaches;

    public static QBswitchSlotBar InstanceOfMain;

    private ProjectSerializationManager projectSerializationManager;

    public void onEnable() {
        this.projectSerializationManager = new ProjectSerializationManager();
        InstanceOfMain = this;
        this.playerCaches = new HashMap<>();
        Bukkit.getPluginManager().registerEvents((Listener)new SwitchItem(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new eventJoin(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new eventLeave(), (Plugin)this);
        for (Player pl : Bukkit.getOnlinePlayers()) {
            File DirectoryPlayerData = new File(InstanceOfMain.getDataFolder() + "/PlayerData/");
            File file = new File(DirectoryPlayerData, pl.getName() + ".json");
            SlotsBar json = null;
            try {
                json = InstanceOfMain.getProjectSerializationManager().deserializePlayerData(fileUtils.loadContent(file));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            if (!file.exists() || json == null) {
                ArrayList<ItemStack> qzd = new ArrayList<>();
                for (int i = 0; i < 9; i++) {
                    if (pl.getInventory().getItem(i) == null) {
                        qzd.add(new ItemStack(Material.AIR));
                    } else {
                        qzd.add(pl.getInventory().getItem(i));
                    }
                }
                InstanceOfMain.playerCaches.put(pl, new SlotsBar(true, qzd));
                return;
            }
            InstanceOfMain.playerCaches.put(pl, json);
        }
    }

    public void onDisable() {
        File DirectoryProj = new File(InstanceOfMain.getDataFolder(), "/PlayerData/");
        DirectoryProj.mkdirs();
        for (Player pl : Bukkit.getOnlinePlayers()) {
            File DirectoryPlayerData = new File(InstanceOfMain.getDataFolder(), "/PlayerData/");
            File file = new File(DirectoryPlayerData, pl.getName() + ".json");
            try {
                fileUtils.createFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String json = this.projectSerializationManager.serializePlayerData(this.playerCaches.get(pl));
            fileUtils.save(file, json);
        }
    }

    public ProjectSerializationManager getProjectSerializationManager() {
        return this.projectSerializationManager;
    }

    public void setProjectSerializationManager(ProjectSerializationManager projectSerializationManager) {
        this.projectSerializationManager = projectSerializationManager;
    }
}
