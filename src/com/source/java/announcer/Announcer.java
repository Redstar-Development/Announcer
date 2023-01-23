//Copyright (C) 2014-2015 Vertanzil . All rights reserved.
//This software is copyrighted work. Copying or reproducing the
//Software to any other server or location for further reproduction
//or redistribution is prohibited, unless such reproduction or
//redistribution is permitted by a license agreement accompanying
//such Software. You may not create derivative works of the Software,
//or attempt to decompile or reverse-engineer the Software unless
//otherwise permitted by law. Use of the Software is subject to
//the license terms of any license agreement that may accompany
//or is provided with the Software.
//Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
//CONDITIONS OF ANY KIND, either express or implied.
package com.source.java.announcer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.util.List;
import java.util.Random;

@SuppressWarnings("ALL")
public class Announcer extends JavaPlugin {
    private int nextLine = 0;
    private final Random random = new Random();
    private List<String> strings = getConfig().getStringList("Announcer.messages");
    private Announcer plugin;



    @Override
    public void onEnable() {
        //getConfig().options().copyDefaults(true);
        //saveConfig();
        this.plugin = plugin;
        if (CheckConfig("config")) {
            if (getConfig().getBoolean("Announcer.Timers.Enabled", true)) {
                if (strings.isEmpty()) {
                    System.out.print("Announcer emmpty doing nothing.");
                } else {
                    Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                        @Override
                        public void run() {
                            String stt = Utils_Chat.replaceColors(strings.get(random.nextInt(strings.size())));
                            Bukkit.broadcastMessage(stt);
                            nextLine++;
                            nextLine = nextLine % strings.size();
                        }
                    }, getConfig().getInt("Announcer.Timers.Startup-Timer"), getConfig().getInt("Announcer.Timers.Announcer-Timer"));
                }
            }
        } else {
            System.out.print("Error the config file" + " " + this.getDataFolder() + File.separator + "config.yml" + " " + "is either missing or broken copying from default.");
            System.out.print("Reloading plugin hold on to your pants.");
            this.saveResource("config.yml", true);
        }
    }


    public void onDisable() {
        Bukkit.getServer().getScheduler().cancelAllTasks();
    }

    boolean CheckConfig(String config) {
        File file = new File(this.getDataFolder() + File.separator + config + ".yml");
        return file.exists();
    }
}

