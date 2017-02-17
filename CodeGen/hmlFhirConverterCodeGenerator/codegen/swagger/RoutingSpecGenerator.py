import SwaggerYaml
import os
import yaml

from os import walk

import pdb

specPath = r'templates/spec'
modelCollectionTemplate = r'templates/spec/models.txt'
writePath = r'/Source/Api/service-hmlFhirConverter/src/main/resources/swagger'
templatePath = r'templates/swaggerSpecPathTemplate.txt'

class RoutingSpecGenerator:

    def get_template(self):
        with open(templatePath, 'r') as fileReader:
            return fileReader.read()

    def write_file(self, path, fileContents):
        with open(path, 'w') as fileWriter:
            fileWriter.write(fileContents)

    def get_file_name(self, className):
        return className + '.yaml'

    def read_base_spec_yaml(self):
        path = os.path.join(specPath, 'swagger-spec.yaml')
        with open(path, 'r') as fileReader:
            return yaml.safe_load(fileReader)

    def read_yaml_file_to_object(self, path):
        with open(path, 'r') as fileReader:
            return yaml.safe_load(fileReader)

    def read_yaml_file_to_string(self, path):
        yamlModel = self.read_yaml_file_to_object(path)
        return yaml.dump(yamlModel)

    def convert_yaml_object_to_string(self, yObj):
        return str(yaml.dump(yObj))

    def write_yaml_to_paths(self, yModel, modelName):
        path = os.path.join(writePath, 'models')
        path = os.path.join(path, self.get_file_name(modelName))
        yamlString = modelName + ':\n'
        yamlString += yaml.dump(yModel, default_flow_style = False)
        yamlModel = yaml.load(yamlString)
        with open(path, 'w') as fileWriter:
            yaml.dump(yamlModel, fileWriter)

    def write_yaml_to_models(self, yModel, modelName):
        path = os.path.join(writePath, 'models')
        path = os.path.join(path, self.get_file_name(modelName))
        yamlString = modelName + ':\n'
        yamlString += yaml.dump(yModel, default_flow_style = False)
        yamlModel = yaml.load(yamlString)
        with open(path, 'w') as fileWriter:
            yaml.dump(yamlModel, fileWriter)

    def get_model_names(self):
        with open(modelCollectionTemplate, 'r') as fileReader:
            return fileReader.read().split(',')

    def create_swagger_spec(self, yamlObject):
        with open(os.path.join(writePath, 'swagger-spec.yaml'), 'w') as fileWriter:
            fileWriter.write(yaml.dump(yamlObject, default_flow_style = False))

    def create_swagger_object(self, swagger, info, schemes, basePath, produces, paths, definitions):
        return SwaggerYaml.SwaggerYaml(
            swagger,
            info,
            schemes,
            basePath,
            produces,
            paths,
            definitions
        )

    def file_exists(self, className):
        for (dirpath, dirnames, filenames) in walk(writePath):
            return className in filenames
