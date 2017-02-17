from controller import ControllerGenerator
#from model import ModelGenerator
from repository import CustomRepositoryGenerator
from repository import RepositoryGenerator
from service import ServiceGenerator
from service import ServiceImplGenerator
from swagger import RoutingSpecGenerator

import os
from os import walk

swagger_model_spec_dir = r'/Source/API/service-hmlFhirConverter/src/main/resources/swagger/models'
swagger_paths_spec_dir = r'/Source/API/service-hmlFhirConverter/src/main/resources/swagger/paths'


def replace_class_instances(className, fileContents):
    fileContents = replace_class_named_instances(className, fileContents)
    fileContents = str(fileContents).replace('**CLASSNAME**', className)
    return replace_class_plural_instances(className, fileContents)


def replace_class_named_instances(className, fileContents):
    return str(fileContents).replace('**classname**', js_camel_case_string(className))


def replace_class_plural_instances(className, fileContents):
    pluralUpperClass = transform_class_name_to_plural(className)
    pluralLowerClass = transform_class_name_to_plural(className.lower())
    fileContents = str(fileContents).replace('**PLURAL**', pluralUpperClass)
    return str(fileContents).replace('**plural**', pluralLowerClass)


def transform_class_name_to_plural(className):
    if className[len(className) - 1] == "y":
        return str(className)[:(len(className) - 1)] + "ies"
    return className + "s"


def js_camel_case_string(string):
    return str(string[0]).lower() + string[1:]

controllerGenerator = ControllerGenerator.ControllerGenerator()
serviceGenerator = ServiceGenerator.ServiceGenerator()
serviceImplGenerator = ServiceImplGenerator.ServiceImplGenerator()
repositoryGenerator = RepositoryGenerator.RepositoryGenerator()
customRepositoryGenerator = CustomRepositoryGenerator.CustomRepositoryGenerator()
routingSpecGenerator = RoutingSpecGenerator.RoutingSpecGenerator()

for name in routingSpecGenerator.get_model_names():
    controllerTemplate = controllerGenerator.get_template()
    serviceTemplate = serviceGenerator.get_template()
    serviceImplTemplate = serviceImplGenerator.get_template()
    repositoryTemplate = repositoryGenerator.get_template()
    customRepositoryTemplate = customRepositoryGenerator.get_template()

    controllerTemplate = replace_class_instances(name, controllerTemplate)
    serviceTemplate = replace_class_instances(name, serviceTemplate)
    serviceImplTemplate = replace_class_instances(name, serviceImplTemplate)
    repositoryTemplate = replace_class_instances(name, repositoryTemplate)
    customRepositoryTemplate = replace_class_instances(name, customRepositoryTemplate)

    if not controllerGenerator.file_exists(name):
        controllerGenerator.write_file(controllerTemplate, name)
    if not serviceGenerator.file_exists(name):
        serviceGenerator.write_file(serviceTemplate, name)
    if not serviceImplGenerator.file_exists(name):
        serviceImplGenerator.write_file(serviceImplTemplate, name)
    if not repositoryGenerator.file_exists(name):
        repositoryGenerator.write_file(repositoryTemplate, name)
    if not customRepositoryGenerator.file_exists(name):
        customRepositoryGenerator.write_file(customRepositoryTemplate, name)

yamlObject = routingSpecGenerator.read_base_spec_yaml()
definitions = {}
paths = {}

for (dirpath, dirnames, filenames) in walk(swagger_model_spec_dir):
    for f in filenames:
        if f.split(".")[-1] == "yaml":
            definitions.update(routingSpecGenerator.read_yaml_file_to_object(os.path.join(dirpath, f)))

for (dirpath, dirnames, filenames) in walk(swagger_paths_spec_dir):
    for f in filenames:
        if f.split(".")[-1] == "yaml":
            paths.update(routingSpecGenerator.read_yaml_file_to_object(os.path.join(dirpath, f)))

swaggerYaml = routingSpecGenerator.create_swagger_object(yamlObject['swagger'], yamlObject['info'], yamlObject['schemes'],
    yamlObject['basePath'], yamlObject['produces'], paths, definitions)
routingSpecGenerator.create_swagger_spec(swaggerYaml)
