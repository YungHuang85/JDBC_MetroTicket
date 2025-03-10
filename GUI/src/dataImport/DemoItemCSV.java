package dataImport;

import java.util.List;

import guiExe.Item;
import utils.GetDataUtil;

public class DemoItemCSV {

	public static void main(String[] args) {
		//讀取csv並且存在List中
		List<String> dataList = GetDataUtil.getData("C:\\Users\\user\\Desktop\\Java_專題1_總資料\\臺北捷運系統票價資料\\臺北捷運系統票價資料(1090301).csv");
		//這個物件用於將讀取到的字串資料轉換為 Item 物件
		ItemService itemService = new ItemService();
		//將 dataList 中的資料轉換為 Item 物件的列表，結果存儲在 itemDataList 中
		List<Item> itemDataList = itemService.getItemList(dataList);
		
		//將Item物件儲存到SQL中
		ItemDAO itemDAO = new ItemDAO();
		for (Item item : itemDataList) {
			itemDAO.saveItem(item);;
		}
		System.out.println("CSV上傳成功");
	}		
}
