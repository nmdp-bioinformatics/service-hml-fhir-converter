package org.nmdp.hmlfhirconverter.config.constants.hml;

/**
 * Created by Andrew S. Brown, Ph.D., <abrown3@nmdp.org>, on 3/16/17.
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

public class HmlFieldConstants {
    //mongo
    public static final String MONGO_ID = "id";
    public static final String MONGO_DATEUPDATED = "date-updated";
    public static final String MONGO_DATECREATED = "date-created";
    public static final String MONGO_ACTIVE = "active";

    //properties
    public static final String PROPERTIES_DESCRIPTION = "description";
    public static final String PROPERTIES_NAME = "name";
    public static final String PROPERTIES_VALUE = "value";
    public static final String PROPERTIES_EXTENDEDITEMS = "extended-item";

    //properties: extended-item
    public static final String EXTENDEDITEM_ITEM = "item";

    //hml
    public static final String HML_HMLID = "hmlid";
    public static final String HML_VERSION = "version";
    public static final String HML_PROJECT = "project";
    public static final String HML_SAMPLE = "sample";
    public static final String HML_REPORTINGCENTER = "reporting-center";
    public static final String HML_PROPERTIES = "properties";
    public static final String HML_TYPINGTESTNAME = "typing-test-name";

    //hmlid
    public static final String HMLID_ROOT = "root";
    public static final String HMLID_EXTENSION = "extension";

    //project
    public static final String PROJECT_DESCRIPTION = "description";
    public static final String PROJECT_NAME = "name";

    //reportingCenter
    public static final String REPORTINGCENTER_CENTERID = "reporting-center-id";
    public static final String REPORTINGCENTER_CONTEXT = "reporting-center-context";

    //sample
    public static final String SAMPLE_ID = "sample-id";
    public static final String SAMPLE_CENTERCODE = "center-code";
    public static final String SAMPLE_COLLECTIONMETHOD = "collection-method";
    public static final String SAMPLE_TYPING = "typing";
    public static final String SAMPLE_PROPERTY = "property";

    //sample: collection-method
    public static final String COLLECTIONMETHOD_DESCRIPTION  = "description";
    public static final String COLLECTIONMETHOD_NAME = "name";

    //sample: typing
    public static final String TYPING_DATE = "date";
    public static final String TYPING_GENEFAMILY = "gene-family";
    public static final String TYPING_TYPINGMETHOD = "typing-method";
    public static final String TYPING_PROPERTY = "property";
    public static final String TYPING_ALLELEASSIGNMENT = "allele-assignment";
    public static final String TYPING_CONSENSUSSEQUENCE = "consensus-sequence";

    //sample: typing: allele-assignment: haploid
    public static final String HAPLOID_LOCUS = "locus";
    public static final String HAPLOID_METHOD = "method";
    public static final String HAPLOID_TYPE = "type";

    //sample: typing: allele-assignment: genotype-list
    public static final String GENOTYPELIST_DIPLOIDCOMBINATION = "diploid-combination";

    //sample: typing: allele-assignment: genotype-list: diploid-combination
    public static final String DIPLOIDCOMBINATION_LOCUSBLOCK = "locus-block";

    //sample: typing: allele-assignment: genotype-list: diploid-combination: locus-block
    public static final String LOCUSBLOCK_ALLELELIST = "allele-list";

    ////sample: typing: allele-assignment: genotype-list: diploid-combination: locus-block: allele-list
    public static final String ALLELELIST_NAME = "name";
    public static final String ALLELELIST_PRESENT = "present";

    //sample: typing: allele-assignment: gl-string
    public static final String GLSTRING_URI = "uri";
    public static final String GLSTRING_VALUE = "value";

    //sample: typing: typing-method
    public static final String TYPINGMETHOD_SBTSANGER = "sbt-sanger";
    public static final String TYPINGMETHOD_SBTNGS = "sbt-ngs";
    public static final String TYPINGMETHOD_SSO = "sso";
    public static final String TYPINGMETHOD_SSP = "ssp";

    //sample: typing: typing-method: sbt-sanger
    public static final String SBTSNGR_LOCUS = "locus";
    public static final String SBTSNGR_TESTID = "test-id";
    public static final String SBTSNGR_TESTIDSOURCE = "test-id-source";
    public static final String SBTSNGR_PROPERTY = "property";
    public static final String SBTSNGR_AMPLIFICATION = "amplification";
    public static final String SBTSNGR_SUBAMPLIFICATION = "sub-amplification";
    public static final String SBTSNGR_GSSP = "gssp";

    //sample: typing: typing-method: sbt-sanger: amplification
    public static final String AMP_REGISTEREDNAME = "registered-name";
    public static final String AMP_SEQUENCE = "sequence";

    //sample: typing: typing-method: sbt-sanger: sub-amplification
    public static final String SUBAMP_REGISTEREDNAME = "registered-name";
    public static final String SUBAMP_SEQUENCE = "sequence";

    //sample: typing: typing-method: sbt-sanger: gssp
    public static final String GSSP_REGISTEREDNAME = "registered-name";
    public static final String GSSP_SEQUENCE = "sequence";
    public static final String GSSP_PRIMERTARGET = "primer-target";
    public static final String GSSP_PRIMERSEQUENCE = "primer-sequence";

    //sample: typing: typing-method: sbt-ngs
    public static final String SBTNGS_LOCUS = "locus";
    public static final String SBTNGS_PROPERTY = "property";
    public static final String SBTNGS_RAWREADS = "raw-reads";
    public static final String SBTNGS_TESTID = "test-id";
    public static final String SBTNGS_TESTIDSOURCE = "test-id-source";

    //sample: typing: typing-method: sbt-ngs: raw reads
    public static final String RAWREADS_ADAPTERTRIMMED = "adapter-trimmed";
    public static final String RAWREADS_AVAILABILITY = "availability";
    public static final String RAWREADS_FORMAT = "format";
    public static final String RAWREADS_PAIRED = "paired";
    public static final String RAWREADS_POOLED = "pooled";
    public static final String RAWREADS_QUALITYTRIMMED = "quality-trimmed";
    public static final String RAWREADS_URI = "uri";

    //sample: typing: typing-method: sso
    public static final String SSO_LOCUS = "locus";
    public static final String SSO_TESTID = "test-id";
    public static final String SSO_TESTIDSOURCE = "test-id-source";
    public static final String SSO_PROPERTY = "property";

    //sample: typing: typing-method: ssp
    public static final String SSP_LOCUS = "locus";
    public static final String SSP_TESTID = "test-id";
    public static final String SSP_TESTIDSOURCE = "test-id-source";
    public static final String SSP_PROPERTY = "property";
    public static final String SSP_SCORES = "scores";

    //typing test names
    public static final String TYPINGTESTNAMES_TESTID = "test-id";
    public static final String TYPINGTESTNAMES_NAME = "name";
    public static final String TYPINGTESTNAMES_DESCRIPTION = "description";

    //sample: typing: allele_assignment
    public static final String ALLELEASSIGNMENT_DATE = "date";
    public static final String ALLELEASSIGNMENT_ALLELEDB = "allele-db";
    public static final String ALLELEASSIGNMENT_ALLELEVERSION = "allele-version";
    public static final String ALLELEASSIGNMENT_PROPERTY = "property";
    public static final String ALLELEASSIGNMENT_GENOTYPE = "genotype";
    public static final String ALLELEASSIGNMENT_GLSTRING = "gl-string";
    public static final String ALLELEASSIGNMENT_HAPLOID = "haploid";

    //sample: typing: consensus-sequence
    public static final String CONSENSUSSEQ_ID = "id";
    public static final String CONSENSUSSEQ_ACTIVE = "active";
    public static final String CONSENSUSSEQ_DATECREATED = "date-created";
    public static final String CONSENSUSSEQ_DATEUPDATED = "date-updated";
    public static final String CONSENSUSSEQ_DATE = "date";
    public static final String CONSENSUSSEQ_REFERENCEDB = "reference-datebase";
    public static final String CONSENSUSSEQ_CONSENSUSSEQUENCEBLOCK = "consensus-sequence-block";

    //sample: typing; consensus-sequence: reference-database
    public static final String REFDB_ID = "id";
    public static final String REFDB_ACTIVE = "active";
    public static final String REFDB_DATECREATED = "date-created";
    public static final String REFDB_DATEUPDATED = "date-updated";


    //sample: typing: consensus-sequence: consensus-sequence-block
    public static final String CSB_ID = "id";
    public static final String CSB_ACTIVE = "active";
    public static final String CSB_CONTINUITY = "continuity";
    public static final String CSB_DATECREATED = "date-created";
    public static final String CSB_DATEUPDATED = "date-updated";
    public static final String CSB_DESCRIPTION = "description";
    public static final String CSB_END = "end";
    public static final String CSB_EXPECTEDCOPYNUM = "expected-copy-number";
    public static final String CSB_PHASESET = "phase-set";
    public static final String CSB_PHASINGGROUP = "phasing-group";
    public static final String CSB_REFSEQID = "reference-sequence-id";
    public static final String CSB_SEQUENCE = "sequence";
    public static final String CSB_START = "start";
    public static final String CSB_STRAND = "strand";
    public static final String CSB_VARIANT = "variant";
    public static final String CSB_SEQQUALITY = "sequence-quality";

    //sample: typing: consensus-sequence: consensus-sequence-block: variant
    public static final String VARIANT_ID = "id";
    public static final String VARIANT_ACTIVE = "active";
    public static final String VARIANT_DATECREATED = "date-created";
    public static final String VARIANT_DATEUPDATED = "date-updated";
    public static final String VARIANT_ALTERNATEBASES = "alternate-bases";
    public static final String VARIANT_ANYATTRIBUTE = "any-attribute";
    public static final String VARIANT_END = "end";
    public static final String VARIANT_FILTER = "filter";
    public static final String VARIANT_NAME = "name";
    public static final String VARIANT_QUALITYSCORE = "quality-score";
    public static final String VARIANT_REFERENCEBASES = "reference-bases";
    public static final String VARIANT_START = "start";
    public static final String VARIANT_URI = "URI";
    public static final String VARIANT_VARIANTID = "variant-id";
    public static final String VARIANT_VARIANTEFFECT = "variant-effect";

    //sample: typing: consensus-sequence: consensus-sequence-block: variant: variant-effect
    public static final String VARIANTEFF_ID = "id";
    public static final String VARIANTEFF_ACTIVE = "active";
    public static final String VARIANTEFF_DATECREATED = "date-created";
    public static final String VARIANTEFF_DATEUPDATED = "date-updated";
    public static final String VARIANTEFF_ANYATTRIBUTE = "any-attribute";
    public static final String VARIANTEFF_HGVS = "hgvs";
    public static final String VARIANTEFF_TERM = "term";
    public static final String VARIANTEFF_URI = "uri";

    //sample: typing: consensus-sequence: consensus-sequence-block: sequence-quality
    public static final String SEQQUAL_ID = "id";
    public static final String SEQQUAL_ACTIVE = "active";
    public static final String SEQQUAL_DATECREATED = "date-created";
    public static final String SEQQUAL_DATEUPDATED = "date-updated";
    public static final String SEQQUAL_QUALITYSCORE = "quality-score";
    public static final String SEQQUAL_SEQUENCEEND = "sequence-end";
    public static final String SEQQUAL_SEQUENCESTART = "sequence-start";

    //sample: typing: consensus-sequence: consensus-sequence-block: sequence: iupac-bases
    public static final String IUPAC_PROPERTY = "property";
    public static final String IUPAC_VALUE = "value";



}