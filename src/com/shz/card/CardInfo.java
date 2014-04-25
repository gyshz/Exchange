package com.shz.card;

public class CardInfo {
	private String name = null;
	private String position = null;
	private String company = null;
	private String tel = null;
	private String email = null;
	private String logo = null;
	private CardStyle style = CardStyle.ONE;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public CardStyle getStyle() {
		return style;
	}

	public void setStyle(CardStyle style) {
		this.style = style;
	}

	@Override
	public String toString() {
		return "CardInformation[ Name:" + this.getName() + "; Position:" + this.getPosition() + "; Company:"
				+ this.getCompany() + "; Telphone:" + this.getTel() + "; Email:" + this.getEmail() + "; LogoPath:"
				+ this.getLogo() + "; Style:" + this.getStyle();
	}
}
