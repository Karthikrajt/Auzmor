package com.auzmor.assignement.controller;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.restexpress.Request;
import org.restexpress.Response;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import com.auzmor.assignement.controller.Messages;


public class SuccessController
extends AbstractDelayingController
{
	public Object create(Request request, Response response)
	{
		long delayms = delay(request);
		response.setResponseCreated();
		String message = request.getHeader("JSON");
		System.out.print("message   1" );
		return new DelayResponse("create", delayms, message);
	}

	public Object read(Request request, Response response)
	{
		long delayms = delay(request);
		String message = request.getHeader("JSON");
		
		try
		{
		
			try
			{
		Person.Builder personBuilder = Person.newBuilder();
		com.googlecode.protobuf.format.JsonFormat.merge(message, personBuilder);

		Person person2 = personBuilder.build();
			}
			catch(Exception e)
			{
				message = "Json is not matching with protobuff ";
			}
		
 
        Properties props = new Properties();
        props.put("metadata.broker.list", "broker1:9092,broker2:9092 ");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("partitioner.class", "com.auzmor.assignement.controller.SimplePartitioner");
        props.put("request.required.acks", "1");
 
        ProducerConfig config = new ProducerConfig(props);
 
        Producer<String, String> producer = new Producer<String, String>(config);
        KeyedMessage<String, String> data = new KeyedMessage<String, String>("Data", person2);
         producer.send(data);
        
        producer.close();
		}
		catch(Exception e)
		{
			message = "Something goes wrong please try again.";
		}

		return new DelayResponse("read", delayms, message);
	}

	
	
	
	public Object update(Request request, Response response)
	{
		long delayms = delay(request);
		String message = request.getHeader("JSON");
	
		System.out.print("message   3" );
		return new DelayResponse("update", delayms, message);
	}

	public Object delete(Request request, Response response)
	{
		long delayms = delay(request);
		String message = request.getHeader("JSON");
		System.out.print("message   4" );
		
		return new DelayResponse("delete", delayms, message);
	}
}
