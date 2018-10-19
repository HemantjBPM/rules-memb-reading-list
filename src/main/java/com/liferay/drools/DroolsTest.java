package com.liferay.drools;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.liferay.models.RuleDataModel;
import com.liferay.utils.TrackingAgendaEventListener;

/*
 * put sample.drl file in any location and put that location name against drlFileLocation
 */

public class DroolsTest {

	private static KieContainer kieContainer = null;
	private static String drlFileLocation = "D:/rules";

	public static KieSession getKieSession() throws IOException {
		if (kieContainer == null) {
			KieServices kieServices = KieServices.Factory.get();
			KieFileSystem kfs = kieServices.newKieFileSystem();
			Files.list(Paths.get(drlFileLocation)).forEach(
					f -> {
						FileInputStream fis;
						try {
							fis = new FileInputStream(f.toFile());
							kfs.write("src/main/resources/" + f.getFileName(),
									kieServices.getResources()
											.newInputStreamResource(fis));
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
			KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
			Results results = kieBuilder.getResults();
			if (results.hasMessages(Message.Level.ERROR)) {
				System.out.println(results.getMessages());
				throw new IllegalStateException("### errors ###");
			}
			kieContainer = kieServices.newKieContainer(kieServices
					.getRepository().getDefaultReleaseId());
		}
		return kieContainer.newKieSession();
	}
	

    private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order){
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
    
	public static LinkedHashMap<String, Integer> sortHashMapByValues(
	        Map<String, Integer> map) {
	    List<String> mapKeys = new ArrayList<>(map.keySet());
	    List<Integer> mapValues = new ArrayList<>(map.values());
	    Collections.sort(mapValues);
	    Collections.sort(mapKeys);

	    LinkedHashMap<String, Integer> sortedMap =
	        new LinkedHashMap<>();

	    Iterator<Integer> valueIt = mapValues.iterator();
	    while (valueIt.hasNext()) {
	        Integer val = valueIt.next();
	        Iterator<String> keyIt = mapKeys.iterator();

	        while (keyIt.hasNext()) {
	            String key = keyIt.next();
	            Integer comp1 = map.get(key);
	            Integer comp2 = val;

	            if (comp1.equals(comp2)) {
	                keyIt.remove();
	                sortedMap.put(key, val);
	                break;
	            }
	        }
	    }
	    return sortedMap;
	}

	public static final void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		try {
			for(int i = 1; i<5;i++){
				TrackingAgendaEventListener ad = new TrackingAgendaEventListener();
				KieSession kieSession = getKieSession();
				kieSession.addEventListener(ad);
				System.out.println(kieSession);
				RuleDataModel f1 = new RuleDataModel();
				f1.setNetWorth(-1);
				f1.setLiquidity(6);
				f1.setSavings(2);
				f1.setExpenses(45);
				f1.setHousingDebt(30);
				f1.setNonHousingDebt(20);
				kieSession.getAgenda().getAgendaGroup("Zone "+i).setFocus();
				kieSession.insert(f1);
				
				// kieSession.getAgenda().getActivationGroup("A1");
				kieSession.fireAllRules();
				System.out.println(f1);
			System.out.println(ad.matchsToString());
			System.out.println(ad.matchRules());
			map.put("Zone " + i, ad.matchRules().size());
			
			}
			System.out.println(map);
			 boolean DESC = false;
			 Map<String, Integer> sortedMap = DroolsTest.sortByComparator(map, DESC);
			 System.out.println(sortedMap);
			 List<String> list = new LinkedList<String>(sortedMap.keySet());
				RuleDataModel f2 = new RuleDataModel();
			 if(sortedMap.get(list.get(0)).equals(sortedMap.get(list.get(1)))){
				 f2.setZones("Zone 1");
			 }else{
				 f2.setZones(list.get(0));
			 }
			 
			System.out.println(f2.getZones());	
			
			
			TrackingAgendaEventListener ad = new TrackingAgendaEventListener();
			KieSession kieSession = getKieSession();
			kieSession.addEventListener(ad);
			System.out.println(kieSession);
			RuleDataModel f1 = new RuleDataModel();
			f1.setNetWorth(-1);
			f1.setLiquidity(6);
			f1.setSavings(2);
			f1.setExpenses(45);
			f1.setHousingDebt(40);
			f1.setNonHousingDebt(20);
			kieSession.getAgenda().getAgendaGroup("Members Ratio").setFocus();
			kieSession.insert(f2);
			
			// kieSession.getAgenda().getActivationGroup("A1");
			kieSession.fireAllRules();
			System.out.println(f2);
		System.out.println(ad.matchsToString());
		System.out.println(ad.matchRules());
			
			
		} catch (Throwable t) {
			t.printStackTrace();
	}

		
	}
}

