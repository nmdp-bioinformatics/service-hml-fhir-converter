package org.nmdp.hmlfhirconverter.domain;

/**
 * Created by abrown3 on 12/21/16.
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Document(collection = "Hml.TypingTestNames")
public class TypingTestName implements Serializable {

    @XmlAttribute
    @Id
    private String id;

    @XmlAttribute
    private String name;

    @XmlAttribute
    private String description;

    public TypingTestName() {

    }

    public TypingTestName(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public TypingTestName(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public TypingTestNameDto toDto() {
        TypingTestNameDto dto = new TypingTestNameDto();

        dto.setName(this.name);
        dto.setId(this.id);
        dto.setDescription(this.description);

        return dto;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "TypingTestName {" +
                " id = " + id +
                " name = " + name +
                " description = " + description +
                " }";
    }
}
