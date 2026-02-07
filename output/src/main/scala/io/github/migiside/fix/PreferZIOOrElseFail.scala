package io.github.migiside.fix

import zio.ZIO

class PreferZIOOrElseFail {

  ZIO.attempt("error1-1").orElseFail("error2-1")

  ZIO.attempt("error1-2").orElseFail("error2-2")

}
