package tw.momocraft.redstoneplus.handlers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import tw.momocraft.redstoneplus.Commands;
import tw.momocraft.redstoneplus.RedstonePlus;
import tw.momocraft.redstoneplus.listeners.BlockGrow;
import tw.momocraft.redstoneplus.listeners.BlockPistonExtend;
import tw.momocraft.redstoneplus.listeners.BlockRedstone;
import tw.momocraft.redstoneplus.utils.DependAPI;
import tw.momocraft.redstoneplus.utils.Utils;

import java.io.File;

public class ConfigHandler {

	private static YamlConfiguration configYAML;
	private static DependAPI depends;

	public static void generateData(File file) {
		configFile();
		setDepends(new DependAPI());
		sendUtilityDepends();
	}

	public static void registerEvents() {
		tw.momocraft.redstoneplus.RedstonePlus.getInstance().getCommand("redstoneplus").setExecutor(new Commands());
		tw.momocraft.redstoneplus.RedstonePlus.getInstance().getServer().getPluginManager().registerEvents(new BlockRedstone(), RedstonePlus.getInstance());
		tw.momocraft.redstoneplus.RedstonePlus.getInstance().getServer().getPluginManager().registerEvents(new BlockPistonExtend(), RedstonePlus.getInstance());
		tw.momocraft.redstoneplus.RedstonePlus.getInstance().getServer().getPluginManager().registerEvents(new BlockGrow(), RedstonePlus.getInstance());
	}

	public static FileConfiguration getConfig(String path) {
		File file = new File(tw.momocraft.redstoneplus.RedstonePlus.getInstance().getDataFolder(), path);
		if (configYAML == null) {
			getConfigData(path);
		}
		return getPath(path, file, false);
	}

	public static FileConfiguration getConfigData(String path) {
		File file = new File(tw.momocraft.redstoneplus.RedstonePlus.getInstance().getDataFolder(), path);
		if (!(file).exists()) {
			try {
				tw.momocraft.redstoneplus.RedstonePlus.getInstance().saveResource(path, false);
			} catch (Exception e) {
				tw.momocraft.redstoneplus.RedstonePlus.getInstance().getLogger().warning("Cannot save " + path + " to disk!");
				return null;
			}
		}
		return getPath(path, file, true);
	}

	public static YamlConfiguration getPath(String path, File file, boolean saveData) {
		if (path.contains("config.yml")) {
			if (saveData) {
				configYAML = YamlConfiguration.loadConfiguration(file);
			}
			return configYAML;
		}
		return null;
	}

	public static void configFile() {
		getConfigData("config.yml");
		File File = new File(tw.momocraft.redstoneplus.RedstonePlus.getInstance().getDataFolder(), "config.yml");
		if (File.exists() && getConfig("config.yml").getInt("config-Version") != 1) {
			if (tw.momocraft.redstoneplus.RedstonePlus.getInstance().getResource("config.yml") != null) {
				String newGen = "config" + Utils.getRandom(1, 50000) + ".yml";
				File newFile = new File(tw.momocraft.redstoneplus.RedstonePlus.getInstance().getDataFolder(), newGen);
				if (!newFile.exists()) {
					File.renameTo(newFile);
					File configFile = new File(tw.momocraft.redstoneplus.RedstonePlus.getInstance().getDataFolder(), "config.yml");
					configFile.delete();
					getConfigData("config.yml");
					ServerHandler.sendConsoleMessage("&aYour config.yml is out of date and new options are available, generating a new one!");
				}
			}
		}
		getConfig("config.yml").options().copyDefaults(false);
	}

	private static void sendUtilityDepends() {
		ServerHandler.sendConsoleMessage("&fUtilizing [ &e"
				+ (getDepends().getVault().vaultEnabled() ? "Vault, " : "")
				+ (getDepends().ResidenceEnabled() ? "Residence " : "")
				+ "&f]");
	}

	public static DependAPI getDepends() {
		return depends;
	}

	private static void setDepends(DependAPI depend) {
		depends = depend;
	}
}