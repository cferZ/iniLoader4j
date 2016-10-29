package debugUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class debugTool {
	static private boolean isDebugMod=true;
	public static void println(Object obj){
		if(isDebugMod)
		System.out.println(obj.toString());
	}
	public static void log(Object obj){
		if(isDebugMod)
			System.out.println(nowTime()+" "+obj.toString());
		else
			//write to log file
			;
	}
	public static String nowTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
		
	}
	public static void main(String[] args) {
		while(true){
			println(nowTime());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
