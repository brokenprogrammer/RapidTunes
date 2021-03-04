import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import icon from '../assets/icon.svg';
import './App.global.css';
import Controls from './component/Controls';
import SongBrowser from './component/SongBrowser';
import { PlaybackMedia } from './types';

const MainView = () => {
  const [media, setMedia] = useState<PlaybackMedia>();

  useEffect(() => {
    console.log('This is called when song is clicked on in song browser.');
  }, [media]);

  return (
    <div>
      <SongBrowser media={media} setMedia={setMedia}></SongBrowser>
      <Controls media={media}></Controls>
    </div>
  );
};

export default function App() {
  return (
    <Router>
      <Switch>
        <Route path="/" component={MainView} />
      </Switch>
    </Router>
  );
}
