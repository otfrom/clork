(ns world)

(def rooms {:hall {:description "A large vaulted hall"
                   :exits {:n :kitchen
                           :w :lounge}}
            :kitchen {:description "A kitchen with a roaring fire"
                      :exits {:s :hall}}
            :lounge {:description "A lounge with a red chez longue"
                     :exits {:e :hall}}})

{:location :hall}
