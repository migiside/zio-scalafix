/*
rule = PreferZIOSome
 */
package io.github.migiside.fix

import zio._

class PreferZIOSome {

  ZIO.succeed(Some(1))

}
