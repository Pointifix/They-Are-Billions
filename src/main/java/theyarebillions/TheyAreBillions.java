package theyarebillions;

import arc.Events;
import arc.util.Align;
import arc.util.CommandHandler;
import arc.util.Log;
import arc.util.Timer;
import mindustry.Vars;
import mindustry.entities.type.Player;
import mindustry.game.EventType;
import mindustry.gen.Call;
import mindustry.plugin.Plugin;
import mindustry.type.Item;
import mindustry.world.modules.ItemModule;

import java.util.HashMap;
import java.util.TimerTask;

public class TheyAreBillions extends Plugin {
    HashMap<String, String> itemUnicodes = new HashMap<String, String>() {
        {
            put("copper", "\uF838");
            put("lead", "\uF837");
            put("metaglass", "\uF836");
            put("graphite", "\uF835");
            put("titanium", "\uF832");
            put("thorium", "\uF831");
            put("silicon", "\uF82F");
            put("plastanium", "\uF82E");
            put("phase-fabric", "\uF82D");
            put("surge-alloy", "\uF82C");
        }
    };

    //register event handlers and create variables in the constructor
    public TheyAreBillions() {

    }

    @Override
    public void init() {
        Log.info("TheyAreBillions Plugin loaded");

        Events.on(EventType.PlayerJoin.class, event -> {
            Call.onInfoPopup("onInfoPopup", Float.MAX_VALUE, Align.center, 50,50,50,50);

            Call.onLabel("\uF838onLabel", Float.MAX_VALUE, 150,50);

            Call.onLabel();

            event.player.getTeam().core().tile
        });

        Events.on(EventType.Trigger.update, ()-> {

        });

        Vars.unitGroup.all().first().getStartState();

        Timer.Task coreItemsInfoToastTask = Timer.schedule(new CoreItemsInfoToast(), 0, 1);
    }

    class CoreItemsInfoToast extends TimerTask {
        public void run() {
            for (Player player : Vars.playerGroup) {
                ItemModule coreItems = player.getTeam().core().items;

                String coreItemsMessage = "";
                for (Item item : Vars.content.items()) {
                    if (coreItems.get(item) != 0) coreItemsMessage += itemUnicodes.get(item.toString()) + coreItems.get(item) + " ";
                }
                Call.onInfoToast(coreItemsMessage, 1.05f);
            }
        }
    }

    //register commands that run on the server
    @Override
    public void registerServerCommands(CommandHandler handler){

    }

    //register commands that player can invoke in-game
    @Override
    public void registerClientCommands(CommandHandler handler){

    }
}
