(ns dc.core
  (:require
   [clojure.string :as str :include-macros true ]
   ;;_[om.core :as om :include-macros true]
   [sablono.core :as sab :include-macros true]
   ;;[quiescent.core :as q :include-macros true]
   )
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest defcard-doc]]))

(enable-console-print!)

(defcard What-is-Devcard " ### Devcards aims to provide a visual REPL experience for ClojureScript

# WHAT!?
"
)
(defcard-doc 
"
### Developing javascript can mean lots of repitition
- Load page 
- Go through the motions
- Find error 
- Change code
- Reload / Rebuild
- Go through the motions
- Does it work yet?
"
)

(defcard-doc
"### Clojure
- Functional laguage
- LISP like lanuage
``` lisp
(add 1 2)
;; => 3
```
- 'Simple' syntax 
- Not many things to learn

### Clojure Script 

- Generates Javascript from Clojure code
- Uses Googles Closure library to optimize js code (confusing)
- Poised to take over the word ( like all the other 1000's of cross compilers)  
- Aims to make the REPL style development possible for web apps 

"
)

(defcard What-is-a-REPL?
"
# REPL : A read–eval–print loop

type stuff in and it gets executed

"
)
#_(defcard Typical-Apple 
  (sab/html [:div {:height "740"}
             [:button {:class "myButton", :href "#" },  "iTouch"   ]
             ])
)

#_(defcard Typical-Google
  (sab/html [:div 
             [:input] [:button "Search" ]
             ])
)
(defn leftHalf []
  {:style {:width "50%", :float "left" } }
)
(defn rightHalf []
  {:style {:width "50%", :float "left" } }
)

(defn calc-a-form [a-form-data]
  (let [{:keys [first-name 
                last-name 
                address-1 
                address-2 
                postal-code 
                full-name] :as data} a-form-data]
    (if (nil? full-name)
      (assoc data :full-name (str/join [first-name " " last-name] ))
      )))

(defn text-field [a-form-data field label ]
  [:div
    [:div { :style {:width "35%",:float "left" } }  label]
    [:div { :style {:width "65%",:float "left" } } 
     [:input { :style {}
               :size 35
               :value (get @a-form-data field )
               :on-change (fn [e]
                            (swap! a-form-data assoc field (.-target.value e))
                            (swap! a-form-data assoc :full-name nil))
              }
     ]] 
  ])

(defn draw-envelope [ a-form-data ]
  (let [{:keys [first-name
                last-name
                full-name
                address-1
                address-2
                postal-code]} (calc-a-form @a-form-data)]
    [:div {:style{ :padding-left "22px"} }
         [:svg [:rect {:width  "100%", :height  "100%" :fill "#FFFACD" ,
                       :stroke-dasharray "5,5", :stroke "#CECEFF" ,:stroke-width "6" }
                 
                ]
          [:image { :href "dog.png", :x "80%", :y "5%" :width"20%", :height "20%"
                   :on-click (fn [e]
                               (print "hi")
                               )
                   }  ]
               [:text {:x "50%", :y "50%", :text-anchor "left" } 
                [:tspan {:x "20%", :y "40%" } full-name ]
                [:tspan {:x "20%", :y "54%", :text-anchor "left"} address-1]
                [:tspan {:x "20%", :y "64%", :text-anchor "left"} address-2]
                [:tspan {:x "20%", :y "80%", :text-anchor "left"} postal-code]      
               ]          
          ]]))

(defn a-form-component [a-form-data]
  (let [{:keys [first-name 
                last-name 
                full-name 
                address-1 
                address-2 
                postal-code]} (calc-a-form @a-form-data)
                [color name-health] (cond
                          (< (.-length full-name) 20) ["green" "Short"]
                          (< (.-length full-name) 30) ["orange" "normal"]
                          :else ["red" "Too Long"])
        ]
    (sab/html
      [:div {:style {:border "1px solid #CECECE", :margin "12px", :padding "12px"  }}
       [:div  (leftHalf)              
               (text-field a-form-data :first-name "First Name")
               (text-field a-form-data :last-name "Last Name")
               [:div  
                [:div { :style {:width "35%",:float "left" } } "Full Name" ]
                [:div { :style {:width "65%", :float "left", :background-color color } } [:input {:value full-name,:size 35 }] 
                 ]  
                
                ]
               
               
               (text-field a-form-data :address-1 "Line1")
               (text-field a-form-data :address-2 "Line2")
               (text-field a-form-data :postal-code "Postal Code")]
       [:div (rightHalf)
        (draw-envelope a-form-data)
        ]
       [:div {:style {:clear "both"} } ]
       ]
      )))
()
(defcard your-awesome-component
  "Envelope Emulator"
  (fn [data-atom _] (a-form-component data-atom))
  {}
)

(defcard-doc

"# This gets old!"
)







(defcard a-form-test
  "Form with a long name filled in "
  (fn [data-atom _] (a-form-component data-atom))
  {:first-name "Huber" :last-name "WOLFESCHLEGELSTEINHAUSENBERGERDORFF" 
   :address-1 "1 Water Way" :postal_code "123213" }
)





(defcard a-form-test
  "A simple form that builds full name case "
  (fn [data-atom _] (a-form-component data-atom))
  {:first-name "Cal" 
   :last-name "Ed"
   :address-1 "123 Happy Plc"
   :address-2 "HubertsVill"
   :postal-code "123123123"
   }

  {:inspect-data true
   :frame true
   :history true})
(defcard a-form-test
  "A simple form that builds full name case "
  (fn [data-atom _] (a-form-component data-atom))
  {:first-name "Jebediah" :last-name "Springoldiens"}
  {:inspect-data false
   :frame true
   :history false})


#_(defcard Your-Application
  (sab/html [:div {:style {:height "200px"} }
               [:div {:style {:height "200px", :float "left" } }
               [:label "FIRST NAME:"] [:input][:label "TYPIC CD:"][:input] [:br]
               [:label "LAST NAME:"] [:input][:label "TQP STAT:"]
                [:input {:type "checkbox"} ][:input {:type "checkbox"} ]  
                [:input {:type "checkbox"} ][:input {:type "checkbox"} ]  
                [:input {:type "checkbox"} ][:input {:type "checkbox"} ]  
                [:input {:type "checkbox"} ]
                [:br]
                [:label "SSN:"] [:input {:size 1}][:input {:size 1}][:input {:size 1}]  
                [:label "TERNARY SFFT/PT:"][:input {:type "checkbox"} ] 
                [:label "vER" ] [:input {:size 5}] [:input {:size 2}] [:br]
                [:label "ID"] [:input] [:label "CAT CD"] [:input] [:id "01020K?"] [:br]
                [:label "DEL PHONE"] [:input {:size 3}] [:input {:size 4}] [:input {:size 5}] [:span "  ...  "] [:label "CITY"] [:input ]
                [:br]
                [:label "AP/AE PHONE"] [:input {:size 3}] [:input {:size 4}] [:input {:size 5}] [:span "  *  "] [:label "POSTAL"] [:input {:size 4} ]

                [:center                 
                [:button "APPLY"] [:button {:disabled true }  "SAVE"] [:button "UNDO"] [:button "HELP"] 
                [:button "DELETE"] [:button "EDIT"] [:br]
                [:button "SELECT"] [:button "BROWSE"] [:button "ERRORS"]
                
                 ]
                
                ]
               [:div {:style {:float "left" }} 
                [:select { :size "12" , :multiple "multiple"} 
                 [:option {:selected "selected" }  "4 - K" ]
                 [:option "AA2-" ]
                 [:option "DK9B" ]
                 [:option {:selected "selected" } "KKA?" ]
                 [:option "CN3" ]
                 [:option {:selected "selected" } "AA-9" ]
[:option {:selected "selected" }  "4 - K" ]
                 [:option "BK-" ]
                 [:option "SIMMO" ]
                 [:option {:selected "selected" } "DMD" ]
                 [:option "SSD" ]
                 [:option {:selected "selected" } "AHD" ]


                 ]
               ]]))


(defn main []
  ;; conditionally start the app based on whether the #main-app-area
  ;; node is on the page
  (if-let [node (.getElementById js/document "main-app-area")]
    (js/React.render (sab/html [:div "This is working"]) node)))

(main)

;; remember to run lein figwheel and then browse to
;; http://localhost:3449/cards.html

