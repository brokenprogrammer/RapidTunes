import React, { useEffect, useState } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { PlaybackMedia } from "./types";
import Controls from "./controls";
import SongBrowser from "./songbrowser";
import Script from "react-load-script";
import { Button } from "@material-ui/core";
import { loginSpotify, refreshSpotifyAuth, authorizeSpotify, initSpotify } from './util/auth';

const MainView = () => {
  const [media, setMedia] = useState<PlaybackMedia>();

  useEffect(() => {
    
  }, []);

  useEffect(() => {
    console.log("This is called when song is clicked on in song browser.");
  }, [media]);

  const handleScriptCreate = () => {
    console.log("Created");
  };

  const handleScriptError = () => {
    console.log("Error");
  };

  const handleScriptLoad = () => {
    window.onSpotifyWebPlaybackSDKReady = async () => {

      const token = await initSpotify();
      //const token = 'BQAGi5TVvBDZKFwz2HrK21y0MW2UIA5ZWmpBxTEeFYwT8HXuTn6qpgwoZJ0nJW6sWZs77Ui0hOyrJO1nab_YWIXOSp_UjU55jZsb4gGoO_sN2JI01w3eJIn2HUPlTQGjyuZhYRdhc6AJVCf8K709w044DB8kDRS7nxNY5MS3Sw_aRJems9mZG7Q'
      console.log(token);

      // TODO(Oskar): Later we want the player to be used from the controls module.
      const player = new window.Spotify.Player({
        name: "RapidTunes",
        getOAuthToken: (cb) => {
          cb(token);
        },
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
      });

      player.addListener("not_ready", ({ device_id }) => {
        console.log("Device ID has gone offline", device_id);
      });

      // Connect to the player!
      player.connect();
    };
  };

  return (
    <div>
      <Button onClick={() => loginSpotify()}>Connect</Button>
      <Button onClick={() => {
        let str = localStorage.getItem('spotifyAuth');
        let spotifyAuth = str ? JSON.parse(str) : null;
        refreshSpotifyAuth(spotifyAuth.refresh_token).then(result => localStorage.setItem('spotifyAuth', JSON.stringify(result)));
      }}>Update</Button>
      <SongBrowser media={media} setMedia={setMedia}></SongBrowser>
      <Controls media={media}></Controls>
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
