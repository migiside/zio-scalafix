/*
rule = PreferZIOMapBoth
 */
package io.github.migiside.fix

import zio.ZIO

class PreferZIOMapBoth {

  ZIO.attempt(1).mapError(e => e).map(_ * 2)

}
