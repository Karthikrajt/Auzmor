package com.auzmor.assignement.serialization;

import org.restexpress.serialization.xml.XstreamXmlProcessor;

import com.auzmor.assignement.controller.DelayResponse;


public class XmlSerializationProcessor1
extends XstreamXmlProcessor
{
	public XmlSerializationProcessor1()
    {
	    super();
		alias("delay_response", DelayResponse.class);
    }
}
