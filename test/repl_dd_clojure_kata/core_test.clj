(ns repl-dd-clojure-kata.core-test
  (:require [clojure.test :refer :all]
            [repl-dd-clojure-kata.core :refer :all]))

(def expected
  {:RTDataId           "rt-main-values-device",
   :CardProjectionType 9,
   :Keyword            "live-values-items",
   :CardProperties     [{:Parameter    {:Name "Aggregated Strings Availability",
                                        :Unit "%"},
                         :Value        {:Value "00.00", :Date "2018-10-01T00:00:00"},
                         :Id           "id-device:ba986401-c31c-43c7-9065-fc12ee711474:1076",
                         :TypeKey      "8537e625-59cd-537f-93ad-c90b17e32036",
                         :DisplayName  "Aggregated Strings Availability",
                         :DataSourceId 6871}
                        {:Parameter    {:Name "Assigned Insolation",
                                        :Unit "kWh/m2"},
                         :Value        {:Value 5.244289227216684,
                                        :Date  "2018-10-02T15:08:34"},
                         :Id           "id-device:ba986401-c31c-43c7-9065-fc12ee711474:71",
                         :TypeKey      "fd69c2e1-3372-5a48-a51a-24261703561c",
                         :DisplayName  "Assigned Insolation",
                         :DataSourceId 2165}
                        {:Parameter    {:Name "Assigned Irradiance",
                                        :Unit "W/m2"},
                         :Value        {:Value 601.030029296875,
                                        :Date  "2000-01-01T00:00:00"},
                         :Id           "id-device:ba986401-c31c-43c7-9065-fc12ee711474:70",
                         :TypeKey      "3564134b-4cab-5757-98ff-4ff8d48deac6",
                         :DisplayName  "Assigned Irradiance",
                         :DataSourceId 2162}
                        {:Parameter   {:Name "Availability",
                                       :Unit "%"},
                         :Value       {:Value 100, :Date "2018-10-01T00:00:00"},
                         :Id          "id-device:ba986401-c31c-43c7-9065-fc12ee711474:73",
                         :TypeKey     "8c4b0e05-6665-5c72-9d94-747a4c6ff0bb",
                         :DisplayName "Availability", :DataSourceId 2186}
                        {:Parameter   {:Name "Comm Status", :Unit "%"},
                         :Value       {:Value 0, :Date "2018-10-02T15:08:36"},
                         :Id          "id-device:ba986401-c31c-43c7-9065-fc12ee711474:74",
                         :TypeKey     "8e95d579-c7f7-50d7-a00a-7fbe6e5b0f4e",
                         :DisplayName "Comm Status", :DataSourceId 41}],
   :DisplayName        "Live values items"})

(def input
  {:RTDataId           "rt-main-values-device",
   :CardProjectionType 9,
   :Keyword            "live-values-items",
   :CardProperties     [{:Parameter    {:Name "Aggregated Strings Availability",
                                        :Unit "%"},
                         :Value        {:Value "99.88", :Date "2018-10-01T00:00:00"},
                         :Id           "id-device:ba986401-c31c-43c7-9065-fc12ee711474:1076",
                         :TypeKey      "8537e625-59cd-537f-93ad-c90b17e32036",
                         :DisplayName  "Aggregated Strings Availability",
                         :DataSourceId 6871}
                        {:Parameter    {:Name "Assigned Insolation",
                                        :Unit "kWh/m2"},
                         :Value        {:Value 5.244289227216684,
                                        :Date  "2018-10-02T15:08:34"},
                         :Id           "id-device:ba986401-c31c-43c7-9065-fc12ee711474:71",
                         :TypeKey      "fd69c2e1-3372-5a48-a51a-24261703561c",
                         :DisplayName  "Assigned Insolation",
                         :DataSourceId 2165}
                        {:Parameter    {:Name "Assigned Irradiance",
                                        :Unit "W/m2"},
                         :Value        {:Value 601.030029296875,
                                        :Date  "2018-10-02T15:08:34"},
                         :Id           "id-device:ba986401-c31c-43c7-9065-fc12ee711474:70",
                         :TypeKey      "3564134b-4cab-5757-98ff-4ff8d48deac6",
                         :DisplayName  "Assigned Irradiance",
                         :DataSourceId 2162}
                        {:Parameter   {:Name "Availability",
                                       :Unit "%"},
                         :Value       {:Value 100, :Date "2018-10-01T00:00:00"},
                         :Id          "id-device:ba986401-c31c-43c7-9065-fc12ee711474:73",
                         :TypeKey     "8c4b0e05-6665-5c72-9d94-747a4c6ff0bb",
                         :DisplayName "Availability", :DataSourceId 2186}
                        {:Parameter   {:Name "Comm Status", :Unit "%"},
                         :Value       {:Value 0, :Date "2018-10-02T15:08:36"},
                         :Id          "id-device:ba986401-c31c-43c7-9065-fc12ee711474:74",
                         :TypeKey     "8e95d579-c7f7-50d7-a00a-7fbe6e5b0f4e",
                         :DisplayName "Comm Status", :DataSourceId 41}],
   :DisplayName        "Live values items"})

(def replacements
  [{:Value {:Value "00.00"}
    :Id    "id-device:ba986401-c31c-43c7-9065-fc12ee711474:1076"}
   {:Value {:Date "2000-01-01T00:00:00"}
    :Id    "id-device:ba986401-c31c-43c7-9065-fc12ee711474:70"}]
  )

(comment
  (def a-replacement
    {:Value {:Value "00.00"}
     :Id    "id-device:ba986401-c31c-43c7-9065-fc12ee711474:1076"})
  (select-keys a-replacement [:Id])
  (select-keys a-replacement [:Value])
  (defn get-replacement-condition [replacement]
    (select-keys replacement [:Id]))
  (defn get-replacement-value [replacement]
    (select-keys replacement [:Value]))
  )

(defn update-in-parameter [parameter id value-keys-map new-value]
  (update-in parameter
             value-keys-map
             #(if (= (:Id parameter) id)
                new-value
                %1)))

(defn substitute [input replacements]
  )

(deftest acceptance-test
  (testing "should replace input according to incoming instructions"
    (is (= expected (substitute input replacements)))))

(deftest unit-tests
  (testing "should update value in parameter when id matches"
    (let [a-parameter {:Parameter    {:Name "Assigned Irradiance",
                                      :Unit "W/m2"},
                       :Value        {:Value 601.030029296875,
                                      :Date  "2018-10-02T15:08:34"},
                       :Id           "id-device:ba986401-c31c-43c7-9065-fc12ee711474:70",
                       :TypeKey      "3564134b-4cab-5757-98ff-4ff8d48deac6",
                       :DisplayName  "Assigned Irradiance",
                       :DataSourceId 2162}
          id "id-device:ba986401-c31c-43c7-9065-fc12ee711474:70"
          value-keys-map [:Value :Date]
          new-value "2000-01-01T00:00:00"]
      (is (= new-value (get-in
                         (update-in-parameter a-parameter id value-keys-map new-value)
                         value-keys-map))))))