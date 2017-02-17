from swagger import RoutingSpecGenerator

import os

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

swagger_paths_spec_dir = r'/Source/API/service-hmlFhirConverter/src/main/resources/swagger/paths'
routingSpecGenerator = RoutingSpecGenerator.RoutingSpecGenerator()
modelNames = routingSpecGenerator.get_model_names()

for model in modelNames:
    modelTemplate = routingSpecGenerator.get_template()
    modelTemplate = replace_class_instances(model, modelTemplate)
    routingSpecGenerator.write_file(os.path.join(swagger_paths_spec_dir, model + '.yaml'), modelTemplate)