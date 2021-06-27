package com.sidisl16.geometry2D.lines.models;

public class Point2D {

	private double x;
	private double y;

	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point2D(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public boolean equals(Object obj) {
		Point2D point = null;
		if (obj instanceof Point2D) {
			point = (Point2D) obj;
		} else {
			return false;
		}

		if (this.x != point.x || this.y != point.y) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Point2D [x=" + x + ", y=" + y + "]";
	}
}