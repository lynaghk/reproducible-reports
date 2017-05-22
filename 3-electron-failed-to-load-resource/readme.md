See Electron Issue: https://github.com/electron/electron/issues/9555

## Problem

How can an Electron application detect when its markup refers to a resource that cannot be loaded?

## Steps to reproduce

Run:

    yarn install
    
to install Electron, then run

    export ELECTRON_ENABLE_LOGGING=true
    yarn start

to start Electron.

In the devtools console, you'll see a "Failed to load resource: net::ERR_FILE_NOT_FOUND", caused by the `<script src="no-such-script.js"></script>` tag in the markup.

But you won't see this message in stderr.
Here's the stderr I get:

```
[19990:0522/111151.870750:INFO:CONSOLE(7)] "hello world", source: file:///Users/kevin/work/reproducible-reports/3-electron-failed-to-load-resource/index.html (7)
[19990:0522/111152.041476:VERBOSE1:CONSOLE(8299)] "Main._createAppUI: 30.738ms", source: chrome-devtools://devtools/bundled/inspector.js (8299)
[19990:0522/111152.096341:VERBOSE1:CONSOLE(8304)] "Main._showAppUI: 52.613ms", source: chrome-devtools://devtools/bundled/inspector.js (8304)
[19990:0522/111152.178276:VERBOSE1:CONSOLE(8305)] "Main._initializeTarget: 19.798ms", source: chrome-devtools://devtools/bundled/inspector.js (8305)
2017-05-22 11:11:52.379 Electron Helper[19994:303] Couldn't set selectedTextBackgroundColor from default ()
```

And here's the stdout I get (logging all events emitted by window's `webContents`).
In particular, note that all `did-get-response-details` events have status code `200`:

```
yarn start v0.23.3
$ electron main.js 
{ '0': 'render-view-deleted',
  '1': 
   { preventDefault: [Function: preventDefault],
     sender: 
      WebContents {
        webContents: [Circular],
        history: [],
        currentIndex: -1,
        pendingIndex: -1,
        inPageIndex: -1,
        _events: [Object],
        _eventsCount: 16,
        _maxListeners: 0,
        browserWindowOptions: [Object],
        emit: [Function] } },
  '2': 5 }
{ '0': 'did-start-loading',
  '1': 
   { preventDefault: [Function: preventDefault],
     sender: 
      WebContents {
        webContents: [Circular],
        history: [],
        currentIndex: -1,
        pendingIndex: -1,
        inPageIndex: -1,
        _events: [Object],
        _eventsCount: 16,
        _maxListeners: 0,
        browserWindowOptions: [Object],
        emit: [Function] } } }
{ '0': 'load-url',
  '1': 'file:///Users/kevin/work/reproducible-reports/3-electron-failed-to-load-resource/index.html',
  '2': {} }
{ '0': 'did-get-response-details',
  '1': 
   { preventDefault: [Function: preventDefault],
     sender: 
      WebContents {
        webContents: [Circular],
        history: [],
        currentIndex: -1,
        pendingIndex: -1,
        inPageIndex: -1,
        _events: [Object],
        _eventsCount: 15,
        _maxListeners: 0,
        browserWindowOptions: [Object],
        emit: [Function] } },
  '2': true,
  '3': 'file:///Users/kevin/work/reproducible-reports/3-electron-failed-to-load-resource/index.html',
  '4': 'file:///Users/kevin/work/reproducible-reports/3-electron-failed-to-load-resource/index.html',
  '5': 200,
  '6': 'GET',
  '7': '',
  '8': { 'access-control-allow-origin': [ '*' ] },
  '9': 'mainFrame' }
{ '0': 'navigation-entry-commited',
  '1': 
   { preventDefault: [Function: preventDefault],
     sender: 
      WebContents {
        webContents: [Circular],
        history: [],
        currentIndex: -1,
        pendingIndex: -1,
        inPageIndex: -1,
        _events: [Object],
        _eventsCount: 15,
        _maxListeners: 0,
        browserWindowOptions: [Object],
        emit: [Function] } },
  '2': 'file:///Users/kevin/work/reproducible-reports/3-electron-failed-to-load-resource/index.html',
  '3': false,
  '4': false }
{ '0': 'did-navigate',
  '1': 
   { preventDefault: [Function: preventDefault],
     sender: 
      WebContents {
        webContents: [Circular],
        history: [Object],
        currentIndex: 0,
        pendingIndex: -1,
        inPageIndex: -1,
        _events: [Object],
        _eventsCount: 15,
        _maxListeners: 0,
        browserWindowOptions: [Object],
        emit: [Function] } },
  '2': 'file:///Users/kevin/work/reproducible-reports/3-electron-failed-to-load-resource/index.html' }
{ '0': 'update-target-url',
  '1': 
   { preventDefault: [Function: preventDefault],
     sender: 
      WebContents {
        webContents: [Circular],
        history: [Object],
        currentIndex: 0,
        pendingIndex: -1,
        inPageIndex: -1,
        _events: [Object],
        _eventsCount: 15,
        _maxListeners: 0,
        browserWindowOptions: [Object],
        emit: [Function] } },
  '2': '' }
{ '0': 'dom-ready',
  '1': 
   { preventDefault: [Function: preventDefault],
     sender: 
      WebContents {
        webContents: [Circular],
        history: [Object],
        currentIndex: 0,
        pendingIndex: -1,
        inPageIndex: -1,
        _events: [Object],
        _eventsCount: 15,
        _maxListeners: 0,
        browserWindowOptions: [Object],
        emit: [Function] } } }
{ '0': 'did-frame-finish-load',
  '1': 
   { preventDefault: [Function: preventDefault],
     sender: 
      WebContents {
        webContents: [Circular],
        history: [Object],
        currentIndex: 0,
        pendingIndex: -1,
        inPageIndex: -1,
        _events: [Object],
        _eventsCount: 15,
        _maxListeners: 0,
        browserWindowOptions: [Object],
        emit: [Function] } },
  '2': true }
{ '0': 'did-finish-load',
  '1': 
   { preventDefault: [Function: preventDefault],
     sender: 
      WebContents {
        webContents: [Circular],
        history: [Object],
        currentIndex: 0,
        pendingIndex: -1,
        inPageIndex: -1,
        _events: [Object],
        _eventsCount: 15,
        _maxListeners: 0,
        browserWindowOptions: [Object],
        emit: [Function] } } }
{ '0': 'did-stop-loading',
  '1': 
   { preventDefault: [Function: preventDefault],
     sender: 
      WebContents {
        webContents: [Circular],
        history: [Object],
        currentIndex: 0,
        pendingIndex: -1,
        inPageIndex: -1,
        _events: [Object],
        _eventsCount: 15,
        _maxListeners: 0,
        browserWindowOptions: [Object],
        emit: [Function] } } }
{ '0': 'devtools-focused',
  '1': 
   { preventDefault: [Function: preventDefault],
     sender: 
      WebContents {
        webContents: [Circular],
        history: [Object],
        currentIndex: 0,
        pendingIndex: -1,
        inPageIndex: -1,
        _events: [Object],
        _eventsCount: 15,
        _maxListeners: 0,
        browserWindowOptions: [Object],
        emit: [Function] } } }
{ '0': 'devtools-opened',
  '1': 
   { preventDefault: [Function: preventDefault],
     sender: 
      WebContents {
        webContents: [Circular],
        history: [Object],
        currentIndex: 0,
        pendingIndex: -1,
        inPageIndex: -1,
        _events: [Object],
        _eventsCount: 15,
        _maxListeners: 0,
        browserWindowOptions: [Object],
        emit: [Function] } } }
{ '0': 'did-get-response-details',
  '1': 
   { preventDefault: [Function: preventDefault],
     sender: 
      WebContents {
        webContents: [Circular],
        history: [Object],
        currentIndex: 0,
        pendingIndex: -1,
        inPageIndex: -1,
        _events: [Object],
        _eventsCount: 15,
        _maxListeners: 0,
        browserWindowOptions: [Object],
        emit: [Function] } },
  '2': true,
  '3': 'file:///Users/kevin/work/reproducible-reports/3-electron-failed-to-load-resource/index.html',
  '4': 'file:///Users/kevin/work/reproducible-reports/3-electron-failed-to-load-resource/index.html',
  '5': 200,
  '6': 'GET',
  '7': '',
  '8': { 'access-control-allow-origin': [ '*' ] },
  '9': 'other' }
{ '0': 'close',
  '1': 
   { preventDefault: [Function: preventDefault],
     sender: 
      WebContents {
        webContents: [Circular],
        history: [Object],
        currentIndex: 0,
        pendingIndex: -1,
        inPageIndex: -1,
        _events: [Object],
        _eventsCount: 15,
        _maxListeners: 0,
        browserWindowOptions: [Object],
        emit: [Function] } } }
{ '0': 'will-destroy',
  '1': 
   { preventDefault: [Function: preventDefault],
     sender: 
      WebContents {
        webContents: [Circular],
        history: [Object],
        currentIndex: 0,
        pendingIndex: -1,
        inPageIndex: -1,
        _events: [Object],
        _eventsCount: 15,
        _maxListeners: 0,
        browserWindowOptions: [Object],
        emit: [Function] } } }
{ '0': 'render-view-deleted',
  '1': 
   { preventDefault: [Function: preventDefault],
     sender: 
      WebContents {
        webContents: [Circular],
        history: [Object],
        currentIndex: 0,
        pendingIndex: -1,
        inPageIndex: -1,
        _events: [Object],
        _eventsCount: 15,
        _maxListeners: 0,
        browserWindowOptions: [Object],
        emit: [Function] } },
  '2': 6 }
{ '0': 'destroyed',
  '1': 
   { preventDefault: [Function: preventDefault],
     sender: 
      WebContents {
        webContents: [Circular],
        history: [Object],
        currentIndex: 0,
        pendingIndex: -1,
        inPageIndex: -1,
        _events: [Object],
        _eventsCount: 15,
        _maxListeners: 0,
        browserWindowOptions: [Object],
        emit: [Function] } } }
Done in 3.30s.
```

