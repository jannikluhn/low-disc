(ns low-disc.plot
  (:require [quil.core :as q]
            [low-disc.core :as ld]))

(def width 500)
(def height 500)
(def n 500)
(def base-hue 0.25)

(defn draw-marker [x y]
  (q/ellipse (* x width) (* y height) 10 10))

(defn draw-seq [s]
  (let [[s1 s2] (split-at (quot (count s) 10) s)]
    (q/fill base-hue 1 1)
    (doseq [[x y] s2] (draw-marker x y))
    (q/fill (rem (+ base-hue 0.5) 1) 1 1)
    (doseq [[x y] s1] (draw-marker x y))))

(defn draw []
  (q/no-loop)
  (q/ellipse-mode :center)
  (q/color-mode :hsb 1)
  (q/background 1)
  (q/no-stroke)
  (draw-seq (take n (ld/halton-unscr-2d)))
  #_(draw-seq (map (partial take-last 2) (ld/hammersley-16d n)))
  #_(draw-seq (take n (ld/uniform-2d)))
  #_(q/save-frame "halton-unscr.png"))

; run sketch
(q/defsketch trigonometry
  :size [width height]
  :draw draw
  :middlewares [:no-bind-output])
