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
  (f)
  (def *test-world* nil))

(use-fixtures :once clork-fixture)

(deftest desc-exits-test
  (is (= "North West" (desc-exits (get-in *test-world* [:rooms :hall])))))

(deftest look-test
  (is (= "hall\nExits: North West\nItems: \n" (look *test-world* :player1)))
  (is (= "kitchen\nExits: East\nItems: \n" (look *test-world* :player2))))

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

