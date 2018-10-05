(require '[cljs.build.api :as cljs])

(cljs/build "src" {:main 'repro.main
                   :target :nodejs
                   :language-in :ecmascript6
                   :output-to "simple.js"
                   :output-dir "out"
                   :optimizations :simple})
