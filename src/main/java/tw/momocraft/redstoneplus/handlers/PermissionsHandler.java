package tw.momocraft.redstoneplus.handlers;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class PermissionsHandler {

	public static boolean hasPermission(CommandSender sender, String permission) {
		if (sender.hasPermission(permission) || sender.hasPermission("redstoneplus.*") || sender.hasPermission("redstoneplus.all") || sender.isOp() || (sender instanceof ConsoleCommandSender)) {
			return true;
		}
		return false;
	}
}