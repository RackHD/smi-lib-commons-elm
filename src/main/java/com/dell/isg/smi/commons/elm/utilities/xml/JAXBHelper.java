/**
 * Copyright � 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.commons.elm.utilities.xml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dell.isg.smi.commons.elm.exception.RuntimeCoreException;

/**
 * The Class JAXBHelper.
 */
public class JAXBHelper {
    private static final Logger log = LoggerFactory.getLogger(JAXBHelper.class);

    private JAXBHelper(){}

    /**
     * Marshal.
     *
     * @param <T> the generic type
     * @param instance the instance
     * @param clazz the clazz
     * @return the string
     */
    public static <T> String marshal(T instance, Class<T> clazz) {
        String document = new String();
        ByteArrayOutputStream baos = null;

        try {
            baos = new ByteArrayOutputStream();
            getMarshaller(clazz).marshal(instance, baos);
            document = baos.toString();
        } catch (Exception e) {
            log.error("Unexpected exception thrown: " + e.getMessage(), e);
            throw new RuntimeCoreException("Unable to marshal " + clazz.getName(), e);
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    log.error("Unexpected exception thrown while closing: " + e.getMessage(), e);
                }
            }
        }

        return document;
    }


    /**
     * Unmarshal.
     *
     * @param <T> the generic type
     * @param xml the xml
     * @param clazz the clazz
     * @return the t
     */
    public static <T> T unmarshal(String xml, Class<T> clazz) {
        JAXBElement<T> instance = null;
        StringReader reader = null;
        try {
            reader = new StringReader(xml);
            instance = getUnmarshaller(clazz).unmarshal(new StreamSource(reader), clazz);
            reader.close();
        } catch (JAXBException e) {
            log.error("Unexpected exception thrown: " + e.getMessage(), e);
            throw new RuntimeCoreException("Unable to unmarshal " + clazz.getName(), e);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return instance.getValue();
    }


    /**
     * Gets the marshaller.
     *
     * @param <T> the generic type
     * @param clazz the clazz
     * @return the marshaller
     */
    private static <T> Marshaller getMarshaller(Class<T> clazz) {
        try {
            return getJAXBContext(clazz).createMarshaller();
        } catch (JAXBException e) {
            log.error("Unexpected exception thrown: " + e.getMessage(), e);
            throw new RuntimeCoreException("Unable to create marshaller", e);
        }
    }


    /**
     * Gets the unmarshaller.
     *
     * @param <T> the generic type
     * @param clazz the clazz
     * @return the unmarshaller
     */
    private static <T> Unmarshaller getUnmarshaller(Class<T> clazz) {
        try {
            return getJAXBContext(clazz).createUnmarshaller();
        } catch (JAXBException e) {
            log.error("Unexpected exception thrown: " + e.getMessage(), e);
            throw new RuntimeCoreException("Unable to create unmarshaller", e);
        }
    }


    /**
     * Gets the JAXB context.
     *
     * @param <T> the generic type
     * @param clazz the clazz
     * @return the JAXB context
     */
    private static <T> JAXBContext getJAXBContext(Class<T> clazz) {
        try {
            return JAXBContext.newInstance(clazz);
        } catch (JAXBException e) {
            log.error("Unexpected exception thrown: " + e.getMessage(), e);
            throw new RuntimeCoreException("Unable to create JAXB context", e);
        }
    }
}
