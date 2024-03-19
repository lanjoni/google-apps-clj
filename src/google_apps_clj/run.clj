(ns google-apps-clj.run
  (:require [google-apps-clj.credentials :as cred]
            [clojure.edn :as edn]))

(defn get-google-creds []
  (let [google-creds (edn/read-string (slurp "config/google-creds.edn"))
        scopes ["https://www.googleapis.com/auth/spreadsheets"]]
     (cred/get-auth-map google-creds scopes)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "This is a test for authentication. Remember to put your configuration file inside `config/google-creds.edn`!")
  (println "Starting...")
  (get-google-creds))
