(ns repl-dd-clojure-kata.core)

(defn replacement->condition [replacement]
  (select-keys replacement [:Id]))

(defn replacement->value [replacement]
  (select-keys replacement [:Value]))

(defn condition->keys [condition]
  (keys condition))

(defn condition->value [condition]
  (get-in condition (condition->keys condition)))

(defn value->keys [value]
  (concat (keys value) (keys (get-in value (keys value)))))

(defn value->value [value]
  (get-in value (value->keys value)))

(defn update-in-parameter
  ([parameter replacement]
   (let [condition (replacement->condition replacement)
         value (replacement->value replacement)]
     (update-in-parameter
       parameter
       condition
       value)))
  ([parameter condition value]
   (let [condition-keys (condition->keys condition)
         condition-value (condition->value condition)
         value-keys (value->keys value)
         value-value (value->value value)]
     (update-in-parameter
       parameter
       condition-keys
       condition-value
       value-keys
       value-value)))
  ([parameter condition-keys condition-value value-keys value-value]
   (update-in parameter
              value-keys
              #(if (= (get-in parameter condition-keys) condition-value)
                 value-value
                 %1))))

(defn update-in-card-propeties [properties replacements]
  (map #(reduce update-in-parameter %1 replacements) properties))

(defn update-in-card [input replacements]
  (update input :CardProperties update-in-card-propeties replacements))