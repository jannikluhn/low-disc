(ns low-disc.utils)

(defn digits-rev [b n]
  "Return the digits of n represented in basis b in reverse order (lowest valued position first)"
  (->> n
       (iterate #(quot % b))
       (take-while #(> % 0))
       (map #(rem % b))
       (into [])))

