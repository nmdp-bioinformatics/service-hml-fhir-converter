package org.nmdp.hmlfhirconverter.service.conversion.converters;

/**
 * Created by Andrew S. Brown, Ph.D., <abrown3@nmdp.org>, on 3/14/17.
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

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonParseException;

import io.swagger.model.Allele;
import io.swagger.model.AlleleAssignment;
import io.swagger.model.Amplification;
import io.swagger.model.CollectionMethod;
import io.swagger.model.ConsensusSequence;
import io.swagger.model.ConsensusSequenceBlock;
import io.swagger.model.DiploidCombination;
import io.swagger.model.ExtendedItem;
import io.swagger.model.Genotype;
import io.swagger.model.GlString;
import io.swagger.model.Gssp;
import io.swagger.model.Haploid;
import io.swagger.model.Hml;
import io.swagger.model.HmlId;
import io.swagger.model.IupacBases;
import io.swagger.model.LocusBlock;
import io.swagger.model.Project;
import io.swagger.model.Property;
import io.swagger.model.RawRead;
import io.swagger.model.ReferenceDatabase;
import io.swagger.model.ReportingCenter;
import io.swagger.model.Sample;
import io.swagger.model.SbtNgs;
import io.swagger.model.SbtSanger;
import io.swagger.model.Sequence;
import io.swagger.model.SequenceQuality;
import io.swagger.model.Sso;
import io.swagger.model.Ssp;
import io.swagger.model.SubAmplification;
import io.swagger.model.Typing;
import io.swagger.model.TypingMethod;
import io.swagger.model.TypingTestName;
import io.swagger.model.Variant;
import io.swagger.model.VariantEffect;
import io.swagger.model.Version;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;

import java.lang.reflect.Type;

import java.util.List;
import java.util.ArrayList;

public class HmlXmlDeserializer implements JsonDeserializer<Hml> {
    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
    public Hml deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject rootJson = json.getAsJsonObject();
        JsonObject jsonObject = rootJson.get("hml").getAsJsonObject();

        Hml hml = new Hml();
        Version version = new Version();

        hml.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        hml.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        hml.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        hml.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        hml.setHmlId(createHmlId(jsonObject.has(HmlFieldConstants.HML_HMLID) ? jsonObject.get(HmlFieldConstants.HML_HMLID).getAsJsonObject() : null));
        hml.setVersion(createVersion(jsonObject.has(HmlFieldConstants.HML_VERSION) ? jsonObject.get(HmlFieldConstants.HML_VERSION).getAsString() : null));
        hml.setProject(createProject(jsonObject.has(HmlFieldConstants.HML_PROJECT) ? jsonObject.get(HmlFieldConstants.HML_PROJECT).getAsJsonObject() : null));
        hml.setSamples(createSamples(jsonObject.has(HmlFieldConstants.HML_SAMPLE) ? jsonObject.get(HmlFieldConstants.HML_SAMPLE).getAsJsonObject() : null));
        hml.setReportingCenters(createRepotingCenters(jsonObject.has(HmlFieldConstants.HML_REPORTINGCENTER) ? jsonObject.get(HmlFieldConstants.HML_REPORTINGCENTER).getAsJsonObject() : null));
        hml.setProperties(createProperties(jsonObject.has(HmlFieldConstants.HML_PROPERTIES) ? jsonObject.get(HmlFieldConstants.HML_PROPERTIES).getAsJsonObject() : null));
        hml.setTypingTestNames(createTypingTestNames(jsonObject.has(HmlFieldConstants.HML_TYPINGTESTNAME) ? jsonObject.get(HmlFieldConstants.HML_TYPINGTESTNAME).getAsJsonObject() : null));

        return hml;
    }

    private Version createVersion(String versionName) {
        Version version = new Version();

        if (versionName == null) {
            return version;
        }

        version.setName(versionName);
        return version;
    }

    private HmlId createHmlId(JsonObject jsonObject) {
        HmlId hmlId = new HmlId();

        if (jsonObject == null) {
            return hmlId;
        }

        hmlId.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        hmlId.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        hmlId.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        hmlId.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        hmlId.setExtension(jsonObject.has(HmlFieldConstants.HMLID_EXTENSION) ? jsonObject.get(HmlFieldConstants.HMLID_EXTENSION).getAsString() : null);
        hmlId.setRootName(jsonObject.has(HmlFieldConstants.HMLID_ROOT) ? jsonObject.get(HmlFieldConstants.HMLID_ROOT).getAsString() : null);

        return hmlId;
    }

    private Project createProject(JsonObject jsonObject) {
        Project project = new Project();

        if (jsonObject == null) {
            return project;
        }

        project.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        project.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        project.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        project.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        project.setDescription(jsonObject.has(HmlFieldConstants.PROJECT_DESCRIPTION) ? jsonObject.get(HmlFieldConstants.PROJECT_DESCRIPTION).getAsString() : null);
        project.setName(jsonObject.has(HmlFieldConstants.PROJECT_NAME) ? jsonObject.get(HmlFieldConstants.PROJECT_NAME).getAsString() : null);

        return project;
    }

    private List<Sample> createSamples(JsonObject jsonObject) {
        List<Sample> samples = new ArrayList<>();
        Sample sample = new Sample();

        if (jsonObject == null) {
            return samples;
        }

        sample.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        sample.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        sample.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        sample.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        sample.setCenterCode(jsonObject.has(HmlFieldConstants.SAMPLE_CENTERCODE) ? jsonObject.get(HmlFieldConstants.SAMPLE_CENTERCODE).getAsString() : null);
        sample.setSampleId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        sample.setTyping(createTyping(jsonObject.has(HmlFieldConstants.SAMPLE_TYPING) ? jsonObject.get(HmlFieldConstants.SAMPLE_TYPING).getAsJsonObject() : null));
        sample.setProperties(createProperties(jsonObject.has(HmlFieldConstants.SAMPLE_PROPERTY) ? jsonObject.get(HmlFieldConstants.SAMPLE_PROPERTY).getAsJsonObject() : null));
        sample.setCollectionMethods(createCollectionMethods(jsonObject.has(HmlFieldConstants.SAMPLE_COLLECTIONMETHOD) ? jsonObject.get(HmlFieldConstants.SAMPLE_COLLECTIONMETHOD).getAsJsonObject() : null));

        samples.add(sample);
        return samples;
    }

    private List<Property> createProperties(JsonObject jsonObject) {
        List<Property> properties = new ArrayList<>();
        Property property = new Property();

        if (jsonObject == null) {
            return properties;
        }

        property.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        property.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        property.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        property.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        property.setDescription(jsonObject.has(HmlFieldConstants.PROPERTIES_DESCRIPTION) ? jsonObject.get(HmlFieldConstants.PROPERTIES_DESCRIPTION).getAsString() : null);
        property.setName(jsonObject.has(HmlFieldConstants.PROPERTIES_NAME) ? jsonObject.get(HmlFieldConstants.PROPERTIES_NAME).getAsString() : null);
        property.setValue(jsonObject.has(HmlFieldConstants.PROPERTIES_VALUE) ? jsonObject.get(HmlFieldConstants.PROPERTIES_VALUE).getAsString() : null);
        property.setExtendedItems(createExtendedItems(jsonObject.has(HmlFieldConstants.PROPERTIES_EXTENDEDITEMS) ? jsonObject.get(HmlFieldConstants.PROPERTIES_EXTENDEDITEMS).getAsJsonObject() : null));

        properties.add(property);
        return properties;
    }

    private List<ReportingCenter> createRepotingCenters(JsonObject jsonObject) {
        List<ReportingCenter> reportingCenters = new ArrayList<>();
        ReportingCenter reportingCenter = new ReportingCenter();

        if (jsonObject == null) {
            return reportingCenters;
        }

        reportingCenter.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        reportingCenter.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        reportingCenter.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        reportingCenter.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        reportingCenter.setContext(jsonObject.has(HmlFieldConstants.REPORTINGCENTER_CONTEXT) ? jsonObject.get(HmlFieldConstants.REPORTINGCENTER_CONTEXT).getAsString() : null);
        reportingCenter.setCenterId(jsonObject.has(HmlFieldConstants.REPORTINGCENTER_CENTERID) ? jsonObject.get(HmlFieldConstants.REPORTINGCENTER_CENTERID).getAsString() : null);

        reportingCenters.add(reportingCenter);
        return reportingCenters;
    }

    private List<TypingTestName> createTypingTestNames(JsonObject jsonObject) {
        List<TypingTestName> typingTestNames = new ArrayList<>();
        TypingTestName typingTestName = new TypingTestName();

        if (jsonObject == null) {
            return typingTestNames;
        }

        typingTestName.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        typingTestName.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        typingTestName.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        typingTestName.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        typingTestName.setDescription(jsonObject.has(HmlFieldConstants.TYPINGTESTNAMES_DESCRIPTION) ? jsonObject.get(HmlFieldConstants.TYPINGTESTNAMES_DESCRIPTION).getAsString() : null);
        typingTestName.setName(jsonObject.has(HmlFieldConstants.TYPINGTESTNAMES_NAME) ? jsonObject.get(HmlFieldConstants.TYPINGTESTNAMES_NAME).getAsString() : null);

        typingTestNames.add(typingTestName);
        return typingTestNames;
    }

    private List<ExtendedItem> createExtendedItems(JsonObject jsonObject) {
        List<ExtendedItem> extendedItems = new ArrayList<>();
        ExtendedItem extendedItem = new ExtendedItem();

        if (jsonObject == null) {
            return extendedItems;
        }

        extendedItem.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        extendedItem.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        extendedItem.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        extendedItem.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        extendedItem.setItem(jsonObject.has(HmlFieldConstants.EXTENDEDITEM_ITEM) ? jsonObject.get(HmlFieldConstants.EXTENDEDITEM_ITEM).getAsJsonObject() : null);

        extendedItems.add(extendedItem);
        return extendedItems;
    }

    private Typing createTyping(JsonObject jsonObject) {
        Typing typing = new Typing();

        if (jsonObject == null) {
            return typing;
        }

        typing.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        typing.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        typing.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        typing.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        typing.setDate(jsonObject.has(HmlFieldConstants.TYPING_DATE) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.TYPING_DATE).getAsString()) : null);
        typing.setGeneFamily(jsonObject.has(HmlFieldConstants.TYPING_GENEFAMILY) ? jsonObject.get(HmlFieldConstants.TYPING_GENEFAMILY).getAsString() : null);
        typing.setTypingMethod(createTypingMethod(jsonObject.has(HmlFieldConstants.TYPING_TYPINGMETHOD) ? jsonObject.get(HmlFieldConstants.TYPING_TYPINGMETHOD).getAsJsonObject() : null));
        typing.setProperties(createProperties(jsonObject.has(HmlFieldConstants.TYPING_PROPERTY) ? jsonObject.get(HmlFieldConstants.TYPING_PROPERTY).getAsJsonObject() : null));
        typing.setAlleleAssignment(createAlleleAssignment(jsonObject.has(HmlFieldConstants.TYPING_ALLELEASSIGNMENT) ? jsonObject.get(HmlFieldConstants.TYPING_ALLELEASSIGNMENT ).getAsJsonObject() : null));
        typing.setConsensusSequence(createConsensusSequence(jsonObject.has(HmlFieldConstants.TYPING_CONSENSUSSEQUENCE) ? jsonObject.get(HmlFieldConstants.TYPING_CONSENSUSSEQUENCE).getAsJsonObject() : null));

        return typing;
    }

    private ConsensusSequence createConsensusSequence(JsonObject jsonObject) {
        return new ConsensusSequence();
    }

    private AlleleAssignment createAlleleAssignment(JsonObject jsonObject) {
        AlleleAssignment alleleAssignment = new AlleleAssignment();

        if (jsonObject == null) {
            return alleleAssignment;
        }

        alleleAssignment.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        alleleAssignment.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        alleleAssignment.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        alleleAssignment.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        alleleAssignment.setDate(jsonObject.has(HmlFieldConstants.ALLELEASSIGNMENT_DATE) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.ALLELEASSIGNMENT_DATE).getAsString()).toDate() : null);
        alleleAssignment.setAlleleDb(jsonObject.has(HmlFieldConstants.ALLELEASSIGNMENT_ALLELEDB) ? jsonObject.get(HmlFieldConstants.ALLELEASSIGNMENT_ALLELEDB).getAsString() : null);
        alleleAssignment.setAlleleVersion(jsonObject.has(HmlFieldConstants.ALLELEASSIGNMENT_ALLELEVERSION) ? jsonObject.get(HmlFieldConstants.ALLELEASSIGNMENT_ALLELEVERSION).getAsString() : null);
        alleleAssignment.setProperties(createProperties(jsonObject.has(HmlFieldConstants.ALLELEASSIGNMENT_PROPERTY) ? jsonObject.get(HmlFieldConstants.ALLELEASSIGNMENT_PROPERTY).getAsJsonObject() : null));
        alleleAssignment.setGenotypes(createGenotypes(jsonObject.has(HmlFieldConstants.ALLELEASSIGNMENT_GENOTYPE) ? jsonObject.get(HmlFieldConstants.ALLELEASSIGNMENT_GENOTYPE).getAsJsonObject() : null));
        alleleAssignment.setGlString(createGlString(jsonObject.has(HmlFieldConstants.ALLELEASSIGNMENT_GLSTRING) ? jsonObject.get(HmlFieldConstants.ALLELEASSIGNMENT_GLSTRING).getAsJsonObject() : null));
        alleleAssignment.setHaploid(createHaploid(jsonObject.has(HmlFieldConstants.ALLELEASSIGNMENT_HAPLOID) ? jsonObject.get(HmlFieldConstants.ALLELEASSIGNMENT_HAPLOID).getAsJsonObject() : null));

        return alleleAssignment;
    }

    private GlString createGlString(JsonObject jsonObject) {
        GlString glstring = new GlString();

        if (jsonObject == null) {
            return glstring;
        }

        glstring.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        glstring.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        glstring.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        glstring.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        glstring.setUri(jsonObject.has(HmlFieldConstants.GLSTRING_URI) ? jsonObject.get(HmlFieldConstants.GLSTRING_URI).getAsString() : null);
        glstring.setValue(jsonObject.has(HmlFieldConstants.GLSTRING_VALUE) ? jsonObject.get(HmlFieldConstants.GLSTRING_VALUE).getAsString() : null);

        return glstring;
    }

    private Haploid createHaploid(JsonObject jsonObject) {
        Haploid haploid = new Haploid();

        if (jsonObject == null) {
            return haploid;
        }

        haploid.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        haploid.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        haploid.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        haploid.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        haploid.setLocus(jsonObject.has(HmlFieldConstants.HAPLOID_LOCUS) ? jsonObject.get(HmlFieldConstants.HAPLOID_LOCUS).getAsString() : null);
        haploid.setMethod(jsonObject.has(HmlFieldConstants.HAPLOID_METHOD) ? jsonObject.get(HmlFieldConstants.HAPLOID_METHOD).getAsString() : null);
        haploid.setType(jsonObject.has(HmlFieldConstants.HAPLOID_TYPE) ? jsonObject.get(HmlFieldConstants.HAPLOID_TYPE).getAsString() : null);

        return haploid;
    }

    private List<Genotype> createGenotypes(JsonObject jsonObject) {
        List<Genotype> genotypes = new ArrayList<>();
        Genotype genotype = new Genotype();

        if (jsonObject == null) {
            return  genotypes;
        }

        genotype.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        genotype.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        genotype.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        genotype.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        genotype.setDiploidCombinations(createDiploidCombinations(jsonObject.has(HmlFieldConstants.GENOTYPELIST_DIPLOIDCOMBINATION) ? jsonObject.get(HmlFieldConstants.GENOTYPELIST_DIPLOIDCOMBINATION).getAsJsonObject() : null));

        genotypes.add(genotype);
        return genotypes;
    }

    private List<DiploidCombination> createDiploidCombinations(JsonObject jsonObject) {
        List<DiploidCombination> diploidCombinations = new ArrayList<>();
        DiploidCombination diploidCombination = new DiploidCombination();

        if (jsonObject == null) {
            return diploidCombinations;
        }

        diploidCombination.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        diploidCombination.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        diploidCombination.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        diploidCombination.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        diploidCombination.setLocusBlock(createLocusBlock(jsonObject.has(HmlFieldConstants.DIPLOIDCOMBINATION_LOCUSBLOCK) ? jsonObject.get(HmlFieldConstants.DIPLOIDCOMBINATION_LOCUSBLOCK).getAsJsonObject() : null));

        diploidCombinations.add(diploidCombination);
        return diploidCombinations;
    }

    private LocusBlock createLocusBlock(JsonObject jsonObject) {
        LocusBlock locusBlock = new LocusBlock();

        if (jsonObject == null) {
            return locusBlock;
        }

        locusBlock.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        locusBlock.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        locusBlock.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        locusBlock.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        locusBlock.setAlleles(createAlleles(jsonObject.has(HmlFieldConstants.LOCUSBLOCK_ALLELELIST) ? jsonObject.get(HmlFieldConstants.LOCUSBLOCK_ALLELELIST).getAsJsonObject() : null));

        return locusBlock;
    }

    private List<Allele> createAlleles(JsonObject jsonObject) {
        List<Allele> alleles = new ArrayList<>();
        Allele allele = new Allele();

        if (jsonObject == null) {
            return alleles;
        }

        allele.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        allele.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        allele.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        allele.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        allele.setName(jsonObject.has(HmlFieldConstants.ALLELELIST_NAME) ? jsonObject.get(HmlFieldConstants.ALLELELIST_NAME).getAsString() : null);
        allele.setPresent(jsonObject.has(HmlFieldConstants.ALLELELIST_PRESENT) ? jsonObject.get(HmlFieldConstants.ALLELELIST_PRESENT).getAsString() : null);

        alleles.add(allele);
        return alleles;
    }

    private TypingMethod createTypingMethod(JsonObject jsonObject) {
        TypingMethod typingMethod = new TypingMethod();

        if (jsonObject == null) {
            return typingMethod;
        }

        typingMethod.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        typingMethod.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        typingMethod.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        typingMethod.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        typingMethod.setSbtSanger(createSbtSanger(jsonObject.has(HmlFieldConstants.TYPINGMETHOD_SBTSANGER) ? jsonObject.get(HmlFieldConstants.TYPINGMETHOD_SBTSANGER).getAsJsonObject() : null));
        typingMethod.setSbtNgs(createSbtNgs(jsonObject.has(HmlFieldConstants.TYPINGMETHOD_SBTNGS) ? jsonObject.get(HmlFieldConstants.TYPINGMETHOD_SBTNGS).getAsJsonObject() : null));
        typingMethod.setSso(createSso(jsonObject.has(HmlFieldConstants.TYPINGMETHOD_SSO) ? jsonObject.get(HmlFieldConstants.TYPINGMETHOD_SSO).getAsJsonObject() : null));
        typingMethod.setSsp(createSsp(jsonObject.has(HmlFieldConstants.TYPINGMETHOD_SSP) ? jsonObject.get(HmlFieldConstants.TYPINGMETHOD_SSP).getAsJsonObject() : null));

        return typingMethod;
    }

    private SbtNgs createSbtNgs(JsonObject jsonObject) {
        SbtNgs sbtNgs = new SbtNgs();

        if (jsonObject == null) {
            return sbtNgs;
        }

        sbtNgs.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        sbtNgs.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        sbtNgs.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        sbtNgs.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        sbtNgs.setLocus(jsonObject.has(HmlFieldConstants.SBTNGS_LOCUS) ? jsonObject.get(HmlFieldConstants.SBTNGS_LOCUS).getAsString() : null);
        sbtNgs.setProperties(createProperties(jsonObject.has(HmlFieldConstants.SBTNGS_PROPERTY) ? jsonObject.get(HmlFieldConstants.SBTNGS_PROPERTY).getAsJsonObject() : null));
        sbtNgs.setRawReads(createRawReads(jsonObject.has(HmlFieldConstants.SBTNGS_RAWREADS) ? jsonObject.get(HmlFieldConstants.SBTNGS_RAWREADS).getAsJsonObject() : null));
        sbtNgs.setTestId(jsonObject.has(HmlFieldConstants.SBTNGS_TESTID) ? jsonObject.get(HmlFieldConstants.SBTNGS_TESTID).getAsString() : null);
        sbtNgs.setTestIdSource(jsonObject.has(HmlFieldConstants.SBTNGS_TESTIDSOURCE) ? jsonObject.get(HmlFieldConstants.SBTNGS_TESTIDSOURCE).getAsString() : null);

        return sbtNgs;
    }

    private List<RawRead> createRawReads(JsonObject jsonObject) {
        List<RawRead> rawReads = new ArrayList<>();
        RawRead rawRead = new RawRead();

        if (jsonObject == null) {
            return rawReads;
        }

        rawRead.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        rawRead.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        rawRead.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        rawRead.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        rawRead.setAdapterTrimmed(jsonObject.has(HmlFieldConstants.RAWREADS_ADAPTERTRIMMED) ? jsonObject.get(HmlFieldConstants.RAWREADS_ADAPTERTRIMMED).getAsBoolean() : null);
        rawRead.setAvailability(jsonObject.has(HmlFieldConstants.RAWREADS_AVAILABILITY) ? jsonObject.get(HmlFieldConstants.RAWREADS_AVAILABILITY).getAsString() : null);
        rawRead.setFormat(jsonObject.has(HmlFieldConstants.RAWREADS_FORMAT) ? jsonObject.get(HmlFieldConstants.RAWREADS_FORMAT).getAsString() : null);
        rawRead.setPaired(jsonObject.has(HmlFieldConstants.RAWREADS_PAIRED) ? jsonObject.get(HmlFieldConstants.RAWREADS_PAIRED).getAsBoolean() : null);
        rawRead.setPooled(jsonObject.has(HmlFieldConstants.RAWREADS_POOLED) ? jsonObject.get(HmlFieldConstants.RAWREADS_POOLED).getAsBoolean() : null);
        rawRead.setQualityTrimmed(jsonObject.has(HmlFieldConstants.RAWREADS_QUALITYTRIMMED) ? jsonObject.get(HmlFieldConstants.RAWREADS_QUALITYTRIMMED).getAsBoolean() : null);
        rawRead.setUri(jsonObject.has(HmlFieldConstants.RAWREADS_URI) ? jsonObject.get(HmlFieldConstants.RAWREADS_URI).getAsString() : null);

        rawReads.add(rawRead);
        return rawReads;
    }

    private Sso createSso(JsonObject jsonObject) {
        Sso sso = new Sso();

        if (jsonObject == null) {
            return sso;
        }

        sso.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        sso.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        sso.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        sso.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        sso.setLocus(jsonObject.has(HmlFieldConstants.SSO_LOCUS) ? jsonObject.get(HmlFieldConstants.SSO_LOCUS).getAsString() : null);
        sso.setTestId(jsonObject.has(HmlFieldConstants.SSO_TESTID) ? jsonObject.get(HmlFieldConstants.SSO_TESTID).getAsString() : null);
        sso.setTestIdSource(jsonObject.has(HmlFieldConstants.SSO_TESTIDSOURCE) ? jsonObject.get(HmlFieldConstants.SSO_TESTIDSOURCE).getAsString() : null);
        sso.setProperties(createProperties(jsonObject.has(HmlFieldConstants.SSO_PROPERTY) ? jsonObject.get(HmlFieldConstants.SSO_PROPERTY).getAsJsonObject() : null));

        return sso;
    }

    private Ssp createSsp(JsonObject jsonObject) {
        Ssp ssp = new Ssp();

        if (jsonObject == null) {
            return ssp;
        }

        ssp.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        ssp.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        ssp.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        ssp.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        ssp.setLocus(jsonObject.has(HmlFieldConstants.SSP_LOCUS) ? jsonObject.get(HmlFieldConstants.SSP_LOCUS).getAsString() : null);
        ssp.setScores(jsonObject.has(HmlFieldConstants.SSP_SCORES) ? jsonObject.get(HmlFieldConstants.SSP_SCORES).getAsString() : null);
        ssp.setTestId(jsonObject.has(HmlFieldConstants.SSP_TESTID) ? jsonObject.get(HmlFieldConstants.SSP_TESTID).getAsString() : null);
        ssp.setTestIdSource(jsonObject.has(HmlFieldConstants.SSP_TESTIDSOURCE) ? jsonObject.get(HmlFieldConstants.SSP_TESTIDSOURCE).getAsString() : null);
        ssp.setProperties(createProperties(jsonObject.has(HmlFieldConstants.SSP_PROPERTY) ? jsonObject.get(HmlFieldConstants.SSP_PROPERTY).getAsJsonObject() : null));

        return ssp;
    }

    private SbtSanger createSbtSanger(JsonObject jsonObject) {
        SbtSanger sbtSanger = new SbtSanger();

        if (jsonObject == null) {
            return  sbtSanger;
        }

        sbtSanger.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        sbtSanger.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        sbtSanger.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        sbtSanger.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        sbtSanger.setLocus(jsonObject.has(HmlFieldConstants.SBTSNGR_LOCUS) ? jsonObject.get(HmlFieldConstants.SBTSNGR_LOCUS).getAsString() : null);
        sbtSanger.setTestId(jsonObject.has(HmlFieldConstants.SBTSNGR_TESTID) ? jsonObject.get(HmlFieldConstants.SBTSNGR_TESTID).getAsString() : null);
        sbtSanger.setTestIdSource(jsonObject.has(HmlFieldConstants.SBTSNGR_TESTIDSOURCE) ? jsonObject.get(HmlFieldConstants.SBTSNGR_TESTIDSOURCE).getAsString() : null);
        sbtSanger.setProperties(createProperties(jsonObject.has(HmlFieldConstants.SBTSNGR_PROPERTY) ? jsonObject.get(HmlFieldConstants.SBTSNGR_PROPERTY).getAsJsonObject() : null));
        sbtSanger.setAmplification(createAmplification(jsonObject.has(HmlFieldConstants.SBTSNGR_AMPLIFICATION) ? jsonObject.get(HmlFieldConstants.SBTSNGR_AMPLIFICATION).getAsJsonObject() : null));
        sbtSanger.setSubAmplification(createSubAmplification(jsonObject.has(HmlFieldConstants.SBTSNGR_SUBAMPLIFICATION) ? jsonObject.get(HmlFieldConstants.SBTSNGR_SUBAMPLIFICATION).getAsJsonObject() : null));
        sbtSanger.setGssp(createGssp(jsonObject.has(HmlFieldConstants.SBTSNGR_GSSP) ? jsonObject.get(HmlFieldConstants.SBTSNGR_GSSP).getAsJsonObject() : null));

        return sbtSanger;
    }

    private Amplification createAmplification(JsonObject jsonObject) {
        Amplification amplification = new Amplification();

        if (jsonObject == null) {
            return amplification;
        }

        amplification.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        amplification.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        amplification.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        amplification.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        amplification.setRegisteredName(jsonObject.has(HmlFieldConstants.AMP_REGISTEREDNAME) ? jsonObject.get(HmlFieldConstants.AMP_SEQUENCE).getAsString() : null);
        amplification.setSequence(createSequence(jsonObject.has(HmlFieldConstants.AMP_SEQUENCE) ? jsonObject.get(HmlFieldConstants.AMP_SEQUENCE).getAsJsonObject() : null));

        return amplification;
    }

    private SubAmplification createSubAmplification(JsonObject jsonObject) {
        SubAmplification subAmplification = new SubAmplification();

        if (jsonObject == null) {
            return subAmplification;
        }

        subAmplification.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        subAmplification.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        subAmplification.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        subAmplification.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        subAmplification.setRegisteredName(jsonObject.has(HmlFieldConstants.SUBAMP_REGISTEREDNAME) ? jsonObject.get(HmlFieldConstants.SUBAMP_REGISTEREDNAME).getAsString() : null);
        subAmplification.setSequence(createSequence(jsonObject.has(HmlFieldConstants.SUBAMP_SEQUENCE) ? jsonObject.get(HmlFieldConstants.SUBAMP_SEQUENCE).getAsJsonObject() : null));

        return subAmplification;
    }

    private Gssp createGssp(JsonObject jsonObject) {
        Gssp gssp = new Gssp();

        if (jsonObject == null) {
            return gssp;
        }

        gssp.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        gssp.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        gssp.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        gssp.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        gssp.setRegisteredName(jsonObject.has(HmlFieldConstants.GSSP_REGISTEREDNAME) ? jsonObject.get(HmlFieldConstants.GSSP_REGISTEREDNAME).getAsString() : null);
        gssp.setPrimerTarget(jsonObject.has(HmlFieldConstants.GSSP_PRIMERTARGET) ? jsonObject.get(HmlFieldConstants.GSSP_PRIMERTARGET).getAsString() : null);
        gssp.setPrimerSequence(jsonObject.has(HmlFieldConstants.GSSP_PRIMERSEQUENCE) ? jsonObject.get(HmlFieldConstants.GSSP_PRIMERSEQUENCE).getAsString() : null);
        gssp.setSequence(createSequence(jsonObject.has(HmlFieldConstants.GSSP_SEQUENCE) ? jsonObject.get(HmlFieldConstants.GSSP_SEQUENCE).getAsJsonObject() : null));

        return gssp;
    }

    private Sequence createSequence(JsonObject jsonObject) {
        Sequence sequence = new Sequence();

        if (jsonObject == null) {
            return sequence;
        }

        sequence.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        sequence.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        sequence.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        sequence.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        sequence.setAnyAttribute(jsonObject.has(HmlFieldConstants.SEQUENCE_ANYATTRIBUTE) ? jsonObject.get(HmlFieldConstants.SEQUENCE_ANYATTRIBUTE).getAsJsonObject() : null);
        sequence.setSequence(jsonObject.has(HmlFieldConstants.SEQUENCE_SEQUENCE) ? jsonObject.get(HmlFieldConstants.SEQUENCE_SEQUENCE).getAsString() : null);
        sequence.setIupacBases(createIupacBases(jsonObject.has(HmlFieldConstants.SEQUENCE_IUPACBASES) ? jsonObject.get(HmlFieldConstants.SEQUENCE_IUPACBASES).getAsJsonObject() : null));

        return sequence;
    }

    private List<IupacBases> createIupacBases(JsonObject jsonObject) {
        List<IupacBases> iupacBases = new ArrayList<>();
        IupacBases iupacBase = new IupacBases();

        if (jsonObject == null) {
            return iupacBases;
        }

        iupacBase.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        iupacBase.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        iupacBase.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        iupacBase.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        iupacBase.setProperty(jsonObject.has(HmlFieldConstants.IUPAC_PROPERTY) ? jsonObject.get(HmlFieldConstants.IUPAC_PROPERTY).getAsString() : null);
        iupacBase.setValue(jsonObject.has(HmlFieldConstants.IUPAC_VALUE) ? jsonObject.get(HmlFieldConstants.IUPAC_VALUE).getAsString() : null);

        iupacBases.add(iupacBase);
        return iupacBases;
    }

    private List<CollectionMethod> createCollectionMethods(JsonObject jsonObject) {
        List<CollectionMethod> collectionMethods = new ArrayList<>();
        CollectionMethod collectionMethod = new CollectionMethod();

        if (jsonObject == null) {
            return collectionMethods;
        }

        collectionMethod.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        collectionMethod.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        collectionMethod.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        collectionMethod.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        collectionMethod.setDescription(jsonObject.has(HmlFieldConstants.COLLECTIONMETHOD_DESCRIPTION) ? jsonObject.get(HmlFieldConstants.COLLECTIONMETHOD_DESCRIPTION).getAsString() : null);
        collectionMethod.setName(jsonObject.has(HmlFieldConstants.COLLECTIONMETHOD_NAME) ? jsonObject.get(HmlFieldConstants.COLLECTIONMETHOD_NAME).getAsString() : null);

        collectionMethods.add(collectionMethod);
        return collectionMethods;
    }
}