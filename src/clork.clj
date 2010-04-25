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

;; (def items
;;      [{:location (atom :stone)
;;         :name "Sword"}
;;        {:location (atom :hall)
;;         :name "Clock"}])

(defstruct player :location :inventory)

(defstruct item :location :description)

(defstruct room :exits :description)

;; (def the-player (atom (struct player :hall [])))

(defn look [world player]
  (let [curr-room (get-in world [:players player :location])]
    (get-in world [:rooms curr-room :description])))

(defn items-for [location]
  (map :name (filter #(= @(:location %) location) items)))

(defn move-player [world player direction]
  (let [curr-room (get-in world [:players player :location])
        routes (get-in world [:rooms curr-room :exits])]
    (if (contains? routes direction)
      (update-in world [:players player] #(merge % {:location (get routes direction)}))
      world)))

(defn move-and-print [direction]
  (swap! the-player #(move-player % rooms direction))
  (println (look rooms (:location @the-player))))

(defn take-an-item [item]
  (dosync
   (swap! the-player assoc :inventory [item]))
  (reset! (:location (find-first #(= (:name %) item) items)) :player))

