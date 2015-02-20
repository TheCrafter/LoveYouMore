package com.thecrafter.loveyoumore.audio;

import android.content.Context;

import java.io.IOException;
import java.util.Vector;

/**
 * Created by TheCrafter on 20/2/2015.
 */
public class AudioHandler {

    private Vector<AudioWrapper> mAudioVector;
    private int nextAudioIndex;

    private Context mAppContext;

    public AudioHandler(Vector<AudioWrapper> vec, Context context){
        this.mAudioVector = vec;
        this.nextAudioIndex = 0;
        this.mAppContext = context;
    }

    //TODO: Implement random chance for next audio here
    private void updateNextAudioIndex(){
        if(nextAudioIndex == mAudioVector.size() - 1)
            nextAudioIndex = 0;
        else
            nextAudioIndex++;
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
            mAudioVector.elementAt(nextAudioIndex).play();
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
