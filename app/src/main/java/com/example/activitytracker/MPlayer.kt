package com.example.activitytracker
import android.content.Context
import android.media.MediaPlayer

public lateinit var mPlayer: MediaPlayer;

// tracks whether any other background music is playing or not
public var isPlaying = false;

/**
 * This is the class for static functions for media player
 * I had to create a new class to preverent multiple background music,
 * when someone visit same activity multiple time (with back button)
 */
class MPlayer {

}

// it will start background music
public fun playAudio(context: Context){

    // if already background music is playing
    // stop it
    if(isPlaying){
        stopAudio();
    }

    // mark is playing as true
    isPlaying = true;

    // start background music
    mPlayer = MediaPlayer.create(context,R.raw.motivational_music);
    mPlayer.start();

    // set music on loop
    mPlayer.setLooping(true);
}


// it will stop the background music
public fun stopAudio(){
    isPlaying = false;
    mPlayer.stop();
}

