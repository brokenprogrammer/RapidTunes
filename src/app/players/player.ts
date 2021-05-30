import { PlaybackMedia } from "../types";

export interface Player {
    playbackTime: any;
    isPlaying: boolean;
    setIsPlaying: any;
    timer: any;
    setTimer: any;

    handlePlaybackTimeChangeCommitted(event: any, newValue: number | number[]): void;
    handlePlaybackTimeChange(event: any, newValue: number | number[]): void;
    handlePlayClicked(event: any): void;
    handleSongClicked(media: PlaybackMedia, deviceID: string): void;
    updateProgressBar(value: number): void;
    changeVolume(volume: number): void;
}