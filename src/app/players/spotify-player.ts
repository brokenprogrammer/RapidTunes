import { PlaybackMedia } from "../types";
import { useSpotifyToken } from "../util/auth";
import { Player } from "./player";
import Spotify from "spotify-web-api-js";

export class SpotifyPlayer implements Player {

    volume: number = 30;
    setVolume: any;
    playbackTime: any;
    isPlaying: boolean = false;
    setIsPlaying: any;
    timer: any;
    setTimer: any;

    constructor(
        volume: number,
        setVolume: any,
        playbackTime: any,
        isPlaying: boolean,
        setIsPlaying: any,
        timer: any,
        setTimer: any
    ) {
        this.volume = volume;
        this.setVolume = setVolume;
        this.playbackTime = playbackTime;
        this.isPlaying = isPlaying;
        this.setIsPlaying = setIsPlaying;
        this.timer = timer;
        this.setTimer = setTimer;
    }

    handleSongClicked(media: PlaybackMedia, deviceID: string): void {
        if (media) {
            useSpotifyToken().then((token) => {
                var spotifyApi = new Spotify();
                spotifyApi.setAccessToken(token);
				spotifyApi.play({ uris: [media.id], device_id: deviceID });
                this.setIsPlaying(true);
				this.updateProgressBar(0);
            });
        }
    }

    handleVolumeChange(event: any, newValue: number | number[]): void {
        this.setVolume(newValue);
    }

    changeVolume(volume: number) {
        useSpotifyToken().then((token) => {
			var spotifyApi = new Spotify();
			spotifyApi.setAccessToken(token);
			spotifyApi.setVolume(volume);
		});
    }

    async handlePlaybackTimeChangeCommitted(event: any, newValue: number | number[]) {
        var spotifyApi = new Spotify();
		spotifyApi.setAccessToken(await useSpotifyToken());
		spotifyApi.seek(newValue as number);

		if (this.isPlaying) {
			this.updateProgressBar(newValue as number);
		} else {
			this.playbackTime.set(newValue as number);
		}
    }

    async handlePlaybackTimeChange(event: any, newValue: number | number[]) {
		if (this.isPlaying) {
			this.updateProgressBar(newValue as number);
		} else {
			this.playbackTime.set(newValue as number);
		}
    }

    async handlePlayClicked(event: any) {
        var spotifyApi = new Spotify();
		spotifyApi.setAccessToken(await useSpotifyToken());

		if (this.isPlaying) {
			spotifyApi.pause();
			clearInterval(this.timer);
		} else {
			spotifyApi.play();
			this.updateProgressBar(this.playbackTime.get());
		}
        this.setIsPlaying(!this.isPlaying);
    }

    updateProgressBar(value: number): void {
		clearInterval(this.timer);
        this.playbackTime.set(value);
        this.setTimer(setInterval(() => {
			this.playbackTime.set(this.playbackTime.get() + 500);
		}, 500));
    }
}