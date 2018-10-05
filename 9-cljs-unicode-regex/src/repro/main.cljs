(ns repro.main)

(prn "Unicode letter category matches?" (not (empty? (re-seq #"(?u)\p{L}" "a"))))
