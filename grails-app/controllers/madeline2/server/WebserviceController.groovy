package madeline2.server

import org.apache.log4j.Logger

/**
 *
 * Controller for creating genograms
 *
 * @author Philip Wu
 */
class WebserviceController {

    Logger logger = Logger.getLogger(WebserviceController.class)

    def genogram() {

        def pedigree = request.getReader().text
        logger.info("pedigree="+pedigree)

        // Save the file to disk to temporary folder
        String filename = request.getRemoteAddr()
        if ( ! filename) {
            filename = ''
        }
        filename += "_"+System.currentTimeMillis()+"_"+Thread.currentThread().id

        logger.info("filename: "+filename)
        File file = File.createTempFile(filename,".madeline2")
        file << pedigree

        logger.info("absPath="+file.absolutePath)

        // Execulte command line to generate genogram
        //File genogramFile = File.createTempFile(filename+"_genogram", ".svg")
        String genogramFilenamePrefix = filename + "_genogram"
        // Write the genogram in the response as a downloadable image file
        String madeline2Command = "madeline2 --outputprefix "+genogramFilenamePrefix +" "+file.absolutePath

        File workingDir = new File("/tmp")
        String resp = CliExec.execCommand(madeline2Command, workingDir, null, true, true)
        logger.info("resp="+resp)

        // Write the image to the response
        String genogramFilename = genogramFilenamePrefix + '.svg'
        File genogramFile = new File(workingDir.absolutePath+"/"+genogramFilename)

        //response.contentType = 'image/svg+xml'
        //response.outputStream << genogramFile.bytes
        //response.outputStream.flush()

        render(file: genogramFile, filename: genogramFile.name, contentType:'image/svg+xml')
    }
}
