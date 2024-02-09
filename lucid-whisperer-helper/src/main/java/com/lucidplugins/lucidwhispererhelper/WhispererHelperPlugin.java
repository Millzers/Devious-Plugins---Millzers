package com.lucidplugins.lucidwhispererhelper;

import com.google.inject.Provides;
import com.lucidplugins.lucidwhispererhelper.api.util.CombatUtils;
import com.lucidplugins.lucidwhispererhelper.api.util.InteractionUtils;
import com.lucidplugins.lucidwhispererhelper.api.util.InventoryUtils;
import com.lucidplugins.lucidwhispererhelper.api.util.NpcUtils;
import com.lucidplugins.lucidwhispererhelper.overlay.WhispererHelperOverlay;
import lombok.Getter;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.*;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import org.pf4j.Extension;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@PluginDescriptor(
        name = "Lucid Whisperer Helper",
        description = "Auto-prays against whisperer and more",
        enabledByDefault = false,
        tags = {"whisperer", "dt2"}
)
@Extension
public class WhispererHelperPlugin extends Plugin
{

    @Inject
    private Client client;

    @Inject
    private ClientThread clientThread;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private WhispererHelperOverlay overlay;

    @Inject
    private WhispererHelperConfig config;

    private List<Projectile> attackProjectiles = new ArrayList<>();

    private static final int LEECH_OBJECT_ACTIVE_ID = 47573;
    private static final int LEECH_OBJECT_INACTIVE_ID = 47575;
    private static final int PILLAR_NPC_ID = 12209;
    public static final int TENTACLE_NPC_ID = 12208;

    private boolean negativeVert = false;
    private boolean negativeDiag = false;

    private int pillarSpawnTick = 0;

    @Getter
    private List<LocalPoint> leeches = new ArrayList<>();

    @Getter
    private int bindTicks = 0;

    @Getter
    private List<NPC> vitas = new ArrayList<>();

    @Getter
    private LocalPoint mostHealthPillar = null;

    @Getter
    private LocalPoint nextMostHealthPillar = null;

    @Getter
    private LocalPoint leastHealthPillar = null;


    private boolean inSpecial = false;

    private boolean activateFragment = false;

    private int lastDodgeTick = 0;

    private final int MAGE_PROJ = 2445;
    private final int MELEE_PROJ = 2467;
    private final int RANGED_PROJ = 2444;

    @Provides
    WhispererHelperConfig getConfig(final ConfigManager configManager)
    {
        return configManager.getConfig(WhispererHelperConfig.class);
    }

    @Subscribe
    private void onGameTick(final GameTick tick)
    {
        vitas = NpcUtils.getAll(npc -> npc != null && npc.getOverheadText() != null && npc.getOverheadText().equals("Vita!"));

        List<NPC> activePillars = NpcUtils.getAll(npc -> npc.getId() == PILLAR_NPC_ID && getHpPercent(npc) > 0);

        if (mostHealthPillar == null && nextMostHealthPillar == null && leastHealthPillar == null)
        {
            if (activePillars != null && activePillars.size() > 0)
            {
                setPillars(activePillars);
            }
        }
        else
        {
            if (getTicksSincePillarSpawn() > 23)
            {
                mostHealthPillar = null;
                nextMostHealthPillar = null;
                leastHealthPillar = null;
            }
        }

        if (bindTicks > 0)
        {
            bindTicks--;
        }

        NPC whisperer = NpcUtils.getNearest(npc -> npc != null && npc.getName().contains("Whisperer"));
        if (whisperer != null && whisperer.getAnimation() == 10257)
        {
            Item venator = InventoryUtils.getFirstItem(ItemID.VENATOR_BOW);
            if (venator != null)
            {
                InventoryUtils.itemInteract(venator.getId(), "Wield");
            }
        }

        if (lastDodgeTick != client.getTickCount() && (client.getTickCount() - lastDodgeTick) < 3)
        {
            if (whisperer == null || client.getLocalPlayer().getInteracting() == whisperer)
            {
                return;
            }

            int hpPercent = getHpPercent(whisperer);

            if (hpPercent <= 0)
            {
                return;
            }

            if (config.autoAttack())
            {
                NpcUtils.interact(whisperer, "Attack");
            }

        }

        if (activateFragment)
        {
            if (config.autoFragment())
            {
                Item fragment = InventoryUtils.getFirstItem("Blackstone fragment");
                if (fragment != null)
                {
                    InventoryUtils.itemInteract(fragment.getId(), "Activate");
                }
            }

            activateFragment = false;
        }


        if (config.autoPray())
        {
            if (attackProjectiles == null || attackProjectiles.size() == 0)
            {
                if (client.isPrayerActive(Prayer.PROTECT_FROM_MISSILES))
                {
                    CombatUtils.togglePrayer(client, Prayer.PROTECT_FROM_MISSILES);
                }
                if (client.isPrayerActive(Prayer.PROTECT_FROM_MAGIC))
                {
                    CombatUtils.togglePrayer(client, Prayer.PROTECT_FROM_MAGIC);
                }
                if (client.isPrayerActive(Prayer.PROTECT_FROM_MELEE))
                {
                    CombatUtils.togglePrayer(client, Prayer.PROTECT_FROM_MELEE);
                }
            }
        }
    }

    private void setPillars(List<NPC> activePillars)
    {
        NPC mostHealth = null;
        NPC nextMostHealth = null;
        NPC leastHealth = null;

        for (NPC npc : activePillars)
        {
            if (getHpPercent(npc) == 100)
            {
                mostHealth = npc;
                break;
            }
        }

        if (mostHealth != null)
        {
            List<NPC> nextCandidates = NpcUtils.getAll(npc -> npc.getId() == PILLAR_NPC_ID && getHpPercent(npc) == 67);

            float leastDistance = 999;
            NPC closest = null;

            for (NPC npc : nextCandidates)
            {
                float distance = InteractionUtils.distanceTo2DHypotenuse(npc.getWorldLocation(), mostHealth.getWorldLocation());
                if (distance < leastDistance)
                {
                    closest = npc;
                    leastDistance = distance;
                }
            }

            if (closest != null)
            {
                nextMostHealth = closest;
            }
        }

        if (nextMostHealth != null)
        {
            List<NPC> nextCandidates = NpcUtils.getAll(npc -> npc.getId() == PILLAR_NPC_ID && getHpPercent(npc) == 35);
            float leastDistance = 999;
            NPC closest = null;

            for (NPC npc : nextCandidates)
            {
                float distance = InteractionUtils.distanceTo2DHypotenuse(npc.getWorldLocation(), mostHealth.getWorldLocation());
                if (distance < leastDistance)
                {
                    closest = npc;
                    leastDistance = distance;
                }
            }

            if (closest != null)
            {
                leastHealth = closest;
            }
        }

        if (mostHealth != null && nextMostHealth != null && leastHealth != null)
        {
            final WorldPoint mostHealthPoint = mostHealth.getWorldLocation().dy(2);
            final WorldPoint nextMostHealthPoint = nextMostHealth.getWorldLocation().dy(2);
            final WorldPoint leastHealthPoint = leastHealth.getWorldLocation().dy(2);

            mostHealthPillar = LocalPoint.fromWorld(client, mostHealthPoint);
            nextMostHealthPillar = LocalPoint.fromWorld(client, nextMostHealthPoint);;
            leastHealthPillar = LocalPoint.fromWorld(client, leastHealthPoint);;
            activateFragment = true;
            pillarSpawnTick = client.getTickCount();
        }
    }

    public int getTicksSincePillarSpawn()
    {
        return client.getTickCount() - pillarSpawnTick;
    }

    private int getHpPercent(NPC npc)
    {
        if (npc == null)
        {
            return 0;
        }

        int ratio = npc.getHealthRatio();
        int scale = npc.getHealthScale();

        return (int) Math.floor((double) ratio  / (double) scale * 100);
    }

    @Subscribe
    private void onChatMessage(ChatMessage event)
    {
        if (event.getMessage().contains("blackstone fragment pulses"))
        {
            inSpecial = true;
            activateFragment = true;
        }

        if (event.getMessage().contains("blackstone fragment loses"))
        {
            inSpecial = false;
            leeches.clear();
        }

        if (event.getMessage().contains("binds you in place"))
        {
            bindTicks = 8;
        }
    }

    @Subscribe
    private void onClientTick(final ClientTick tick)
    {
        attackProjectiles.removeIf(proj -> proj.getRemainingCycles() < 1);

        WorldPoint fromInstance = WorldPoint.fromLocalInstance(client, client.getLocalPlayer().getLocalLocation());
        leeches.removeIf(leech -> {
            WorldPoint wp = WorldPoint.fromLocalInstance(client, leech);
            return wp.getRegionX() == fromInstance.getRegionX() && wp.getRegionY() == fromInstance.getRegionY();
        });

        Prayer prayer = null;
        int lowestRemaining = 999;

        for (Projectile projectile : attackProjectiles)
        {

            if (projectile.getRemainingCycles() < lowestRemaining)
            {
                prayer = getPrayer(projectile.getId());
                lowestRemaining = projectile.getRemainingCycles();
            }
        }

        if (prayer != null)
        {
            if (config.autoPray())
            {
                CombatUtils.activatePrayer(client, prayer);
            }
        }
    }

    @Subscribe
    private void onNpcSpawned(final NpcSpawned event)
    {
        if (event.getNpc().getId() == TENTACLE_NPC_ID && lastDodgeTick != client.getTickCount())
        {
            lastDodgeTick = client.getTickCount();
            if (config.autoDodge())
            {
                switch (event.getNpc().getOrientation())
                {
                    case 1536:
                    case 1024:
                    case 512:
                    case 0:
                        negativeDiag = !negativeDiag;
                        InteractionUtils.walk(client.getLocalPlayer().getWorldLocation().dx(negativeDiag ? negativeVert ? 1 : -1 : 1).dy(negativeDiag ? negativeVert ? 1 : -1 : 1));
                        if (negativeVert)
                        {
                            negativeVert = false;
                        }
                        break;
                    default:
                        negativeVert = !negativeVert;
                        InteractionUtils.walk(client.getLocalPlayer().getWorldLocation().dx(negativeVert ? -1 : 1));
                        break;
                }
            }
        }
    }

    @Subscribe
    private void onGameObjectSpawned(GameObjectSpawned event)
    {
        if (event.getGameObject().getId() == LEECH_OBJECT_ACTIVE_ID)
        {
            leeches.add(event.getGameObject().getLocalLocation());
            activateFragment = true;
        }
    }

    @Subscribe
    private void onProjectileMoved(final ProjectileMoved event)
    {
        int id = event.getProjectile().getId();

        if (id != MAGE_PROJ && id != RANGED_PROJ && id != MELEE_PROJ)
        {
            return;
        }

        if (!containsProjectile(event.getProjectile()))
        {
            attackProjectiles.add(event.getProjectile());
        }
    }

    private Prayer getPrayer(int id)
    {
        switch (id)
        {
            case MAGE_PROJ:
                return Prayer.PROTECT_FROM_MAGIC;
            case RANGED_PROJ:
                return Prayer.PROTECT_FROM_MISSILES;
            case MELEE_PROJ:
                return Prayer.PROTECT_FROM_MELEE;
        }
        return null;
    }

    private boolean containsProjectile(Projectile projectile)
    {
        for (Projectile proj : attackProjectiles)
        {
            if (proj.getId() == projectile.getId() && proj.getRemainingCycles() == projectile.getRemainingCycles())
            {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void startUp()
    {
        clientThread.invoke(this::pluginEnabled);
    }

    private void pluginEnabled()
    {
        if (!overlayManager.anyMatch(p -> p == overlay))
        {
            overlayManager.add(overlay);
        }
    }

    @Override
    protected void shutDown()
    {
        if (overlayManager.anyMatch(p -> p == overlay))
        {
            overlayManager.remove(overlay);
        }
    }
}