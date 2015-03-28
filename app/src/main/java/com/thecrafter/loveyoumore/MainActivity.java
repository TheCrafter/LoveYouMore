package com.thecrafter.loveyoumore;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.thecrafter.loveyoumore.audio.AudioHandler;
import com.thecrafter.loveyoumore.audio.AudioWrapper;
import com.thecrafter.loveyoumore.util.RandomIntGenerator;

import java.util.Vector;


public class MainActivity extends Activity {

    /** Object to manage audio */
    private AudioHandler mAudioHandler;

    /** Array with all messages to appear on screen. Messages are loaded from xml.  */
    private String[] mMsgArray;

    /** Used to generate random indexes for mMsgArray. */
    private RandomIntGenerator mMsgIndexGenerator;

    /** A shared toast to show simple messages */
    private Toast mToast;

    /** If this is true, pressing the heart will play a sound, otherwise show message. */
    private boolean mMusicModeOn = true;

    // Animations
    /** Bumping animation */
    private Animation mBumpAnimation;

    /** Rotation animation */
    private Animation mRotateAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init animations
        mBumpAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.heart_scaleup_anim);
        mRotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.heart_rotate_anim);

        // Create audio vector and fill it with sound resources
        Vector<AudioWrapper> audioVector = new Vector<>();

        audioVector.add(new AudioWrapper(getApplicationContext(), R.raw.msg1));
        audioVector.add(new AudioWrapper(getApplicationContext(), R.raw.msg2));
        audioVector.add(new AudioWrapper(getApplicationContext(), R.raw.msg3));
        audioVector.add(new AudioWrapper(getApplicationContext(), R.raw.msg4));
        audioVector.add(new AudioWrapper(getApplicationContext(), R.raw.msg5));
        audioVector.add(new AudioWrapper(getApplicationContext(), R.raw.msg6));
        audioVector.add(new AudioWrapper(getApplicationContext(), R.raw.msg7));
        audioVector.add(new AudioWrapper(getApplicationContext(), R.raw.msg8));
        audioVector.add(new AudioWrapper(getApplicationContext(), R.raw.msg9));
        audioVector.add(new AudioWrapper(getApplicationContext(), R.raw.msg10));

        // Pass the vector to audio handler
        mAudioHandler = new AudioHandler(audioVector, getApplicationContext());

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

                ImageView heartImage = (ImageView) findViewById(R.id.heart_img);

                // Change image
                if (mMusicModeOn)
                    heartImage.setImageResource(R.drawable.heart_purple);
                else
                    heartImage.setImageResource(R.drawable.heart);

                // Change mode
                mMusicModeOn = !mMusicModeOn;
                return true;
            }
        });
    }

    public void onImageClick(View v) {

        // Check if we are on music mode or not
        if(mMusicModeOn){
            // Start bumping animation for heart image
            v.startAnimation(mBumpAnimation);

            // Update music state
            mAudioHandler.update();
        }
        else{
            // Start rotate animation for heart image
            v.startAnimation(mRotateAnimation);

            // Show the next message
            if(mToast != null)
                mToast.cancel();

            mToast = Toast.makeText(getApplicationContext(), mMsgArray[mMsgIndexGenerator.getNextInt()], Toast.LENGTH_LONG);
            mToast.show();

            // Update the next message
            mMsgIndexGenerator.updateNextInt();
        }
    }
}
