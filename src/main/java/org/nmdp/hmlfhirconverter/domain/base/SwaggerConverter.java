package org.nmdp.hmlfhirconverter.domain.base;

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

import org.springframework.data.annotation.Transient;

import org.nmdp.hmlfhirconverter.util.MappingConverter;

public class SwaggerConverter<T extends ISwaggerConverter<T, U>, U> extends SwaggerStaticConverter implements ISwaggerConverter<T, U> {

    @Transient
    private final Class<T> tClass;

    @Transient
    private final Class<U> uClass;

    public SwaggerConverter(Class<T> tClass, Class<U> uClass) {
        this.tClass = tClass;
        this.uClass = uClass;
    }

    @Override
    public T convertFromSwagger(U swagger) {
        return super.convertFromSwagger(swagger, tClass);
    }

    @Override
    public U toDto(T nmdpInstance) {
        return super.toDto(nmdpInstance, uClass);
    }

    @Override
    public U toDto(T nmdpInstance, MappingConverter<T, U> mapper) {
        return super.toDto(nmdpInstance, uClass, mapper);
    }
}