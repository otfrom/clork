(ns clork-test
  (:use clork :reload-all)
  (:use world :reload-all)
  (:use clojure.test))

(defn clork-fixture [f]
  (def *test-world* *world*)
  (f)
  (def *test-world* nil))

(use-fixtures :once clork-fixture)

(deftest get-player-items-test
  (is (= #{} (get-items *test-world* :player1)))
  (is (= #{:cloth} (get-items *test-world* :player2))))

(deftest desc-exits-test
  (is (= "North West" (desc-exits (get-in *test-world* [:rooms :hall])))))

(deftest look-test
  (is (= "hall\nExits: North West\nItems: A very pointy sword. Some wet water.\n" (look *test-world* :player1)))
  (is (= "kitchen\nExits: East\nItems: \n" (look *test-world* :player2))))

(deftest move-test
  ;; player 1 testing
  (is (= :kitchen (get-in (move-player *test-world* :player1 :w) [:players :player1 :location])))
  (is (= :study (get-in (move-player *test-world* :player1 :n) [:players :player1 :location])))
  ;; if there isn't an exit you stay in the room
  (is (= :hall (get-in (move-player *test-world* :player1 :e) [:players :player1 :location])))
  ;; player 2 testing
  (is (= :hall (get-in (move-player *test-world* :player2 :e) [:players :player2 :location]))))

(deftest current-room-test
  (is (= (get-in *test-world* [:rooms :hall]) (current-room *test-world* :player1)))
  (is (= (get-in *test-world* [:rooms :kitchen]) (current-room *test-world* :player2))))

(deftest has-item-test
  (is (has-item? (get-in *test-world* [:players :player2]) :cloth))
  (is (not (has-item? (get-in *test-world* [:players :player1]) :cloth)))
  (is (not (has-item? (get-in *test-world* [:players :player2]) :penguin)))
  (is (has-item? (current-room *test-world* :player1) :sword))
  (is (has-item? (current-room *test-world* :player1) :water))
  (is (not (has-item? (current-room *test-world* :player1) :penguin))))

(deftest add-to-player-test
  (is (= #{:water} (get-items (add-to-player *test-world* :player1 :water) :player1)))
  (is (= #{:sword} (get-items (add-to-player *test-world* :player1 :sword) :player1)))
  (is (= #{:penguin} (get-items (add-to-player *test-world* :player1 :penguin) :player1))))

(deftest remove-from-room-test
  (is (= #{:water :sword} (get-in (remove-from-room *test-world* :hall :penguin) [:rooms :hall :items])))
  (is (= #{:water} (get-in (remove-from-room *test-world* :hall :sword) [:rooms :hall :items])))
  (is (= #{:sword} (get-in (remove-from-room *test-world* :hall :water) [:rooms :hall :items]))))

(deftest pick-up-test
  (is (= #{:water} (get-items (pick-up *test-world* :player1 :water) :player1)))
  (is (= :sword (get-in (pick-up *test-world* :player1 :sword) [:players :player1 :items :sword])))
  (is (= #{} (get-in (pick-up *test-world* :player1 :penguin) [:players :player1 :items])))
  (is (= #{:water} (get-in (pick-up *test-world* :player1 :sword) [:rooms :hall :items])))
  (is (= #{:sword} (get-in (pick-up *test-world* :player1 :water) [:rooms :hall :items]))))

(deftest remove-from-player-test
  (is (= #{} (get-items (remove-from-player *test-world* :player2 :cloth) :player2))))

(deftest add-to-room-test
  (is (= #{:cloth} (get-in (add-to-room *test-world* :kitchen :cloth) [:rooms :kitchen :items])))
  (is (= #{:penguin} (get-in (add-to-room *test-world* :kitchen :penguin) [:rooms :kitchen :items]))))

(deftest drop-item-test
  (is (= #{} (get-items (drop-item *test-world* :player2 :cloth) :player2)))
  (is (= #{:cloth} (get-in (drop-item *test-world* :player2 :cloth) [:rooms :kitchen :items])))
  (is (= #{:sword :water} (get-in (drop-item *test-world* :player1 :cloth) [:rooms :hall :items]))))
