package madeline2.server

import org.apache.log4j.Logger
import org.springframework.web.multipart.MultipartFile

/**
 * Controller for generating genograms by uploading a pedigree file
 */
class UploadController {

    Logger logger = Logger.getLogger(UploadController.class)
    GenogramService genogramService

    def index() {




    }

    def generate() {
        logger.info("generate")
        MultipartFile multipartFile = request.getFile('pedigreeFile')

        File pedigreeFile = genogramService.generatePedigreeFile(request)
        multipartFile.transferTo(pedigreeFile)

        try {
            File genogramFile = genogramService.generateGenogram(pedigreeFile)

            render(file: genogramFile, filename: genogramFile.name, contentType:'image/svg+xml')
            return
        } catch (Exception e) {
            render e.message
        }
    }
}
