(ns low-disc.test
  (:require [clojure.test :refer :all]
            [low-disc.utils :refer [digits-rev]]
            [low-disc.vdc :as vdc]
            [low-disc.halton :as halton]))


(deftest utils-tests
  (is (= (digits-rev 10 123405) [5 0 4 3 2 1]))
  (is (= (digits-rev 10 0) [])))

(deftest vdc-tests
  (is (= (take 4 (vdc/vdc 2)) [1/2 1/4 3/4 1/8]))
  (is (every? #(< 0 % 1) (take 1000 (vdc/vdc 29))))
  (is (every? #(< 0 % 1) (take 1000 (vdc/vdc-scr [0 3 1 4 2] 5)))))

(deftest halton-tests
  (is (= (take 4 (halton/halton [2 3])) [[1/2 1/3] [1/4 2/3] [3/4 1/9] [1/8 4/9]]))
  (let [h (take 1000 (halton/halton-scr [[0 2 1] [0 4 2 3 1]] [3 5]))]
    (is (every? (fn [[x y]] (and (< 0 x 1) (< 0 y 1))) h))
    (is (every? #(= (count %) 2) h)))
  (is (= (count (first (halton/halton-nd 1))) 1))
  (is (= (count (first (halton/halton-nd 2))) 2))
  (is (= (count (first (halton/halton-nd 15))) 15))
  (is (nil? (halton/halton-nd 17))))
