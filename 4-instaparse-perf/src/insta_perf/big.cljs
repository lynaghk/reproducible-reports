(ns insta-perf.big
  (:require [instaparse.core :as insta]))

(enable-console-print!)

(def parse
  (insta/parser ""
   ))

(prn (parse "1s_20"))
