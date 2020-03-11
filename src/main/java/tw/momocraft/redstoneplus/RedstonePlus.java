package tw.momocraft.redstoneplus;

import org.bukkit.plugin.java.JavaPlugin;
import tw.momocraft.redstoneplus.handlers.ConfigHandler;
import tw.momocraft.redstoneplus.handlers.ServerHandler;

public class RedstonePlus extends JavaPlugin {
    private static tw.momocraft.redstoneplus.RedstonePlus instance;

    @Override
    public void onEnable() {
        instance = this;
        ConfigHandler.generateData(getFile());
        ConfigHandler.registerEvents();
        ServerHandler.sendConsoleMessage("&fhas been Enabled.");
    }

    @Override
    public void onDisable() {
        ServerHandler.sendConsoleMessage("&fhas been Disabled.");
    }

    public static tw.momocraft.redstoneplus.RedstonePlus getInstance() {
        return instance;
    }
}