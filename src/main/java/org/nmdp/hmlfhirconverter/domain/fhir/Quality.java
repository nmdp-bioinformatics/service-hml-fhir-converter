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

import java.math.BigDecimal;

public class Quality {
    private Type code;
    private Sequence standardSequence;
    private Integer start;
    private Integer end;
    private Score score;
    private Method method;
    private BigDecimal truthTP;
    private BigDecimal queryTP;
    private BigDecimal truthFN;
    private BigDecimal queryFP;
    private BigDecimal gtFP;
    private BigDecimal precision;
    private BigDecimal recall;
    private BigDecimal fScore;

    public Type getCode() {
        return code;
    }

    public void setCode(Type code) {
        this.code = code;
    }

    public Sequence getStandardSequence() {
        return standardSequence;
    }

    public void setStandardSequence(Sequence standardSequence) {
        this.standardSequence = standardSequence;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public BigDecimal getTruthTP() {
        return truthTP;
    }

    public void setTruthTP(BigDecimal truthTP) {
        this.truthTP = truthTP;
    }

    public BigDecimal getQueryTP() {
        return queryTP;
    }

    public void setQueryTP(BigDecimal queryTP) {
        this.queryTP = queryTP;
    }

    public BigDecimal getTruthFN() {
        return truthFN;
    }

    public void setTruthFN(BigDecimal truthFN) {
        this.truthFN = truthFN;
    }

    public BigDecimal getQueryFP() {
        return queryFP;
    }

    public void setQueryFP(BigDecimal queryFP) {
        this.queryFP = queryFP;
    }

    public BigDecimal getGtFP() {
        return gtFP;
    }

    public void setGtFP(BigDecimal gtFP) {
        this.gtFP = gtFP;
    }

    public BigDecimal getPrecision() {
        return precision;
    }

    public void setPrecision(BigDecimal precision) {
        this.precision = precision;
    }

    public BigDecimal getRecall() {
        return recall;
    }

    public void setRecall(BigDecimal recall) {
        this.recall = recall;
    }

    public BigDecimal getfScore() {
        return fScore;
    }

    public void setfScore(BigDecimal fScore) {
        this.fScore = fScore;
    }
}
