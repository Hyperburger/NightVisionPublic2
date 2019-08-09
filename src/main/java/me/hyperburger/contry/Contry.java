package me.hyperburger.contry;

import me.hyperburger.contry.listeners.NightCmd;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public final class Contry extends JavaPlugin
{
public ArrayList<Player> nv_player = new ArrayList<>();




    @Override
    public void onEnable() {
        getCommand("nv").setExecutor(new NightCmd(this));
        getConfig().options().copyDefaults();
        this.saveDefaultConfig();
        }

        // Plugin startup logic


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
