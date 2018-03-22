package lookapp.com.lookapp;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by liorr on 3/22/18.
 */

public class SoundPlayer {

    MediaPlayer mediaPlayer;

    public SoundPlayer(Context context) {
        mediaPlayer = MediaPlayer.create(context, R.raw.alert);
    }

    public void playSound() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    public void stopSound() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}
