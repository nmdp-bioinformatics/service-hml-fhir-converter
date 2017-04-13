package org.nmdp.hmlfhirconverter.domain.fhir;

import org.joda.time.DateTime;

/**
 * Created by Andrew S. Brown, Ph.D., <abrown3@nmdp.org>, on 3/28/17.
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

public class Observation {
    private String identifier;
    private Reference basedOn;
    private Status status;
    private Category category;
    private Code code;
    private String subject;
    private String context;
    private DateTime effective;
    private DateTime issued;
    private Practitioner performer;
    private Object value;
    private Object dataAbsentReason;
    private Object interpretation;
    private String comment;
    private Object bodySite;
    private Object method;
    private Specimen specimen;
    private Device device;
}
