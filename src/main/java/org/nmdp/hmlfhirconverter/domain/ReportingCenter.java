package org.nmdp.hmlfhirconverter.domain;

/**
 * Created by Andrew S. Brown, Ph.D., <abrown3@nmdp.org>, on 1/11/17.
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

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Document(collection = "Hml.ReportingCenters")
public class ReportingCenter implements Serializable {

    @XmlAttribute
    @Id
    private String id;

    @XmlAttribute
    private String context;

    @XmlAttribute
    private Boolean active;

    @XmlAttribute
    private Date dateCreated;

    public ReportingCenter() {

    }

    public ReportingCenter(String context) {
        this.context = context;
    }

    public ReportingCenter(String id, String context) {
        this.id = id;
        this.context = context;
    }

    public ReportingCenter(io.swagger.model.ReportingCenter reportingCenter) {
        this.id = reportingCenter.getId();
        this.context = reportingCenter.getContext();
        this.active = reportingCenter.getActive();
        this.dateCreated = handleDateStamping(reportingCenter.getDateCreated());
    }

    public ReportingCenterDto toDto() {
        ReportingCenterDto dto = new ReportingCenterDto();

        dto.setId(this.id);
        dto.setContext(this.context);
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
