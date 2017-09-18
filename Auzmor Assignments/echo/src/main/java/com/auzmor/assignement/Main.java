package com.auzmor.assignement;

import org.restexpress.RestExpress;

import org.restexpress.util.Environment;

import com.auzmor.assignement.serialization.SerializationProvider1;


public class Main
{
	public static void main(String[] args) throws Exception
	{
		RestExpress.setSerializationProvider(new SerializationProvider1());
		Configuration config = Environment.load(args, Configuration.class);
		RestExpress server = new RestExpress()
		    .setName(config.getName())
		    .setPort(config.getPort());

		defineRoutes(server, config);

		if (config.getWorkerCount() > 0)
		{
			server.setIoThreadCount(config.getWorkerCount());
		}

		if (config.getExecutorThreadCount() > 0)
	    {
	    	server.setExecutorThreadCount(config.getExecutorThreadCount());
	    }

		mapExceptions(server);
		server.bind();
		server.awaitShutdown();
	}

	/**
     * @param server
     * @param config
     */
    private static void defineRoutes(RestExpress server, Configuration config)
    {
		
		// Waits the delay_ms number of milliseconds and responds with a 200.
		// Supports GET, PUT, POST, DELETE methods.
		server.uri("/success/{delay_ms}.{format}", config.getSuccessController());


    }

	/**
     * @param server
     */
    private static void mapExceptions(RestExpress server)
    {
//    	server
//    	.mapException(ItemNotFoundException.class, NotFoundException.class)
//    	.mapException(DuplicateItemException.class, ConflictException.class)
//    	.mapException(ValidationException.class, BadRequestException.class);
    }
}
