package com.tengen;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkerHelloWorld {
	public static void main(String[] args) {
		try {

			Configuration config = new Configuration();
			config.setClassForTemplateLoading(FreeMarkerHelloWorld.class, "/");
			Template helloTemplate = config.getTemplate("hello.ftl");
			StringWriter writer = new StringWriter();
			Map<String, Object> helloMap = new HashMap<String, Object>();
			helloMap.put("name", "Free Marker");
			helloTemplate.process(helloMap, writer);
			System.out.println(writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
