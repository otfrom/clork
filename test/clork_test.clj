(ns clork-test
  (:use clojure.test clork))

(defn clork-fixture [f]
  (def test-rooms {:test-room-1 {:exits {:w :test-room-2}}})
  (f)
  (def test-rooms nil))

(use-fixtures :once clork-fixture)

(deftest move-test
  (is (= (move-player {:location :test-room-1} test-rooms :w) {:location :test-room-2})))

(deftest the-sword-is-in-the-hall
  (is (= (items-for :stone)  ["Sword"]))
  (is (= (items-for :hall) ["Clock"])))


