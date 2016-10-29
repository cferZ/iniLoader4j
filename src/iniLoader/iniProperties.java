package iniLoader;

import java.util.HashMap;
import java.util.List;

public class iniProperties {
	private HashMap<String, HashMap<String,List<String>>> properties=new HashMap<>();
	
	void setProperties(HashMap<String, HashMap<String,List<String>>> properties){
		this.properties=properties;
	}
	
	/**
	 * get first value in [section] & name=[key]
	 * @param section
	 * @param key
	 * @return value OR "" for no such key
	 */
	public String getValue(String section,String key){
		HashMap<String,List<String>> keys=properties.get(section);
		if(keys==null){
			return "";
		}
		List<String> values=keys.get(key);
		if(values==null){
			return "";
		}
		return values.get(0);
	}
	/**
	 * get all values in [section] name=[key]
	 * @param section
	 * @param key
	 * @return values' list OR  null for no such key
	 */
	public List<String> getValues(String section,String key){
		HashMap<String,List<String>> keys=properties.get(section);
		if(keys==null){
			return null;
		}
		return keys.get(key);
	}
	
	
}
