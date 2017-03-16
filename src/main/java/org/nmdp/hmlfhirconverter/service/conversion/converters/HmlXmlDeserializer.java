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

import com.google.gson.*;

import io.swagger.model.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HmlXmlDeserializer implements JsonDeserializer<Hml> {

    public Hml deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject rootJson = json.getAsJsonObject();
        JsonObject jsonObject = rootJson.get("hml").getAsJsonObject();

        Hml hml = new Hml();
        Version version = new Version();

        hml.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        hml.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        hml.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        hml.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        hml.setHmlId(createHmlId(jsonObject.has("hmlid") ? jsonObject.get("hmlid").getAsJsonObject() : null));
        hml.setVersion(createVersion(jsonObject.has("version") ? jsonObject.get("version").getAsString() : null));
        hml.setProject(createProject(jsonObject.has("project") ? jsonObject.get("project").getAsJsonObject() : null));
        hml.setSamples(createSamples(jsonObject.has("sample") ? jsonObject.get("sample").getAsJsonObject() : null));
        hml.setReportingCenters(createRepotingCenters(jsonObject.has("reporting-center") ? jsonObject.get("reporting-center").getAsJsonObject() : null));
        hml.setProperties(createProperties(jsonObject.has("properties") ? jsonObject.get("properties").getAsJsonObject() : null));
        hml.setTypingTestNames(createTypingTestNames(jsonObject.has("typing-test-name") ? jsonObject.get("typing-test-name").getAsJsonObject() : null));

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

        hmlId.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        hmlId.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        hmlId.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        hmlId.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        hmlId.setExtension(jsonObject.has("extension") ? jsonObject.get("extension").getAsString() : null);
        hmlId.setRootName(jsonObject.has("root") ? jsonObject.get("root").getAsString() : null);

        return hmlId;
    }

    private Project createProject(JsonObject jsonObject) {
        Project project = new Project();

        if (jsonObject == null) {
            return project;
        }

        project.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        project.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        project.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        project.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        project.setDescription(jsonObject.has("description") ? jsonObject.get("description").getAsString() : null);
        project.setName(jsonObject.has("name") ? jsonObject.get("name").getAsString() : null);

        return project;
    }

    private List<Sample> createSamples(JsonObject jsonObject) {
        List<Sample> samples = new ArrayList<>();
        Sample sample = new Sample();

        if (jsonObject == null) {
            return samples;
        }

        sample.setId(jsonObject.has("_id") ? jsonObject.get("_id").getAsString() : null);
        sample.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        sample.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        sample.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        sample.setCenterCode(jsonObject.has("center-code") ? jsonObject.get("center-code").getAsString() : null);
        sample.setSampleId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        sample.setTyping(createTyping(jsonObject.has("typing") ? jsonObject.get("typing").getAsJsonObject() : null));
        sample.setProperties(createProperties(jsonObject.has("property") ? jsonObject.get("property").getAsJsonObject() : null));
        sample.setCollectionMethods(createCollectionMethods(jsonObject.has("collection-method") ? jsonObject.get("collection-method").getAsJsonObject() : null));

        samples.add(sample);
        return samples;
    }

    private List<Property> createProperties(JsonObject jsonObject) {
        List<Property> properties = new ArrayList<>();
        Property property = new Property();

        if (jsonObject == null) {
            return properties;
        }

        property.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        property.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        property.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        property.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        property.setDescription(jsonObject.has("description") ? jsonObject.get("description").getAsString() : null);
        property.setName(jsonObject.has("name") ? jsonObject.get("name").getAsString() : null);
        property.setValue(jsonObject.has("value") ? jsonObject.get("value").getAsString() : null);
        property.setExtendedItems(createExtendedItems(jsonObject.has("extended-items") ? jsonObject.get("extended-items").getAsJsonObject() : null));

        properties.add(property);
        return properties;
    }

    private List<ReportingCenter> createRepotingCenters(JsonObject jsonObject) {
        List<ReportingCenter> reportingCenters = new ArrayList<>();
        ReportingCenter reportingCenter = new ReportingCenter();

        if (jsonObject == null) {
            return reportingCenters;
        }

        reportingCenter.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        reportingCenter.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        reportingCenter.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        reportingCenter.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        reportingCenter.setContext(jsonObject.has("context") ? jsonObject.get("context").getAsString() : null);
        reportingCenter.setCenterId(jsonObject.has("center-id") ? jsonObject.get("center-id").getAsString() : null);

        reportingCenters.add(reportingCenter);
        return reportingCenters;
    }

    private List<TypingTestName> createTypingTestNames(JsonObject jsonObject) {
        List<TypingTestName> typingTestNames = new ArrayList<>();
        TypingTestName typingTestName = new TypingTestName();

        if (jsonObject == null) {
            return typingTestNames;
        }

        typingTestName.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        typingTestName.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        typingTestName.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        typingTestName.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        typingTestName.setDescription(jsonObject.has("description") ? jsonObject.get("description").getAsString() : null);
        typingTestName.setName(jsonObject.has("name") ? jsonObject.get("name").getAsString() : null);

        typingTestNames.add(typingTestName);
        return typingTestNames;
    }

    private List<ExtendedItem> createExtendedItems(JsonObject jsonObject) {
        List<ExtendedItem> extendedItems = new ArrayList<>();
        ExtendedItem extendedItem = new ExtendedItem();

        if (jsonObject == null) {
            return extendedItems;
        }

        extendedItem.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        extendedItem.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        extendedItem.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        extendedItem.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        extendedItem.setItem(jsonObject.has("item") ? jsonObject.get("item").getAsJsonObject() : null);

        extendedItems.add(extendedItem);
        return extendedItems;
    }

    private Typing createTyping(JsonObject jsonObject) {
        Typing typing = new Typing();

        if (jsonObject == null) {
            return typing;
        }

        typing.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        typing.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        typing.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        typing.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        typing.setDate(jsonObject.has("date") ? new Date(jsonObject.get("date").getAsString()) : null);
        typing.setGeneFamily(jsonObject.has("gene-family") ? jsonObject.get("gene-family").getAsString() : null);
        typing.setTypingMethod(createTypingMethod(jsonObject.has("typing-method") ? jsonObject.get("typing-method").getAsJsonObject() : null));
        typing.setProperties(createProperties(jsonObject.has("property") ? jsonObject.get("property").getAsJsonObject() : null));
        typing.setAlleleAssignment(createAlleleAssignment(jsonObject.has("allele-assignment") ? jsonObject.get("allele-assignment").getAsJsonObject() : null));
        typing.setConsensusSequence(createConsensusSequence(jsonObject.has("consensus-sequence") ? jsonObject.get("consensus-sequence").getAsJsonObject() : null));

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

        alleleAssignment.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        alleleAssignment.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        alleleAssignment.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        alleleAssignment.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        alleleAssignment.setDate(jsonObject.has("date") ? new Date(jsonObject.get("date").getAsString()) : null);
        alleleAssignment.setAlleleDb(jsonObject.has("allele-db") ? jsonObject.get("allele-db").getAsString() : null);
        alleleAssignment.setAlleleVersion(jsonObject.has("allele-version") ? jsonObject.get("allele-version").getAsString() : null);
        alleleAssignment.setProperties(createProperties(jsonObject.has("property") ? jsonObject.get("property").getAsJsonObject() : null));
        alleleAssignment.setGenotypes(createGenotypes(jsonObject.has("genotype") ? jsonObject.get("genotype").getAsJsonObject() : null));
        alleleAssignment.setGlString(createGlString(jsonObject.has("gl-string") ? jsonObject.get("gl-string").getAsJsonObject() : null));
        alleleAssignment.setHaploid(createHaploid(jsonObject.has("haploid") ? jsonObject.get("haploid").getAsJsonObject() : null));

        return alleleAssignment;
    }

    private GlString createGlString(JsonObject jsonObject) {
        GlString glstring = new GlString();

        if (jsonObject == null) {
            return glstring;
        }

        glstring.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        glstring.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        glstring.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        glstring.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        glstring.setUri(jsonObject.has("uri") ? jsonObject.get("uri").getAsString() : null);
        glstring.setValue(jsonObject.has("value") ? jsonObject.get("value").getAsString() : null);

        return glstring;
    }

    private Haploid createHaploid(JsonObject jsonObject) {
        Haploid haploid = new Haploid();

        if (jsonObject == null) {
            return haploid;
        }

        haploid.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        haploid.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        haploid.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        haploid.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        haploid.setLocus(jsonObject.has("locus") ? jsonObject.get("locus").getAsString() : null);
        haploid.setMethod(jsonObject.has("method") ? jsonObject.get("method").getAsString() : null);
        haploid.setType(jsonObject.has("type") ? jsonObject.get("type").getAsString() : null);

        return haploid;
    }

    private List<Genotype> createGenotypes(JsonObject jsonObject) {
        List<Genotype> genotypes = new ArrayList<>();
        Genotype genotype = new Genotype();

        if (jsonObject == null) {
            return  genotypes;
        }

        genotype.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        genotype.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        genotype.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        genotype.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        genotype.setDiploidCombinations(createDiploidCombinations(jsonObject.has("diploid-combination") ? jsonObject.get("diploid-combination").getAsJsonObject() : null));

        genotypes.add(genotype);
        return genotypes;
    }

    private List<DiploidCombination> createDiploidCombinations(JsonObject jsonObject) {
        List<DiploidCombination> diploidCombinations = new ArrayList<>();
        DiploidCombination diploidCombination = new DiploidCombination();

        if (jsonObject == null) {
            return diploidCombinations;
        }

        diploidCombination.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        diploidCombination.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        diploidCombination.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        diploidCombination.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        diploidCombination.setLocusBlock(createLocusBlock(jsonObject.has("locus-block") ? jsonObject.get("locus-block").getAsJsonObject() : null));

        diploidCombinations.add(diploidCombination);
        return diploidCombinations;
    }

    private LocusBlock createLocusBlock(JsonObject jsonObject) {
        LocusBlock locusBlock = new LocusBlock();

        if (jsonObject == null) {
            return locusBlock;
        }

        locusBlock.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        locusBlock.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        locusBlock.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        locusBlock.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        locusBlock.setAlleles(createAlleles(jsonObject.has("allele-list") ? jsonObject.get("allele-list").getAsJsonObject() : null));

        return locusBlock;
    }

    private List<Allele> createAlleles(JsonObject jsonObject) {
        List<Allele> alleles = new ArrayList<>();
        Allele allele = new Allele();

        if (jsonObject == null) {
            return alleles;
        }

        allele.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        allele.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        allele.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        allele.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        allele.setName(jsonObject.has("name") ? jsonObject.get("name").getAsString() : null);
        allele.setPresent(jsonObject.has("present") ? jsonObject.get("present").getAsString() : null);

        alleles.add(allele);
        return alleles;
    }

    private TypingMethod createTypingMethod(JsonObject jsonObject) {
        TypingMethod typingMethod = new TypingMethod();

        if (jsonObject == null) {
            return typingMethod;
        }

        typingMethod.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        typingMethod.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        typingMethod.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        typingMethod.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        typingMethod.setSbtSanger(createSbtSanger(jsonObject.has("sbt-sanger") ? jsonObject.get("sbt-sanger").getAsJsonObject() : null));
        typingMethod.setSbtNgs(createSbtNgs(jsonObject.has("sbt-ngs") ? jsonObject.get("sbt-ngs").getAsJsonObject() : null));
        typingMethod.setSso(createSso(jsonObject.has("sso") ? jsonObject.get("sso").getAsJsonObject() : null));
        typingMethod.setSsp(createSsp(jsonObject.has("ssp") ? jsonObject.get("ssp").getAsJsonObject() : null));

        return typingMethod;
    }

    private SbtNgs createSbtNgs(JsonObject jsonObject) {
        SbtNgs sbtNgs = new SbtNgs();

        if (jsonObject == null) {
            return sbtNgs;
        }

        sbtNgs.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        sbtNgs.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        sbtNgs.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        sbtNgs.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        sbtNgs.setLocus(jsonObject.has("locus") ? jsonObject.get("locus").getAsString() : null);
        sbtNgs.setProperties(createProperties(jsonObject.has("property") ? jsonObject.get("property").getAsJsonObject() : null));
        sbtNgs.setRawReads(createRawReads(jsonObject.has("raw-reads") ? jsonObject.get("raw-reads").getAsJsonObject() : null));
        sbtNgs.setTestId(jsonObject.has("test-id") ? jsonObject.get("test-id").getAsString() : null);
        sbtNgs.setTestIdSource(jsonObject.has("test-id-source") ? jsonObject.get("test-id-source").getAsString() : null);

        return sbtNgs;
    }

    private List<RawRead> createRawReads(JsonObject jsonObject) {
        List<RawRead> rawReads = new ArrayList<>();
        RawRead rawRead = new RawRead();

        if (jsonObject == null) {
            return rawReads;
        }

        rawRead.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        rawRead.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        rawRead.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        rawRead.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        rawRead.setAdapterTrimmed(jsonObject.has("adapter-trimmed") ? jsonObject.get("adapter-trimmed").getAsBoolean() : null);
        rawRead.setAvailability(jsonObject.has("availability") ? jsonObject.get("availability").getAsString() : null);
        rawRead.setFormat(jsonObject.has("format") ? jsonObject.get("format").getAsString() : null);
        rawRead.setPaired(jsonObject.has("paired") ? jsonObject.get("paired").getAsBoolean() : null);
        rawRead.setPooled(jsonObject.has("pooled") ? jsonObject.get("pooled").getAsBoolean() : null);
        rawRead.setQualityTrimmed(jsonObject.has("quality-trimmed") ? jsonObject.get("quality-trimmed").getAsBoolean() : null);
        rawRead.setUri(jsonObject.has("uri") ? jsonObject.get("uri").getAsString() : null);

        rawReads.add(rawRead);
        return rawReads;
    }

    private Sso createSso(JsonObject jsonObject) {
        Sso sso = new Sso();

        if (jsonObject == null) {
            return sso;
        }

        sso.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        sso.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        sso.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        sso.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        sso.setLocus(jsonObject.has("locus") ? jsonObject.get("locus").getAsString() : null);
        sso.setTestId(jsonObject.has("test-id") ? jsonObject.get("test-id").getAsString() : null);
        sso.setTestIdSource(jsonObject.has("test-id-source") ? jsonObject.get("test-id-source").getAsString() : null);
        sso.setProperties(createProperties(jsonObject.has("property") ? jsonObject.get("property").getAsJsonObject() : null));

        return sso;
    }

    private Ssp createSsp(JsonObject jsonObject) {
        Ssp ssp = new Ssp();

        if (jsonObject == null) {
            return ssp;
        }

        ssp.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        ssp.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        ssp.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        ssp.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        ssp.setLocus(jsonObject.has("locus") ? jsonObject.get("locus").getAsString() : null);
        ssp.setScores(jsonObject.has("scores") ? jsonObject.get("scores").getAsString() : null);
        ssp.setTestId(jsonObject.has("test-id") ? jsonObject.get("test-id").getAsString() : null);
        ssp.setTestIdSource(jsonObject.has("test-id-source") ? jsonObject.get("test-id-source").getAsString() : null);
        ssp.setProperties(createProperties(jsonObject.has("property") ? jsonObject.get("property").getAsJsonObject() : null));

        return ssp;
    }

    private SbtSanger createSbtSanger(JsonObject jsonObject) {
        SbtSanger sbtSanger = new SbtSanger();

        if (jsonObject == null) {
            return  sbtSanger;
        }

        sbtSanger.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        sbtSanger.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        sbtSanger.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        sbtSanger.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        sbtSanger.setLocus(jsonObject.has("locus") ? jsonObject.get("locus").getAsString() : null);
        sbtSanger.setTestId(jsonObject.has("test-id") ? jsonObject.get("test-id").getAsString() : null);
        sbtSanger.setTestIdSource(jsonObject.has("test-id-source") ? jsonObject.get("test-id-source").getAsString() : null);
        sbtSanger.setProperties(createProperties(jsonObject.has("property") ? jsonObject.get("property").getAsJsonObject() : null));
        sbtSanger.setAmplification(createAmplification(jsonObject.has("amplification") ? jsonObject.get("amplification").getAsJsonObject() : null));
        sbtSanger.setSubAmplification(createSubAmplification(jsonObject.has("sub-amplification") ? jsonObject.get("sub-amplification").getAsJsonObject() : null));
        sbtSanger.setGssp(createGssp(jsonObject.has("gssp") ? jsonObject.get("gssp").getAsJsonObject() : null));

        return sbtSanger;
    }

    private Amplification createAmplification(JsonObject jsonObject) {
        Amplification amplification = new Amplification();

        if (jsonObject == null) {
            return amplification;
        }

        amplification.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        amplification.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        amplification.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        amplification.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        amplification.setRegisteredName(jsonObject.has("registered-name") ? jsonObject.get("registered-name").getAsString() : null);
        amplification.setSequence(createSequence(jsonObject.has("sequence") ? jsonObject.get("sequence").getAsJsonObject() : null));

        return amplification;
    }

    private SubAmplification createSubAmplification(JsonObject jsonObject) {
        SubAmplification subAmplification = new SubAmplification();

        if (jsonObject == null) {
            return subAmplification;
        }

        subAmplification.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        subAmplification.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        subAmplification.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        subAmplification.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        subAmplification.setRegisteredName(jsonObject.has("registered-name") ? jsonObject.get("registered-name").getAsString() : null);
        subAmplification.setSequence(createSequence(jsonObject.has("sequence") ? jsonObject.get("sequence").getAsJsonObject() : null));

        return subAmplification;
    }

    private Gssp createGssp(JsonObject jsonObject) {
        Gssp gssp = new Gssp();

        if (jsonObject == null) {
            return gssp;
        }

        gssp.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        gssp.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        gssp.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        gssp.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        gssp.setRegisteredName(jsonObject.has("registered-name") ? jsonObject.get("registered-name").getAsString() : null);
        gssp.setPrimerTarget(jsonObject.has("primer-target") ? jsonObject.get("primer-target").getAsString() : null);
        gssp.setPrimerSequence(jsonObject.has("primer-sequence") ? jsonObject.get("primer-sequence").getAsString() : null);
        gssp.setSequence(createSequence(jsonObject.has("sequence") ? jsonObject.get("sequence").getAsJsonObject() : null));

        return gssp;
    }

    private Sequence createSequence(JsonObject jsonObject) {
        Sequence sequence = new Sequence();

        if (jsonObject == null) {
            return sequence;
        }

        sequence.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        sequence.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        sequence.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        sequence.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        sequence.setAnyAttribute(jsonObject.has("any-attribute") ? jsonObject.get("any-attribute").getAsJsonObject() : null);
        sequence.setSequence(jsonObject.has("sequence") ? jsonObject.get("sequence").getAsString() : null);
        sequence.setIupacBases(createIupacBases(jsonObject.has("iupac-bases") ? jsonObject.get("iupac-bases").getAsJsonObject() : null));

        return sequence;
    }

    private List<IupacBases> createIupacBases(JsonObject jsonObject) {
        List<IupacBases> iupacBases = new ArrayList<>();
        IupacBases iupacBase = new IupacBases();

        if (jsonObject == null) {
            return iupacBases;
        }

        iupacBase.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        iupacBase.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        iupacBase.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        iupacBase.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        iupacBase.setProperty(jsonObject.has("property") ? jsonObject.get("property").getAsString() : null);
        iupacBase.setValue(jsonObject.has("value") ? jsonObject.get("value").getAsString() : null);

        iupacBases.add(iupacBase);
        return iupacBases;
    }

    private List<CollectionMethod> createCollectionMethods(JsonObject jsonObject) {
        List<CollectionMethod> collectionMethods = new ArrayList<>();
        CollectionMethod collectionMethod = new CollectionMethod();

        if (jsonObject == null) {
            return collectionMethods;
        }

        collectionMethod.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        collectionMethod.setActive(jsonObject.has("active") ? jsonObject.get("active").getAsBoolean() : null);
        collectionMethod.setDateCreated(jsonObject.has("date-created") ? new Date(jsonObject.get("date-created").getAsString()) : null);
        collectionMethod.setDateUpdated(jsonObject.has("date-updated") ? new Date(jsonObject.get("date-updated").getAsString()) : null);
        collectionMethod.setDescription(jsonObject.has("description") ? jsonObject.get("description").getAsString() : null);
        collectionMethod.setName(jsonObject.has("name") ? jsonObject.get("name").getAsString() : null);

        collectionMethods.add(collectionMethod);
        return collectionMethods;
    }
}