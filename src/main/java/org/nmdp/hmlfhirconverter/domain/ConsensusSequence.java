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

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Document(collection = "Hml.Samples.Typings.ConsesnusSequences")
public class ConsensusSequence extends SwaggerConverter<ConsensusSequence, io.swagger.model.ConsensusSequence> implements Serializable, ICascadable {

    private static Class<ConsensusSequence> tClass = ConsensusSequence.class;
    private static Class<io.swagger.model.ConsensusSequence> uClass = io.swagger.model.ConsensusSequence.class;

    public ConsensusSequence() {
        super(tClass, uClass);
    }

    public ConsensusSequence(Boolean active) {
        super (tClass, uClass);
        this.active = active;
        this.dateUpdated = null;
        this.referenceDatabase = new ReferenceDatabase(true);
        this.consensusSequenceBlock = new ConsensusSequenceBlock(true);
    }

    @XmlAttribute
    @Id
    private String id;

    @XmlAttribute
    private Date date;

    @XmlAttribute
    private ReferenceDatabase referenceDatabase;

    @XmlAttribute
    private ConsensusSequenceBlock consensusSequenceBlock;

    @XmlAttribute
    private Boolean active;

    @XmlAttribute
    private Date dateCreated;

    @XmlAttribute
    private Date dateUpdated;
}
