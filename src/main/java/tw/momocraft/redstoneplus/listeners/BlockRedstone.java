package tw.momocraft.redstoneplus.listeners;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import tw.momocraft.redstoneplus.handlers.ConfigHandler;

public class BlockRedstone implements Listener {


    @EventHandler
    public void onBlockRedstone(org.bukkit.event.block.BlockRedstoneEvent e) {
        if (e.getBlock().getType() == Material.REDSTONE_WIRE || e.getBlock().getType() == Material.REPEATER) {
            if (e.getOldCurrent() == 0) {
                if (getIgnore(e.getBlock().getLocation())) {
                    return;
                }
                // add and check
            }
        }

    }

    private boolean getIgnore(Location loc) {
        if (ConfigHandler.getDepends().ResidenceEnabled()) {
            ClaimedResidence res = Residence.getInstance().getResidenceManager().getByLoc(loc);
            //Residence - In protect area.
            if (res != null) {
                return ConfigHandler.getConfig("config.yml").getStringList("Spawn.").contains(res.getResidenceName());
            }
            return false;
        }
        return true;
    }



}
