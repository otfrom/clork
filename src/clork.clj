(ns clork
  (:use world))

(def direction-desc {:n "North" :s "South" :e "East" :w "West"})

(defn desc-exits [room]
  (let [exits (keys (:exits room))
        direction-strings (sort (map #(% direction-desc) exits))]
    (reduce print-str direction-strings)))

(defn current-room [world player-name]
  (let [location-name (get-in world [:players player-name :location])]
    (get-in world [:rooms location-name])))

(defn look [world player-name]
  (let [room (current-room world player-name)
        item-descs (sort (map #(get-in world [:items % :description]) (:items room)))]
    (str (println-str (:description room)) 
         (println-str "Exits:" (desc-exits room))
         (println-str "Items:" (reduce print-str item-descs)))))

(defn move-player [world player-name direction]
  (let [routes (:exits (current-room world player-name))]
    (if (contains? routes direction)
      (update-in world [:players player-name] assoc :location (get routes direction))
      world)))

(defn has-item? [container item-name]
     (contains? (:items container) item-name))

(defn add-to-items [world player-name item-name]
  (let [room (current-room world player-name)]
    (if (has-item? room item-name) (update-in world [:players player-name :items] conj item-name)
        world)))

(defn remove-from-room [world room-name item-name]
  (update-in world [:rooms room-name :items] disj item-name))

(defn pick-up [world player-name item-name]
  (let [room-name (get-in world [:players player-name :location])]
    (remove-from-room (add-to-items world player-name item-name) room-name item-name)))

;; (doseq [in (repeatedly #(read-line)) :while in] (process-input in))
