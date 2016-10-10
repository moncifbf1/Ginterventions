package org.ocp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;

@Configuration
public class MyConfig {
	@Bean
	public SimpleJaxWsServiceExporter getJWS(){
		SimpleJaxWsServiceExporter exporter = new SimpleJaxWsServiceExporter();
		exporter.setBaseAddress("http://localhost:8089/goi");
		return exporter;
	}
}
