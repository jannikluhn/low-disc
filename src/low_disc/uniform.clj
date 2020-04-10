(ns low-disc.uniform)

(defn uniform-nd-point [n]
  "Return a single n-dimensional point chosen uniformly at random."
  (into [] (repeatedly n rand)))

(defn uniform-nd [n]
  "Return a lazy sequence of n dimensional points chosen independently and uniformly at random."
  (repeatedly (partial uniform-nd-point n)))

(defn uniform-1d [] (uniform-nd 1))
(defn uniform-2d [] (uniform-nd 2))
(defn uniform-3d [] (uniform-nd 3))
(defn uniform-4d [] (uniform-nd 4))
(defn uniform-5d [] (uniform-nd 5))
(defn uniform-6d [] (uniform-nd 6))
(defn uniform-7d [] (uniform-nd 7))
(defn uniform-8d [] (uniform-nd 8))
(defn uniform-9d [] (uniform-nd 9))
(defn uniform-10d [] (uniform-nd 10))
(defn uniform-11d [] (uniform-nd 11))
(defn uniform-12d [] (uniform-nd 12))
(defn uniform-13d [] (uniform-nd 13))
(defn uniform-14d [] (uniform-nd 14))
(defn uniform-15d [] (uniform-nd 15))
(defn uniform-16d [] (uniform-nd 16))
