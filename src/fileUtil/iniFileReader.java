package fileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import debugUtil.debugTool;
import iniLoader.iniProperties;

public class iniFileReader {
	protected File file=null;
	protected BufferedReader fbr=null;
	protected boolean isFileMarked=true;
	/**
	 * construct a iniFileReader
	 * @param fPath inifile's Path
	 */
	public iniFileReader(String fPath)  {
		// TODO Auto-generated constructor stub
		file=new File(fPath);
		try {
			reLoadFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String readLine(){
		String line=new String();
		try {
			if(fbr!=null)
				line=fbr.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return line;
	}
	public String skip2ReadLine(int lineNum){
		String line=new String();
		try {
			if(fbr!=null)	
				for(int i=0;i<lineNum;i++)
					fbr.readLine();
			line=fbr.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return line;
	}
	
	
	
	
	
	/**
	 * reload File 
	 * <p>use to load file or reset seekPoint when stream refused to be marked<br>
	 *
	 * @throws IOException  thrown when old stream close error or Now stream marked error
	 */
	public void reLoadFile() throws IOException{
		debugTool.println("reload call");
		if(fbr!=null){
			debugTool.println("fbr not null");
			if(isFileMarked){
				fbr.reset();
				return;
			}
			fbr.close();
			fbr=null;
			debugTool.println("fbr not null reset it");
		}
		if(file.exists())
				fbr=new BufferedReader(new FileReader(file));
		else
			debugTool.println("file not found : "+file.getAbsolutePath());
		if(fbr.markSupported()){
				fbr.mark((int) (file.length()+1));
				isFileMarked=true;
		}
		else
			isFileMarked=false;
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		if(fbr!=null){
			fbr.close();
			fbr=null;
		}
	}
}
