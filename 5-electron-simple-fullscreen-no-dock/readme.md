See Electron Issue: https://github.com/electron/electron/issues/11468

## Problem

A `simpleFullscreen: true` BrowserWindow is short the space of the OS X menu bar when `app.dock.hide()` has been called.

If app.dock.hide() has been called, the simple fullscreened window is short by the height of the OS X menu bar.
(Tested with Electron 1.8.2-beta.3).

I'm on OS X 10.9.5, which is too old for me to build Electron from source and fix.
However, I suspect the issue is related to this part of the [pull request](https://github.com/electron/electron/pull/10254):

```objc
NSApplicationPresentationOptions options =
    NSApplicationPresentationAutoHideDock +
    NSApplicationPresentationAutoHideMenuBar;
[NSApp setPresentationOptions:options];

was_maximizable_ = IsMaximizable();
was_movable_ = IsMovable();

NSRect fullscreenFrame = [window.screen frame];

if ( !fullscreen_window_title() ) {
  // Hide the titlebar
  SetStyleMask(false, NSTitledWindowMask);

  // Resize the window to accomodate the _entire_ screen size
  fullscreenFrame.size.height -= [[[NSApplication sharedApplication] mainMenu] menuBarHeight];
```

My hypothesis for the behavior is that when NSApplicationPresentationAutoHideMenuBar is set, then the menuBarHeight call returns 0, but if app.dock.hide() has been called, then NSApplicationPresentationAutoHideMenuBar has no effect and so the last line of this snippet reduces the size by the menu bar height.



## Steps to reproduce

Run:

    yarn install
    
to install Electron, then run

    yarn start-dock
    yarn start-no-dock

to test the two cases.

