package com.liferay.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class CommonUtils {

	private static KieContainer kieContainer = null;

		public static KieSession createKession(String ruleLocation) throws Exception {
			System.out.println("Creating KieSession!");
			if(null == kieContainer){
				initialize(ruleLocation);
			}
			return kieContainer.newKieSession();
		}

		public static void initialize(String ruleLocation) throws Exception {
			KieServices kieServices = KieServices.Factory.get();
			KieFileSystem kfs = kieServices.newKieFileSystem();
			try {
				Files.list(Paths.get(ruleLocation)).forEach(f -> {
				FileInputStream fis;
					try {
						fis = new FileInputStream(f.toFile());
						kfs.write(LiferayConstant.RULEPATHCONSTANT + f.getFileName(),
								kieServices.getResources().newInputStreamResource(fis));
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
			KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
			
			Results results = kieBuilder.getResults();
			if (results.hasMessages(Message.Level.ERROR)) {
				System.out.println(results.getMessages());
				throw new IllegalStateException(results.getMessages().toString());
			}
			kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
			System.out.println("Kie Container Created!");
		}
		
		public static Connection getDBConnection(String driverClassName, String url, String username, String password) throws Exception{
			   Connection conn = null;
			   Class.forName(driverClassName);
			   conn = DriverManager.getConnection(url, username, password);
			   return conn;
		}
		
		public static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order){
	        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());
	        Collections.sort(list, new Comparator<Entry<String, Integer>>(){
	            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2){
	                if (order)
	                    return o1.getValue().compareTo(o2.getValue());
	                else
	                    return o2.getValue().compareTo(o1.getValue());
	            }
	        });
	        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
	        for (Entry<String, Integer> entry : list)
	            sortedMap.put(entry.getKey(), entry.getValue());
	        return sortedMap;
	    }
}
