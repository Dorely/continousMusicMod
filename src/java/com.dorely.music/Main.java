package com.dorely.music;

import org.apache.commons.lang3.builder.ToStringBuilder;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
//import net.minecraftforge.fml.relauncher.ReflectionHelper;

@Mod(modid = Main.MODID, name = Main.MODNAME, version = Main.VERSION)
public class Main {

    public static final String MODID = "continousMusic";
    public static final String MODNAME = "Continous Music";
    public static final String VERSION = "1.0.0";

    @Instance
    public static Main instance = new Main();

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
    	System.out.println("Called method: PreInit");
    	
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
    	System.out.println("Called method: Init");
    	
    	//MinecraftForge.EVENT_BUS.register(new MusicEventHandler());
    	//MusicTicker MTicker = ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), "mcMusicTicker");
		//ReflectionHelper.setPrivateValue(MusicTicker.class, MTicker, 0, "timeUntilNextMusic");
    	//Class MC = Minecraft.getMinecraft().getClass();
    	Minecraft MC = Minecraft.getMinecraft();
    	String MCString = ToStringBuilder.reflectionToString(MC);
    	System.out.println("Minecraft to String: "+MCString);
    	int MCTickerNameEndIndex = MCString.indexOf("net.minecraft.client.audio.MusicTicker")-1;
    	int MCTickerNameStartIndex = MCString.lastIndexOf(',', MCTickerNameEndIndex)+1;
    	String MCTickerName = MCString.substring(MCTickerNameStartIndex, MCTickerNameEndIndex);
    	
    	System.out.println("MusicTicker Name: "+MCTickerName);
    	
    	MusicEventHandler MTicker = new MusicEventHandler();
    	ObfuscationReflectionHelper.setPrivateValue(Minecraft.class, Minecraft.getMinecraft(), MTicker, MCTickerName);
    	//ReflectionHelper.setPrivateValue(Minecraft.class, Minecraft.getMinecraft(), MTicker, "mcMusicTicker");
    	  	
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    	System.out.println("Called method: PostInit");
    }
    
    
}
