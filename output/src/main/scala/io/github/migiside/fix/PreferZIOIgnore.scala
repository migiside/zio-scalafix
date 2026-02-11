package io.github.migiside.fix

import zio.ZIO

class PreferZIOIgnore {

  ZIO.attempt(1).ignore

}
