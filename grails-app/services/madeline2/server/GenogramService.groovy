package madeline2.server

import grails.transaction.Transactional
import org.apache.log4j.Logger

/**
 * Service for generating genograms
 * @author Philip Wu
 */
class GenogramService {

    Logger logger = Logger.getLogger(GenogramService.class)
    def grailsApplication


    public File generatePedigreeFile(def request) {

        // Save the file to disk to temporary folder
        String filename = request.getRemoteAddr()
        if ( ! filename) {
            filename = ''
        }
        // Guarantee uniqueness across multiple concurrent requests
        filename += "_"+System.currentTimeMillis()+"_"+Thread.currentThread().id
        File pedigreeFile = File.createTempFile(filename,".madeline2")

        return pedigreeFile

    }

    /**
     * Generate the genogram file from the given pedigree file
     * @param pedigreeFile
     * @return
     */
    public File generateGenogram(File pedigreeFile) {
        // Execulte command line to generate genogram
        //File genogramFile = File.createTempFile(filename+"_genogram", ".svg")
        //String genogramFilenamePrefix = filename + "_genogram"
        // Write the genogram in the response as a downloadable image file
        String madeline2Command = "madeline2 --color --outputprefix "+pedigreeFile.name +" "+pedigreeFile.absolutePath

        //File workingDir = new File("/tmp")
        File workingDir = new File(System.getProperty('java.io.tmpdir'))
        logger.info("workingDir="+workingDir)
        String resp = CliExec.execCommand(madeline2Command, workingDir, null, true, true)
        logger.info("resp="+resp)

        // Write the image to the response
        String genogramFilename = pedigreeFile.name + '.svg'
        File genogramFile = new File(workingDir.absolutePath+"/"+genogramFilename)

        return genogramFile
    }
}
