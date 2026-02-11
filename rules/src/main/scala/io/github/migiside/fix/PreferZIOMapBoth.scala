package io.github.migiside.fix

import scalafix.v1._

import scala.meta._

class PreferZIOMapBoth extends SemanticRule("PreferZIOMapBoth") {

  override def fix(implicit doc: SemanticDocument): Patch = {
    doc.tree.collect {
      case t @ Term.Apply.After_4_6_0(
            Term.Select(
              Term.Apply.After_4_6_0(
                Term.Select(qual, mapError @ Term.Name("mapError")),
                Term.ArgClause(List(mapErrorFn), _)
              ),
              map @ Term.Name("map")
            ),
            Term.ArgClause(List(mapFn), _)
          ) if mapError.symbol.value == "zio/ZIO#mapError()." && map.symbol.value == "zio/ZIO#map()." =>
        Patch.replaceTree(t, s"${qual.toString()}.mapBoth(${mapErrorFn.toString()}, ${mapFn.toString()})")
    }.asPatch
  }

}
