(ns low-disc.core
  (:require [low-disc.utils :refer [digits-rev]]))

;
; Van der Corput
;
(defn vdc-el [p b n]
  "Returns the nth element of the base b van der Corput sequence scrambled with permutation p."
  (->> (map #(/ %1 %2)
            (map #(nth p %) (digits-rev b n))
            (iterate #(* b %) b))
       (reduce +)))

(defn vdc
  "Returns the base b van der Corput sequence scrambled with permutation p.

  Without an explicitly given length l, an infinite sequence is created."
  ([p b]
   (->> (range)
        (map inc)
        (map #(vdc-el p b %))))
  ([p b l]
   (take l (vdc p b))))

(defn vdc-unscr
  "Returns the unscrambled base b van der Corput sequence.

  Without an explicitly given length l, an infinite sequence is created."
  ([b] (vdc (range b) b))
  ([b l] (take l (vdc-unscr b))))

;
; Halton
;
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

(defn halton
  "Returns the Halton sequence for bases bs scrambled with permutations ps."
  ([ps bs]
   (apply map vector (map vdc ps bs)))
  ([ps bs l]
   (take l (halton))))

(defn halton-nd
  "Returns an n-dimensional scrambled Halton sequence.

  Without an explicitly given length l, an infinite sequence is created

  The first n prime numbers act as bases.

  Scrambling is performed using the permutations found using Braaten and Weller's algorithm by
  Vandewoestyne and Cools (https://doi.org/10.1016/j.cam.2005.05.022)."
  ([n]
   {:pre [(<= n (max (count permutations)
                    (count primes)))]}
   (let [bs (take n primes)
         ps (map permutations bs)]
     (halton ps bs)))
  ([n l] (take l (halton-nd n))))

(defn halton-unscr
  "Returns the unscrambled Halton sequence for bases bs.

  Without an explicitly given length l, an infinite sequence is created."
  ([bs]
   (halton (map range bs) bs))
  ([bs l]
   (take l (halton-unscr bs))))

(defn halton-unscr-nd
  "Returns an n-dimensional unscrambled Halton sequence.

  Without an explicitly given length l, an infinite sequence is created

  The first n prime numbers act as bases."
  ([n]
   {:pre [(<= n (count primes))]}
   (halton-unscr (take n primes)))
  ([n l] (take l (halton-unscr-nd n))))

; halton-1d ... halton-16d, halton-unscr-1d ... halton-unscr-16d
(dotimes [n 16]
  (intern *ns*
          (symbol (str "halton-" n "d"))
          (partial halton-nd n))
  (intern *ns*
          (symbol (str "halton-unscr-" n "d"))
          (partial halton-nd n)))

;
; Hammersley
;
(defn hammersley-nd [n l]
  "Return the scrambled n-dimensional Hammersley set of size l.

  The implementation is based on the scrambled Halton sequence."
  (->> (halton-nd (dec n))
       (map-indexed #(conj %2 (/ %1 l)))
       (take l)))

(defn hammersley-unscr-nd [n l]
  "Return the unscrambled n-dimensional Hammersley set of size l.

  The implementation is based on the unscrambled Halton sequence."
  (->> (halton-unscr-nd (dec n))
       (map-indexed #(conj %2 (/ %1 l)))
       (take l)))

; hammersley-1d ... hammersley-16d, hammersley-unscr-1d ... hammersley-16d
(dotimes [n 16]
  (intern *ns*
          (symbol (str "hammersley-" n "d"))
          (partial hammersley-nd n))
  (intern *ns*
          (symbol (str "hammersley-unscr-" n "d"))
          (partial hammersley-unscr-nd n)))

;
; Uniform
;
(defn uniform-nd-point [n]
  "Return a single n-dimensional point chosen uniformly at random."
  (into [] (repeatedly n rand)))

(defn uniform-nd
  "Return a lazy sequence of n-dimensional points chosen independently and uniformly at random.

  Without an explicitly given length l, an infinite sequence is created."
  ([n] (repeatedly (partial uniform-nd-point n)))
  ([n l] (take l (uniform-nd n))))

; uniform-1d ... uniform-16d
(dotimes [n 16]
  (intern *ns*
          (symbol (str "uniform-" n "d"))
          (partial uniform-nd n)))
