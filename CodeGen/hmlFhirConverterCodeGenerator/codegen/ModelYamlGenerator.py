from swagger import RoutingSpecGenerator

swagger_model_spec_dir = r'/Source/API/service-hmlFhirConverter/src/main/resources/swagger/models'
original_swagger_spec = r'templates/spec/orig-swagger.yaml'
routingSpecGenerator = RoutingSpecGenerator.RoutingSpecGenerator()
modelNames = routingSpecGenerator.get_model_names()
swaggerOriginalYaml = routingSpecGenerator.read_yaml_file_to_object(original_swagger_spec)

for model in modelNames:
    modelYaml = swaggerOriginalYaml['definitions'][model]
    routingSpecGenerator.write_yaml_to_models(modelYaml, model)
