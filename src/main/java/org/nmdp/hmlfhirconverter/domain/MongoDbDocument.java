package org.nmdp.hmlfhirconverter.domain;

/**
 * Created by Andrew S. Brown, Ph.D., <abrown3@nmdp.org>, on 1/19/17.
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

import org.modelmapper.ModelMapper;

import org.nmdp.hmlfhirconverter.util.ModelMapperFactory;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class MongoDbDocument<T, U> {

    private static final Logger LOG = Logger.getLogger(MongoDbDocument.class);

    public static <T, U> T convertFromSwagger(U u, Class<T> tClass) {
        final ModelMapper mapper = ModelMapperFactory.getPrivateModelMapper();
        List<Field> dateFields = Arrays.stream(u.getClass().getDeclaredFields())
                .filter(Objects::nonNull)
                .filter(f -> f.getType().equals(Date.class))
                .collect(Collectors.toList());

        for (Field field : dateFields) {
            Date handledDate = handleDateField(field, u);

            try {
                field.setAccessible(true);
                field.set(u, handledDate);
            } catch (Exception ex) {
                LOG.error(ex);
            }
        }

        return mapper.map(u, tClass);
    }

    public static <T, U> U toDto(T t, Class<U> tClass) {
        final ModelMapper mapper = ModelMapperFactory.getPrivateModelMapper();
        return mapper.map(t, tClass);
    }

    private static <U> Date handleDateField(Field field, U u) {
        field.setAccessible(true);
        final Pattern pattern = Pattern.compile(".*?[Cc]reated");

        try {
            final Matcher matcher = pattern.matcher(field.getName());

            if (!matcher.matches()) {
                return (Date)field.get(u);
            }

            if (field.get(u) == null) {
                return new Date();
            }

            return (Date)field.get(u);
        } catch (Exception ex) {
            LOG.error(ex);
        }

        return new Date();
    }
}
