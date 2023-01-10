// https://scalameta.org/munit/docs/getting-started.html
// import com.doofin.stdScala.stdScalaCross._
import com.doofin.stdScalaCross._

class dbgTest extends munit.FunSuite {
  test("dbg should work with dynamic value") {
    val a = 1
    val b = 2
    val c = (0 to 10).foldLeft(0)(_ + _)
    dbg(a, b, c)
    dbg(a)

  }
  test("print multiple variables") { pt(1, 2, "a") }
  test("fixed length vector map") {
    val result = (1, 2, 3).mapConst((x: Int) => x * 2)
  }
  /*   test("example test that succeeds") {
    val obtained = 42
    val expected = 42
    assertEquals(obtained, expected)
  } */
}
