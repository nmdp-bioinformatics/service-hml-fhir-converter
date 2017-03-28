package org.nmdp.hmlfhirconverter.domain;

/**
 * Created by Andrew S. Brown, Ph.D., <abrown3@nmdp.org>, on 2/12/17.
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

import org.nmdp.hmlfhirconverter.domain.base.SwaggerConverter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Document(collection = "Hml.Samples.Typings.AlleleAssignments")
public class AlleleAssignment extends SwaggerConverter<AlleleAssignment, io.swagger.model.AlleleAssignment> implements Serializable, ICascadable {

    private static Class<AlleleAssignment> tClass = AlleleAssignment.class;
    private static Class<io.swagger.model.AlleleAssignment> uClass = io.swagger.model.AlleleAssignment.class;

    public AlleleAssignment() {
        super(tClass, uClass);
    }

    public AlleleAssignment(Boolean active) {
        super (tClass, uClass);
        this.active = active;
        this.dateUpdated = null;
        this.properties = new ArrayList<>();
        this.haploid = new Haploid(true);
        this.genotypes = new ArrayList<>();
    }

    @XmlAttribute
    @Id
    private String id;

    @XmlAttribute
    private String alleleDb;

    @XmlAttribute
    private Date date;

    @XmlAttribute
    private String alleleVersion;

    @XmlAttribute
    private List<Property> properties;

    @XmlAttribute
    private Haploid haploid;

    @XmlAttribute
    private List<Genotype> genotypes;

    @XmlAttribute
    private Boolean active;

    @XmlAttribute
    private Date dateCreated;

    @XmlAttribute
    private Date dateUpdated;
}