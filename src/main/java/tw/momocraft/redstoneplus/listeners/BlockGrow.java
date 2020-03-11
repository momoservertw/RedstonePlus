package tw.momocraft.redstoneplus.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import tw.momocraft.redstoneplus.handlers.ServerHandler;

import java.util.HashSet;

public class BlockGrow implements Listener {

    @EventHandler
    public void onBlockPistonExtend(BlockGrowEvent e) {
        ServerHandler.sendConsoleMessage("Grow");
        if (e.getBlock().getType().equals(Material.MELON_STEM) ||
                e.getBlock().getType().equals(Material.MELON_STEM)) {
            Block growBlock = e.getNewState().getBlock();
            e.
            if (growBlock.getType().equals(Material.AIR)) {
                HashSet<Material> list = new HashSet<Material>();
                int range = 2;
                for (int x = -range; x <= range; x++) {
                    for (int y = -range; y <= range; y++) {
                        for (int z = -range; z <= range; z++) {
                            Material block = e.getBlock().getLocation().clone().add(x, y, z).getBlock().getType();
                            if (block == Material.OBSERVER || block == Material.PISTON || block == Material.STICKY_PISTON) {
                                list.add(block);
                            }
                        }
                    }
                }

                if (list.contains(Material.OBSERVER) && list.contains(Material.PISTON) ||
                        list.contains(Material.OBSERVER) && list.contains(Material.STICKY_PISTON))
                ServerHandler.sendConsoleMessage("Cancel and drop");
                e.setCancelled(true);
                return;
            }
        }
    }
}
