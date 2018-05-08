extern crate log_panics;
extern crate simple_logger;

pub fn panic_please() {
    simple_logger::init().unwrap();
    log_panics::init();
    panic!("oh no!");
}
