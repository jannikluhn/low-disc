(ns low-disc.uniform)

(defn uniform-nd-point [n]
  "Return a single n-dimensional point chosen uniformly at random."
  (into [] (repeatedly n rand)))

(defn uniform-nd [n]
  "Return a lazy sequence of n dimensional points chosen independently and uniformly at random."
  (repeatedly (partial uniform-nd-point n)))

; define uniform-1d up until uniform-16d
(dotimes [n 16]
  (intern *ns*
          (symbol (str "uniform-" n "d"))
          (partial uniform-nd n)))
