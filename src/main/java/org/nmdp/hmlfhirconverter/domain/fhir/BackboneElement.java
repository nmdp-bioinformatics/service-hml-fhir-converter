package org.nmdp.hmlfhirconverter.domain.fhir;

/**
 * Created by Andrew S. Brown, Ph.D., <abrown3@nmdp.org>, on 4/25/17.
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

public class BackboneElement {
    private Chromosome chromosome;
    private String genomeBuild;
    private ReferenceSequenceId referenceSequenceId;
    private Sequence referenceSeqPointer;
    private String referenceSeqString;
    private Integer strand;
    private Integer windowStart;
    private Integer windowEnd;

    public Chromosome getChromosome() {
        return chromosome;
    }

    public void setChromosome(Chromosome chromosome) {
        this.chromosome = chromosome;
    }

    public String getGenomeBuild() {
        return genomeBuild;
    }

    public void setGenomeBuild(String genomeBuild) {
        this.genomeBuild = genomeBuild;
    }

    public ReferenceSequenceId getReferenceSequenceId() {
        return referenceSequenceId;
    }

    public void setReferenceSequenceId(ReferenceSequenceId referenceSequenceId) {
        this.referenceSequenceId = referenceSequenceId;
    }

    public Sequence getReferenceSeqPointer() {
        return referenceSeqPointer;
    }

    public void setReferenceSeqPointer(Sequence referenceSeqPointer) {
        this.referenceSeqPointer = referenceSeqPointer;
    }

    public String getReferenceSeqString() {
        return referenceSeqString;
    }

    public void setReferenceSeqString(String referenceSeqString) {
        this.referenceSeqString = referenceSeqString;
    }

    public Integer getStrand() {
        return strand;
    }

    public void setStrand(Integer strand) {
        this.strand = strand;
    }

    public Integer getWindowStart() {
        return windowStart;
    }

    public void setWindowStart(Integer windowStart) {
        this.windowStart = windowStart;
    }

    public Integer getWindowEnd() {
        return windowEnd;
    }

    public void setWindowEnd(Integer windowEnd) {
        this.windowEnd = windowEnd;
    }
}
