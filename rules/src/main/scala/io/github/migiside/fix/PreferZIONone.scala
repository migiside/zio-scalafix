package io.github.migiside.fix

import scalafix.v1._

import scala.meta._

class PreferZIONone extends SemanticRule("PreferZIONone") {

  override def fix(implicit doc: SemanticDocument): Patch = {
    doc.tree.collect {
      case Term.Apply.After_4_6_0(
            Term.Select(
              Term.Name("ZIO"),
              sm @ Term.Name("succeed")
            ),
            argc @ Term.ArgClause(
              List(
                n @ Term.Name("None")
              ),
              _
            )
          ) if n.symbol.value == "scala/None." => {
        Patch.replaceTree(sm, "none") + Patch.replaceTree(argc, "")
      }
    }.asPatch

  }

}
