import React, { useEffect, useState } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { PlaybackMedia } from "./types";
import Controls from "./controls";
import SongBrowser from "./songbrowser";
// @ts-ignore
import Script from "react-load-script";

const MainView = () => {
  const [media, setMedia] = useState<PlaybackMedia>();

  useEffect(() => {}, []);

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
    // @ts-ignore
    window.onSpotifyWebPlaybackSDKReady = () => {
      const token =
        "BQCb4e7uRbh0AadeMfAyMewOyId75orPHjsbOSzMPZhKaP3fQn3wMAwLnQDXTidgFklLrdXKkyjWEPhLErN7H1QBEPC1YMMjFi55UzxxN4dPkF4BgtwWpg_D6LwmSmY8vPi0MMPCk9-cXLNqMFEf9y97n-eDZo7R1dKINNm6O_w-Lg";
      // @ts-ignore
      const player = new window.Spotify.Player({
        name: "RapidTunes",
        // @ts-ignore
        getOAuthToken: (cb) => {
          cb(token);
        },
      });

      // @ts-ignore
      player.addListener("initialization_error", ({ message }) => {
        console.error(message);
      });
      // @ts-ignore
      player.addListener("authentication_error", ({ message }) => {
        console.error(message);
      });
      // @ts-ignore
      player.addListener("account_error", ({ message }) => {
        console.error(message);
      });
      // @ts-ignore
      player.addListener("playback_error", ({ message }) => {
        console.error(message);
      });

      // @ts-ignore
      player.addListener("player_state_changed", (state) => {
        console.log(state);
      });

      // @ts-ignore
      player.addListener("ready", ({ device_id }) => {
        console.log("Ready with Device ID", device_id);
      });

      // @ts-ignore
      player.addListener("not_ready", ({ device_id }) => {
        console.log("Device ID has gone offline", device_id);
      });

      // Connect to the player!
      player.connect();
    };
  };

  return (
    <div>
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
