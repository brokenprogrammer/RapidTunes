import React, { useState, useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import IconButton from '@material-ui/core/IconButton';
import SkipPreviousIcon from '@material-ui/icons/SkipPrevious';
import SkipNextIcon from '@material-ui/icons/SkipNext';
import PlayArrowIcon from '@material-ui/icons/PlayArrow';
import Typography from '@material-ui/core/Typography';
import logo from '../../assets/icon.svg';
import Box from '@material-ui/core/Box';
import Slider from '@material-ui/core/Slider';
import VolumeDown from '@material-ui/icons/VolumeDown';
import VolumeUp from '@material-ui/icons/VolumeUp';
import Grid from '@material-ui/core/Grid';
import { PlaybackMedia } from '../types';

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
    backgroundColor: '#6E7480',
  },
  appBar: {
    top: 'auto',
    bottom: 0,
    backgroundColor: '#6E7480',
  },
  grow: {
    flexGrow: 1,
  },
  fabButton: {
    position: 'absolute',
    zIndex: 1,
    top: -30,
    left: 0,
    right: 0,
    margin: '0 auto',
  },
  imgIcon: {
    height: 50,
    marginRight: theme.spacing(2),
  },
  volume: {
    width: 200,
    color: '#FFFFFF',
  },
  volumeSlider: {
    color: '#FFFFFF',
  },
  playbackTimeSlider: {
    width: 500,
    color: '#FFFFFF',
  },
}));

interface Props {
  media: PlaybackMedia;
}

function Controls({ media }: Props) {
  const classes = useStyles();
  const [volume, setVolume] = useState<number>(30);
  const [playbackTime, setPlaybackTime] = useState(0);

  useEffect(() => {}, []);

  // TODO(Oskar): Perhaps use debounce or similar for this, if user drags
  // the slider then the request will happen too many times.
  const handleVolumeChange = (event: any, newValue: number | number[]) => {
    setVolume(newValue as number);
  };

  const handlePlaybackTimeChange = (
    event: any,
    newValue: number | number[]
  ) => {
    setPlaybackTime(newValue as number);
  };

  const handlePlayClicked = (event: any) => {};

  return (
    <AppBar position="fixed" className={classes.appBar}>
      <Toolbar>
        <img
          src={media === null ? logo : media?.thumbnail_url}
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
              onClick={(event) => handlePlayClicked(event)}
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
                  className={classes.playbackTimeSlider}
                  value={playbackTime}
                  onChange={(event, value) =>
                    handlePlaybackTimeChange(event, value)
                  }
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
                onChange={(event, value) => handleVolumeChange(event, value)}
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
