(ns clork)

;; represent a room
;; represent a set of rooms
;; represent monsters
;; (declare hall kitchen bag-of-gold sword)

;; (def cell {:exits [hall kitchen]
;;            :items [bag-of-gold sword]})

;; (def hall {:exits [cell]
;;            :items []})

(defstruct player :room)

(defstruct room :north :south :east :west)

(defn west
  "Move west from from"
  [from])

(defn north
  [from])

(defn south
  [from])

(defn east
  [from])
