

clj --main cljs.main --output-to main_simple.js --target node --optimizations simple --compile repro.main
clj --main cljs.main --output-to main_advanced.js --target node --optimizations advanced --compile repro.main 

node main_simple.js

