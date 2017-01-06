<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Genogram Generator</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
<content tag="nav">
    <li class="dropdown">
        <a href="mailto:it.apf@anu.edu.au" >it.apf@anu.edu.au</a>
    </li>
</content>

<div id="content" role="main">
    <section class="row colset-2-its">
        <h1>Welcome to Genogram Generator</h1>

        <p>
            Upload a pedigree file to generate a genogram diagram in SVG format. An example genogram below:
        </p>

        <div class="svg" role="presentation">
            <div class="grails-logo-container">
                <asset:image src="genogram.png" width="40%" style="padding: 15px;"/>
            </div>
        </div>


        <div role="navigation">

            <h2>Upload Pedigree</h2>
            <div style="margin-left: 30px;">
                <div>
                    Accepted file formats (scroll down to section 'Pedigree file formats'). It is highly recommended to use the <b>tab delimited format</b>:
                    <a href="http://madeline.med.umich.edu/madeline/documentation.php" target="_blank">Pedigree file formats</a>
                </div>
                <div>
                    Sample file: <a href="http://madeline.med.umich.edu/madeline/testdata/input/si_002.data">si_002.data</a>
                </div>
                <div>
                    <g:uploadForm action="generate">
                        <input type="file" name="pedigreeFile" />
                        <br/>
                        <g:submitButton name="generate" value="Submit"  />
                    </g:uploadForm>
                </div>
            </div>
        </div>

        <div role="navigation">
            <h2>Web service</h2>

            <div style="margin-left: 30px;">

                <p>Example using the cURL command for linux operating systems:</p>

                <p style="border: solid 1px gray; padding: 10px;">
                    curl -X POST -H 'Content-Type:text/plain' --data-binary @pedigreeFile.ped ${grailsApplication.config.grails.serverURL}/${grailsApplication.config.server.contextPath}/webservice/genogram
                </p>
            </div>
        </div>

        <div role="navigation">
            <h2>Acknowledgements</h2>

            <div style="margin-left: 30px;">

                <p>
                    This project uses Madeline 2.0 as the underlying technology for generating genograms. More details about Madeline 2.0 can be found here:
                    <a href="http://madeline.med.umich.edu/madeline/" target="_blank">Madeline 2.0</a>
                </p>
            </div>
        </div>

        <div role="navigation">
            <h2>Open Source</h2>

            <div style="margin-left: 30px;">

                <p>
                    This project is open source and available on GitHub:
                    <br/>
                    <a href="https://github.com/AustralianNationalUniversity/madeline2-server" target="_blank">GitHub Repository</a>
                </p>
            </div>
        </div>

    </section>
</div>

</body>
</html>
