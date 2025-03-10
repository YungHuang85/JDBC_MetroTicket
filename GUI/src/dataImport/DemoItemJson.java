package dataImport;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import guiExe.Item;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

//gson可將json格式轉換為java格式
public class DemoItemJson {
    public static void main(String[] args) {
    	
    	//要讀取的json路徑
        String filePath = "C:\\Users\\user\\Desktop\\JAVA_專題1\\臺北捷運系統票價資料\\臺北捷運系統票價資料(1090301).json";
        
        // 將LocalDateTime修改為可讓gson判讀
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();

        try (
        	//打開指定的 JSON 文件
        	FileReader reader = new FileReader(filePath)) {
            //使用 TypeToken 獲取 List<Item> 的類型信息，以便於 Gson 能夠正確地將 JSON 轉換為 Java 對象
            Type itemListType = new TypeToken<List<Item>>(){}.getType();
            
            //將 JSON 轉換為 List 存儲在 itemList 中
            List<Item> itemList = gson.fromJson(reader, itemListType);
            
            //初始化 ItemDAO 實例
            ItemDAO itemCsvDAO = new ItemDAO();

            // 調用 saveItem 方法將每個 Item 物件儲存到資料庫中
            for (Item item : itemList) {
                itemCsvDAO.saveItem(item);
            }
            System.out.println("JSON上傳成功");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
