package org.nmdp.hmlfhirconverter.domain.fhir;

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

import java.util.Date;

public class Patient {
    private String identifier;
    private Boolean active;
    private String name;
    private String telecom;
    private String gender;
    private Date birthDate;
    private Boolean deceased;
    private Address address;
    private Enum<MaritalStatus> maritalStatus;
    private Boolean multipleBirth;
    private Byte[] photo;
    private Practitioner generalPractitioner;
    private Organization managingOrganization;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelecom() {
        return telecom;
    }

    public void setTelecom(String telecom) {
        this.telecom = telecom;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getDeceased() {
        return deceased;
    }

    public void setDeceased(Boolean deceased) {
        this.deceased = deceased;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Enum<MaritalStatus> getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Enum<MaritalStatus> maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Boolean getMultipleBirth() {
        return multipleBirth;
    }

    public void setMultipleBirth(Boolean multipleBirth) {
        this.multipleBirth = multipleBirth;
    }

    public Byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(Byte[] photo) {
        this.photo = photo;
    }

    public Practitioner getGeneralPractitioner() {
        return generalPractitioner;
    }

    public void setGeneralPractitioner(Practitioner generalPractitioner) {
        this.generalPractitioner = generalPractitioner;
    }

    public Organization getManagingOrganization() {
        return managingOrganization;
    }

    public void setManagingOrganization(Organization managingOrganization) {
        this.managingOrganization = managingOrganization;
    }
}
