package fr.leod1.events;

import fr.leod1.QBswitchSlotBar;
import fr.leod1.Db.fileUtils;
import fr.leod1.stockageSlots.SlotsBar;
import fr.leod1.QBswitchSlotBar;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class eventJoin implements Listener {
    @EventHandler
    public void erv(PlayerJoinEvent e) {
        Player pl = e.getPlayer();
        File DirectoryPlayerData = new File(QBswitchSlotBar.InstanceOfMain.getDataFolder() + "/PlayerData/");
        File file = new File(DirectoryPlayerData, pl.getName() + ".json");
        SlotsBar json = null;
        try {
            json = QBswitchSlotBar.InstanceOfMain.getProjectSerializationManager().deserializePlayerData(fileUtils.loadContent(file));
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
            QBswitchSlotBar.InstanceOfMain.playerCaches.put(pl, new SlotsBar(true, qzd));
            return;
        }
        QBswitchSlotBar.InstanceOfMain.playerCaches.put(pl, json);
    }
}
