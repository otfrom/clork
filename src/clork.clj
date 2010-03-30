(ns clork
  (:use world)
  (:use clojure.contrib.seq-utils))
;; represent a room
;; represent a set of rooms
;; represent monsters
;; (declare hall kitchen bag-of-gold sword)

;; (def cell {:exits [hall kitchen]
;;            :items [bag-of-gold sword]})

;; (def hall {:exits [cell]
;;            :items []})

(def items
     [ {:location (atom :stone)
        :name "Sword"}
       {:location (atom :hall)
        :name "Clock"}])

(defstruct player :location :inventory)

(def the-player (atom (struct player :hall [])))

(defn look
  ([] (look rooms))
  ([rooms] (look rooms (:location @the-player)))
  ([rooms room]
     (:description (room rooms))))

(defn items-for [location]
  (map :name (filter #(= @(:location %) location) items)))

(defn move [rooms from direction]
  (direction (:exits (rooms from))))



(defn move-player [a-player rooms direction]
  (let [new-room (move rooms (:location a-player) direction)]
    (if new-room (struct player new-room) a-player) ))




(defn move-and-print [direction]
  (swap! the-player #(move-player % rooms direction))
  (println (look rooms (:location @the-player))))


 (map #(defn %1 [] (move-and-print %2))
      ['north 'south 'east 'west]
      [:n :s :e :w])

(defn take-an-item [item]
  (dosync
   (swap! the-player assoc :inventory [item]))
  (reset! (:location (find-first #(= (:name %) item) items)) :player)
  )

