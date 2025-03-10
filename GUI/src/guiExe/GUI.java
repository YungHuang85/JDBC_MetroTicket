package guiExe;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GUI {
	
	//GUI主窗口和分頁變數
    private JFrame frame;
    private JTabbedPane tabbedPane;

    // 查詢變數
    private JTextArea resultArea;
    private JButton fetchButton;

    // 刪除變數
    private JButton deleteButton;
    private JButton deleteAllButton;
    private JTextArea deleteResultArea;

    // 插入變數
    private JTextField insertStartStationField;
    private JTextField insertFinalStationField;
    private JTextField fullPriceField;
    private JTextField elderlyPriceField;
    private JTextField childPriceField;
    private JTextField distanceField;
    private JButton insertButton;
    private JTextArea insertResultArea;

    // 更新變數
    private JTextField updateStartStationField;
    private JTextField updateFinalStationField;
    private JTextField updateFullPriceField;
    private JTextField updateElderlyPriceField;
    private JTextField updateChildPriceField;
    private JButton updateButton;
    private JTextArea updateResultArea;

    // 總查詢變數
    private JTextArea viewAllResultArea;
    private JButton viewAllButton;
    private JButton exportCSVButton;
    
    // 提醒字串
    private static final String DEFAULT_MESSAGE = "請輸入站別";
    private static final String DEFAULT_MESSAGE1 = "請輸入票價 NTD";
    private static final String DEFAULT_MESSAGE2 = "請輸入距離 km";

    public GUI() {
        // 建立Jframe
        frame = new JFrame("Taipei Metro - Ticket Price System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 850);
        frame.setLayout(new BorderLayout());

        // 建立tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Serif", Font.BOLD, 15));
        tabbedPane.addTab("檢視單筆資料", createFetchPanel());
        tabbedPane.addTab("檢視所有資料", createViewAllPanel());
        tabbedPane.addTab("刪除票價資料", createDeletePanel());
        tabbedPane.addTab("新增票價資料", createInsertPanel());
        tabbedPane.addTab("更新票價資訊", createUpdatePanel());
        
        // 設定分頁位置
        frame.add(tabbedPane, BorderLayout.CENTER);
        // 顯示所有設定
        frame.setVisible(true);
    }
     
     //設定combobox選單站別
    String[] stations = {
    		"台北橋", "台北車站", "台北小巨蛋", "台北101/世貿", "台大醫院",
    		"麟光", "蘆洲", "關渡", "雙連", "龍山寺",
    		"頭前庄", "橋和", "劍潭", "劍南路", "輔大",
    		"葫洲", "萬隆", "萬芳醫院", "萬芳社區", "新莊",
    		"新埔民生", "新埔", "新店區公所", "新店", "新北產業園區",
    		"新北投", "圓山", "象山", "菜寮", "港墘",
    		"景美", "景安", "景平", "復興崗", "善導寺",
    		"頂溪", "頂埔", "淡水", "國父紀念館", "唭哩岸",
    		"動物園", "迴龍", "海山", "徐匯中學", "紅樹林",
    		"科技大樓", "後山埤", "南勢角", "南港軟體園區", "南港展覽館",
    		"南港", "南京復興", "南京三民", "信義安和", "芝山",
    		"板橋", "板新", "松江南京", "松山機場", "松山",
    		"東湖", "東門", "明德", "昆陽", "忠義",
    		"忠孝新生", "忠孝敦化", "忠孝復興", "府中", "幸福",
    		"奇岩", "亞東醫院", "辛亥", "秀朗橋", "西湖",
    		"西門", "行天宮", "竹圍", "江子翠", "先嗇宮",
    		"石牌", "永寧", "永春", "永安市場", "民權西路",
    		"市政府", "台電大樓", "古亭", "北門", "北投",
    		"木柵", "文德", "六張犁", "公館", "內湖",
    		"丹鳳", "中原", "中和", "中正紀念堂", "中山國中",
    		"中山國小", "中山", "小碧潭", "小南門", "大橋頭", 
    		"大湖公園", "大直", "大坪林", "大安森林公園", "大安",
    		"士林", "土城", "三重國小", "三重", "三和國中",
    		"三民高中", "十四張", "七張"};

    //swing panel檢視指定票價
    private JPanel createFetchPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // 建置jpanel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));
      
        // Replace with actual station names
        JComboBox<String> startStationComboBox = new JComboBox<>(stations);
        JComboBox<String> finalStationComboBox = new JComboBox<>(stations);
        
        Font comboBoxFont = new Font("Serif", Font.BOLD, 16); 
        startStationComboBox.setFont(comboBoxFont);
        finalStationComboBox.setFont(comboBoxFont);
          
             
        JLabel startStationLabel = new JLabel("出發站:");
        JLabel finalStationLabel = new JLabel("到達站:");
        
        Font labelFont = new Font("Monospaced", Font.BOLD, 18);
        startStationLabel.setFont(labelFont);
        finalStationLabel.setFont(labelFont);

        inputPanel.add(startStationLabel);
        inputPanel.add(startStationComboBox);

        inputPanel.add(finalStationLabel);
        inputPanel.add(finalStationComboBox);

        fetchButton = new JButton("搜尋資料");
        Font buttonFont = new Font("Serif", Font.BOLD, 18);
        fetchButton.setFont(buttonFont);
        fetchButton.setPreferredSize(new Dimension(100, 40));
        inputPanel.add(fetchButton);

        // console區域
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        
        Font font = new Font("Serif", Font.BOLD, 20);
        resultArea.setFont(font);
       
        // 新增console
        JScrollPane scrollPane = new JScrollPane(resultArea);
        frame.add(scrollPane);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // Button action listener
        fetchButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve selected stations
                String startStation = (String) startStationComboBox.getSelectedItem();
                String finalStation = (String) finalStationComboBox.getSelectedItem();
                fetchTicketPrice(startStation, finalStation);
            }
        });
        return panel;
    }

    //swing panel刪除票價
    private JPanel createDeletePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));
        buttonPanel.setLayout(new GridLayout(1, 2));

        // Replace with actual station names
        JComboBox<String> startStationComboBox = new JComboBox<>(stations);
        JComboBox<String> finalStationComboBox = new JComboBox<>(stations);
        
        Font comboBoxFont = new Font("Serif", Font.BOLD, 16);
        startStationComboBox.setFont(comboBoxFont);
        finalStationComboBox.setFont(comboBoxFont);
        
  
        JLabel startStationLabel= new JLabel("出發站:");
        JLabel finalStationLabel = new JLabel("到達站:");
              
        Font labelFont = new Font("Monospaced", Font.BOLD, 18);
        startStationLabel.setFont(labelFont);
        finalStationLabel.setFont(labelFont);
        
        inputPanel.add(startStationLabel);
        inputPanel.add(startStationComboBox);
        
        inputPanel.add(finalStationLabel);
        inputPanel.add(finalStationComboBox);
        
        Font buttonFont = new Font("Serif", Font.BOLD, 18);
        deleteButton = new JButton("刪除指定資料");
        deleteButton.setFont(buttonFont);
        deleteButton.setPreferredSize(new Dimension(100, 40));
        inputPanel.add(deleteButton);
        
        deleteAllButton = new JButton("*刪除全部資料");
        deleteAllButton.setFont(buttonFont);
        deleteAllButton.setPreferredSize(new Dimension(100, 30));
        deleteAllButton.setBackground(Color.ORANGE);
        buttonPanel.add(deleteAllButton);

        
        deleteResultArea = new JTextArea();
        deleteResultArea.setEditable(false);
        
        Font font = new Font("Serif", Font.BOLD, 20);
        deleteResultArea.setFont(font);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(new JScrollPane(deleteResultArea), BorderLayout.CENTER);

        // Button action listener
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve selected stations
                String startStation = (String) startStationComboBox.getSelectedItem();
                String finalStation = (String) finalStationComboBox.getSelectedItem();
                deleteTicketPrice(startStation, finalStation); // Call the correct method here       
          }
        });
        deleteAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve selected stations
               
         
                deleteAll();
          }
        });

        return panel;
    }

    //swing panel新增票價
    private JPanel createInsertPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(13, 1));
        
        Font labelFont = new Font("Serif", Font.BOLD, 15);//設定字體

        JLabel insertStartStationJLabel = new JLabel("出發站:");
        insertStartStationJLabel.setFont(labelFont);           
        insertStartStationField = new JTextField(DEFAULT_MESSAGE);
        insertStartStationField.setForeground(Color.GRAY);
        setupFocusListener(insertStartStationField);
        inputPanel.add(insertStartStationJLabel);
        inputPanel.add(insertStartStationField);

        JLabel insertFinalStationJLabel = new JLabel("到達站:");
        insertFinalStationJLabel.setFont(labelFont);
        insertFinalStationField = new JTextField(DEFAULT_MESSAGE);
        insertFinalStationField.setForeground(Color.GRAY);
        setupFocusListener(insertFinalStationField);
        inputPanel.add(insertFinalStationJLabel);
        inputPanel.add(insertFinalStationField);

        JLabel insertFullPriceJLabel = new JLabel("全票票價:");
        insertFullPriceJLabel.setFont(labelFont);
        fullPriceField = new JTextField(DEFAULT_MESSAGE1);
        fullPriceField.setForeground(Color.GRAY);
        setupFocusListener(fullPriceField);
        inputPanel.add(insertFullPriceJLabel);
        inputPanel.add(fullPriceField);

        JLabel elderlyPriceJLabel = new JLabel("敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價:");
        elderlyPriceJLabel.setFont(labelFont);
        elderlyPriceField = new JTextField(DEFAULT_MESSAGE1);
        elderlyPriceField.setForeground(Color.GRAY);
        setupFocusListener(elderlyPriceField);
        inputPanel.add(elderlyPriceJLabel);
        inputPanel.add(elderlyPriceField);

        JLabel childPriceJLabel = new JLabel("臺北市兒童優惠票價:");
        childPriceJLabel.setFont(labelFont);
        childPriceField = new JTextField(DEFAULT_MESSAGE1);
        childPriceField.setForeground(Color.GRAY);
        setupFocusListener(childPriceField);
        inputPanel.add(childPriceJLabel);
        inputPanel.add(childPriceField);

        JLabel distanceLabel = new JLabel("距離:");
        distanceLabel.setFont(labelFont);
        distanceField = new JTextField(DEFAULT_MESSAGE2);
        distanceField.setForeground(Color.GRAY);
        setupFocusListener(distanceField);
        inputPanel.add(distanceLabel);
        inputPanel.add(distanceField);

        insertButton = new JButton("加入資料");
        Font buttonFont = new Font("Serif", Font.BOLD, 16);
        insertButton.setFont(buttonFont);
        inputPanel.add(insertButton);

        // 顯示console
        insertResultArea = new JTextArea();
        insertResultArea.setEditable(false);
        
        Font font = new Font("Serif", Font.BOLD, 18); // 选择字体和大小
        insertResultArea.setFont(font);
        //控制面板顯示位置
        panel.add(inputPanel, BorderLayout.NORTH);
        //console顯示位置
        panel.add(new JScrollPane(insertResultArea), BorderLayout.CENTER);

        // Button action listener
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertTicketPrice();
            }
        });
        return panel;
    }

    //swing panel更新票價
    private JPanel createUpdatePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(11, 1));

        
        Font labelFont = new Font("Serif", Font.BOLD, 15);//設定字體
        
        JLabel updateStartStationJLabel = new JLabel("出發站:");
        updateStartStationJLabel.setFont(labelFont);  //設定接口
        updateStartStationField = new JTextField(DEFAULT_MESSAGE);       
        updateStartStationField.setForeground(Color.GRAY);
        setupFocusListener(updateStartStationField);
        inputPanel.add(updateStartStationJLabel);//順序要在輸入值上方
        inputPanel.add(updateStartStationField);
        
        JLabel updateFinalStationJLabel = new JLabel("到達站:");
        updateFinalStationJLabel.setFont(labelFont);
        updateFinalStationField = new JTextField(DEFAULT_MESSAGE);
        updateFinalStationField.setForeground(Color.GRAY);
        setupFocusListener(updateFinalStationField);
        inputPanel.add(updateFinalStationJLabel);
        inputPanel.add(updateFinalStationField);
        

        JLabel updateFullPriceJLabel = new JLabel("全票票價:");
        updateFullPriceJLabel.setFont(labelFont);
        updateFullPriceField = new JTextField(DEFAULT_MESSAGE1);
        updateFullPriceField.setForeground(Color.GRAY);
        setupFocusListener(updateFullPriceField);
        inputPanel.add(updateFullPriceJLabel);
        inputPanel.add(updateFullPriceField);

        JLabel updateElderlyPriceJLabel = new JLabel("敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價:");
        updateElderlyPriceJLabel.setFont(labelFont);
        updateElderlyPriceField = new JTextField(DEFAULT_MESSAGE1);
        updateElderlyPriceField.setForeground(Color.GRAY);
        setupFocusListener(updateElderlyPriceField); 
        inputPanel.add(updateElderlyPriceJLabel);
        inputPanel.add(updateElderlyPriceField);

        JLabel updateChildPriceJLabel = new JLabel("臺北市兒童優惠票價:");   
        updateChildPriceJLabel.setFont(labelFont);
        updateChildPriceField = new JTextField(DEFAULT_MESSAGE1);
        updateChildPriceField.setForeground(Color.GRAY);
        setupFocusListener(updateChildPriceField);     
        inputPanel.add(updateChildPriceJLabel);
        inputPanel.add(updateChildPriceField);

        updateButton = new JButton("更新資料");
        Font buttonFont = new Font("Serif", Font.BOLD, 16);
        updateButton.setFont(buttonFont);
        inputPanel.add(updateButton);

        // console參數
        updateResultArea = new JTextArea();
        updateResultArea.setEditable(false);
        
        Font font = labelFont;
        updateResultArea.setFont(font);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(updateResultArea), BorderLayout.CENTER);

        // Button action listener
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTicketPrice();
            }
        });

        return panel;
    }

    // swing panel檢視所有票價
    private JPanel createViewAllPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // 設定button字樣,字體,大小
        Font buttonFont = new Font("Serif", Font.BOLD, 17);
        
        viewAllButton = new JButton("Check Data");
        viewAllButton.setFont(buttonFont);
        viewAllButton.setPreferredSize(new Dimension(150, 40));
        
        exportCSVButton = new JButton("Export Data");
        exportCSVButton.setFont(buttonFont);
        exportCSVButton.setPreferredSize(new Dimension(150, 40));
        
        
        viewAllResultArea = new JTextArea();
        viewAllResultArea.setEditable(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewAllButton);
        buttonPanel.add(exportCSVButton);
      
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(new JScrollPane(viewAllResultArea), BorderLayout.CENTER);

        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAllTicketPrices();
            }
        });

        exportCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportTicketPricesToCSV();
            }   
        });
        return panel;
    }
    
    // 檢視票價邏輯
    private void fetchTicketPrice(String startStation, String finalStation) {
        

        if (startStation == null || startStation.isEmpty() || finalStation == null || finalStation.isEmpty()) {
            resultArea.setText("請選擇有效的站別。");
            return;
        }

        selectSQL sql = new selectSQL();
        Item item1 = sql.readItem(startStation, finalStation);

        resultArea.setText("");

        if (item1 != null) {
            String result = String.format(
                "出發站: %s%n" + "到達站: %s%n" + "全票票價: %d NTD%n" + "敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價: %d NTD%n" + "臺北市兒童優惠票價: %d NTD%n" + "距離: %.2f km%n",
                item1.get起站(),item1.get訖站(),item1.get全票票價(),item1.get敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價(),item1.get臺北市兒童優惠票價(),item1.get距離()
            );

            // 顯示錯誤回應
            resultArea.setText(result);
        } else {
            resultArea.setText("無相關站別資料, 請重新選擇");
        }
    }
    
 // 檢視所有票價邏輯
    private void viewAllTicketPrices() {
        selectAllSQL selectAll = new selectAllSQL();
        List<Item> itemList = selectAll.selectAllSQL();

        StringBuilder result = new StringBuilder();
        for (Item item : itemList) {
            result.append(item.toString()).append("\n");
        }

        viewAllResultArea.setText(result.toString());
    }
    
    // 刪除票價邏輯    
    private void deleteTicketPrice(String startStation, String finalStation) {
     
        if (startStation == null || startStation.isEmpty() || finalStation == null || finalStation.isEmpty()) {
            deleteResultArea.setText("請選擇有效的站別。");
            return;
        }

        deleteSQL sql = new deleteSQL();
        sql.deleteByProductId(startStation, finalStation);
        deleteResultArea.setText("|刪除成功|\n出發站: " + startStation + "\n到達站: " + finalStation);
    }
    
    private void deleteAll() {
    	deleteAll sqldeleteAll = new deleteAll();
    	sqldeleteAll.deleteAllData();
    	deleteResultArea.setText("全部刪除成功");	
    }
    

    // 插入票價邏輯
    private void insertTicketPrice() {
        String startStation = insertStartStationField.getText();
        String finalStation = insertFinalStationField.getText();
        String fullPrice = fullPriceField.getText();
        String elderlyPrice = elderlyPriceField.getText();
        String childPrice = childPriceField.getText();
        String distance = distanceField.getText();
        
       
        if (startStation.equals(DEFAULT_MESSAGE) || finalStation.equals(DEFAULT_MESSAGE) ||
            fullPrice.isEmpty() || elderlyPrice.isEmpty() || childPrice.isEmpty() || distance.isEmpty()) {
            insertResultArea.setText("請填寫所有必填欄位。");
            return;       
        }
        Item item = new Item();
        item.set起站(startStation);
        item.set訖站(finalStation);
        item.set全票票價(Integer.parseInt(fullPrice));
        item.set敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價(Integer.parseInt(elderlyPrice));
        item.set臺北市兒童優惠票價(Integer.parseInt(childPrice));
        item.set距離(Double.parseDouble(distance));
        insertSQL sql = new insertSQL();
        sql.saveItem(item);
        insertResultArea.setText("|新增成功|\n出發站: " + startStation + "\n到達站: " + finalStation + "\n全票票價: " + 
                fullPrice +" NTD\n敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價: " + elderlyPrice + " NTD\n臺北市兒童優惠票價: " + childPrice + " NTD\n距離: " + distance + " km");
    }
    
    // 更新票價邏輯
    private void updateTicketPrice() {
        String startStation = updateStartStationField.getText();
        String finalStation = updateFinalStationField.getText();
        String fullPrice = updateFullPriceField.getText();
        String elderlyPrice = updateElderlyPriceField.getText();
        String childPrice = updateChildPriceField.getText();

        if (startStation.equals(DEFAULT_MESSAGE) || finalStation.equals(DEFAULT_MESSAGE) ||
            fullPrice.isEmpty() || elderlyPrice.isEmpty() || childPrice.isEmpty()) {
            updateResultArea.setText("請填寫所有必填欄位。");
            return;
        }

        // 建立物件保存屬性
        Item updatedItem = new Item();
        updatedItem.set起站(startStation);
        updatedItem.set訖站(finalStation);
        try {
            updatedItem.set全票票價(Integer.parseInt(fullPrice));
            updatedItem.set敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價(Integer.parseInt(elderlyPrice));
            updatedItem.set臺北市兒童優惠票價(Integer.parseInt(childPrice));
        } catch (NumberFormatException e) {
            updateResultArea.setText("請輸入有效的價格。");
            return;
        }

        updateSQL sql = new updateSQL();
        boolean success = sql.updateItem(updatedItem);
        
        if (success) {
            updateResultArea.setText("|更新成功|\n出發站: " + startStation + "\n到達站: " + finalStation + "\n全票票價: " + 
                    fullPrice +" NTD\n敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價: " + elderlyPrice + " NTD\n臺北市兒童優惠票價: " + childPrice + " NTD");
        } else {
            updateResultArea.setText("更新失敗: 請檢查站名是否正確。");
        }
    
}

    

    // 輸出票價為csv或json邏輯
    private void exportTicketPricesToCSV() {
        selectAllSQL selectAll = new selectAllSQL();
        List<Item> items = selectAll.selectAllSQL();
     
        StringBuilder csvContent = new StringBuilder();
        csvContent.append("出發站,到達站,全票票價,敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價,臺北市兒童優惠票價,距離\n");
        for (Item item : items) {
            csvContent.append(item.get起站()).append(",")
                    .append(item.get訖站()).append(",")
                    .append(item.get全票票價()).append(",")
                    .append(item.get敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價()).append(",")
                    .append(item.get臺北市兒童優惠票價()).append(",")
                    .append(item.get距離()).append("\n");
        }
              
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) ->
                        new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, context) ->
                        LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .create();

        String jsonContent = gson.toJson(items);
 
        // 儲存csv與json
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("選擇儲存路徑");
        FileNameExtensionFilter filterJSON = new FileNameExtensionFilter(".json", "json");
        FileNameExtensionFilter filterCSV = new FileNameExtensionFilter(".csv", "csv");
        
        fileChooser.setFileFilter(filterJSON);
        fileChooser.setFileFilter(filterCSV);
        
        int userSelection = fileChooser.showSaveDialog(frame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String selectedFilterDescription = fileChooser.getFileFilter().getDescription();

            try {
            	  //寫出csv
                if (selectedFilterDescription.equals(".csv")) {
                    if (!fileToSave.getName().toLowerCase().endsWith(".csv")) {
                        fileToSave = new File(fileToSave.getAbsolutePath() + ".csv");
                    }                    
                    try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileToSave), "MS950"))) {
                        writer.write(csvContent.toString());
                    }
                  //寫出json         
                } if (selectedFilterDescription.equals(".json")) {
                    if (!fileToSave.getName().toLowerCase().endsWith(".json")) {
                        fileToSave = new File(fileToSave.getAbsolutePath() + ".json");
                    }
                    try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileToSave), StandardCharsets.UTF_8))) {
                        writer.write(jsonContent);
                    }
                }
                JOptionPane.showMessageDialog(null, "Data exported successfully to " + fileToSave.getAbsolutePath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error saving file: " + e.getMessage());
            }
        }
    }
    
    // Focus listener for the text fields
    private void setupFocusListener(JTextField textField) {
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textField.getText().equals(DEFAULT_MESSAGE)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
                if (textField.getText().equals(DEFAULT_MESSAGE1)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
                if (textField.getText().equals(DEFAULT_MESSAGE2)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
                
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(DEFAULT_MESSAGE);
                }
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(DEFAULT_MESSAGE1);
                }
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(DEFAULT_MESSAGE2);
                }
            }
                   
        });
    }

}