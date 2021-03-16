import React, { useState, useEffect } from "react";
import SearchBar from "material-ui-search-bar";
import { makeStyles } from "@material-ui/core/styles";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import CardMedia from "@material-ui/core/CardMedia";
import IconButton from "@material-ui/core/IconButton";
import Typography from "@material-ui/core/Typography";
import PlayArrowIcon from "@material-ui/icons/PlayArrow";
import Grid from "@material-ui/core/Grid";
import Box from "@material-ui/core/Box";
import { MediaType, PlaybackMedia } from "./types";
import Spotify from "spotify-web-api-js";

const useStyles = makeStyles((theme) => ({
  root: {
    display: "flex",
    height: 200,
  },
  details: {
    display: "flex",
    flexDirection: "column",
    width: "75%",
  },
  content: {
    flex: "1 0 auto",
  },
  cover: {
    width: 151,
  },
  controls: {
    display: "flex",
    alignItems: "center",
    paddingLeft: theme.spacing(1),
    paddingBottom: theme.spacing(1),
  },
  playIcon: {
    height: 38,
    width: 38,
  },
}));

interface Props {
  media: PlaybackMedia | undefined;
  setMedia: (arg0: PlaybackMedia) => void;
}

function SongBrowser({ media, setMedia }: Props) {
  const [searchValue, setSearchValue] = useState("");
  const [songs, setSongs] = useState([]);

  const classes = useStyles();

  useEffect(() => {}, []);

  function startSong(mediaRequest: PlaybackMedia) {
    setMedia(mediaRequest);
  }

  // TODO(Oskar): Load in next page when scrolling to bottom.
  function performSpotifySearch(value: string) {
    var spotifyApi = new Spotify();
    spotifyApi.setAccessToken("YOUR_ACCESS_TOKEN_HERE");

    spotifyApi.searchTracks(value).then(
      function (data: any) {
        console.log(data.tracks.items);
        setSongs(data.tracks.items);
      },
      function (err: any) {
        console.log("Something went wrong!", err);
      }
    );
  }

  return (
    <Box width="80%">
      <Box component="span" m={5}>
        <SearchBar
          value={searchValue}
          onChange={(newValue) => setSearchValue(newValue)}
          onRequestSearch={() => performSpotifySearch(searchValue)}
        />
      </Box>

      <Grid container spacing={3}>
        {/* // TODO(Oskar): Do not use any type for value as its bad practice. */}
        {songs.map((value: any, index: number) => {
          console.log(value);
          // TODO(Oskar): Allow more than tracks later
          if (value.type === "track") {
            let media: PlaybackMedia = {
              id: value.uri,
              media_type: MediaType.Spotify,
              media_title: value.name,
              media_author: value.artists[0].name,
              thumbnail_url: value.album.images[0].url,
              media_total_time: value.duration_ms / 1000,
            };

            return (
              <Grid item xs={4}>
                <Card className={classes.root} onClick={() => startSong(media)}>
                  <div className={classes.details}>
                    <CardContent className={classes.content}>
                      <Typography component="h6" variant="h6">
                        {value.name}
                      </Typography>
                      <Typography variant="subtitle2" color="textSecondary">
                        {value.artists[0].name}
                      </Typography>
                    </CardContent>
                    <div className={classes.controls}>
                      <IconButton aria-label="play/pause">
                        <PlayArrowIcon className={classes.playIcon} />
                      </IconButton>
                    </div>
                  </div>
                  <CardMedia
                    className={classes.cover}
                    image={value.album.images[0].url}
                    title={value.name}
                  />
                </Card>
              </Grid>
            );
          } else {
            // TODO(Oskar): Do something propper here.
            return null;
          }
        }, {})}
      </Grid>
    </Box>
  );
}

export default SongBrowser;
