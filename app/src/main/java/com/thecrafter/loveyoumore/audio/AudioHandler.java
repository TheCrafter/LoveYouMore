package com.thecrafter.loveyoumore.audio;

import android.content.Context;

import java.io.IOException;
import java.util.Vector;
import java.util.Random;

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

    // A vector with all sounds.
    private Vector<AudioWrapper> mAudioVector;

    // Index in mAudioVector for the next audio to play.
    private int mNextAudioIndex;

    // An array with all last played audios that will affect the random select.
    private int[] mLastPlayedAudios;
    // Size of mLastPlayedAudios.
    private final int mLastPlayedAudiosSize = 3;

    // Application context from the app that owns this AudioHandler.
    private Context mAppContext;

    // Random generator to update randomly mNextAudioIndex.
    private Random mRand;


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

        mRand = new Random(System.currentTimeMillis());
        mNextAudioIndex = 0;

        mLastPlayedAudios = new int[mLastPlayedAudiosSize];

        for (int i = 0; i < mLastPlayedAudiosSize - 1; i++)
            mLastPlayedAudios[i] = -1;
    }

    /**
     * Generates a random int within a specific range.
     * @param min The minimum int to generate.
     * @param max The maximum int to generate.
     * @return The random generated int.
     */
    private int getRandomInt(int min, int max){
        return mRand.nextInt((max - min) + 1) + min;
    }


    /**
     * Appends an index of an audio file from mAudioVector to last played audios array. This is
     * done by shifting the contents of the array to the left and getting rid of the last element.
     * Example:
     * We have [5,4,6,8] and we want to append 1.
     * Result: [1,5,4,6].
     * @param index Index of audio file from mAudioVector to append.
     */
    private void appendToLastPlayedAudios(int index){
        for (int i = 0; i < mLastPlayedAudiosSize; i++)
            if (i == mLastPlayedAudiosSize - 1)
                mLastPlayedAudios[0] = index;
            else
                mLastPlayedAudios[i + 1] = mLastPlayedAudios[i];
    }

    /**
     * Check if an index of an audio file from mAudioVector exists in last played audios array
     * or not.
     * @param index Index from an audio file from mAudioVector to check if exists.
     * @return True if exists, False otherwise.
     */
    private boolean existInLastPlayedAudios(int index){
        for(int i = 0; i < mLastPlayedAudiosSize - 1; i++)
            if (mLastPlayedAudios[i] == index)
                return true;

        return false;
    }

    /**
     * Randomly decides what the next audio to play should be.
     */
    private void updateNextAudioIndex(){
        int nextIndex;

        // Exclude the previously played audio files.
        do{
            nextIndex = getRandomInt(0, mAudioVector.size() - 1);
        }while(existInLastPlayedAudios(nextIndex));

        appendToLastPlayedAudios(nextIndex);
        mNextAudioIndex = nextIndex;
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
