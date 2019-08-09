package me.hyperburger.contry.listeners;

import me.hyperburger.contry.ConfingManager;
import me.hyperburger.contry.Contry;
import me.hyperburger.contry.Methods;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;



public class NightCmd implements CommandExecutor {


    Contry plugin;

    public NightCmd(Contry plugin) {
        this.plugin = plugin;
    }

    String noperm = Methods.pl("&7Only &8[&6Alien&8]&c+ &7can use &b/nv");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        //Text Components --
        TextComponent msg = new TextComponent(Methods.pl("&cHey, you already have nightvision.\n&cif you want to turn it off use: &b/nv off \n&cor &b&nclick on the message&7."));
        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Methods.pl("&cClick to turn off&7.")).create()));
        msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/nv off"));

        TextComponent msgoff = new TextComponent(Methods.pl("&7You have &bturned off &7your night vision.\n&bClick&7 the message to turn it on."));
        msgoff.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Methods.pl("&aClick to activate&7.")).create()));
        msgoff.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/nv"));

        String titlemsg = Methods.pl("&c&lヽ(ಠ_ಠ)ノ");
        String subtitle = Methods.pl("&f(( &7You do not have access&f ))");



        //   If player has nightvision already
        if (args.length == 0) {
            if (p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                p.spigot().sendMessage(msg);
                return true;
            }
        }

        //Check for permission and add night vision.
        if (p.hasPermission("nv.use")){
            if (args.length == 0) {
                plugin.nv_player.add(p);
                if (plugin.nv_player.contains(p)) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 90000, 2));
                    p.sendMessage(Methods.pl("&aYou have used your night vision perk!"));
                    return true;
                }
            }

    }else if(args.length == 0){
            p.sendMessage(Methods.pl(noperm));
            p.sendTitle(titlemsg, subtitle, 20, 20, 20);
            return true;
        }

        switch (args[0].toLowerCase()) {

            case "off": { //Nv off
                if(p.hasPermission("nv.use")) {
                    p.spigot().sendMessage(msgoff);
                    plugin.nv_player.remove(p);
                    p.removePotionEffect(PotionEffectType.NIGHT_VISION);
                    return true;
                }else if(!p.hasPermission("nv.use")){
                    p.sendMessage(noperm);
                }
                break;
            }

            case "help":{
                p.sendMessage(Methods.pl("&7&m----------------------------"));
                p.sendMessage(Methods.pl("&8* &cCoded by HyperBurger"));
                p.sendMessage(Methods.pl("&7&m----------------------------"));
                break;
            }

            default:
                p.sendMessage(Methods.pl("&cIncorrect Args."));

        }




        return true;
    }
}
