(defproject kioo-example "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :jvm-opts ^:replace ["-Xmx1g" "-server"]

  :dependencies [[kioo "0.4.1-SNAPSHOT"]
                 [org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2511"]
                 [reagent "0.4.2"]]

  :plugins [[lein-cljsbuild "1.0.4"]]

  :source-paths ["src"]
  :resource-paths ["resources"]
  
  :cljsbuild { 
    :builds [{:id "dev"
              :source-paths ["src"]
              :compiler {
                :output-to "app.js"
                :pretty-print true         
                :optimizations :simple
                :preamble ["react/react.js"]
                :externs ["react/externs/react.js"]}}]})
