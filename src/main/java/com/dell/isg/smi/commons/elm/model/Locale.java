/**
 * Copyright � 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2016.03.04 at 01:18:28 PM CST
//

package com.dell.isg.smi.commons.elm.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class Locale.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Locale", propOrder = { "clientLocale" })
public class Locale {

    protected String clientLocale;


    /**
     * Gets the value of the clientLocale property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getClientLocale() {
        return clientLocale;
    }


    /**
     * Sets the value of the clientLocale property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setClientLocale(String value) {
        this.clientLocale = value;
    }

}
