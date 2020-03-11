package tw.momocraft.redstoneplus.utils;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import tw.momocraft.redstoneplus.handlers.ConfigHandler;
import tw.momocraft.redstoneplus.handlers.ServerHandler;

public class VaultAPI {
    private Economy econ = null;
    private boolean isEnabled = false;

    public VaultAPI() {
        this.setVaultStatus(Bukkit.getServer().getPluginManager().getPlugin("Vault") != null);
    }

    private void enableEconomy() {
        if (ConfigHandler.getConfig("config.yml").getBoolean("softDepend.Vault") && tw.momocraft.redstoneplus.RedstonePlus.getInstance().getServer().getPluginManager().getPlugin("Vault") != null) {
            if (!this.setupEconomy()) {
                ServerHandler.sendErrorMessage("There was an issue setting up Vault to work with RedstonePlus!");
                ServerHandler.sendErrorMessage("If this continues, please contact the plugin developer!");
            }
        }
    }

    private boolean setupEconomy() {
        if (tw.momocraft.redstoneplus.RedstonePlus.getInstance().getServer().getPluginManager().getPlugin("Vault") == null) {  return false; }
        RegisteredServiceProvider<Economy> rsp = tw.momocraft.redstoneplus.RedstonePlus.getInstance().getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {  return false; }
        this.econ = rsp.getProvider();
        return this.econ != null;
    }

    public Economy getEconomy() {
        return this.econ;
    }

    public boolean vaultEnabled() {
        return this.isEnabled;
    }

    private void setVaultStatus(boolean bool) {
        if (bool) { this.enableEconomy(); }
        this.isEnabled = bool;
    }
}