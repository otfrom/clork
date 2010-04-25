(ns clork-test
  (:use clojure.test clork))

(defn clork-fixture [f]
  (def *test-world* {:rooms {:hall (struct room
                                           {:w :kitchen
                                            :n :study}
                                           "hall")
                             :kitchen (struct room
                                              {:e :hall}
                                              "kitchen")
                             :study (struct room
                                            {:s :hall}
                                            "study")}
                     :players {:player1 (struct player :hall [])
                               :player2 (struct player :kitchen [])}
                     :items {}})
  (def test-rooms {:test-room-1 {:exits {:w :test-room-2}}})
  (f)
  (def test-rooms nil))

(use-fixtures :once clork-fixture)

(deftest look-test
  (is (= "hall" (look *test-world* :player1)))
  (is (= "kitchen" (look *test-world* :player2))))

(deftest move-test
  (is (= :kitchen (get-in (move-player *test-world* :player1 :w) [:players :player1 :location])))
  (is (= :study (get-in (move-player *test-world* :player1 :n) [:players :player1 :location])))
  (is (= :hall (get-in (move-player *test-world* :player1 :e) [:players :player1 :location]))))

;; (deftest the-item-is-in
;;   (is (= (items-for :stone)  ["Sword"]))
;;   (is (= (items-for :hall) ["Clock"])))

;; (deftest take-item-test
;;   (is (= (:inventory @the-player) []))
;;   (do (take-an-item "Clock")
;;       (is (= (:inventory @the-player) ["Clock"]))
;;       (is (= (items-for :player) ["Clock"]))))

