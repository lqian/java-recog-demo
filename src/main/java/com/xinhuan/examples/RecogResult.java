/**
 * 
 */
package com.xinhuan.examples;

/**
 * @author xinhuan
 *
 */
public class RecogResult {
	String plateNo;				//号牌 
	String plateColorName = "unknown"; // 号码颜色名
	int plateFlag = -1;       //号牌标志
	 
	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public String getPlateColorName() {
		return plateColorName;
	}

	public void setPlateColorName(String plateColorName) {
		this.plateColorName = plateColorName;
	}

	public int getPlateFlag() {
		return plateFlag;
	}

	public void setPlateFlag(int plateFlag) {
		this.plateFlag = plateFlag;
	}

	public static class InnerDetail {
		public float prob;
		public Rect box;
		public String name;
		public float getProb() {
			return prob;
		}
		public void setProb(float prob) {
			this.prob = prob;
		}
		public Rect getBox() {
			return box;
		}
		public void setBox(Rect box) {
			this.box = box;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		} 
	}
	
	public static class RecogBrand {
		public int mainBrand;
		public  int subBrand;
		public  int model;
		public String brandFullName;
		public int getMainBrand() {
			return mainBrand;
		}
		public void setMainBrand(int mainBrand) {
			this.mainBrand = mainBrand;
		}
		public int getSubBrand() {
			return subBrand;
		}
		public void setSubBrand(int subBrand) {
			this.subBrand = subBrand;
		}
		public int getModel() {
			return model;
		}
		public void setModel(int model) {
			this.model = model;
		}
		public String getBrandFullName() {
			return brandFullName;
		}
		public void setBrandFullName(String brandFullName) {
			this.brandFullName = brandFullName;
		}
	}
	
	public static class Rect {
		public int x, y, w, h;

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public int getW() {
			return w;
		}

		public void setW(int w) {
			this.w = w;
		}

		public int getH() {
			return h;
		}

		public void setH(int h) {
			this.h = h;
		}
	}
}
