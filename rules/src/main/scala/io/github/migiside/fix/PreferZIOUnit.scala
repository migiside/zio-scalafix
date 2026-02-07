package io.github.migiside.fix

import scalafix.v1._

import scala.meta._

class PreferZIOUnit extends SemanticRule("PreferZIOUnit") {

  override def fix(implicit doc: SemanticDocument): Patch = {
    doc.tree.collect {
      case t @ Term.Apply.After_4_6_0(
            s @ Term.Select(
              Term.Name("ZIO"),
              Term.Name("succeed")
            ),
            Term.ArgClause(
              List(Lit.Unit()),
              _
            )
          ) if s.symbol.value == "zio/ZIOCompanionVersionSpecific#succeed()." =>
        Patch.replaceTree(t, "ZIO.unit")
    }.asPatch
  }

}
