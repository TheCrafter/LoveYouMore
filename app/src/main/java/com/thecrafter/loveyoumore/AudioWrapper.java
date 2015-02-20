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
    private int mResId;

    public AudioWrapper(Context context, int resid){
        this.mResId = resid;

        mMediaPlayer = MediaPlayer.create(context, mResId);
        mMusicPlaying = false;

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mMusicPlaying = false;
            }
        });
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

        // Stop the music
        mMediaPlayer.stop();

        // Reset it back to start and get it ready to play
        mMediaPlayer.prepare();
        mMediaPlayer.seekTo(0);
    }

    public void recreate(Context context){
        mMediaPlayer = MediaPlayer.create(context, mResId);
        mMusicPlaying = false;
    }

    public boolean isMusicPlaying(){
        return mMusicPlaying;
    }
}
