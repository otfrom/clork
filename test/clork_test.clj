(ns clork-test
  (:use clojure.test clork))

(defn clork-fixture [f]
  (def test-rooms {:test-room-1 {:exits {:w :test-room-2}}})
  (f)
  (def test-rooms nil))

(use-fixtures :once clork-fixture)

(deftest move-test
  (is (=  (:location (move-player {:location :test-room-1} test-rooms :w)) :test-room-2)))

(deftest the-item-is-in
  (is (= (items-for :stone)  ["Sword"]))
  (is (= (items-for :hall) ["Clock"])))

(deftest take-item-test
  (is (= (:inventory @the-player) []))
  (do (take-an-item "Clock")
      (is (= (:inventory @the-player) ["Clock"]))
      (is (= (items-for :player) ["Clock"]))))

