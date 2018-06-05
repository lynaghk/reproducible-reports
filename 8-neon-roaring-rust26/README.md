# C library compilation error, but only with Neon on Mac

This is a minimal Rust / node.js project created by `neon new` with a single dependency, [croaring-rs](https://github.com/saulius/croaring-rs).

On OS X with Rust 1.26.1, the compilation fails with

    process didn't exit successfully: `/Users/dev/work/reproducible-reports/8-neon-roaring-rust26/native/target/release/build/croaring-sys-cc20bb91cebf5e62/build-script-build` (signal: 11, SIGSEGV: invalid memory reference)
    
However, it compiles fine under Rust 1.25.0, without Neon, and on Linux.

See the following table:

    | Compiles? | Rust version | OS           | Neon? |
    |-----------+--------------+--------------+-------|
    | No        |       1.26.1 | OS X 10.9.5  | yes   |
    | Yes       |       1.25.0 | OS X 10.9.5  | yes   |
    | Yes       |       1.26.1 | OS X 10.9.5  | no    |
    | Yes       |       1.25.0 | OS X 10.9.5  | no    |
    | Yes       |       1.26.1 | Ubuntu 18.04 | yes   |
    | Yes       |       1.25.0 | Ubuntu 18.04 | yes   |
    | Yes       |       1.26.1 | Ubuntu 18.04 | no    |
    | Yes       |       1.25.0 | Ubuntu 18.04 | no    |


To compile with Neon, run:

    yarn install
    
in this directory.

To compile without Neon, run

    git clone https://github.com/saulius/croaring-rs
    cd croaring-rs
    cargo test
    
To try different versions of Rust, you can run (for example):

    rustup override set 1.25.0

before one of the two commands above.

Here's the build error in full from the failing case:

    error: failed to run custom build command for `croaring-sys v0.3.1`
    process didn't exit successfully: `/Users/dev/work/reproducible-reports/8-neon-roaring-rust26/native/target/release/build/croaring-sys-cc20bb91cebf5e62/build-script-build` (signal: 11, SIGSEGV: invalid memory reference)
    --- stdout
    TARGET = Some("x86_64-apple-darwin")
    OPT_LEVEL = Some("3")
    TARGET = Some("x86_64-apple-darwin")
    HOST = Some("x86_64-apple-darwin")
    TARGET = Some("x86_64-apple-darwin")
    TARGET = Some("x86_64-apple-darwin")
    HOST = Some("x86_64-apple-darwin")
    CC_x86_64-apple-darwin = None
    CC_x86_64_apple_darwin = None
    HOST_CC = None
    CC = None
    HOST = Some("x86_64-apple-darwin")
    TARGET = Some("x86_64-apple-darwin")
    HOST = Some("x86_64-apple-darwin")
    CFLAGS_x86_64-apple-darwin = None
    CFLAGS_x86_64_apple_darwin = None
    HOST_CFLAGS = None
    CFLAGS = None
    DEBUG = Some("false")
    running: "cc" "-O3" "-ffunction-sections" "-fdata-sections" "-fPIC" "-m64" "-std=c11" "-march=native" "-O3" "-Wall" "-Wextra" "-o" "/Users/dev/work/reproducible-reports/8-neon-roaring-rust26/native/target/release/build/croaring-sys-6347002cab243e23/out/CRoaring/roaring.o" "-c" "CRoaring/roaring.c"
    exit code: 0
    TARGET = Some("x86_64-apple-darwin")
    TARGET = Some("x86_64-apple-darwin")
    HOST = Some("x86_64-apple-darwin")
    AR_x86_64-apple-darwin = None
    AR_x86_64_apple_darwin = None
    HOST_AR = None
    AR = None
    TARGET = Some("x86_64-apple-darwin")
    TARGET = Some("x86_64-apple-darwin")
    running: "ar" "crs" "/Users/dev/work/reproducible-reports/8-neon-roaring-rust26/native/target/release/build/croaring-sys-6347002cab243e23/out/libroaring.a" "/Users/dev/work/reproducible-reports/8-neon-roaring-rust26/native/target/release/build/croaring-sys-6347002cab243e23/out/CRoaring/roaring.o"
    exit code: 0
    TARGET = Some("x86_64-apple-darwin")






