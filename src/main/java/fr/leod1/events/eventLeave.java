package fr.leod1.events;


import fr.leod1.Db.fileUtils;
import fr.leod1.stockageSlots.SlotsBar;
import fr.leod1.QBswitchSlotBar;
import java.io.File;
import java.io.IOException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class eventLeave implements Listener {
    @EventHandler
    public void fqsd(PlayerQuitEvent e) {
        Player pl = e.getPlayer();
        File DirectoryPlayerData = new File(QBswitchSlotBar.InstanceOfMain.getDataFolder(), "/PlayerData/");
        File file = new File(DirectoryPlayerData, pl.getName() + ".json");
        try {
            fileUtils.createFile(file);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        SlotsBar zebi = (SlotsBar)QBswitchSlotBar.InstanceOfMain.playerCaches.get(pl);
        String json = QBswitchSlotBar.InstanceOfMain.getProjectSerializationManager().serializePlayerData(zebi);
        fileUtils.save(file, json);
    }
}