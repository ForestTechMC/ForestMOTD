# ForestMOTD
![badge](https://img.shields.io/github/downloads/ForestTechMC/ForestMOTD/total)
![badge](https://img.shields.io/github/last-commit/ForestTechMC/ForestMOTD)
![badge](https://img.shields.io/badge/platform-spigot|bungee-lightgrey)
[![badge](https://img.shields.io/discord/896466173166747650?label=discord)](https://discord.gg/2PpdrfxhD4)
[![badge](https://img.shields.io/github/license/ForestTechMC/ForestMOTD)](https://github.com/ForestTechMC/ForestMOTD/blob/master/LICENSE.txt)

Plugin with config and API for your MOTD!

## Table of contents

* [Getting started](#getting-started)
* [Config](#config)
* [API Usage](#api-usage)
* [License](#license)

## Getting started

1. Turn off server
2. Add ForestSit into /plugins
3. Turn on server
4. And have fun!

### Config

Here you can see the configuration interface

<details>
    <summary>Config</summary>

```yml
#
# ForestMOTD v1.0
#
# | If you find bug or you have an idea for an adjustment, please contact us on
#   https://discord.com/invite/2PpdrfxhD4
#
# | %online% - Return number of players on server
# | %defaultMax% - Default server max server player size
# | %version% - Server version
# | %configPlayersMax% - Config player max number
#
# | Permission = forestMOTD.admin
#

#
# MOTD
#
# | Use ":n:" to do second line
#   "maxPlayers: -1" - This will set default server number
#
motd:
  maxPlayers: 69
  # | 64x64 size icon
  iconName: "server-icon.png"
  text: "{#30FF5A>}&lForest{#7DFFC2<}&f&lMOTD:n:&rBest server in the Universe!"
  #
  # | If you want to use hover box on spigot, download ProtocolLib! (5.0.0+)
  #
  hoverBox:
    - "&c❤  &a&lForest&7&lTech &c❤"
    - " "
    - " &aPlugins with &c❤"
    - " "

#
# | Messages
#
message:
  noPerm: "{#malachite>}&lForest{#bluestone<}&f&lMOTD &7You don't have sufficient permissions..."
  reload: "{#malachite>}&lForest{#bluestone<}&f&lMOTD &7Config & Data reloaded..."
```
</details>

### API Usage

```java

    // Only think you have to know if you want to change it on Spigot or Bungee
    // BungeeForestMOTDAPI | SpigotForestMOTDAPI

    public static void reloadData() {
        Bungee.getInstance().getBungeeMOTDManager().loadData();
    }

    public static void changeMOTD(String motdText) {
        Bungee.getInstance().getBungeeMOTDManager().setMotdText(motdText);
    }

    public static void changeIcon(String iconName) {
        Bungee.getInstance().getBungeeMOTDManager().changeIconName(iconName);
    }

    public static void changeMaxPlayers(int maxPlayers) {
        Bungee.getInstance().getBungeeMOTDManager().setMaxPlayers(maxPlayers);
    }

    public static void changeHoverBox(List<String> list) {
        Bungee.getInstance().getBungeeMOTDManager().setHoverBox(list);
    }


```





## License
ForestSit is licensed under the permissive MIT license. Please see [`LICENSE.txt`](https://github.com/ForestTechMC/ForestMOTD/blob/master/LICENSE.txt) for more information.