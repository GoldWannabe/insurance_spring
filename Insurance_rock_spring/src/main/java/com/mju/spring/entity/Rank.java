package com.mju.spring.entity;

public class Rank {
	private String rankID;
	private EMaterial material;
	private double fireFacilities;
	private boolean height;
	private int scale;
	private double surroundingFacilities;
	private EPurpose purpose;
	
	private enum EMaterial {
		wood, rock, concrete, iron, brick
	};

	private enum EPurpose {
		living, factory, culturalAsset, store, office, carPark, warehouse
	};

	public String getRankID() {
		return this.rankID;
	}

	public void setRankID(String rankID) {
		this.rankID = rankID;
	}

	public double getFireFacilities() {
		return fireFacilities;
	}

	public void setFireFacilities(double fireFacilities) {
		this.fireFacilities = fireFacilities;
	}

	public boolean isHeight() {
		return height;
	}

	public void setHeight(boolean height) {
		this.height = height;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public double getSurroundingFacilities() {
		return surroundingFacilities;
	}

	public void setSurroundingFacilities(double surroundingFacilities) {
		this.surroundingFacilities = surroundingFacilities;
	}

	public String getMaterial() {
		return this.material.toString();
	}

	public void setMaterial(String material) {
		//jsp에서 1부터 return, 배열은 0부터 시작함
			this.material = EMaterial.valueOf(material);
		
	}

	public String getPurpose() {
		return this.purpose.toString();
	}

	public void setPurpose(String purpose) {
			this.purpose = EPurpose.valueOf(purpose);
	}
	
	
}