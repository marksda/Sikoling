package org.Sikoling.main.restful;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import jakarta.ws.rs.ApplicationPath;


@ApplicationPath("api")
public class RestfulApplication extends ResourceConfig {
	
	public RestfulApplication() {
		packages("org.Sikoling.main.restful").register(MultiPartFeature.class);
	}
	
}
