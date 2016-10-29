package iniLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import debugUtil.debugTool;
import fileUtil.iniFileReader;

public class iniLoader {
	
	protected HashMap<String,Long> sectionsRefer=new HashMap<>();
	protected iniFileReader inifile=null;
	private final String sectionRe="(?m)^\\[[^\\]\\r\\n]+]";
	private final String keyValueRe="(?m)^([^=;\r\n]+)=([^;\r\n]*)";
	private final String commentRe="";
	
	public iniLoader(String fPath) {
		// TODO Auto-generated constructor stub
		inifile=new iniFileReader(fPath);
	}
	
	public iniProperties loadAllProperties(){
		iniProperties result=new iniProperties();
		HashMap<String,List<String>> keys = null;
		HashMap<String, HashMap<String,List<String>>> sections = new HashMap<>();
		List<String> values;
		String sectionName=new String();
		String line=new String();
		
		try {
			inifile.reLoadFile();//reset fileReader to file start
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(null!=(line=inifile.readLine())){//read file line by line
			line=line.trim();
			debugTool.println(line);
			if(line.matches(sectionRe)){//section
				debugTool.println("match a section");
				if(!sectionName.equals(""))
					sections.put(sectionName, keys);
				sectionName=line.substring(line.indexOf("[")+1, line.indexOf("]"));
				keys=sections.get(sectionName);
				if(keys==null){
					keys=new HashMap<>();
				}
			}
			if(line.matches(keyValueRe)){//key=value
				debugTool.println("match a keyvalue");
				if(!sectionName.equals("")){
					String key=line.substring(0,line.indexOf("=")).trim();
					String value=line.substring(line.indexOf("=")+1).trim();
					values=keys.get(key);
					debugTool.println("\t"+line);
					if(values==null){
						values=new ArrayList<>();
						values.add(value);
						keys.put(key, values);
					}
					else
						values.add(value);
				}
			}
		}
		if(!sectionName.equals(""))
			sections.put(sectionName, keys);
		result.setProperties(sections);
		return result;
	}
	
	
	
	
	/**
	 * read the file & produce a section map
	 * @throws IOException throw when readLine error
	 */
	private void loadSections() throws IOException{
		if(inifile==null)
			return;
		inifile.reLoadFile();
		sectionsRefer=new HashMap<>();
		String line=new String();
		for(long i=0;(line=inifile.readLine().trim())!=null;i++){
			if(line.matches("")){//[section]
				sectionsRefer.put(line.substring(line.indexOf("[")+1, line.indexOf("]")),i);
			}
		}
	}
}
