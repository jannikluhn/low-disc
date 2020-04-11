(defproject low-disc "0.1.0-SNAPSHOT"
  :description "Low-discrepancy sequences"
  :url "http://github.com/absentpig/low-disc"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :repl-options {}
  :profiles {:dev {:dependencies [[quil "3.1.0"]]}}
  :jar-exclusions [#"low_disc/plot\.clj"])
