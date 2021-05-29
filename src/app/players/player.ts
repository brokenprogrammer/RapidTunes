import { PlaybackMedia } from "../types";

export interface Player {
    volume: number;
    setVolume: any;
    playbackTime: any;
    isPlaying: boolean;
    setIsPlaying: any;
    timer: any;
    setTimer: any;

    handleVolumeChange(event: any, newValue: number | number[]): void;
    handlePlaybackTimeChangeCommitted(event: any, newValue: number | number[]): void;
    handlePlaybackTimeChange(event: any, newValue: number | number[]): void;
    handlePlayClicked(event: any): void;
    handleSongClicked(media: PlaybackMedia, deviceID: string): void;
    updateProgressBar(value: number): void;
    changeVolume(volume: number): void;
}