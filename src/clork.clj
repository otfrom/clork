(ns clork)

(use 'world)
;; represent a room
;; represent a set of rooms
;; represent monsters
;; (declare hall kitchen bag-of-gold sword)



;; (def cell {:exits [hall kitchen]
;;            :items [bag-of-gold sword]})

;; (def hall {:exits [cell]
;;            :items []})


(defn look [rooms room]
  (:description (room rooms)))

(defn move [rooms from direction]
  (direction (:exits (rooms from))))

(defstruct player :location)

(defn move-player [a-player rooms direction]
  (let [new-room (move rooms (:location a-player) direction)]
    (if new-room (struct player new-room) a-player) ))


(def the-player (atom (struct player :hall)))

(defn move-and-print [direction]
  (swap! the-player #(move-player % rooms direction))
  (println (look rooms (:location @the-player))))


 (map #(defn %1 [] (move-and-print %2))
      ['north 'south 'east 'west]
      [:n :s :e :w])

    
