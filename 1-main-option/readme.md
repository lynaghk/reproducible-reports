Ticket at: http://dev.clojure.org/jira/browse/CLJS-2007

## Problem

The ClojureScript compiler's `:main` option specifies an "entry point namespace".
In the output JavaScript, I expect the compiler to include only the entry point namespace, and the transitive closure of namespaces required by that namespace.

This example shows that, for ClojureScript r1.9.473, the `:main` seems to be ignored when `:optimizations :whitespace` is set.


## Steps to reproduce

Run:

    curl -L https://github.com/clojure/clojurescript/releases/download/r1.9.473/cljs.jar
    java -cp cljs.jar:src clojure.main build.clj

    open simple.html
    open whitespace.html

In the browser's JavaScript console, `simple.html` prints "A loaded", "Main loaded", which is what I expect --- the namespace `foo.b` (not required by `foo.main`) is not executed.
However, in the `whitespace.html` console, it does print "B loaded".


## Potential fixes

I'd prefer the whitespace optimizations case to act the same as the simple optimizations case.

If this is not possible, I think the compiler should throw an error when the `:main` option is provided together with `:optimizations :whitespace` to prevent confusion / frustration.


## Other thoughts

Maybe this can be fixed by just adding `:whitespace` here: https://github.com/clojure/clojurescript/blob/b799fb9dbddf8bf10815df01b56940c4c705296a/src/main/clojure/cljs/closure.clj#L2270
