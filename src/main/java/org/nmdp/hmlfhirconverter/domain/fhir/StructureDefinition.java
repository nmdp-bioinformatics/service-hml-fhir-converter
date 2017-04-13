package org.nmdp.hmlfhirconverter.domain.fhir;

/**
 * Created by Andrew S. Brown, Ph.D., <abrown3@nmdp.org>, on 4/13/17.
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

import java.net.URL;

public class StructureDefinition {

    private URL url;
    private String identifier;
    private String version;
    private String name;
    private String title;
    private Status status;
    private Boolean experimental;
    private DateTime date;
    private String publisher;
    private Contact contact;
    private String description;
    private UsageContext useContext;
    private Jurisdiction jurisdiction;
    private String purpose;
    private String copyright;
    private String keyword;
    private String fhirVersion;
    private StructureDefinitionKind kind;
    private Boolean abstracted;
    private ExtensionContext contextType;
    private String context;
    private String contextVariant;
    private FhirDefinedType type;
    private URL baseDefinition;
    private TypeDerivationRule derivation;

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getExperimental() {
        return experimental;
    }

    public void setExperimental(Boolean experimental) {
        this.experimental = experimental;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UsageContext getUseContext() {
        return useContext;
    }

    public void setUseContext(UsageContext useContext) {
        this.useContext = useContext;
    }

    public Jurisdiction getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(Jurisdiction jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getFhirVersion() {
        return fhirVersion;
    }

    public void setFhirVersion(String fhirVersion) {
        this.fhirVersion = fhirVersion;
    }

    public StructureDefinitionKind getKind() {
        return kind;
    }

    public void setKind(StructureDefinitionKind kind) {
        this.kind = kind;
    }

    public Boolean getAbstracted() {
        return abstracted;
    }

    public void setAbstracted(Boolean abstracted) {
        this.abstracted = abstracted;
    }

    public ExtensionContext getContextType() {
        return contextType;
    }

    public void setContextType(ExtensionContext contextType) {
        this.contextType = contextType;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getContextVariant() {
        return contextVariant;
    }

    public void setContextVariant(String contextVariant) {
        this.contextVariant = contextVariant;
    }

    public FhirDefinedType getType() {
        return type;
    }

    public void setType(FhirDefinedType type) {
        this.type = type;
    }

    public URL getBaseDefinition() {
        return baseDefinition;
    }

    public void setBaseDefinition(URL baseDefinition) {
        this.baseDefinition = baseDefinition;
    }

    public TypeDerivationRule getDerivation() {
        return derivation;
    }

    public void setDerivation(TypeDerivationRule derivation) {
        this.derivation = derivation;
    }
}
