package org.dimdev.jeid;

import net.minecraft.potion.Potion;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeVoid;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.dimdev.jeid.network.MessageManager;
import org.dimdev.jeid.proxy.ServerProxy;

@Mod(modid = "jeid", name = "JustEnoughIDs", acceptedMinecraftVersions = "[1.12,1.13)", version = "1.0.4-SNAPSHOT")
@Mod.EventBusSubscriber()
public class JEID {
    public static final Biome errorBiome = new BiomeVoid(new Biome.BiomeProperties("A mod doesn't support extended biome IDs -- report to JEID"))
            .setRegistryName("jeid:error_biome");

    @Mod.Instance("jeid")
    public static JEID instance;

    @SidedProxy(clientSide = "org.dimdev.jeid.proxy.ClientProxy", serverSide = "org.dimdev.jeid.proxy.ServerProxy")
    public static ServerProxy proxy;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        proxy.init();
    }

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        proxy.preInit();
        // Register messages
        MessageManager.init();
        // Error Biome Registration
        GameRegistry.findRegistry(Biome.class).register(errorBiome);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        JEIDTransformer.REGISTRY = net.minecraftforge.registries.GameData.getWrapper(Potion.class);
    }


}