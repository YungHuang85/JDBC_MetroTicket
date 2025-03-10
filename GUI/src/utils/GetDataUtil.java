package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class GetDataUtil {
	//讀取外部資料的工具類靜態方法
	public static List<String> getData(String dataPath){
		ArrayList<String> dataList = new ArrayList<String>();
		try {
			FileInputStream fileInputStream = new FileInputStream(new File(dataPath));
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,Charset.forName("MS950"));
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String content = "";
		while(bufferedReader.ready()) {
			content = bufferedReader.readLine();
			System.out.println(content);
			dataList.add(content);
		}
		//刪除標頭
		dataList.remove(0);
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataList;
	}
}
