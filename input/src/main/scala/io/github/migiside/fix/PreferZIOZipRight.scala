/*
rule = PreferZIOZipRight
 */
package io.github.migiside.fix

import zio.ZIO

class PreferZIOZipRight {

  ZIO.succeed(1).flatMap(_ => ZIO.succeed(2))

  Seq(1).flatMap(_ => Seq(2))

}
