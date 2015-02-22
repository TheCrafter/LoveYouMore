package com.thecrafter.loveyoumore.audio;

import android.content.Context;

import java.io.IOException;
import java.util.Vector;
import java.util.Random;

/**
 * Created by TheCrafter on 20/2/2015.
 */
public class AudioHandler {

    // A vector with all sounds.
    private Vector<AudioWrapper> mAudioVector;

    // Index in mAudioVector for the next audio to play.
    private int mNextAudioIndex;

    // Application context from the app that owns this AudioHandler.
    private Context mAppContext;


    // Random generator to update randomly mNextAudioIndex.
    private Random mRand;


    public AudioHandler(Vector<AudioWrapper> vec, Context context){
        this.mAudioVector = vec;
        this.mAppContext = context;

        mRand = new Random(System.currentTimeMillis());
        mNextAudioIndex = 0;
    }

    private int getRandomInt(int min, int max){
        return mRand.nextInt((max - min) + 1) + min;
    }

    //TODO: Make exclude an array, not just single numbers
    private void updateNextAudioIndex(){
        int nextIndex;

        // Exclude the previously played audio
        do{
            nextIndex = getRandomInt(0, mAudioVector.size() - 1);
        }while(nextIndex == mNextAudioIndex);

        mNextAudioIndex = nextIndex;
    }

    private int getCurPlayingIndex(){
        for(int i = 0; i < mAudioVector.size(); i++)
            if(mAudioVector.elementAt(i).isMusicPlaying())
                return i;

        return -1;
    }

    public void update(){
        int curPlayingIndex = getCurPlayingIndex();

        // No music is playing so play next audio
        if(curPlayingIndex == -1){
            mAudioVector.elementAt(mNextAudioIndex).play();
            updateNextAudioIndex();
        }
        // Stop the audio that is already playing
        else {
            try{
                mAudioVector.elementAt(curPlayingIndex).stop();
            }
            catch(IllegalStateException e){
                mAudioVector.elementAt(curPlayingIndex).recreate(mAppContext);
            } catch (IOException e) {
                e.printStackTrace();
                mAudioVector.elementAt(curPlayingIndex).recreate(mAppContext);
            }
        }
    }
}
