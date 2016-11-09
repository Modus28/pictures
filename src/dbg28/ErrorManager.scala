package dbg28

import java.text.SimpleDateFormat
import java.util.Date
import java.util.logging.{FileHandler, Level, Logger, SimpleFormatter}

/**
  * EECS 293
  * Created by Daniel on 11/8/2016.
  * dbg28@case.edu
  *
  *
  */
object ErrorManager {
  // By default we are not in an error state
  var errorState: Boolean = false

  def reportError(objectWithError: Object, descr: String): Unit ={
    errorState = true
    print("error")
    log(objectWithError, descr)
  }

  def log(objectToLow: Object, description: String): Unit = {
    val dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss")
    val date = new Date

    val fileHandler = new FileHandler("pictures.log", true)
    // Log will save in the pictures repository, but where depends on the system
    // Ctrl+F "pictures.log" to find it
    fileHandler.setFormatter(new SimpleFormatter())

    val log: Logger = Logger.getLogger("picturesLog")
    log.addHandler(fileHandler)
    log.setLevel(Level.INFO)
    log.setUseParentHandlers(false)


    log.severe(s"${dateFormat.format(date)} " +
      s" Class $objectToLow failed with error: $description")
  }
}
