import os

from os import walk

templatePath = r'templates/modelTemplate.txt'
writePath = r'/Source/Api/service-hmlFhirConverter/src/main/java/org/nmdp/hmlfhirconverter/domain'


class ModelGenerator:
    def get_template(self):
        with open(templatePath, 'r') as fileReader:
            return fileReader.read()

    def write_file(self, fileContents, fileName):
        path = os.path.join(writePath, self.get_file_name(fileName))
        with open(path, 'w') as fileWriter:
            fileWriter.write(fileContents)

    def get_file_name(self, className):
        return className + '.java'

    def file_exists(self, className):
        for (dirpath, dirnames, filenames) in walk(writePath):
            return self.get_file_name(className) in filenames
