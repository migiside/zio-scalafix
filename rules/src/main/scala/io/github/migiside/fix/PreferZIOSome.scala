package io.github.migiside.fix

import scalafix.v1._

import scala.meta._

class PreferZIOSome extends SemanticRule("PreferZIOSome") {

  override def fix(implicit doc: SemanticDocument): Patch = {
    doc.tree.collect {
      case Term.Apply.After_4_6_0(
            Term.Select(
              Term.Name("ZIO"),
              sm @ Term.Name("succeed")
            ),
            Term.ArgClause(
              List(
                s @ Term.Apply.After_4_6_0(
                  Term.Name("Some"),
                  Term.ArgClause(
                    List(value @ _),
                    _
                  )
                )
              ),
              _
            )
          ) if s.symbol.value == "scala/Some." => {
        Patch.replaceTree(sm, "some") + Patch.replaceTree(s, value.toString())
      }
    }.asPatch

  }

}
