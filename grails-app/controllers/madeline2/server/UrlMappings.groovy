package madeline2.server

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(redirect: '/upload')
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
