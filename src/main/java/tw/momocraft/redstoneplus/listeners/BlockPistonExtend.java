package tw.momocraft.redstoneplus.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import tw.momocraft.redstoneplus.handlers.ServerHandler;


public class BlockPistonExtend implements Listener {

    @EventHandler
    public void onBlockPistonExtend(BlockPistonExtendEvent e) {
        if (e.getBlocks().size() == 0) {
            String direction = e.getDirection().name();
            Location faceBlock;
            if (direction.equals("NORTH")) {
                faceBlock = e.getBlock().getLocation();
                faceBlock.setZ(faceBlock.getZ() + 1);
            } else if (direction.equals("SOUTH")) {
                faceBlock = e.getBlock().getLocation();
                faceBlock.setZ(faceBlock.getZ() - 1);
            } else if (direction.equals("WEST")) {
                faceBlock = e.getBlock().getLocation();
                faceBlock.setX(faceBlock.getX() - 1);
            } else {
                faceBlock = e.getBlock().getLocation();
                faceBlock.setX(faceBlock.getX() + 1);
            }
            if (e.getBlock().getWorld().getNearbyEntities(faceBlock, 2, 2, 2).isEmpty()) {
                int range = 2;
                for (int x = -range; x <= range; x++) {
                    for (int y = -range; y <= range; y++) {
                        for (int z = -range; z <= range; z++) {
                            Location loc = e.getBlock().getLocation().clone().add(x, y, z);
                            if (loc.getBlock().getType() == Material.MELON_STEM ||
                                    loc.getBlock().getType() == Material.PUMPKIN_STEM) {
                                ServerHandler.sendConsoleMessage("Cancel");
                                e.setCancelled(true);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
}
