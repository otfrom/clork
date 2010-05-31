(ns world
  (:use clork))

(defrecord Player [location items])

(defrecord Item [description])

(defrecord Container [description items])

(defrecord Room [exits description items])

(def *world* {:rooms {:hall (Room.
                             {:w :kitchen
                              :n :study}
                             "hall"
                             #{:sword :water})
                      :kitchen (Room.
                                {:e :hall}
                                "kitchen"
                                #{})
                      :study (Room.
                              {:s :hall}
                              "study"
                              #{})}
              :players {:player1 (Player. :hall #{})
                        :player2 (Player. :kitchen #{:cloth})}
              :items {:sword (Item. "A very pointy sword.")
                      :cloth (Item. "A dirty dishcloth.")
                      :water (Item. "Some wet water.")}})

