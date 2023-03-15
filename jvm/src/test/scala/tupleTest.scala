// https://scalameta.org/munit/docs/getting-started.html
import com.doofin.stdScalaCross._

/* fixed length vector as tuples  */
class tupleTest extends munit.FunSuite {

  test("map int vectors to int") {
    val result = (1, 2, 3).mapConst((x: Int) => x * 2)
  }

  /* test("map int vectors to int") {
    val result = (1, 2, 3).mapConst((x: Int) => (x * 2).toString())
  } */

}
