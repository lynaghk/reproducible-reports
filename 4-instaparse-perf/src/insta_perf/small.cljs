(ns insta-perf.small
  (:require [instaparse.core :as insta]))

(enable-console-print!)

(def parse
  (insta/parser
   "
S = AB*
AB = A B
A = 'a'+
B = 'b'+"))

(prn (parse "aaaaabbbaaaabb"))
