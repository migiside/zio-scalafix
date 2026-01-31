package io.github.migiside.fix

import scalafix.v1._

import scala.meta._

class PreferZIOAs extends SemanticRule("PreferZIOAs") {

  override def fix(implicit doc: SemanticDocument): Patch = {
    doc.tree.collect {
      case Term.Apply.After_4_6_0(
            Term.Select(_, method @ Term.Name("map")),
            arg @ Term.ArgClause(
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
          ) if method.symbol.value == "zio/ZIO#map()." =>
        Patch.replaceTree(method, s"as") + Patch.replaceTree(arg, s"(${body.toString()})")
    }.asPatch
  }

}
