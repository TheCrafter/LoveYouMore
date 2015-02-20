package com.thecrafter.loveyoumore;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.io.IOException;
import java.util.Vector;


public class MainActivity extends Activity {

    private Vector<AudioWrapper> mAudioVector;
    private int posInAudioVec;

    private int getCurPlayingIndex(){
        for(int i = 0; i < mAudioVector.size(); i++)
            if(mAudioVector.elementAt(i).isMusicPlaying())
                return i;

        return -1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAudioVector = new Vector<AudioWrapper>();

        mAudioVector.add(new AudioWrapper(getApplicationContext(), R.raw.sample0));
        mAudioVector.add(new AudioWrapper(getApplicationContext(), R.raw.sample1));
        mAudioVector.add(new AudioWrapper(getApplicationContext(), R.raw.sample2));

        posInAudioVec = 0;
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

        // Update pos
        if(posInAudioVec == 3)
            posInAudioVec = 0;

        // If the music is already playing stop it, else start it
        // When music stops, it should reset back to start
        // If there is an exception, recreate audio from the beginning
        int playingIndex = getCurPlayingIndex();
        if(playingIndex != -1){
            try{
                mAudioVector.elementAt(playingIndex).stop();
            }
            catch(IllegalStateException e){
                mAudioVector.elementAt(playingIndex).recreate(getApplicationContext());
            } catch (IOException e) {
                e.printStackTrace();
                mAudioVector.elementAt(playingIndex).recreate(getApplicationContext());
            }
        }
        else{
            mAudioVector.elementAt(posInAudioVec).play();
            posInAudioVec++;
        }
    }
}
