package io.github.migiside.fix

import scalafix.v1._

import scala.meta._

class PreferZIOZipRight extends SemanticRule("PreferZIOZipRight") {

  override def fix(implicit doc: SemanticDocument): Patch = {
    doc.tree.collect {
      case term @ Term.Apply.After_4_6_0(
            Term.Select(select @ _, method @ Term.Name("flatMap")),
            Term.ArgClause(
              List(
                Term.Function.After_4_6_0(
                  Term.ParamClause(
                    List(Term.Param(_, Name.Placeholder(), _, _)),
                    _
                  ),
                  body @ _
                )
              ),
              _
            )
          ) if method.symbol.value == "zio/ZIO#flatMap()." =>
        Patch.replaceTree(
          term,
          Term
            .ApplyInfix(
              select,
              Term.Name("*>"),
              Type.ArgClause(Nil),
              Term.ArgClause(List(body))
            )
            .toString()
        )
    }.asPatch
  }

}
