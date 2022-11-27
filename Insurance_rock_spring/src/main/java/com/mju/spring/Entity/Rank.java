package com.mju.spring.Entity;

import java.util.UUID;

public class Rank {
	private String rankID;
	private EMaterial material;
	private double fireFacilities;
	private boolean height;
	private int scale;
	private double surroundingFacilities;

	private enum EMaterial {
		wood, rock, concrete, iron, brick
	};

	private enum EPurpose {
		living, factory, culturalAsset, store, office, carPark, warehouse
	};
	
	public Rank() {
		this.rankID = UUID.randomUUID().toString();
	}

	private EPurpose purpose;

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
		if (material.equals(EMaterial.rock.toString())) {
			this.material = EMaterial.rock;
		} else if (material.equals(EMaterial.wood.toString())) {
			this.material = EMaterial.wood;
		} else if (material.equals(EMaterial.concrete.toString())) {
			this.material = EMaterial.concrete;
		} else if (material.equals(EMaterial.iron.toString())) {
			this.material = EMaterial.iron;
		} else if (material.equals(EMaterial.brick.toString())) {
			this.material = EMaterial.brick;
		}
	}

	public String getPurpose() {
		return this.purpose.toString();
	}

	public void setPurpose(String purpose) {
		if (purpose.equals(EPurpose.living.toString())) {
			this.purpose = EPurpose.living;
		} else if (purpose.equals(EPurpose.factory.toString())) {
			this.purpose = EPurpose.factory;
		} else if (purpose.equals(EPurpose.culturalAsset.toString())) {
			this.purpose = EPurpose.culturalAsset;
		} else if (purpose.equals(EPurpose.store.toString())) {
			this.purpose = EPurpose.store;
		} else if (purpose.equals(EPurpose.office.toString())) {
			this.purpose = EPurpose.office;
		} else if (purpose.equals(EPurpose.carPark.toString())) {
			this.purpose = EPurpose.carPark;
		} else if (purpose.equals(EPurpose.warehouse.toString())) {
			this.purpose = EPurpose.warehouse;
		}

	}
}