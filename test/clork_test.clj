(ns clork-test
  (:use clojure.test clork))

(defn clork-fixture [f]
  (def *test-world* {:rooms {:test-room-1 {:exits
                                           {:w :test-room-2
                                            :n :test-room-3}}
                             :test-room-2 {:exits {:e :test-room-1}}
                             :test-room-3 {:exits {:s :test-room-1}}}
                     :players {:player1 (struct player :test-room-1 [])
                               :player2 (struct player :test-room-2 [])}
                     :items {}})
  (def test-rooms {:test-room-1 {:exits {:w :test-room-2}}})
  (f)
  (def test-rooms nil))

(use-fixtures :once clork-fixture)

(deftest move-test
  (is (= :test-room-2 (get-in (move-player *test-world* :player1 :w) [:players :player1 :location])))
  (is (= :test-room-3 (get-in (move-player *test-world* :player1 :n) [:players :player1 :location])))
  (is (= :test-room-1 (get-in (move-player *test-world* :player1 :e) [:players :player1 :location]))))

(deftest move-test-old
  (is (=  (:location (move-player {:location :test-room-1} test-rooms :w)) :test-room-2)))

(deftest the-item-is-in
  (is (= (items-for :stone)  ["Sword"]))
  (is (= (items-for :hall) ["Clock"])))

(deftest take-item-test
  (is (= (:inventory @the-player) []))
  (do (take-an-item "Clock")
      (is (= (:inventory @the-player) ["Clock"]))
      (is (= (items-for :player) ["Clock"]))))

