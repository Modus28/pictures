package dbg28.Dirty

import java.io.{BufferedReader, ByteArrayInputStream, ByteArrayOutputStream, InputStreamReader}
import java.nio.charset.StandardCharsets
import annotation.tailrec
import dbg28.Pictures.Point
/**
  * EECS 293
  * Created by Daniel on 11/3/2016.
  * dbg28@case.edu
  *
  * Accepts user input, contains dirty data
  */
object InputHandler {
  val graphPattern = "/([A-Z*])/g".r
  val rowOrColumnPattern = "(\\d)".r


  def processInput(input: List[String]): Unit = {
    // Parse out the first two lines as integers and set those as locally stored variables
    // For each set of text between empty lines, create a Set of Points for it,
    // send all but the last one of the list of these sets to a Pictures process method
    // Send the final set, the completed graph, to a separate processing method
  }


  def main(args: Array[String]) {



    def read: Seq[String]= {
    // WE ARE TRYING TO HAVE IT TERMINATE AFTER TWO EMPTY LINES
      @tailrec
      def reread(xs: Seq[String], wasEmpty: Boolean): Seq[String] = {
        val s = scala.io.StdIn.readLine()
        val currentlyEmpty = s.isEmpty
        println(s)

        if (currentlyEmpty && wasEmpty) xs else reread(s +: xs, wasEmpty)
      }

      reread(Seq[String](), false)
    }
    val string = read

    println(s"test: $string")
















/*


    val bi: BufferedReader = new BufferedReader(new InputStreamReader(System.in))
    val str = "Failed logins from:" + "\n" +
      "   2.115.68.148 (host148-68-static.115-2-b.business.telecomitalia.it): 7 times" + '\n' +
      "   43.255.188.161: 2968 times" + '\n' +
      "   47.251.162.66 (insynq-1.border2.sef203.pnap.net): 521 times" + '\n' +
      "   58.137.72.110: 7 times" + '\n' + "Misc data for testing" + "Illegal users from: " + '\n' +
      "   27.254.44.45: 1 time" + "\n" +
      "   59.175.153.96 (96.153.175.59.broad.wh.hb.dynamic.163data.com.cn): 1 time" + '\n' +
      "   63.251.162.66 (insynq-1.border2.sef005.pnap.net): 521 times" + '\n' +
      "   58.137.72.110: 5 times" + '\n' +
      "   83.251.162.66 (insynq-1.border2.sef043.pnap.net): 521 times" + '\n' +
      "   27.254.44.45: 1 time" + '\n' +
      "   13.251.162.66 (insynq-1.border2.sef005.pnap.net): 521 times" + '\n' +
      "   50.62.42.229 (ip-50-62-42-229.ip.secureserver.net): 7 times" + '\n' +
      "   50.62.42.232 (ip-50-62-42-229.ip.secureserver.thisisatestforalargenamethatwillbeovereightycharacters.net): 6 times"

    System.setIn(new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8)))

    var lines: List[String] = List.empty[String]
    var line: String = ""
    while ({line = bi.readLine(); line} != null){
      lines ::: List(line)
    }
    println(lines(0))

//      for (String numStr: line.split("\\s"))
//    sum += Integer.parseInt(numStr);






*/

    // Accept block of input as List of Strings and send it to processor method in Lists of lines
  }
}
