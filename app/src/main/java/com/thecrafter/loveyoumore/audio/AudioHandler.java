package com.thecrafter.loveyoumore.audio;

import android.content.Context;

import com.thecrafter.loveyoumore.util.RandomIntGenerator;

import java.io.IOException;
import java.util.Vector;

/**
 * Created by TheCrafter on 20/2/2015.
 *
 * AudioHandler is responsible for Audio playing. It manages the random access to each
 * audio resource. Every time update() is called the state of the music is changed.
 * If the music is already playing, it stops and rolls back to start while if there is no
 * music playing, the next audio starts.
 * Next audio is always random with a simple limitation that the an audio cannot be played
 * if it has been already played between the last "mLastPlayedAudiosSize" audios.
 */
public class AudioHandler {

    /** A vector with all sounds. */
    private Vector<AudioWrapper> mAudioVector;

    /** Application context from the app that owns this AudioHandler. */
    private Context mAppContext;

    /** Random generator to update randomly the next audio in mAudioVector to be played. */
    private RandomIntGenerator mRandomizer;

    /**
     * The only constructor of the class. Gets a vector with resources and the
     * context of the application. Then initializes class variables. mLastPlayedAudios
     * starts with all elements set to -1.
     *
     * @param vec A vector with audio resources to play.
     * @param context The context of the application.
     */
    public AudioHandler(Vector<AudioWrapper> vec, Context context){
        this.mAudioVector = vec;
        this.mAppContext = context;

        mRandomizer = new RandomIntGenerator(mAudioVector.size() - 1, 15);
    }

    /**
     * Get the index of the currently playing resource or -1 if no music is playing.
     * @return The index of the music playing or -1 if no music is playing.
     */
    private int getCurPlayingIndex(){
        for(int i = 0; i < mAudioVector.size(); i++)
            if(mAudioVector.elementAt(i).isMusicPlaying())
                return i;

        return -1;
    }

    /**
     * Updates the state of AudioHandler. If the music is stopped, starts a new track but if the
     * music is playing, stops it.
     */
    public void update(){
        int curPlayingIndex = getCurPlayingIndex();

        // No music is playing so play next audio
        if(curPlayingIndex == -1){
            mAudioVector.elementAt(mRandomizer.getNextInt()).play();
            mRandomizer.updateNextInt();
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
