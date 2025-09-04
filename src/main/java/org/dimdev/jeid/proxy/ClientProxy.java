package org.dimdev.jeid.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends ServerProxy{


    @Override
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
