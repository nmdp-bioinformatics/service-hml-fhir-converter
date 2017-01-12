package org.nmdp.hmlfhirconverter.domain;

/**
 * Created by abrown3 on 12/21/16.
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

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

    @XmlAttribute
    private Boolean active;

    @XmlAttribute
    private Date dateCreated;

    public TypingTestName() {

    }

    public TypingTestName(io.swagger.model.TypingTestName typingTestName) {
        this.name = typingTestName.getName();
        this.description = typingTestName.getDescription();
        this.id = typingTestName.getId();
        this.active = typingTestName.getActive();
        this.dateCreated = handleDateStamping(typingTestName.getDateCreated());
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
        dto.setActive(this.active);
        dto.setDateCreated(this.dateCreated);

        return dto;
    }

    private Date handleDateStamping(Date date) {
        if (date == null) {
            return new Date();
        }

        return date;
    }
}
