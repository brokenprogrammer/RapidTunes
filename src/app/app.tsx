import React, { useEffect, useState } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { PlaybackMedia } from "./types";
import Controls from "./controls";
import SongBrowser from "./songbrowser";
import Script from "react-load-script";
import { useSpotifyToken } from "./util/auth";
import SpotifyAPI from "spotify-web-api-js";

const MainView = () => {
  const [media, setMedia] = useState<PlaybackMedia>();
  const [spotifyPlayer, setSpotifyPlayer] =
    useState<Spotify.Player | null>(null);
  const [spotifyAPI, setSpotifyAPI] =
    useState<SpotifyAPI.SpotifyWebApiJs | null>(null);
  const [spotifyDeviceId, setSpotifyDeviceId] = useState<string>("");

  const handleScriptCreate = () => {
    console.log("Created");
  };

  const handleScriptError = () => {
    console.log("Error");
  };

  const handleScriptLoad = () => {
    window.onSpotifyWebPlaybackSDKReady = async () => {
      setSpotifyAPI(new SpotifyAPI());
      spotifyAPI?.setAccessToken(await useSpotifyToken());

      // TODO(Oskar): Later we want the player to be used from the controls module.
      const player = new window.Spotify.Player({
        name: "RapidTunes",
        getOAuthToken: (cb) => {
          console.log("PlaybackSDK asks for new token.");
          useSpotifyToken().then((token) => {
            cb(token);
          });
        },
        volume: 0.2,
      });

      player.addListener("initialization_error", ({ message }) => {
        console.error(message);
      });

      player.addListener("authentication_error", ({ message }) => {
        console.error(message);
      });

      player.addListener("account_error", ({ message }) => {
        console.error(message);
      });

      player.addListener("playback_error", ({ message }) => {
        console.error(message);
      });

      player.addListener("player_state_changed", (state) => {
        console.log(state);
      });

      player.addListener("ready", ({ device_id }) => {
        console.log("Ready with Device ID", device_id);
        setSpotifyDeviceId(device_id);

        useSpotifyToken().then((token) => {
          spotifyAPI?.setAccessToken(token);
          spotifyAPI?.transferMyPlayback([device_id], { play: true });
        });
      });

      player.addListener("not_ready", ({ device_id }) => {
        console.log("Device ID has gone offline", device_id);
      });

      // Connect to the player!
      player.connect();
      setSpotifyPlayer(player);
    };
  };

  return (
    <div>
      <SongBrowser media={media} setMedia={setMedia}></SongBrowser>
      <Controls media={media} deviceID={spotifyDeviceId}></Controls>
      <Script
        url="https://sdk.scdn.co/spotify-player.js"
        onCreate={handleScriptCreate}
        onError={handleScriptError}
        onLoad={handleScriptLoad}
      />
    </div>
  );
};

const App = () => {
  return (
    <Router>
      <Switch>
        <Route path="/" component={MainView} />
      </Switch>
    </Router>
  );
};

export default App;
