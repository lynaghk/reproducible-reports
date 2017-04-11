(require '[cljs.build.api :as cljs])
(import 'java.io.File)

;;java -cp cljs.jar:src clojure.main build.clj

(cljs/build "src" {:main 'foo.main
                   :output-to "whitespace.js"
                   :output-dir "resources/out"
                   :language-in :ecmascript5
                   :optimizations :whitespace})

(cljs/build "src" {:main 'foo.main
                   :output-to "simple.js"
                   :output-dir "resources/out"
                   :language-in :ecmascript5
                   :optimizations :simple})
