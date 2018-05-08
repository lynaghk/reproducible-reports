extern crate a_panic;

#[macro_use]
extern crate neon;

use neon::vm::{Call, JsResult};
use neon::js::JsString;

fn hello(call: Call) -> JsResult<JsString> {
    a_panic::panic_please();

    let scope = call.scope;
    Ok(JsString::new(scope, "hello node").unwrap())
}

register_module!(m, { m.export("hello", hello) });
