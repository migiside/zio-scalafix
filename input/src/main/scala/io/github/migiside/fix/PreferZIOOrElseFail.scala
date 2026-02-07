/*
rule = PreferZIOOrElseFail
 */
package io.github.migiside.fix

import zio.ZIO

class PreferZIOOrElseFail {

  ZIO.attempt("error1-1").orElse(ZIO.fail("error2-1"))

  ZIO.attempt("error1-2").mapError(_ => "error2-2")

}
