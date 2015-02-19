package com.thecrafter.loveyoumore;

import android.media.MediaPlayer;
import android.renderscript.RSInvalidStateException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    private AudioWrapper[] mAudioArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAudioArray = new AudioWrapper[1];
        mAudioArray[0] = new AudioWrapper(getApplicationContext(), R.raw.sample);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onImageClick(View v) {
        // Start bumping animation for heart image
        v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.heart_scaleup_anim));

        // If the music is already playing stop it, else start it
        // When music stops, it should reset back to start
        // If there is an exception, recreate audio from the beginning
        if(mAudioArray[0].isMusicPlaying())
            try{
                mAudioArray[0].stop();
            }
            catch(IllegalStateException e){
                mAudioArray[0].recreate(getApplicationContext());
            } catch (IOException e) {
                e.printStackTrace();
                mAudioArray[0].recreate(getApplicationContext());
            }
        else
            mAudioArray[0].play();
    }
}
