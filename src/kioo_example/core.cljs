(ns kioo-example.core
  (:require [kioo.reagent :refer [content set-attr do-> substitute listen lifecycle]]
            [reagent.core :as reagent :refer [atom]])
  (:require-macros [kioo.reagent :refer [defsnippet deftemplate]]))

(declare data nav)


(defsnippet my-nav-item "main.html" [:.nav-item]
  [[caption func]]
  {[:a] (do-> (content caption)
              (listen :on-click func))})


(defsnippet my-header "main.html" [:header] []
  {[:h1] (content (:header @data))
   [:ul] (content (map my-nav-item (:navigation @nav)))})


(deftemplate my-page "main.html" []
  {[:header] (substitute [my-header])
    [:.content] (do->
                  (content (:content @data))
                  (lifecycle {
                              :did-mount (fn [this]
                                           (.log js/console "did-mount!"))
                              }))})


(def data (atom {:header "main"
                 :content "Hello World"}))

(def nav (atom {:navigation [["home" #(swap! data
                                             assoc :content "home")]
                             ["next" #(swap! data
                                             assoc :content "next")]]}))

(reagent/render-component [my-page] (.-body js/document))

