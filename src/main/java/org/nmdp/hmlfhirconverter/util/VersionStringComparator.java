package org.nmdp.hmlfhirconverter.util;

/**
 * Created by Andrew S. Brown, Ph.D., <abrown3@nmdp.org>, on 1/24/17.
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

import org.nmdp.hmlfhirconvertermodels.dto.Version;

import java.util.Comparator;

public class VersionStringComparator implements Comparator<Version> {

    @Override
    public int compare(Version v1, Version v2) {
        int version1 = Integer.parseInt(v1.getName().replace(".", ""));
        int version2 = Integer.parseInt(v2.getName().replace(".", ""));

        if (version1 > version2) {
            return 1;
        } else if (version1 < version2) {
            return -1;
        } else {
            return 0;
        }
    }
}
