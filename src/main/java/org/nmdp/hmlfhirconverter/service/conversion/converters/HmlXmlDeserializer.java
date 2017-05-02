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
import io.swagger.model.ReferenceSequence;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.nmdp.hmlfhirconverter.config.constants.hml.HmlFieldConstants;
import org.nmdp.hmlfhirconverter.service.conversion.converters.create.*;
import org.nmdp.hmlfhirconverter.util.PrimitiveParser;

import java.lang.reflect.Type;

import java.util.*;


public class HmlXmlDeserializer implements JsonDeserializer<Hml> {

    //can remove formatter after completion of "create" package
    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public Hml deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject rootJson = json.getAsJsonObject();
        JsonObject jsonObject = rootJson.get("hml").getAsJsonObject();

        Hml hml = new Hml();

        hml.setId(jsonObject.has(HmlFieldConstants.MONGO_ID) ? jsonObject.get(HmlFieldConstants.MONGO_ID).getAsString() : null);
        hml.setActive(jsonObject.has(HmlFieldConstants.MONGO_ACTIVE) ? jsonObject.get(HmlFieldConstants.MONGO_ACTIVE).getAsBoolean() : null);
        hml.setDateCreated(jsonObject.has(HmlFieldConstants.MONGO_DATECREATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATECREATED).getAsString()).toDate() : null);
        hml.setDateUpdated(jsonObject.has(HmlFieldConstants.MONGO_DATEUPDATED) ? formatter.parseDateTime(jsonObject.get(HmlFieldConstants.MONGO_DATEUPDATED).getAsString()).toDate() : null);
        hml.setHmlId(CreateHmlId.createHmlId(jsonObject.has(HmlFieldConstants.HML_HMLID) ? jsonObject.get(HmlFieldConstants.HML_HMLID).getAsJsonObject() : null));
        hml.setVersion(CreateVersion.createVersion(jsonObject.has(HmlFieldConstants.HML_VERSION) ? jsonObject.get(HmlFieldConstants.HML_VERSION).getAsString() : null));
        hml.setProject(CreateProject.createProject(jsonObject.has(HmlFieldConstants.HML_PROJECT) ? jsonObject.get(HmlFieldConstants.HML_PROJECT).getAsJsonObject() : null));
        hml.setSamples(PrimitiveParser.isArray(jsonObject,HmlFieldConstants.HML_SAMPLE) ? CreateSample.createSamplesAr(jsonObject.has(HmlFieldConstants.HML_SAMPLE) ? jsonObject.get(HmlFieldConstants.HML_SAMPLE).getAsJsonArray() : null):
            CreateSample.createSamplesOb(jsonObject.has(HmlFieldConstants.HML_SAMPLE) ? jsonObject.get(HmlFieldConstants.HML_SAMPLE).getAsJsonObject() : null));
        hml.setReportingCenters(CreateReportingCenters.createReportingCenters(jsonObject.has(HmlFieldConstants.HML_REPORTINGCENTER) ? jsonObject.get(HmlFieldConstants.HML_REPORTINGCENTER).getAsJsonObject() : null));
        hml.setProperties(CreateProperties.createProperties(jsonObject.has(HmlFieldConstants.HML_PROPERTIES) ? jsonObject.get(HmlFieldConstants.HML_PROPERTIES).getAsJsonObject() : null));
        hml.setTypingTestNames(CreateTypingTestNames.createTypingTestNames(jsonObject.has(HmlFieldConstants.HML_TYPINGTESTNAME) ? jsonObject.get(HmlFieldConstants.HML_TYPINGTESTNAME).getAsJsonObject() : null));

        return hml;
    }

}