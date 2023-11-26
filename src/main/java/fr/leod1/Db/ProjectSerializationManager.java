package fr.leod1.Db;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.leod1.stockageSlots.SlotsBar;

public class ProjectSerializationManager {
    private Gson gson = createRightGson();

    private Gson createRightGson() {
        return (new GsonBuilder()).setPrettyPrinting().serializeNulls().disableHtmlEscaping().create();
    }

    public String serializePlayerData(SlotsBar dataPlayer) {
        return this.gson.toJson(dataPlayer);
    }

    public SlotsBar deserializePlayerData(String json) {
        return (SlotsBar)this.gson.fromJson(json, SlotsBar.class);
    }
}
