(ns world
  (:use clork))

(def *world* {:rooms {:hall (struct room
                                    {:w :kitchen
                                     :n :study}
                                    "hall"
                                    [:sword])
                      :kitchen (struct room
                                       {:e :hall}
                                       "kitchen")
                      :study (struct room
                                     {:s :hall}
                                     "study")}
              :players {:player1 (struct player :hall [])
                        :player2 (struct player :kitchen [])}
              :items {:sword (struct item "A very pointy sword.")}})

