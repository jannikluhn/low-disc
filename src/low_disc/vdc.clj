(ns low-disc.vdc
  (:require [low-disc.utils :refer [digits-rev]]))

(defn vdc-el-scr [p b n]
  "Returns the nth element of the base b van der Corput sequence scrambled with permutation p."
  (->> (map #(/ %1 %2)
            (map #(nth p %) (digits-rev b n))
            (iterate #(* b %) b))
       (reduce +)))

(defn vdc-scr [p b]
  "Returns the base b van der Corput sequence scrambled with permutation p."
  (->> (range)
       (map inc)
       (map #(vdc-el-scr p b %))))

(defn vdc [b]
  "Returns the unscrambled base b van der Corput sequence."
  (vdc-scr (range b) b))
