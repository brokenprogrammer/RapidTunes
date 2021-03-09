const { app, BrowserWindow } = require("electron");

const isDev = true;

function createWindow() {
  const win = new BrowserWindow({
    width: 800,
    height: 600,
    webPreferences: {
      nodeIntegration: true,
      worldSafeExecuteJavaScript: true,
    },
  });

  if (isDev) {
    win.loadURL("http://localhost:3000/index.html");
  } else {
    // TODO(Oskar): Load built file..
    // 'build/index.html'
    // win.loadURL(`file://${__dirname}/../index.html`);
  }
}

app.whenReady().then(createWindow);

app.on("window-all-closed", () => {
  if (process.platform !== "darwin") {
    app.quit();
  }
});

app.on("activate", () => {
  if (BrowserWindow.getAllWindows().length === 0) {
    createWindow();
  }
});
