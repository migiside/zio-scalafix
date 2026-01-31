package io.github.migiside.fix

import zio._

class PreferZIOAs {

  ZIO.succeed(1).as(2)

  Seq(1).map(_ => 2)

}
