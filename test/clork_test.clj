(ns clork-test
  (:use clork :reload-all)
  (:use world :reload-all)
  (:use clojure.test))

(defn clork-fixture [f]
  (def *test-world* *world*)
  (f)
  (def *test-world* nil))

(use-fixtures :once clork-fixture)

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

(deftest get-player-inv-test
  (is (= [] (get-inventory *test-world* :player1)))
  (is (= [:cloth] (get-inventory *test-world* :player2))))

(deftest current-room-test
  (is (= :hall (current-room *test-world* :player1)))
  (is (= :kitchen (current-room *test-world* :player2))))

(deftest add-to-inventory-test
  (is (= [:water] (get-inventory (add-to-inv *test-world* :player1 :water) :player1))))

(deftest pick-up-test
  (is (= [:water] (get-inventory (pick-up *test-world* :player1 :water) :player1)))
  (is (not (= nil (get-in (pick-up *test-world* :player1 :sword) [:players :player1 :inventory 0]))))
  (is (= [] (get-in (pick-up *test-world* :player1 :penguin) [:players :player1 :inventory])))
  (is (= [] (get-in (pick-up *test-world* :player1 :sword) [:rooms :hall :items]))))
