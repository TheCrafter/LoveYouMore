package com.thecrafter.loveyoumore.audio;

import android.content.Context;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by TheCrafter on 19/2/2015.
 *
 * This class is a resource wrapper for audio resources. Has functions to handle the stop and play
 * and restart of audio files.
 */
public class AudioWrapper {

    // The media player for this resource.
    private MediaPlayer mMediaPlayer;

    // Flag to determine if music is playing or not.
    private boolean mMusicPlaying;

    // Id if the audio resource.
    private int mResId;

    /**
     * The only constructor of the class. Gets the context of the application and the id of
     * the resource you want to load in this AudioWrapper.
     * @param context The context of the application.
     * @param resid The id of the resource to load on this AudioWrapper.
     */
    public AudioWrapper(Context context, int resid){
        this.mResId = resid;

        mMediaPlayer = MediaPlayer.create(context, mResId);
        mMusicPlaying = false;

        // When track is complete, change playing flag (mMusicPlaying) to false.
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mMusicPlaying = false;
            }
        });
    }

    /**
     * Starts the track. If the track is already playing, does nothing.
     */
    public void play(){
        if(mMusicPlaying)
            return;
        else
            mMusicPlaying = true;

        mMediaPlayer.start();
    }

    /**
     * Stops the track and returns it to start. If the track is not playing, does nothing.
     * @throws IllegalStateException IllegalStateException thrown by MediaPlayer::prepare().
     * @throws IOException IOException thrown by MediaPlayer::prepare().
     */
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

    /**
     * Recreates the track.
     * @param context The context of the application.
     */
    public void recreate(Context context){
        mMediaPlayer = MediaPlayer.create(context, mResId);
        mMusicPlaying = false;
    }

    /**
     * Check if the music is currently playing or not.
     * @return True if music is already playing, False otherwise.
     */
    public boolean isMusicPlaying(){
        return mMusicPlaying;
    }
}
