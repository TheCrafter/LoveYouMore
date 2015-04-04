package com.thecrafter.loveyoumore.util;

import java.util.Random;

/**
 * Created by TheCrafter on 27/3/2015.
 *
 * RandomIntGenerator is a class that groups together all present and future logic
 * for random generation of integers.
 * Main functionality of RandomIntGenerator is that whenever the nextInt gets updated,
 * the last values (number of ignored values is the mHistorySize) are ignored.
 * Usage:
 * Every time the user wants to generate a new integer should make a call on
 * updateNextInt() and to access that integer should call getNextInt().
 */
public class RandomIntGenerator {

    /** Random object used to generate random numbers */
    private Random mRand = new Random(System.currentTimeMillis());

    /** This number shows the maximum unique numbers you can have continuously. */
    private int mHistorySize;

    /** Default value for mHistorySize. Used when you don't specify another value on the constructor. */
    private static final int mDefaultHistorySize = 3;

    /** Index in mAudioVector for the next audio to play. */
    private int mNextInt = 0;

    /** An array with all last played audios that will affect the random select. */
    private int[] mLastInts;

    /** Maximum int to return. */
    private int mMaxInt;

    /** Maximum int to return. */
    private int mMinInt = 0;

    public RandomIntGenerator(int max, int historySize){
        this.mMaxInt = max;
        this.mHistorySize = historySize;

        mLastInts = new int[mHistorySize];
        for (int i = 0; i < mHistorySize - 1; i++)
            mLastInts[i] = -1;
    }

    public RandomIntGenerator(int max){
        this(max, mDefaultHistorySize);
    }

    /**
     * Appends an index of an audio file from mAudioVector to last played audios array. This is
     * done by shifting the contents of the array to the left and getting rid of the last element.
     * Example:
     * We have [5,4,6,8] and we want to append 1.
     * Result: [1,5,4,6].
     * @param index Index of audio file from mAudioVector to append.
     */
    private void appendToLastInts(int index){
        for (int i = mHistorySize - 1; i >= 1; i--)
            mLastInts[i] = mLastInts[i - 1];

        mLastInts[0] = index;
    }

    /**
     * Check if an index of an audio file from mAudioVector exists in last played audios array
     * or not.
     * @param index Index from an audio file from mAudioVector to check if exists.
     * @return True if exists, False otherwise.
     */
    private boolean existInLastPlayedAudios(int index){
        for(int i = 0; i < mHistorySize - 1; i++)
            if (mLastInts[i] == index)
                return true;

        return false;
    }

    /**
     * Randomly decides what the next audio to play should be.
     */
    public void updateNextInt(){
        int nextIndex;

        // Exclude the previously played audio files.
        do{
            nextIndex = getRandomInt(mMinInt, mMaxInt);
        }while(existInLastPlayedAudios(nextIndex));

        appendToLastInts(nextIndex);
        mNextInt = nextIndex;
    }

    /**
     * Generates a random int within a specific range.
     * @param min The minimum int to generate.
     * @param max The maximum int to generate.
     * @return The random generated int.
     */
    public int getRandomInt(int min, int max){
        return mRand.nextInt((max - min) + 1) + min;
    }

    /**
     * Returns the next integer.
     * @return The next integer.
     */
    public int getNextInt(){
        return mNextInt;
    }
}
