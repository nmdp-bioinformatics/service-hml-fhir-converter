package org.nmdp.hmlfhirconverter.mapping.object;

/**
 * Created by Andrew S. Brown, Ph.D., <andrew@nmdp.org>, on 5/25/17.
 * <p>
 * service-hml-fhir-converter
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

import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;
import org.nmdp.hmlfhirconvertermodels.domain.*;

@Mapper
public interface DoDtoMapper {

    DoDtoMapper INSTANCE = Mappers.getMapper(DoDtoMapper.class);

    Allele map(org.nmdp.hmlfhirconvertermodels.dto.Allele value);
    AlleleAssignment map(org.nmdp.hmlfhirconvertermodels.dto.AlleleAssignment value);
    Amplification map(org.nmdp.hmlfhirconvertermodels.dto.Amplification value);
    CollectionMethod map(org.nmdp.hmlfhirconvertermodels.dto.CollectionMethod value);
    ConsensusSequence map(org.nmdp.hmlfhirconvertermodels.dto.ConsensusSequence value);
    ConsensusSequenceBlock map(org.nmdp.hmlfhirconvertermodels.dto.ConsensusSequenceBlock value);
    DiploidCombination map(org.nmdp.hmlfhirconvertermodels.dto.DiploidCombination value);
    ExtendedItem map(org.nmdp.hmlfhirconvertermodels.dto.ExtendedItem value);
    Genotype map(org.nmdp.hmlfhirconvertermodels.dto.Genotype value);
    GlString map(org.nmdp.hmlfhirconvertermodels.dto.GlString value);
    Gssp map(org.nmdp.hmlfhirconvertermodels.dto.Gssp value);
    Haploid map(org.nmdp.hmlfhirconvertermodels.dto.Haploid value);
    Hml map(org.nmdp.hmlfhirconvertermodels.dto.Hml value);
    HmlId map(org.nmdp.hmlfhirconvertermodels.dto.HmlId value);
    IupacBases map(org.nmdp.hmlfhirconvertermodels.dto.IupacBases value);
    LocusBlock map(org.nmdp.hmlfhirconvertermodels.dto.LocusBlock value);
    Project map(org.nmdp.hmlfhirconvertermodels.dto.Project value);
    Property map(org.nmdp.hmlfhirconvertermodels.dto.Property value);
    RawRead map(org.nmdp.hmlfhirconvertermodels.dto.RawRead value);
    ReferenceDatabase map(org.nmdp.hmlfhirconvertermodels.dto.ReferenceDatabase value);
    ReferenceSequence map(org.nmdp.hmlfhirconvertermodels.dto.ReferenceSequence value);
    ReportingCenter map(org.nmdp.hmlfhirconvertermodels.dto.ReportingCenter value);
    Sample map(org.nmdp.hmlfhirconvertermodels.dto.Sample value);
    SbtNgs map(org.nmdp.hmlfhirconvertermodels.dto.SbtNgs value);
    SbtSanger map(org.nmdp.hmlfhirconvertermodels.dto.SbtSanger value);
    Sequence map(org.nmdp.hmlfhirconvertermodels.dto.Sequence value);
    SequenceQuality map(org.nmdp.hmlfhirconvertermodels.dto.SequenceQuality value);
    Sso map(org.nmdp.hmlfhirconvertermodels.dto.Sso value);
    Ssp map(org.nmdp.hmlfhirconvertermodels.dto.Ssp value);
    SubAmplification map(org.nmdp.hmlfhirconvertermodels.dto.SubAmplification value);
    Typing map(org.nmdp.hmlfhirconvertermodels.dto.Typing value);
    TypingMethod map(org.nmdp.hmlfhirconvertermodels.dto.TypingMethod value);
    TypingTestName map(org.nmdp.hmlfhirconvertermodels.dto.TypingTestName value);
    Variant map(org.nmdp.hmlfhirconvertermodels.dto.Variant value);
    VariantEffect map(org.nmdp.hmlfhirconvertermodels.dto.VariantEffect value);
    Version map(org.nmdp.hmlfhirconvertermodels.dto.Version value);
}
