import com.doofin.stdScalaCross._

val a = 1
val b = 2
val dyn = (0 to 10).foldLeft(0)(_ + _)
dbg(a, b, dyn)
