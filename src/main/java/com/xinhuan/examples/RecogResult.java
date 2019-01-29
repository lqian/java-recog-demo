/**
 * 
 */
package com.xinhuan.examples;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author xinhuan
 *
 */
public class RecogResult {
	Rect vehicleBox;   			// 车辆位置
	RecogBrand recogBrand;  	// 车辆品牌识别
	Rect plateBox;         	 //车牌位置
	int colorId = -1;      	 //车身颜色编码
	String colorName;       	//车身颜色名
	int typeId = -1;			//车身类型编码
	float prob;
	String typeName;			//车身类型名称

	String plateNo;				//号牌
	int plateColor = -1;    	// 号码颜色编码
	String plateColorName = "unknown"; // 号码颜色名
	
	@JsonProperty("DMD")
	int DMD = -1;             //危化品标志
	int plateFlag = -1;       //号牌标志
	int mainDriverFlag = -1;    //主驾驶员检测标志
	int secondDriverFlag = -1;  //副驾驶员检测标志
	int mainDriverBeltFlag = -1;   // 主驾驶员安全带检测标志 0 未系, 1 已系, 默认 -1 未知
	int secondDriverBeltFlag = -1; // 副驾驶员安全带检测标志 0 未系, 1 已系, 默认 -1 未知
	int mainDriverPhoneFlag = 0;   // 主驾驶员打电话检测标志 0 未打电话, 1 打电话, 默认 0
	int secondDriverPhoneFlag = 0; // 副驾驶员打电话检测标志 0 未打电话, 1 打电话, 默认 0
	int mainSunVisorFlag = 0;      //  主遮阳板是否打开, 0 未打开，1 打开, 默认 0
	int secondSunVisorFlag = 0;   // 主遮阳板是否打开, 0 未打开，1 打开, 默认 0
	
	
	List<InnerDetail> faceDetails = new ArrayList<InnerDetail>();  // 细分特征
		
	public Rect getVehicleBox() {
		return vehicleBox;
	}

	public void setVehicleBox(Rect vehicleBox) {
		this.vehicleBox = vehicleBox;
	}

	public RecogBrand getRecogBrand() {
		return recogBrand;
	}

	public void setRecogBrand(RecogBrand recogBrand) {
		this.recogBrand = recogBrand;
	}

	public Rect getPlateBox() {
		return plateBox;
	}

	public void setPlateBox(Rect plateBox) {
		this.plateBox = plateBox;
	}

	public int getColorId() {
		return colorId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public float getProb() {
		return prob;
	}

	public void setProb(float prob) {
		this.prob = prob;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public int getPlateColor() {
		return plateColor;
	}

	public void setPlateColor(int plateColor) {
		this.plateColor = plateColor;
	}

	public String getPlateColorName() {
		return plateColorName;
	}

	public void setPlateColorName(String plateColorName) {
		this.plateColorName = plateColorName;
	}

	public int getDMD() {
		return DMD;
	}

	public void setDMD(int dMD) {
		this.DMD = dMD;
	}

	public int getPlateFlag() {
		return plateFlag;
	}

	public void setPlateFlag(int plateFlag) {
		this.plateFlag = plateFlag;
	}

	public int getMainDriverFlag() {
		return mainDriverFlag;
	}

	public void setMainDriverFlag(int mainDriverFlag) {
		this.mainDriverFlag = mainDriverFlag;
	}

	public int getSecondDriverFlag() {
		return secondDriverFlag;
	}

	public void setSecondDriverFlag(int secondDriverFlag) {
		this.secondDriverFlag = secondDriverFlag;
	}

	public int getMainDriverBeltFlag() {
		return mainDriverBeltFlag;
	}

	public void setMainDriverBeltFlag(int mainDriverBeltFlag) {
		this.mainDriverBeltFlag = mainDriverBeltFlag;
	}

	public int getSecondDriverBeltFlag() {
		return secondDriverBeltFlag;
	}

	public void setSecondDriverBeltFlag(int secondDriverBeltFlag) {
		this.secondDriverBeltFlag = secondDriverBeltFlag;
	}

	public int getMainDriverPhoneFlag() {
		return mainDriverPhoneFlag;
	}

	public void setMainDriverPhoneFlag(int mainDriverPhoneFlag) {
		this.mainDriverPhoneFlag = mainDriverPhoneFlag;
	}

	public int getSecondDriverPhoneFlag() {
		return secondDriverPhoneFlag;
	}

	public void setSecondDriverPhoneFlag(int secondDriverPhoneFlag) {
		this.secondDriverPhoneFlag = secondDriverPhoneFlag;
	}

	public int getMainSunVisorFlag() {
		return mainSunVisorFlag;
	}

	public void setMainSunVisorFlag(int mainSunVisorFlag) {
		this.mainSunVisorFlag = mainSunVisorFlag;
	}

	public int getSecondSunVisorFlag() {
		return secondSunVisorFlag;
	}

	public void setSecondSunVisorFlag(int secondSunVisorFlag) {
		this.secondSunVisorFlag = secondSunVisorFlag;
	}

	public List<InnerDetail> getFaceDetails() {
		return faceDetails;
	}

	public void setFaceDetails(List<InnerDetail> faceDetails) {
		this.faceDetails = faceDetails;
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
