package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	Clip clip;
	URL soundURL[]=new URL[30];
	float prevVol = 0;
    float cVol = 6.0f;
    boolean mute = false;
    FloatControl fc;
	public Sound() {
		soundURL[0]=getClass().getResource("/sound/backgroundSTMTP.wav");
		//System.out.print("done");
	
	}
	public void setFile(int i) {
		try {
			AudioInputStream ais=AudioSystem.getAudioInputStream(soundURL[i]);
			clip=AudioSystem.getClip();
			clip.open(ais);
			fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            fc.setValue(cVol);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void play() {
		clip.start();
	}
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		clip.stop();
	}
	public void volumeUp() {
		
		cVol += 3.0f;
        if(cVol > 6.0f) {
            cVol = 6.0f;
        }
        fc.setValue(cVol);
	}
	public void volumeDown() {
        cVol -= 3.0f;
        if(cVol < -77.0f) {
            cVol = -80.0f;
        }
        fc.setValue(cVol);  
    }
    public void mute() {
        if(mute == false) {
            prevVol = cVol;
            cVol = -80.0f;
            fc.setValue(cVol);
            mute = true;
        }else if(mute == true) {
            cVol = prevVol;
            fc.setValue(cVol);
            mute = false;
            
        }
    }
}
