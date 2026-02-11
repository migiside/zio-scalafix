package io.github.migiside.fix

import scalafix.v1._

import scala.meta._

class PreferZIOIgnore extends SemanticRule("PreferZIOIgnore") {

  override def fix(implicit doc: SemanticDocument): Patch = {
    doc.tree.collect {
      case t @ Term.Apply.After_4_6_0(
            Term.Select(qual, method @ Term.Name("catchAll")),
            Term.ArgClause(
              List(
                Term.Function.After_4_6_0(
                  Term.ParamClause(List(Term.Param(_, _, _, _)), _),
                  unit @ Term.Select(Term.Name("ZIO"), Term.Name("unit"))
                )
              ),
              _
            )
          ) if method.symbol.value == "zio/ZIO#catchAll()." && unit.symbol.value == "zio/ZIO.unit." =>
        Patch.replaceTree(t, s"${qual.toString()}.ignore")
    }.asPatch
  }

}
