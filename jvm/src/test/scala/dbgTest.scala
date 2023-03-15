// https://scalameta.org/munit/docs/getting-started.html
import com.doofin.stdScalaCross.*

/* basic test case that passes as long as the test body does not crash */
class dbgTest extends munit.FunSuite {
  test("dbg should work with dynamic computed values") {
    val a = 1
    val b = 2
    val dyn = (0 to 10).foldLeft(0)(_ + _)
    dbg(a, b, dyn)
    dbg(a)
  }

  test("dbg should work with List") {
    val c = (0 to 10).toList
    dbg(c)
  }

  test("print multiple variables") { pt(1, 2, "a") }

  /*   test("example test that succeeds") {
    val obtained = 42
    val expected = 42
    assertEquals(obtained, expected)
  } */
}
