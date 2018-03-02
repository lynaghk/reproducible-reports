const electron = require('electron')
const app = electron.app
const BrowserWindow = electron.BrowserWindow

app.dock.hide()

app.on('ready', function(){

  new BrowserWindow({simpleFullscreen: true, 
                     fullscreen: true})

  setTimeout(function(){app.exit()}, 3000)

})
