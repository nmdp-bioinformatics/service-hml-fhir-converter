package org.nmdp.hmlfhirconverter.domain;

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

import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

abstract class MongoDataRepositoryModel implements IMongoDataRepositoryModel {

    private static final Logger LOG = Logger.getLogger(MongoDataRepositoryModel.class);

    @Override
    public <T> Object getPropertyValueByName(T t, String propertyName) throws NoSuchFieldException {
        Field property = Arrays.stream(t.getClass().getDeclaredFields())
                .filter(Objects::nonNull)
                .filter(f -> f.getName().equals(propertyName))
                .findFirst()
                .get();

        if (property == null) {
            throw new NoSuchFieldException("Field " + propertyName + " does not exist on " + t.getClass());
        }

        try {
            property.setAccessible(true);
            return property.get(t);
        } catch (Exception ex) {
            LOG.error(ex);
            throw new ClassCastException();
        }
    }
}