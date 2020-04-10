(ns low-disc.halton
  (:require [low-disc.vdc :as vdc]))

(def ^:private primes [2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53])

(def ^:private permutations
  "from https://doi.org/10.1016/j.cam.2005.05.022"
  {2 [0 1]
   3 [0 2 1]
   5 [0 2 4 1 3]
   7 [0 3 5 1 6 2 4]
   11 [0 5 8 2 10 3 6 1 9 4 7]
   13 [0 6 10 2 8 4 12 1 9 5 11 3 7]
   17 [0 8 13 3 11 5 16 1 10 7 14 4 12 2 15 6 9]
   19 [0 9 14 3 17 6 11 1 15 7 12 4 18 8 2 16 10 5 13]
   23 [0 11 17 4 20 7 13 2 22 9 15 5 18 1 14 10 21 6 16 3 19 8 12]
   29 [0 14 22 5 18 9 27 2 20 11 25 7 16 3 24 13 19 6 28 10 1 23 15 12 26 4 17 8 21]
   31 [0 16 8 26 4 22 13 29 2 19 11 24 6 20 14 28 1 17 9 30 10 23 5 21 15 3 27 12 25 7 18]
   37 [0 18 28 6 23 11 34 3 25 14 31 8 20 36 1 16 27 10 22 13 32 4 29 17 7 35 19 2 26 12 30 9 24 15
       33 5 21]
   41 [0 20 31 7 26 12 38 3 23 34 14 17 29 5 40 10 24 1 35 18 28 9 33 15 21 4 37 13 30 8 39 19 25 2
       32 11 22 36 6 27 16]
   43 [0 21 32 7 38 13 25 3 35 17 28 10 41 5 23 30 15 37 1 19 33 11 26 42 8 18 29 4 39 14 22 34 6
       24 12 40 2 31 20 16 36 9 27]
   47 [0 23 35 8 41 14 27 3 44 18 31 11 37 5 25 39 16 21 33 1 46 12 29 19 42 7 28  10 36 22 4 43 17
       32 13 38 2 26 45 15 30 6 34 20 40 9 24]
   53 [0 26 40 9 33 16 49 4 36 21 45 12 29 6 51 23 38 14 43 1 30 19 47 10 34 24 42 3 27 52 15 18 39
       7 46 22 32 5 48 13 35 25 8 44 31 17 50 2 37 20 28 11 41]})

(defn halton-scr
  "Returns the Halton sequence for bases bs scrambled with permutations ps."
  ([ps bs]
   (apply map vector (map vdc/vdc-scr ps bs)))
  ([ps bs l]
   (take l (halton-scr))))

(defn halton 
  "Returns the unscrambled Halton sequence for bases bs.

  Without an explicitly given length l, an infinite sequence is created."
  ([bs]
   (halton-scr (map range bs) bs))
  ([bs l]
   (take l (halton bs))))

(defn halton-nd
  "Returns an n dimensional scrambled Halton sequence.

  Without an explicitly given length l, an infinite sequence is created

  The first n prime numbers act as bases.

  Scrambling is performed using the permutations found using Braaten and Weller's algorithm by
  Vandewoestyne and Cools (https://doi.org/10.1016/j.cam.2005.05.022)."
  ([n]
   {:pre [(<= n (max (count permutations)
                    (count primes)))]}
   (let [bs (take n primes)
         ps (map permutations bs)]
     (halton-scr ps bs)))
  ([n l] (take l (halton-nd n))))

; define halton-1d up until halton-16d
(dotimes [n 16]
  (intern *ns*
          (symbol (str "halton-" n "d"))
          (partial halton-nd n)))
