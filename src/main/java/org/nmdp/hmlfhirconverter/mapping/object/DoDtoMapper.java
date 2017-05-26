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
import org.nmdp.hmlfhirconverter.domain.*;

@Mapper
public interface DoDtoMapper {

    DoDtoMapper INSTANCE = Mappers.getMapper(DoDtoMapper.class);

    Allele map(io.swagger.model.Allele value);
    AlleleAssignment map(io.swagger.model.AlleleAssignment value);
    Amplification map(io.swagger.model.Amplification value);
    CollectionMethod map(io.swagger.model.CollectionMethod value);
    ConsensusSequence map(io.swagger.model.ConsensusSequence value);
    ConsensusSequenceBlock map(io.swagger.model.ConsensusSequenceBlock value);
    DiploidCombination map(io.swagger.model.DiploidCombination value);
    ExtendedItem map(io.swagger.model.ExtendedItem value);
    Genotype map(io.swagger.model.Genotype value);
    GlString map(io.swagger.model.GlString value);
    Gssp map(io.swagger.model.Gssp value);
    Haploid map(io.swagger.model.Haploid value);
    Hml map(io.swagger.model.Hml value);
    HmlId map(io.swagger.model.HmlId value);
    IupacBases map(io.swagger.model.IupacBases value);
    LocusBlock map(io.swagger.model.LocusBlock value);
    Project map(io.swagger.model.Project value);
    Property map(io.swagger.model.Property value);
    RawRead map(io.swagger.model.RawRead value);
    ReferenceDatabase map(io.swagger.model.ReferenceDatabase value);
    ReferenceSequence map(io.swagger.model.ReferenceSequence value);
    ReportingCenter map(io.swagger.model.ReportingCenter value);
    Sample map(io.swagger.model.Sample value);
    SbtNgs map(io.swagger.model.SbtNgs value);
    SbtSanger map(io.swagger.model.SbtSanger value);
    Sequence map(io.swagger.model.Sequence value);
    SequenceQuality map(io.swagger.model.SequenceQuality value);
    Sso map(io.swagger.model.Sso value);
    Ssp map(io.swagger.model.Ssp value);
    SubAmplification map(io.swagger.model.SubAmplification value);
    Typing map(io.swagger.model.Typing value);
    TypingMethod map(io.swagger.model.TypingMethod value);
    TypingTestName map(io.swagger.model.TypingTestName value);
    Variant map(io.swagger.model.Variant value);
    VariantEffect map(io.swagger.model.VariantEffect value);
    Version map(io.swagger.model.Version value);
}
