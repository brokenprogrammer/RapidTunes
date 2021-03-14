import React, { useEffect, useState } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { PlaybackMedia } from "./types";
import Controls from "./controls";
import SongBrowser from "./songbrowser";

const MainView = () => {
  const [media, setMedia] = useState<PlaybackMedia>();

  useEffect(() => {}, []);

  useEffect(() => {
    console.log("This is called when song is clicked on in song browser.");
  }, [media]);

  return (
    <div>
      <SongBrowser media={media} setMedia={setMedia}></SongBrowser>
      <Controls media={media}></Controls>
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
