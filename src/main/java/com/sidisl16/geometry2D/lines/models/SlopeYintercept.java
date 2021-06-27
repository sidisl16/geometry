package com.sidisl16.geometry2D.lines.models;

public class SlopeYintercept {

	private double slope;
	private double yIntercept;

	public SlopeYintercept(double slope, double yIntercept) {
		this.slope = slope;
		this.yIntercept = yIntercept;
	}

	public double getSlope() {
		return slope;
	}

	public double getyIntercept() {
		return yIntercept;
	}

	@Override
	public boolean equals(Object obj) {
		SlopeYintercept si = null;
		if (obj instanceof SlopeYintercept) {
			si = (SlopeYintercept) obj;
		} else {
			return false;
		}

		if (this.slope != si.slope || this.yIntercept != si.yIntercept) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "SlopeYintercept [slope=" + slope + ", yIntercept=" + yIntercept + "]";
	}
}