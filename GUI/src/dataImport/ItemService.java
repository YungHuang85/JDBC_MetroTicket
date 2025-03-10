package dataImport;

import java.util.ArrayList;
import java.util.List;

import guiExe.Item;

//目的是將從 CSV 檔案或其他來源讀取的資料轉換為 Item 物件
public class ItemService {
	public List<Item> getItemList(List<String> dataList){
		// 建立ArrayList 用來儲存Item物件
		ArrayList<Item> ItemDataList = new ArrayList<Item>();
		// 將每一行資料以逗號為分隔符進行分割，將結果儲存在 tokens 陣列中。
		for(int i=0; i<dataList.size();i++) {
			String[] tokens = dataList.get(i).split(",");
								
			//建立Item物件
			Item item = new Item();
			//將 tokens 中的值設置到 Item 物件的相應屬性
			item.set起站(tokens[0]);
			item.set訖站(tokens[1]);
			item.set全票票價(Integer.parseInt(tokens[2]));
			item.set敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價(Integer.parseInt(tokens[3]));
			item.set臺北市兒童優惠票價(Integer.parseInt(tokens[4]));
			item.set距離(Double.parseDouble(tokens[5]));
			
			//將Item物件加到ArrayList
			ItemDataList.add(item);				
		}
		//返回所有 Item 物件內的資料
		return ItemDataList;	
	}
}
