package com.auzmor.assignement.serialization;

import org.restexpress.response.ErrorResponseWrapper;
import org.restexpress.response.ResponseWrapper;
import org.restexpress.serialization.AbstractSerializationProvider;
import org.restexpress.serialization.SerializationProcessor;

public class SerializationProvider1
extends AbstractSerializationProvider
{
	// SECTION: CONSTANTS

	private static final SerializationProcessor JSON_SERIALIZER = new JsonSerializationProcessor();
	private static final SerializationProcessor XML_SERIALIZER = new XmlSerializationProcessor1();
	private static final ResponseWrapper RESPONSE_WRAPPER = new ErrorResponseWrapper();

	public SerializationProvider1()
    {
	    super();
	    add(JSON_SERIALIZER, RESPONSE_WRAPPER, true);
	    add(XML_SERIALIZER, RESPONSE_WRAPPER);
    }

	public static SerializationProcessor json()
	{
		return JSON_SERIALIZER;
	}

	public static SerializationProcessor xml()
	{
		return XML_SERIALIZER;
	}
}
