package test;

import java.util.List;

import debugUtil.debugTool;
import iniLoader.iniLoader;
import iniLoader.iniProperties;

public class test {
	public static void main(String[] args) {
		iniLoader loader=new iniLoader("./test.ini");
		iniProperties properties=loader.loadAllProperties();
		String db=properties.getValue("COMMON", "DB");
		debugTool.println("db : "+db);
		List<String> dbList=properties.getValues("COMMON", "DB");
		for(String value:dbList){
			debugTool.println("dbListValue : "+value);
		}
	}
}
