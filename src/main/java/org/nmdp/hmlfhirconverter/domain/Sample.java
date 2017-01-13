package org.nmdp.hmlfhirconverter.domain;

/**
 * Created by Andrew S. Brown, Ph.D., <abrown3@nmdp.org>, on 1/12/17.
 * <p>
 * service-hmlFhirConverter
 * Copyright (c) 2012-2017 National Marrow Donor Program (NMDP)
 * <p>
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation; either version 3 of the License, or (at
 * your option) any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; with out even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public
 * License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library;  if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307  USA.
 * <p>
 * > http://www.fsf.org/licensing/licenses/lgpl.html
 * > http://www.opensource.org/licenses/lgpl-license.php
 */

import org.nmdp.hmlfhirconverter.util.Converters;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Document(collection = "Hml.Samples")
public class Sample implements Serializable {

    @XmlAttribute
    @Id
    private String id;

    @XmlAttribute
    private String centerCode;

    @XmlAttribute
    private List<CollectionMethod> collectionMethods;

    @XmlAttribute
    private Boolean active;

    @XmlAttribute
    private Date dateCreated;

    public Sample() {

    }

    public Sample(io.swagger.model.Sample sample) {
        this.id = sample.getId();
        this.centerCode = sample.getCenterCode();
        this.collectionMethods = Converters.convertList(
                sample.getCollectionMethods(), c -> new CollectionMethod(c));
        this.active = sample.getActive();
        this.dateCreated = handleDateStamping(sample.getDateCreated());
    }

    public SampleDto toDto() {
        SampleDto dto = new SampleDto();

        dto.setId(this.id);
        dto.setCenterCode(this.centerCode);
        dto.setCollectionMethods(Converters.convertList(this.collectionMethods, c -> c.toDto()));
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
