package io.github.migiside.fix

import scalafix.v1._

import scala.meta._

class PreferZIOOrElseFail extends SemanticRule("PreferZIOOrElseFail") {

  override def fix(implicit doc: SemanticDocument): Patch = {
    doc.tree.collect {
      case Term.Apply.After_4_6_0(
            Term.Select(_, method @ Term.Name("orElse")),
            arg @ Term.ArgClause(
              List(
                Term.Apply.After_4_6_0(
                  failSelect @ Term.Select(_, Term.Name("fail")),
                  Term.ArgClause(List(failArg), _)
                )
              ),
              _
            )
          ) if method.symbol.value == "zio/ZIO#orElse()." && failSelect.symbol.value == "zio/ZIO.fail()." =>
        Patch.replaceTree(method, "orElseFail") + Patch.replaceTree(arg, s"(${failArg.toString()})")
      case Term.Apply.After_4_6_0(
            Term.Select(_, method @ Term.Name("mapError")),
            arg @ Term.ArgClause(
              List(
                Term.Function.After_4_6_0(
                  Term.ParamClause(List(Term.Param(_, Name.Placeholder(), _, _)), _),
                  body
                )
              ),
              _
            )
          ) if method.symbol.value == "zio/ZIO#mapError()." =>
        Patch.replaceTree(method, "orElseFail") + Patch.replaceTree(arg, s"(${body.toString()})")
    }.asPatch
  }

}
