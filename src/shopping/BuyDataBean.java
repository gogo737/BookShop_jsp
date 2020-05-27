package shopping;

import java.sql.Timestamp;

public class BuyDataBean {
	private Long buy_id;//구매아이디
	private String buyer;//구매자
	private int book_id;//구매된 책아이디
	private String book_title;//구매된 책명
	private int buy_price;//판매가
	private byte buy_count;//판매수량
	private String book_image;//책이미지
	private Timestamp buy_date;//구매일자
	private String account;//결제계좌
	private String deliveryName;//배송지
	private String deliveryTel ;//배송지 전화번호
	private String deliveryAddress;//배송지 주소
	private String sanction;//배송상황
	
	public Long getBuy_id() {
		return buy_id;
	}
	public void setBuy_id(Long buy_id) {
		this.buy_id = buy_id;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getBook_title() {
		return book_title;
	}
	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}
	public int getBuy_price() {
		return buy_price;
	}
	public void setBuy_price(int buy_price) {
		this.buy_price = buy_price;
	}
	public byte getBuy_count() {
		return buy_count;
	}
	public void setBuy_count(byte buy_count) {
		this.buy_count = buy_count;
	}
	public String getBook_image() {
		return book_image;
	}
	public void setBook_image(String book_image) {
		this.book_image = book_image;
	}
	public Timestamp getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(Timestamp buy_date) {
		this.buy_date = buy_date;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getDeliveryName() {
		return deliveryName;
	}
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	public String getDeliveryTel() {
		return deliveryTel;
	}
	public void setDeliveryTel(String deliveryTel) {
		this.deliveryTel = deliveryTel;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getSanction() {
		return sanction;
	}
	public void setSanction(String sanction) {
		this.sanction = sanction;
	}
}