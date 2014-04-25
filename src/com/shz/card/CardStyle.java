package com.shz.card;

import com.shz.nfcexchange.R;

public enum CardStyle {
	ONE(R.layout.card_style_one, 1), TWO(R.layout.card_style_one, 2);

	private int layoutId = 0;
	private int index = 0;

	/**
	 * get layout id by index
	 * 
	 * @param index
	 * @return If exist return layout id, else return -1.
	 * */
	public static int getLayoutByIndex(int index) {
		for (CardStyle s : CardStyle.values()) {
			if (s.getIndex() == index) {
				return s.layoutId;
			}
		}
		return -1;
	}
	
	/**
	 * get CardStyle id by Layout Id
	 * 
	 * @param id
	 * @return If exist return CardStyle, else return null.
	 * */
	public static CardStyle getCardStyleByLayoutId(int id) {
		for (CardStyle s : CardStyle.values()) {
			if (s.getLayoutId() == id) {
				return s;
			}
		}
		return null;
	}

	public int getLayoutId() {
		return this.layoutId;
	}

	public int getIndex() {
		return this.index;
	}

	private CardStyle(int id, int index) {
		this.layoutId = id;
		this.index = index;
	}

}
