package org.nmdp.hmlfhirconverter.domain.fhir;

/**
 * Created by Andrew S. Brown, Ph.D., <abrown3@nmdp.org>, on 3/28/17.
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

import org.joda.time.DateTime;

public class Observation {
    
    private Identifier identifier;
    private Reference basedOn;
    private Status status;
    private Category category;
    private Code code;
    private String subject;
    private String context;
    private DateTime effective;
    private DateTime issued;
    private Practitioner performer;
    private Object value;
    private Object dataAbsentReason;
    private Object interpretation;
    private String comment;
    private Object bodySite;
    private Object method;
    private Specimen specimen;

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Reference getBasedOn() {
        return basedOn;
    }

    public void setBasedOn(Reference basedOn) {
        this.basedOn = basedOn;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public DateTime getEffective() {
        return effective;
    }

    public void setEffective(DateTime effective) {
        this.effective = effective;
    }

    public DateTime getIssued() {
        return issued;
    }

    public void setIssued(DateTime issued) {
        this.issued = issued;
    }

    public Practitioner getPerformer() {
        return performer;
    }

    public void setPerformer(Practitioner performer) {
        this.performer = performer;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getDataAbsentReason() {
        return dataAbsentReason;
    }

    public void setDataAbsentReason(Object dataAbsentReason) {
        this.dataAbsentReason = dataAbsentReason;
    }

    public Object getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(Object interpretation) {
        this.interpretation = interpretation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Object getBodySite() {
        return bodySite;
    }

    public void setBodySite(Object bodySite) {
        this.bodySite = bodySite;
    }

    public Object getMethod() {
        return method;
    }

    public void setMethod(Object method) {
        this.method = method;
    }

    public Specimen getSpecimen() {
        return specimen;
    }

    public void setSpecimen(Specimen specimen) {
        this.specimen = specimen;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    private Device device;
}
