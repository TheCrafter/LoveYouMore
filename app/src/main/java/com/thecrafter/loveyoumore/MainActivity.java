package com.thecrafter.loveyoumore;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.thecrafter.loveyoumore.audio.AudioWrapper;
import com.thecrafter.loveyoumore.audio.AudioHandler;

import java.util.Vector;


public class MainActivity extends Activity {

    private AudioHandler mAudioHandler;

    private boolean mMusicModeOn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        ImageView heartImage = (ImageView)findViewById(R.id.heart_img);

        // Set initial image
        heartImage.setImageResource(R.drawable.heart);

        heartImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                ImageView heartImage = (ImageView)findViewById(R.id.heart_img);

                // Change image
                if(mMusicModeOn)
                    heartImage.setImageResource(R.drawable.heart_purple);
                else
                    heartImage.setImageResource(R.drawable.heart);

                // Change mode
                mMusicModeOn = !mMusicModeOn;
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_lovemsg) {
            Toast.makeText(getApplicationContext(), "Hey! I Love you <3", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(id == R.id.action_complimentmsg){
            Toast.makeText(getApplicationContext(), "Ok, you are gorgeous!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onImageClick(View v) {
        // Start bumping animation for heart image
        v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.heart_scaleup_anim));

        if(mMusicModeOn){
            // Update music state
            mAudioHandler.update();
        }
        else{
            Toast.makeText(getApplicationContext(), "Text mode on!", Toast.LENGTH_SHORT).show();
        }
    }
}
