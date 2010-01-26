(ns clork)

;; represent a room
;; represent a set of rooms
;; represent monsters
(def cell {:exits [hall kitchen]
           :items [bag-of-gold sword]})

(def hall {:exits [cell]
           :items []})



