;;   Copyright (c) Zachary Tellman. All rights reserved.
;;   The use and distribution terms for this software are covered by the
;;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;;   which can be found in the file epl-v10.html at the root of this distribution.
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any other, from this software.

(ns potemkin.test.macros
  (:use
    [clojure test]
    [potemkin macros]))

(defn simple-unify-form []
  (unify-gensyms
    `(let [cnt## (atom 0)]
       ~@(map
           (fn [_] `(swap! cnt## inc))
           (range 100))
       cnt##)))

(deftest test-unify-form
  (is (= 100 @(eval (simple-unify-form)))))

(deftest test-condp-case
  (let [f #(condp-case identical? %
             (:abc :def) :foo
             :xyz :bar)]
    (is (= :foo (f :abc) (f :def)))
    (is (= :bar (f :xyz)))))

(deftest test-try*
  )
