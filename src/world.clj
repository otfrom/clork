(ns world
  (:use clork))

(defrecord Player [location items])

(defstruct item :description)

(defstruct container :description :items)

(defstruct room :exits :description :items)

(def *world* {:rooms {:hall (struct room
                                    {:w :kitchen
                                     :n :study}
                                    "hall"
                                    [:sword :water])
                      :kitchen (struct room
                                       {:e :hall}
                                       "kitchen")
                      :study (struct room
                                     {:s :hall}
                                     "study")}
              :players {:player1 (Player. :hall [])
                        :player2 (Player. :kitchen [:cloth])}
              :items {:sword (struct item "A very pointy sword.")
                      :cloth (struct item "A dirty dishcloth.")
                      :water (struct item "Some wet water.")}})

