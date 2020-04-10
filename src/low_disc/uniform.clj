(ns low-disc.uniform)

(defn uniform-nd-point [n]
  "Return a single n-dimensional point chosen uniformly at random."
  (into [] (repeatedly n rand)))

(defn uniform-nd
  "Return a lazy sequence of n dimensional points chosen independently and uniformly at random.

  Without an explicitly given length l, an infinite sequence is created."
  ([n] (repeatedly (partial uniform-nd-point n)))
  ([n l] (take l (uniform-nd n))))

; define uniform-1d up until uniform-16d
(dotimes [n 16]
  (intern *ns*
          (symbol (str "uniform-" n "d"))
          (partial uniform-nd n)))
