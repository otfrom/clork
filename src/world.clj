(ns world
  (:use clork))

(defrecord Player [location inventory])

(defstruct item :description)

(defstruct container :description :inventory)

(defstruct room :exits :description :items)

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
              :players {:player1 (Player. :hall [])
                        :player2 (Player. :kitchen [])}
              :items {:sword (struct item "A very pointy sword.")}})

