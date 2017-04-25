package org.nmdp.hmlfhirconverter.domain.fhir;

import org.springframework.stereotype.*;

/**
 * Created by Andrew S. Brown, Ph.D., <abrown3@nmdp.org>, on 3/27/17.
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

public class Sequence {
    private Identifier identifier;
    private Code type;
    private Integer coordinateSystem;
    private Patient patient;
    private Specimen specimen;
    private Device device;
    private Organization performer;
    private Integer quantity;
    private BackboneElement referenceSeq;
    private Variant variant;
    private String observedSeq;
    private Quality quality;
    private Integer readCoverage;
    private Repository repository;
    private Sequence pointer;

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Code getType() {
        return type;
    }

    public void setType(Code type) {
        this.type = type;
    }

    public Integer getCoordinateSystem() {
        return coordinateSystem;
    }

    public void setCoordinateSystem(Integer coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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

    public Organization getPerformer() {
        return performer;
    }

    public void setPerformer(Organization performer) {
        this.performer = performer;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BackboneElement getReferenceSeq() {
        return referenceSeq;
    }

    public void setReferenceSeq(BackboneElement referenceSeq) {
        this.referenceSeq = referenceSeq;
    }

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }

    public String getObservedSeq() {
        return observedSeq;
    }

    public void setObservedSeq(String observedSeq) {
        this.observedSeq = observedSeq;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public Integer getReadCoverage() {
        return readCoverage;
    }

    public void setReadCoverage(Integer readCoverage) {
        this.readCoverage = readCoverage;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public Sequence getPointer() {
        return pointer;
    }

    public void setPointer(Sequence pointer) {
        this.pointer = pointer;
    }
}
