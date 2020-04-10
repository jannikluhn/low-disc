(ns low-disc.hammersley
  (:require [low-disc.halton :as halton]))

(defn hammersley-nd [n l]
  "Return the n-dimensional Hammersley set of size l.

  The implementation is based on the scrambled Halton sequence."
  (->> (halton/halton-nd (dec n))
       (map-indexed #(conj %2 (/ %1 l)))
       (take l)))

; define uniform-1d up until uniform-16d
(dotimes [n 16]
  (intern *ns*
          (symbol (str "hammersley-" n "d"))
          (partial hammersley-nd n)))
