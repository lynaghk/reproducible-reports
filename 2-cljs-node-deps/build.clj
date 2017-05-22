(require '[cljs.build.api :as cljs])
(import 'java.io.File)

;;java -cp cljs.jar:src clojure.main build.clj

;;run using local checkout of cljs
;;java -cp /Users/kevin/software/clojurescript/lib/*:/Users/kevin/software/clojurescript/src/main/cljs:/Users/kevin/software/clojurescript/src/main/clojure:src clojure.main build.clj

(def extern-paths
  (->> (file-seq (File. "../../uitron/resources/externs"))
       (filter #(.endsWith (.getName %) "js"))
       (map #(.getPath %))))

(cljs/build "src" {:main 'foo.main
                   :verbose true
                   :output-to "compiled.js"
                   :output-dir "out"
                   :optimizations :advanced
                   :externs extern-paths

                   :closure-warnings {:non-standard-jsdoc :off
                                      :global-this :off}

                   :foreign-libs
                   (->> (cljs/node-inputs [{:file "cljs_node_requires.js"}])
                        (map (fn [{:keys [file] :as ijs} ]
                               (cond-> ijs

                                 (.contains file "node_modules/react/react.js")
                                 (assoc :provides ["react"])

                                 (.contains file "node_modules/react-dom/index.js")
                                 (assoc :provides ["react-dom"])

                                 (.contains file "node_modules/react-dom/server.js")
                                 (assoc :provides ["react-dom-server"])))))})
