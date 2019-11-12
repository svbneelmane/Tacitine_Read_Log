/*
 * Main
 */
package main;

import livelogtracking.LiveLogTracking;

/**
 *
 * @author Prajwal Bhat
 */
public class LogTrack {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LiveLogTracking logTracking = new LiveLogTracking();
        logTracking.ShowFilePathEntryScreen();
    }
}