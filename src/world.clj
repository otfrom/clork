(ns world
  (:use clork))

(defrecord Player [location items])

(defrecord Item [description])

(defstruct container :description :items)

(defstruct room :exits :description :items)

(def *world* {:rooms {:hall (struct room
                                    {:w :kitchen
                                     :n :study}
                                    "hall"
                                    #{:sword :water})
                      :kitchen (struct room
                                       {:e :hall}
                                       "kitchen"
                                       #{})
                      :study (struct room
                                     {:s :hall}
                                     "study"
                                     #{})}
              :players {:player1 (Player. :hall #{})
                        :player2 (Player. :kitchen #{:cloth})}
              :items {:sword (Item. "A very pointy sword.")
                      :cloth (Item. "A dirty dishcloth.")
                      :water (Item. "Some wet water.")}})

