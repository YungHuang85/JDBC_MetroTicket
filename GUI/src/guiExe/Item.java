package guiExe;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Item implements Serializable {
	
	private String 起站;
	private String 訖站;
	private Integer 全票票價;
	private Integer 敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價;
	private Integer 臺北市兒童優惠票價;
	private Double 距離;
	private LocalDateTime UpdateTime;
	
	public Item() {
		super();
	}

	public Item(String 起站, String 訖站, Integer 全票票價, Integer 敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價, Integer 臺北市兒童優惠票價, Double 距離,
			LocalDateTime updateTime) {
		super();
		this.起站 = 起站;
		this.訖站 = 訖站;
		this.全票票價 = 全票票價;
		this.敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價 = 敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價;
		this.臺北市兒童優惠票價 = 臺北市兒童優惠票價;
		this.距離 = 距離;
		UpdateTime = updateTime;
	}
	
	public Item(String 起站, String 訖站, Integer 全票票價, Integer 敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價, Integer 臺北市兒童優惠票價, Double 距離) {
		super();
		this.起站 = 起站;
		this.訖站 = 訖站;
		this.全票票價 = 全票票價;
		this.敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價 = 敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價;
		this.臺北市兒童優惠票價 = 臺北市兒童優惠票價;
		this.距離 = 距離;
	}

	public String get起站() {
		return 起站;
	}

	public void set起站(String 起站) {
		this.起站 = 起站;
	}

	public String get訖站() {
		return 訖站;
	}

	public void set訖站(String 訖站) {
		this.訖站 = 訖站;
	}

	public Integer get全票票價() {
		return 全票票價;
	}

	public void set全票票價(Integer 全票票價) {
		this.全票票價 = 全票票價;
	}

	public Integer get敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價() {
		return 敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價;
	}

	public void set敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價(Integer 敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價) {
		this.敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價 = 敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價;
	}

	public Integer get臺北市兒童優惠票價() {
		return 臺北市兒童優惠票價;
	}

	public void set臺北市兒童優惠票價(Integer 臺北市兒童優惠票價) {
		this.臺北市兒童優惠票價 = 臺北市兒童優惠票價;
	}

	public Double get距離() {
		return 距離;
	}

	public void set距離(Double 距離) {
		this.距離 = 距離;
	}

	public LocalDateTime getUpdateTime() {
		return UpdateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		UpdateTime = updateTime;
	}

	@Override
	public String toString() {
	    return String.format("出發站: %s; 到達站: %s; 全票票價: %d NTD; 敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價: %d NTD; 臺北市兒童優惠票價: %d NTD; 距離: %.2f km; 更新時間: %s",
	        起站,訖站,全票票價,敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價,臺北市兒童優惠票價,距離,UpdateTime);
	}


	public Object getStartStation() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
	



