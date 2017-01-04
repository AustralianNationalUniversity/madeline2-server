package madeline2.server

import org.apache.log4j.Logger


/**
 * Created by philip on 4/01/17.
 */
class CliExec {

    static Logger logger = Logger.getLogger(CliExec.class)

    /**
     * Execute on the command line
     * @param command List of command arguments or command represented as a String
     * @return
     */
    public static String execCommand(Object command, File workingDir = null, OutputStream os=null, Boolean doLog = true, Boolean ignoreStdErr = false) {
        boolean doReturn = false
        if (os == null) {
            os =  new ByteArrayOutputStream()
            doReturn = true
        }
        ByteArrayOutputStream serr = new ByteArrayOutputStream()

        if (doLog) {
            logger.info("Running command: "+command)
        }
        Process p = command.execute(null, workingDir)
        p.consumeProcessOutput(os, serr)
        p.waitFor()
        //p.waitForProcessOutput()
        //p.waitForOrKill(30000)

        if (! ignoreStdErr && serr.size() > 0) {
            logger.error(serr.toString())
            throw new Exception(serr.toString())
        }

        if (doReturn)
            return new String(os.toString())
        else
            return null
    }


}
