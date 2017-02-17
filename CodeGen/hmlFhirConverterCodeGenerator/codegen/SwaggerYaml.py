

class SwaggerYaml:

    def __init__(self, swagger, info, schemes, basePath, produces, paths, definitions):
        self.swagger = swagger
        self.info = info
        self.schemes = schemes
        self.basePath = basePath
        self.produces = produces
        self.paths = paths
        self.definitions = definitions
