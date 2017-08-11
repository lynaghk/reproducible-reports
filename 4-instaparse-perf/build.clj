(require '[cljs.build.api :as cljs])
(import 'java.io.File)

;;java -cp cljs.jar:src clojure.main build.clj

(cljs/build "src" {:main 'insta-perf.hello
                   :output-to "hello.js"
                   :output-dir "out"
                   :optimizations :advanced})

(cljs/build "src" {:main 'insta-perf.small
                   :output-to "small.js"
                   :output-dir "out"
                   :optimizations :advanced})

(cljs/build "src" {:main 'insta-perf.big
                   :output-to "big.js"
                   :output-dir "out"
                   :optimizations :advanced})
