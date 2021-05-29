import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import IconButton from "@material-ui/core/IconButton";
import SkipPreviousIcon from "@material-ui/icons/SkipPrevious";
import SkipNextIcon from "@material-ui/icons/SkipNext";
import PlayArrowIcon from "@material-ui/icons/PlayArrow";
import Typography from "@material-ui/core/Typography";
// import logo from "../../assets/icon.svg";
import Box from "@material-ui/core/Box";
import Slider from "@material-ui/core/Slider";
import VolumeDown from "@material-ui/icons/VolumeDown";
import VolumeUp from "@material-ui/icons/VolumeUp";
import Grid from "@material-ui/core/Grid";
import { PlaybackMedia } from "./types";
import Spotify from "spotify-web-api-js";
import { useSpotifyToken } from "./util/auth";
import { useDebounce } from "use-debounce";
import useTrait from "./hooks/useTrait";
import { SpotifyPlayer } from "./players/spotify-player";

const useStyles = makeStyles((theme) => ({
	text: {
		padding: theme.spacing(2, 2, 0),
	},
	paper: {
		paddingBottom: 50,
	},
	list: {
		marginBottom: theme.spacing(2),
	},
	subheader: {
		backgroundColor: "#6E7480",
	},
	appBar: {
		top: "auto",
		bottom: 0,
		backgroundColor: "#6E7480",
	},
	grow: {
		flexGrow: 1,
	},
	fabButton: {
		position: "absolute",
		zIndex: 1,
		top: -30,
		left: 0,
		right: 0,
		margin: "0 auto",
	},
	imgIcon: {
		height: 50,
		marginRight: theme.spacing(2),
	},
	volume: {
		width: 200,
		color: "#FFFFFF",
	},
	volumeSlider: {
		color: "#FFFFFF",
	},
	playbackTimeSlider: {
		width: 500,
		color: "#FFFFFF",
	},
}));

interface Props {
	media: PlaybackMedia | undefined;
	deviceID: string | undefined;
}

function Controls({ media, deviceID }: Props) {
	const classes = useStyles();
  	const [volume, setVolume] = useState<number>(30);
	const [isPlaying, setIsPlaying] = useState<boolean>(false);
  	const playbackTime = useTrait(0);
	const [timer, setTimer] = useState<any>();
	const [debouncedVolume] = useDebounce(volume, 350, { maxWait: 2 });

	const spotifyPlayer: SpotifyPlayer = new SpotifyPlayer(
		volume,
		setVolume,
		playbackTime,
		isPlaying,
		setIsPlaying,
		timer,
		setTimer
	);

	useEffect(() => {
		console.log("This is called when song is clicked on in song browser.");
		if (media) {
			spotifyPlayer.handleSongClicked(media, deviceID as string);
		}
	}, [media]);

	useEffect(() => {
		spotifyPlayer.changeVolume(debouncedVolume);
	}, [debouncedVolume]);

	const handleVolumeChange = async (
		event: any,
		newValue: number | number[]
	) => {
		spotifyPlayer.handleVolumeChange(event, newValue);
	};

	const handlePlaybackTimeChangeCommitted = async (
		event: any,
		newValue: number | number[]
	) => {
		spotifyPlayer.handlePlaybackTimeChangeCommitted(event, newValue);
	};

	const handlePlaybackTimeChange = async (
		event: any,
		newValue: number | number[]
	) => {
		spotifyPlayer.handlePlaybackTimeChange(event, newValue);
	};

	const handlePlayClicked = async (event: any) => {
		spotifyPlayer.handlePlayClicked(event);
	};

	return (
		<AppBar position="fixed" className={classes.appBar}>
			<Toolbar>
				<img
					src={media === null ? undefined : media?.thumbnail_url}
					className={classes.imgIcon}
					alt="Playback media"
				/>
				<div>
					<Typography component="h6" variant="h6">
						{media?.media_title}
					</Typography>
					<Typography variant="subtitle2" color="textSecondary">
						{media?.media_author}
					</Typography>
				</div>
				<div className={classes.grow} />
				<Box>
					<Box flexDirection="row">
						<IconButton edge="start" color="inherit" aria-label="skip previous">
							<SkipPreviousIcon />
						</IconButton>
						<IconButton
							edge="start"
							color="inherit"
							aria-label="play"
							onClick={async (event) => await handlePlayClicked(event)}
						>
							<PlayArrowIcon />
						</IconButton>
						<IconButton edge="start" color="inherit" aria-label="skip next">
							<SkipNextIcon />
						</IconButton>
					</Box>
					<Box flexDirection="row">
						<Grid container spacing={2}>
							<Grid item>
								<Slider
									min={0}
									max={media ? media.media_total_time : 1} // TODO(Oskar): What do we do here?
									className={classes.playbackTimeSlider}
									value={playbackTime.get()}
									onChangeCommitted={async (event, value) =>
										handlePlaybackTimeChangeCommitted(event, value)
									}
									onChange={(event, value) => {
										handlePlaybackTimeChange(event, value)
									}}
								/>
							</Grid>
						</Grid>
					</Box>
				</Box>
				<div className={classes.grow} />
				<Box className={classes.volume}>
					<Typography id="continuous-slider" gutterBottom>
						Volume
					</Typography>
					<Grid container spacing={2}>
						<Grid item>
							<VolumeDown />
						</Grid>
						<Grid item xs>
							<Slider
								className={classes.volumeSlider}
								value={volume}
								onChange={async (event, value) =>
									await handleVolumeChange(event, value)
								}
								aria-labelledby="continuous-slider"
							/>
						</Grid>
						<Grid item>
							<VolumeUp />
						</Grid>
					</Grid>
				</Box>
			</Toolbar>
		</AppBar>
	);
}

export default Controls;
