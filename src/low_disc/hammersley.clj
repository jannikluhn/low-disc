(ns low-disc.hammersley
  (:require [low-disc.halton :as halton]))

(defn hammersley-nd [n l]
  "Return the n-dimensional Hammersley set of size l.

  The implementation is based on the scrambled Halton sequence.
  
  If n is too high, nil is returned."
  (if-let [base (halton/halton-nd (dec n))]
    (->> base
         (map-indexed #(conj %2 (/ %1 l)))
         (take l))))

(defn hammersley-1d [n] (hammersley-nd 1 n))
(defn hammersley-2d [n] (hammersley-nd 2 n))
(defn hammersley-3d [n] (hammersley-nd 3 n))
(defn hammersley-4d [n] (hammersley-nd 4 n))
(defn hammersley-5d [n] (hammersley-nd 5 n))
(defn hammersley-6d [n] (hammersley-nd 6 n))
(defn hammersley-7d [n] (hammersley-nd 7 n))
(defn hammersley-8d [n] (hammersley-nd 8 n))
(defn hammersley-9d [n] (hammersley-nd 9 n))
(defn hammersley-10d [n] (hammersley-nd 10 n))
(defn hammersley-11d [n] (hammersley-nd 11 n))
(defn hammersley-12d [n] (hammersley-nd 12 n))
(defn hammersley-13d [n] (hammersley-nd 13 n))
(defn hammersley-14d [n] (hammersley-nd 14 n))
(defn hammersley-15d [n] (hammersley-nd 15 n))
(defn hammersley-16d [n] (hammersley-nd 16 n))
(defn hammersley-17d [n] (hammersley-nd 17 n))
