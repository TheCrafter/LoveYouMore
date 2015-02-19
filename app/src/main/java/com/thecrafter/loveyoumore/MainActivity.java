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

    private  MediaPlayer mediaPlayer;
    private boolean mMusicPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sample);
        mMusicPlaying = false;
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
        v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.heart_scaleup_anim));

        if(mMusicPlaying)
        {
            mediaPlayer.stop();

            try{
                mediaPlayer.prepare();
                mediaPlayer.seekTo(0);
            }
            catch(IllegalStateException e){

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            mediaPlayer.start(); // no need to call prepare(); create() does that for you
        }

        mMusicPlaying = !mMusicPlaying;
    }
}
