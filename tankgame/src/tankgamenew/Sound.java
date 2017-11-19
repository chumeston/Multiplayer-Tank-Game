package tankgamenew;
import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {

        public static final AudioClip bullet = Applet.newAudioClip(Sound.class.getResource("bullet.wav"));
        public static final AudioClip music = Applet.newAudioClip(Sound.class.getResource("menusound.wav"));
}