package stdScala

trait types {
  type SeqTup[a, b] = Seq[(a, b)]
  type |[a, b] = Either[a, b]
//  sealed trait coprod3[a, b, c]

  val unit: Unit = ()
  def undefined: Nothing = ???
}
