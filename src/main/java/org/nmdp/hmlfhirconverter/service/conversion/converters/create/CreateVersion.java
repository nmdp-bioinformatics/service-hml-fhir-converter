package org.nmdp.hmlfhirconverter.service.conversion.converters.create;
import io.swagger.model.Version;
/**
 * Created by ekan on 5/2/2017.
 */
public class CreateVersion {

    public static Version createVersion(String versionName) {
        Version version = new Version();

        if (versionName == null) {
            return version;
        }

        version.setName(versionName);
        return version;
    }
}
