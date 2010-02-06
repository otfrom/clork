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
            :kitchen {:description "A kitchen with a roaring fire"
                      :exits {:s :hall}}
            :lounge {:description "A lounge with a red chez longue"
                     :exits {:e :hall}}})

(defn look [rooms room]
  (:description (room rooms)))

(defn move [rooms from direction]
  (direction (:exits (rooms from))))

(defstruct player :location)

(defn move-player [a-player rooms direction]
  (let [new-room (move rooms (:location a-player) direction)]
    (if new-room (struct player new-room) a-player)))

(def the-player (atom (struct player :hall)))

(defn west []
  (swap! the-player #(move-player % rooms :w))
  (println (look rooms (:location @the-player))))
