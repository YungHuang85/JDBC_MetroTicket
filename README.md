# 台北捷運查票系統

# 專案簡介

本專案是一個使用Java & JDBC 開發的應用程式，主要功能包括：

    -- 出發站至到達站各票價查詢(全票、敬老票、兒童票)、距離
    -- 各站票價CRUD
    -- 資料輸出成Json或Excel檔案

# 技術棧

    Java SE - 核心 Java 標準庫，提供基本的語言功能與 API
    JDK 17 - Java 開發工具包，使用 Eclipse Adoptium Zulu JDK 17
    JDBC - Java 資料庫連線技術，透過 SQL 操作關聯式資料庫
    SQL Server - 資料庫，使用 Microsoft SQL Server JDBC 驅動
    Gson - JSON 解析與序列化工具，用於物件與 JSON 之間的轉換
    JNDI - Java 命名與目錄介面，用於綁定與查詢外部資源
    JMX - Java 管理擴展，用於監控與管理 Java 應用
    Eclipse IDE - 開發工具，提供 Java 程式設計與除錯環境

# 專案結構

    GUI  # 主專案目錄
    │
    ├── src  # 原始碼資料夾
    │   │
    │   ├── createSqlTable  # 建立 SQL 資料表
    │   │   ├── createSqlTable.java  # 建立 SQL Table 的類別
    │   │
    │   ├── dataImport  # 資料導入模組
    │   │   ├── DemoItemCSV.java  # CSV 格式的資料導入
    │   │   ├── DemoItemJson.java  # JSON 格式的資料導入
    │   │   ├── ItemDAO.java  # 資料存取物件（Data Access Object）
    │   │   ├── ItemService.java  # 服務層邏輯處理
    │   │   ├── LocalDateTimeAdapter.java  # 日期時間格式轉換適配器
    │   │
    │   ├── guiExe  # GUI 執行相關模組
    │   │   ├── deleteAll.java  # 刪除所有資料
    │   │   ├── deleteSQL.java  # 刪除特定 SQL 資料
    │   │   ├── GUI.java  # GUI 介面主程式
    │   │   ├── insertSQL.java  # 插入 SQL 資料
    │   │   ├── mainGUIexe.java  # GUI 執行主程式
    │   │   ├── selectAllSQL.java  # 查詢所有 SQL 資料
    │   │   ├── selectSQL.java  # 查詢特定 SQL 資料
    │   │   ├── updateSQL.java  # 更新 SQL 資料
    │   │
    │   ├── utils  # 工具類別
    │   │   ├── GetDataUtil.java  # 取得資料的工具類
    │   │   ├── JDBCutils.java  # JDBC 連線工具類
    │
    ├── Referenced Libraries  # 參考函式庫
    │
    └── JRE System Library [JDK 17]  # 使用的 Java 開發工具包（JDK 17）


# 環境設置

1. 前置需求

    請確保你的開發環境已安裝以下工具：

    JDK 17 或更新版本
    Microsoft SQL Server 2022 
    SQL Server Management Studio (SSMS)
    MSSQL JDBC 驅動
    Gson
    Eclipse IDE

2. 設定環境變數

    確保Java Build Path 的Libraries導入 gson-2.11.0.jar 與 mssql-jdbc-12.8.1.jre11.jar (jar位於jarLib資料夾)

3. 啟動專案

    --確保SQL資料表皆執行(可從DemoItemCSV導入csv, csv位於"臺北捷運系統票價資料"內)

    --執行資料夾 TPEmetroTicket_exe內的 TPE_metroTicket.exe 執行檔即可使用. 
