## Problem



## Steps to reproduce

Run:

    curl -L https://github.com/clojure/clojurescript/releases/download/r1.9.671/cljs.jar > cljs.jar
    java -cp cljs.jar:src:instaparse/src clojure.main build.clj

    time node compiled.js
