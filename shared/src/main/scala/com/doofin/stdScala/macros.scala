package com.doofin.stdScala

import scala.quoted.* // imports Quotes, Expr
import pprint.*

object macros {

  /** like rust's dbg! ,support several arguments : dbg(a, b, c) . inspired by
    * https://blog.softwaremill.com/starting-with-scala-3-macros-a-short-tutorial-88e9d2b2584c
    */
  def debugImpl(exprs: Expr[Seq[Any]])(using Quotes): Expr[Unit] = {
    import quotes.reflect.*
    // inline def levUp(e: Expr[_]) = ${ e }

    def getExprValue(e: Expr[Any]): Expr[String] = {
      /* WIP: pretty print
       */
      val exprName = e.show
      val pStr = pprint.apply(e).plainText // not work yet
      // println(pStr)

      /* '{
        ${ Expr(exprName) } + " = " + $e + ${ Expr(pStr) }
      } */

      '{
        ${ Expr(exprName) } + " = " + $e
      }
    }

    val loc = exprs match
      case Varargs(es) =>
        es.head.asTerm.symbol.pos
      case e =>
        e.asTerm.symbol.pos

    val locStr = (loc
      .map { x =>
        val ln = x.startLine
        val fileName =
          x.sourceFile.path.split("/").reverse.take(2).reverse.mkString("/")
        s"[$fileName:$ln]: "
      })
      .getOrElse("[can't show LOC]: ")
    val locStrExpr = Expr(locStr)

    val stringExps: Seq[Expr[String]] = exprs match {
      case Varargs(es) =>
        es.map { e =>
          // println(e.asTerm.symbol.pos);
          e.asTerm match {
            case Literal(c: Constant) => Expr(c.value.toString) // just Literal
            case _ => getExprValue(e) // not Literal,evaluate it first
          }
        }
      case e => List(getExprValue(e))
    }

    val concatenatedStringsExp = (stringExps)
      .reduceOption((e1, e2) => '{ $e1 + ", " + $e2 })
      .getOrElse('{ "" })

    val finalStr = '{ $locStrExpr + $concatenatedStringsExp }

    '{ println($finalStr) }
  }
}
