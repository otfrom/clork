(ns clork)

;; represent a room
;; represent a set of rooms
;; represent monsters
;; (declare hall kitchen bag-of-gold sword)



;; (def cell {:exits [hall kitchen]
;;            :items [bag-of-gold sword]})

;; (def hall {:exits [cell]
;;            :items []})

(def rooms {:hall {:description "A large vaulted hall"
                   :exits {:n :kitchen
                           :w :lounge}}
            :kitchen {:description "A kitchern with a roaring fire"
                      :exits {:s :hall}
                      }
            :lounge {:description "A lounge with a red chez longue"
                     :exits {:e :hall}}
           })

(defn look [room]
(:description (room rooms))
  )

(defstruct player :room)



(defn west
  "Move west from from"
  [from])



(defn north
  [from])

(defn south
  [from])

(defn east
  [from])
