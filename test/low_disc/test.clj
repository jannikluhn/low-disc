(ns low-disc.test
  (:require [clojure.test :refer :all]
            [low-disc.utils :refer [digits-rev]]
            [low-disc.core :refer :all]))


(deftest utils-tests
  (is (= (digits-rev 10 123405) [5 0 4 3 2 1]))
  (is (= (digits-rev 10 0) [])))

(deftest vdc-tests
  (is (= (take 4 (vdc-unscr 2)) [1/2 1/4 3/4 1/8]))
  (is (every? #(< 0 % 1) (take 1000 (vdc-unscr 29))))
  (is (every? #(< 0 % 1) (take 1000 (vdc [0 3 1 4 2] 5))))
  (is (= (take 4 (vdc-unscr 2)) (vdc-unscr 2 4))))

(deftest halton-tests
  (is (= (take 4 (halton-unscr [2 3])) [[1/2 1/3] [1/4 2/3] [3/4 1/9] [1/8 4/9]]))
  (let [h (take 1000 (halton [[0 2 1] [0 4 2 3 1]] [3 5]))]
    (is (every? (fn [[x y]] (and (< 0 x 1) (< 0 y 1))) h))
    (is (every? #(= (count %) 2) h)))
  (is (= (count (first (halton-nd 1))) 1))
  (is (= (count (first (halton-nd 2))) 2))
  (is (= (count (first (halton-nd 15))) 15))
  (is (= (take 4 (halton-4d)) (halton-4d 4))))

(deftest hammersley-tests
  (is (= (count (hammersley-5d 100)) 100))
  (is (every? #(= % 5) (map count (hammersley-5d 100)))))
