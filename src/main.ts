import { app, BrowserWindow, protocol } from "electron";
import path from 'path';

let win: BrowserWindow | null = null;

const createWindow = (): void => {
  win = new BrowserWindow({
    width: 800,
    height: 600,
    webPreferences: {
      nodeIntegration: true,
      worldSafeExecuteJavaScript: true,
      plugins: true,
    },
  });

  win.loadFile("index.html");
};

app.on("ready", createWindow);

app.whenReady().then(() => {
  protocol.registerFileProtocol('rapidtunes', (request, callback) => {
    let code = request.url.split('?code=')[1];
    const url = path.normalize(`${__dirname}/index.html`);

    win?.loadFile(url, { query: { "spotify_code": code } });
  });
});
