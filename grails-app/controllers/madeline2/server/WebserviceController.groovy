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

    GenogramService genogramService

    def genogram() {

        String pedigree = request.getReader().text

        // Save the file to disk to temporary folder
        File pedigreeFile = genogramService.generatePedigreeFile(request)
        pedigreeFile << pedigree

        // Labels
        List<String> labels = params.list('label')
        logger.info('labels='+labels)

        try {
            File genogramFile = genogramService.generateGenogram(pedigreeFile, labels)
            render(file: genogramFile, filename: genogramFile.name, contentType: 'image/svg+xml')
            return
        } catch (Exception e) {
            render e.message
            return
        }
    }
}
