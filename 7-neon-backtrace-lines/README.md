# No line numbers in Neon backtrace

## Steps to reproduce

On OS X 10.9.5, Rust can compile a release binary that includes line numbers in the backtrace:


    cd native/a_panic
    cargo run --release
    
Output:

    2018-05-08 15:22:34 ERROR [log_panics] thread 'main' panicked at 'oh no!': src/lib.rs:7
    stack backtrace:
       0:        0x1000fba9e - backtrace::backtrace::trace::h6954c675fdb81650
                            at /Users/dev/.cargo/registry/src/github.com-1ecc6299db9ec823/backtrace-0.3.7/src/backtrace/libunwind.rs:53
       1:        0x1000fa01c - backtrace::capture::Backtrace::new::h44f202edd8c21d9b
                            at /Users/dev/.cargo/registry/src/github.com-1ecc6299db9ec823/backtrace-0.3.7/src/capture.rs:88
    ...


However, the same code compiled with Neon has no line numbers in the backtrace:

    yarn install
    node -e 'require("./")'
    
Output:

    2018-05-08 15:27:42 ERROR [log_panics] thread 'unnamed' panicked at 'oh no!': a_panic/src/lib.rs:7
    stack backtrace:
       0:        0x106de4e6e - backtrace::backtrace::trace::ha74a84b436b4090e
       1:        0x106de299c - backtrace::capture::Backtrace::new::h180d14cb08d60248
       2:        0x106de1eed - log_panics::init::_$u7b$$u7b$closure$u7d$$u7d$::h990ff31ff59f1e7d
       3:        0x106e12870 - std::panicking::rust_panic_with_hook::hfb431ab23831437f
       4:        0x106dd8821 - std::panicking::begin_panic::h6ef215fe9e0c41fe
       5:        0x106dd878d - a_panic::panic_please::h8d3d02c8208552ed
       6:        0x106dc9cb5 - backtrace_test::hello::hb394c4370bb7ceec
       7:        0x106dc9e5b - std::panicking::try::do_call::h5c5f1cbb0951adfa (.llvm.5600580859378085660)
       8:        0x106e2d38e - __rust_maybe_catch_panic
    ...

    
The output is the same, even when using the `--debug` flag:

    yarn run -- neon build --debug
    node -e 'require("./")'

or when adding

    [profile.release]
    debug = true
    
to Cargo.toml.
