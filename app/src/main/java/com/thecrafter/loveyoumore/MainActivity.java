package com.thecrafter.loveyoumore;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.thecrafter.loveyoumore.audio.AudioHandler;
import com.thecrafter.loveyoumore.audio.AudioWrapper;
import com.thecrafter.loveyoumore.util.RandomIntGenerator;

import java.io.IOException;
import java.util.Vector;


public class MainActivity extends Activity {

    private AudioHandler mAudioHandler;
    private AudioHandler mAudioHandler2;
    private String[] mMsgArray;
    private RandomIntGenerator mMsgIndexGenerator;

    private Toast mToast; // Shared toast to show simple messages
    private MediaPlayer mClickSoundPlayer; // Media player for onClick sounds

    // Animations
    private Animation mBumpAnimation;
    private Animation mRotateAnimation;
    private Animation mFadeAnimation;

    // Modes
    private enum Mode {
        MUSIC1,
        TEXT1,
        MUSIC2
    }
    private Mode mMode = Mode.MUSIC1;

    private void nextMode() {
        Mode next;
        switch (mMode) {
            case MUSIC1: next = Mode.TEXT1; break;
            case TEXT1:  next = Mode.MUSIC2; break;
            case MUSIC2: next = Mode.MUSIC1;  break;
            default:     next = Mode.MUSIC1;
        }
        mMode = next;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init onClick Sound Player
        mClickSoundPlayer = MediaPlayer.create(this, R.raw.buttonclicksound);

        // Init animations
        mBumpAnimation = AnimationUtils.loadAnimation(this, R.anim.heart_scaleup_anim);
        mRotateAnimation = AnimationUtils.loadAnimation(this, R.anim.heart_rotate_anim);
        mFadeAnimation = AnimationUtils.loadAnimation(this, R.anim.heart_fade_anim);

        // Create audio vectors and fill them with sound resources
        Vector<AudioWrapper> audioVector = new Vector<>();
        audioVector.add(new AudioWrapper(this, R.raw.msg1));
        audioVector.add(new AudioWrapper(this, R.raw.msg2));
        audioVector.add(new AudioWrapper(this, R.raw.msg3));
        audioVector.add(new AudioWrapper(this, R.raw.msg4));
        audioVector.add(new AudioWrapper(this, R.raw.msg5));
        audioVector.add(new AudioWrapper(this, R.raw.msg6));
        audioVector.add(new AudioWrapper(this, R.raw.msg7));
        audioVector.add(new AudioWrapper(this, R.raw.msg8));
        audioVector.add(new AudioWrapper(this, R.raw.msg9));
        audioVector.add(new AudioWrapper(this, R.raw.msg10));
        audioVector.add(new AudioWrapper(this, R.raw.msg11));
        audioVector.add(new AudioWrapper(this, R.raw.msg12));
        audioVector.add(new AudioWrapper(this, R.raw.msg13));
        audioVector.add(new AudioWrapper(this, R.raw.msg14));
        audioVector.add(new AudioWrapper(this, R.raw.msg15));
        audioVector.add(new AudioWrapper(this, R.raw.msg16));
        audioVector.add(new AudioWrapper(this, R.raw.msg17));
        audioVector.add(new AudioWrapper(this, R.raw.msg18));
        audioVector.add(new AudioWrapper(this, R.raw.msg19));
        audioVector.add(new AudioWrapper(this, R.raw.msg20));

        Vector<AudioWrapper> audioVector2 = new Vector<>();
        audioVector2.add(new AudioWrapper(this, R.raw.msg21));
        audioVector2.add(new AudioWrapper(this, R.raw.msg22));
        audioVector2.add(new AudioWrapper(this, R.raw.msg23));
        audioVector2.add(new AudioWrapper(this, R.raw.msg24));
        audioVector2.add(new AudioWrapper(this, R.raw.msg25));
        audioVector2.add(new AudioWrapper(this, R.raw.msg26));
        audioVector2.add(new AudioWrapper(this, R.raw.msg27));
        audioVector2.add(new AudioWrapper(this, R.raw.msg28));
        audioVector2.add(new AudioWrapper(this, R.raw.msg29));
        audioVector2.add(new AudioWrapper(this, R.raw.msg30));
        audioVector2.add(new AudioWrapper(this, R.raw.msg31));
        audioVector2.add(new AudioWrapper(this, R.raw.msg32));
        audioVector2.add(new AudioWrapper(this, R.raw.msg33));
        audioVector2.add(new AudioWrapper(this, R.raw.msg34));
        audioVector2.add(new AudioWrapper(this, R.raw.msg35));
        audioVector2.add(new AudioWrapper(this, R.raw.msg36));
        audioVector2.add(new AudioWrapper(this, R.raw.msg37));
        audioVector2.add(new AudioWrapper(this, R.raw.msg38));
        audioVector2.add(new AudioWrapper(this, R.raw.msg39));

        // Pass the vector to audio handlers
        mAudioHandler = new AudioHandler(audioVector, this);
        mAudioHandler2 = new AudioHandler(audioVector2, this);

        // Get message array
        mMsgArray = getResources().getStringArray(R.array.msg_array);

        // Init RandomIntGenerator
        mMsgIndexGenerator = new RandomIntGenerator(
                mMsgArray.length - 1,
                ((mMsgArray.length - 1) * 2) / 3);

        // Set initial image
        ImageView heartImage = (ImageView)findViewById(R.id.heart_img);
        heartImage.setImageResource(R.drawable.heart);

        heartImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                // Vibrate for 50 millisecond
                Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(50);

                ImageView heartImage = (ImageView) findViewById(R.id.heart_img);

                // Change mode
                nextMode();

                // Change image
                switch (mMode) {
                    case MUSIC1:
                        heartImage.setImageResource(R.drawable.heart);
                        break;
                    case MUSIC2:
                        heartImage.setImageResource(R.drawable.heart_yellow);
                        break;
                    case TEXT1:
                        heartImage.setImageResource(R.drawable.heart_purple);
                        break;
                }

                return true;
            }
        });
    }

    public void onImageClick(View v) {

        switch (mMode) {
            case MUSIC1: {
                // Start bumping animation for heart image
                v.startAnimation(mBumpAnimation);

                // Update music state
                mAudioHandler.update();
                break;
            }

            case MUSIC2: {
                v.startAnimation(mFadeAnimation);

                // Update music state
                mAudioHandler2.update();
                break;
            }

            case TEXT1: {
                // Play a System Sound
                mClickSoundPlayer.stop();
                try {
                    mClickSoundPlayer.prepare();
                }
                catch (IOException e){
                    System.out.println(e.toString());
                }
                mClickSoundPlayer.seekTo(0);
                mClickSoundPlayer.start();

                // Start rotate animation for heart image
                v.startAnimation(mRotateAnimation);

                // Show the next message
                if(mToast != null)
                    mToast.cancel();

                mToast = Toast.makeText(this, mMsgArray[mMsgIndexGenerator.getNextInt()], Toast.LENGTH_LONG);
                mToast.show();

                // Update the next message
                mMsgIndexGenerator.updateNextInt();
                break;
            }
        }
    }
}
