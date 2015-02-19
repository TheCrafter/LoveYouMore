package com.thecrafter.loveyoumore;

import android.content.Context;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by TheCrafter on 19/2/2015.
 */
public class AudioWrapper {

    private MediaPlayer mMediaPlayer;
    private boolean mMusicPlaying;

    public AudioWrapper(Context context, int resid){
        mMediaPlayer = MediaPlayer.create(context, resid);
        mMusicPlaying = false;
    }

    public void play(){
        if(mMusicPlaying)
            return;
        else
            mMusicPlaying = true;

        mMediaPlayer.start();
    }

    public void stop() throws IllegalStateException, IOException{
        if(!mMusicPlaying)
            return;
        else
            mMusicPlaying = false;

        mMediaPlayer.stop();

        mMediaPlayer.prepare();
        mMediaPlayer.seekTo(0);
    }

    public boolean isMusicPlaying(){
        return mMusicPlaying;
    }
}
