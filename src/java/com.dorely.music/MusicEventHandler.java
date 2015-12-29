package com.dorely.music;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.PositionedSoundRecord;


public class MusicEventHandler extends MusicTicker {
	
    private final Minecraft mc = Minecraft.getMinecraft();
    private ISound currentMusic;
    private int timeUntilNextMusic = 100;
    
	 public MusicEventHandler(){
		 super(Minecraft.getMinecraft());
	 }
	
	 @Override
	 public void update()
	    {
	        MusicTicker.MusicType musictype = this.mc.getAmbientMusicType();

	        if (this.currentMusic != null)
	        {
	            if (!musictype.getMusicLocation().equals(this.currentMusic.getSoundLocation()))
	            {
	                this.mc.getSoundHandler().stopSound(this.currentMusic);
	                this.timeUntilNextMusic = 100;
	            }

	            if (!this.mc.getSoundHandler().isSoundPlaying(this.currentMusic))
	            {
	                this.currentMusic = null;
	                this.timeUntilNextMusic = 100;
	            }
	        }

	        if (this.currentMusic == null && this.timeUntilNextMusic-- <= 0)
	        {
	            this.currentMusic = PositionedSoundRecord.create(musictype.getMusicLocation());
	            this.mc.getSoundHandler().playSound(this.currentMusic);
	            this.timeUntilNextMusic = 100;
	        }
	    }
}
