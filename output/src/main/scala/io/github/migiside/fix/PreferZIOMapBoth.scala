package io.github.migiside.fix

import zio.ZIO

class PreferZIOMapBoth {

  ZIO.attempt(1).mapBoth(e => e, _ * 2)

}
