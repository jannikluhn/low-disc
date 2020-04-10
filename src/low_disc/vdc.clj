(ns low-disc.vdc
  (:require [low-disc.utils :refer [digits-rev]]))

(defn vdc-el-scr [p b n]
  "Returns the nth element of the base b van der Corput sequence scrambled with permutation p."
  (->> (map #(/ %1 %2)
            (map #(nth p %) (digits-rev b n))
            (iterate #(* b %) b))
       (reduce +)))

(defn vdc-scr
  "Returns the base b van der Corput sequence scrambled with permutation p.

  Without an explicitly given length l, an infinite sequence is created."
  ([p b]
   (->> (range)
        (map inc)
        (map #(vdc-el-scr p b %))))
  ([p b l]
   (take l (vdc-scr p b))))

(defn vdc
  "Returns the unscrambled base b van der Corput sequence.

  Without an explicitly given length l, an infinite sequence is created."
  ([b] (vdc-scr (range b) b))
  ([b l] (take l (vdc b))))
