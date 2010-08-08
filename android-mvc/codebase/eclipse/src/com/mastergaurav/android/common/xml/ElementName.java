/**
 * ElementName.java $version 2010.02.20
 * 
 * Copyright 2010 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.mastergaurav.android.common.xml;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents name of the element to which a field in model may map to. <br/>
 * 
 * @author Accenture India
 * @see XMLDeserializer
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( {
	ElementType.FIELD
})
public @interface ElementName
{
	/**
	 * Name of the element being deserialized
	 */
	String value();
}
