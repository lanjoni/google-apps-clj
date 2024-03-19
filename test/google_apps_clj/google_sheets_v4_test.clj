(ns google-apps-clj.google-sheets-v4-test
  (:require [clj-time.core :as time]
            [clojure.test :refer :all]
            [google-apps-clj.google-sheets-v4 :refer :all])
  (:import [com.google.api.services.sheets.v4.model CellData RowData]))

(deftest test-cell-conversion
  (is (= "foo" (cell->clj "foo")))
  (is (= 2.0 (cell->clj 2.0)))
  (is (= (time/date-time 1950 6 15)
         (cell->clj (time/date-time 1950 6 15))))
  (is (= "foo" (cell->clj (coerce-to-cell "foo"))))
  (is (= 2.0 (cell->clj (coerce-to-cell 2.0M))))
  (is (= ":foo" (cell->clj (coerce-to-cell :foo))))
  (is (= nil (cell->clj (coerce-to-cell nil))))
  (is (= (time/date-time 1950 6 15)
         (cell->clj (coerce-to-cell (time/date-time 1950 6 15)))))
  (is (= 2.01M (cell->clj (currency-cell 2.01M))))
  (is (instance? CellData (cell->clj (formula-cell "A1+B2"))))
  (is (instance? RowData (row->row-data ["foo" nil 2.0])))
  (is (thrown? Exception (coerce-to-cell 299792.457999999984M))))
