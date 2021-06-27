package com.sidisl16.geometry2D.lines;

import com.sidisl16.geometry2D.lines.models.Line;
import com.sidisl16.geometry2D.lines.models.Point2D;
import com.sidisl16.geometry2D.lines.models.SlopeYintercept;

/**
 * 
 * The class {@code Lines} contains methods for performing basic geometric
 * operations on line in Cartesian coordinate system (2D plane) such to find
 * distance, slope, intercept, incident point and to check perpendicular,
 * parallel of two lines.
 * 
 * @author siddharth.prasad
 *
 */
public final class Lines {

	/**
	 * Making constructor private to restrict unnecessary creation of object as all
	 * methods are static
	 */
	private Lines() {
	}

	/**
	 * 
	 * Takes the two arguments as point coordinates {@code Point2D}, and returns the
	 * calculated distance between them in 2D plane.
	 * 
	 * @param a {@code Point2D} first coordinate of the line
	 * @param b {@code Point2D} second coordinate of the line
	 * @return distance between coordinate a and b
	 * @throws IllegalArgumentException if a or b is null
	 */
	public static double getDistance(Point2D a, Point2D b) throws IllegalArgumentException {
		checkObjectNullable(a, b);
		double xdiff = b.getX() - a.getX();
		double ydiff = b.getY() - a.getY();
		return Math.sqrt(xdiff * xdiff + ydiff * ydiff);
	}

	/**
	 * 
	 * The method returns the slope and y-intercept of the line connect the points
	 * in the argument.
	 * <p>
	 * To find slope(pa(x1,y1), pb(x2,y2)), m = (y2-y1)/(x2-x1)
	 * </p>
	 * <p>
	 * To find y-intercept, y = mx+b, to find use b = y - mx
	 * </p>
	 * 
	 * @param pa {@code Point2D} first coordinate of the line
	 * @param pb {@code Point2D} second coordinate of the line
	 * @return {@code SlopeYintercept} contains slope and y-intercept
	 * @throws IllegalArgumentException if pa or pb is null
	 */
	public static SlopeYintercept getSlopeIntercept(Point2D pa, Point2D pb) throws IllegalArgumentException {
		checkObjectNullable(pa, pb);

		double x1 = pa.getX();
		double y1 = pa.getY();

		double x2 = pb.getX();
		double y2 = pb.getY();

		double xdiff = x2 - x1;
		double ydiff = y2 - y1;

		// if Line is parallel to y axis, then y intercept will be infinity, return
		// Double.MAX_VALUE
		if (xdiff == 0) {
			return new SlopeYintercept(0.0, Double.MAX_VALUE);
		}

		// if line is parallel to x axis
		if (ydiff == 0) {
			return new SlopeYintercept(0.0, y1);
		}

		double m = ydiff / xdiff;

		// find b from y - intercept using formula y = mx + b, we can pick any of the
		// point, in our case picking point a(x,y),
		// to find b the formula becomes b = y - mx;
		double b = y1 - m * x1;

		// find y - intercept using formula y = mx + b
		double y = (m * x1) + b;

		return new SlopeYintercept(m, y);
	}

	/**
	 * The overloaded method returns the slope and y-intercept of the line connect
	 * the points in the argument.
	 * <p>
	 * To find slope(pa(x1,y1), pb(x2,y2)), m = (y2-y1)/(x2-x1)
	 * </p>
	 * <p>
	 * To find y-intercept, y = mx+b, to find use b = y - mx
	 * </p>
	 * 
	 * @param l {@code Line} contains two points {@code Point2D} start and end of
	 *          the line
	 * @return {@code SlopeYintercept} contains slope and y-intercept
	 * @throws IllegalArgumentException
	 */
	public static SlopeYintercept getSlopeIntercept(Line l) throws IllegalArgumentException {
		checkObjectNullable(l);
		return getSlopeIntercept(l.getStart(), l.getEnd());
	}

	/**
	 * The method checks the if the lines in the argument are parallel to each other
	 * 
	 * <p>
	 * if determinant (d) is 0 then lines are parallel to find determinant of
	 * l1(start(x1,y1), end(x2,y2)) and l2 l1(start(x3,y3), end(x4,y4))
	 * </p>
	 * <p>
	 * d = (y2-y1) * (x4-x3) / (y4-y3) * (x2-x1)
	 * </p>
	 * 
	 * @param l1 {@code Line} contains two points {@code Point2D} start and end of
	 *           the line 1
	 * @param l2 {@code Line} contains two points {@code Point2D} start and end of
	 *           the line 2
	 * @return true if the lines are parallel or false
	 * @throws IllegalArgumentException if any of the lines and its points are null
	 */
	public static boolean isParallel(Line l1, Line l2) throws IllegalArgumentException {
		checkObjectNullable(l1, l2);
		checkObjectNullable(l1.getStart(), l1.getEnd(), l2.getStart(), l2.getEnd());

		double a1 = l1.getEnd().getY() - l1.getStart().getY();
		double b1 = l1.getEnd().getX() - l1.getStart().getX();

		double a2 = l2.getEnd().getY() - l2.getStart().getY();
		double b2 = l2.getEnd().getX() - l2.getStart().getX();

		double determinant = a1 * b2 - a2 * b1;

		return determinant == 0;
	}

	/**
	 * The method checks if the two lines in the argument are perpendicular
	 * 
	 * <p>
	 * To check, this method calculates the slope of the two lines and if their
	 * product is -1, then the lines are perpendicular to each other
	 * </p>
	 * 
	 * <p>
	 * To find slope(start(x1,y1), end(x2,y2)) for a line, m = (y2-y1)/(x2-x1)
	 * </p>
	 * 
	 * @param l1 {@code Line} contains two points {@code Point2D} start and end of
	 *           the line 1
	 * @param l2 {@code Line} contains two points {@code Point2D} start and end of
	 *           the line 2
	 * @return true if the lines are perpendicular or false
	 * @throws IllegalArgumentException if any of the lines and its points are null
	 * 
	 */
	public static boolean isPerpendicular(Line l1, Line l2) throws IllegalArgumentException {
		checkObjectNullable(l1, l2);

		checkObjectNullable(l1.getStart(), l1.getEnd(), l2.getStart(), l2.getEnd());

		// start and end points for line 1
		double x1 = l1.getStart().getX();
		double y1 = l1.getStart().getY();
		double x2 = l1.getEnd().getX();
		double y2 = l1.getEnd().getY();

		// start and end points for line 2
		double x3 = l2.getStart().getX();
		double y3 = l2.getStart().getY();
		double x4 = l2.getEnd().getX();
		double y4 = l2.getEnd().getY();

		double x1diff = x2 - x1;
		double x2diff = x4 - x3;

		double y1diff = y2 - y1;
		double y2diff = y4 - y3;
		double m1, m2;

		// Both lines have infinite slope
		if (x1diff == 0 && x2diff == 0) {
			return false;
		}

		// only line 1 has infinite slope
		if (x2 - x1 == 0) {
			m2 = (y4 - y3) / (x4 - x3);
			if (m2 == 0)
				return true;
			else
				return false;
		}

		// only line 2 has infinite slope
		if (x4 - x3 == 0) {
			m1 = (y2 - y1) / (x2 - x1);
			if (m1 == 0)
				return true;
			else
				return false;
		}

		m1 = y1diff / x1diff;
		m2 = y2diff / x2diff;

		return m1 * m2 == -1;
	}

	/**
	 * The method returns the point at which the lines in the argument are
	 * intersecting each other
	 * 
	 * <p>
	 * Lets assume, equation for l1 a1x + b1y = c1 <br>
	 * and equation for l1 a2x + b2y = c2
	 * </p>
	 * <p>
	 * To find determinant of l1(start(x1,y1), end(x2,y2)) and l2 l1(start(x3,y3),
	 * end(x4,y4))
	 * </p>
	 * <p>
	 * d = (y2-y1) * (x4-x3) / (y4-y3) * (x2-x1)
	 * </p>
	 * To find intersecting point p(x,y), x = (b2 * c1 - b1 * c2) / determinant <br>
	 * y = (a1 * c2 - a2 * c1) / determinant
	 * </p>
	 * 
	 * @param l1 {@code Line} contains two points {@code Point2D} start and end of
	 *           the line 1
	 * @param l2 {@code Line} contains two points {@code Point2D} start and end of
	 *           the line 2
	 * @return {@code Point2D} point of intersection
	 * @throws IllegalArgumentException if any of the lines and its points are null
	 */
	public static Point2D getIncidentPoint(Line l1, Line l2) throws IllegalArgumentException {
		checkObjectNullable(l1, l2);

		checkObjectNullable(l1.getStart(), l1.getEnd(), l2.getStart(), l2.getEnd());

		// equation for l1 a1x + b1y = c1
		double a1 = l1.getEnd().getY() - l1.getStart().getY();
		double b1 = l1.getStart().getX() - l1.getEnd().getX();
		double c1 = a1 * (l1.getStart().getX()) + b1 * (l1.getStart().getY());

		// equation for l1 a2x + b2y = c2
		double a2 = l2.getEnd().getY() - l2.getStart().getY();
		double b2 = l2.getStart().getX() - l2.getEnd().getX();
		double c2 = a2 * (l2.getStart().getX()) + b2 * (l2.getStart().getY());

		double determinant = a1 * b2 - a2 * b1;

		if (determinant == 0) {
			// The lines are parallel, return Double.MAX_VALUE for p(x,y)
			return new Point2D(Double.MAX_VALUE, Double.MAX_VALUE);
		}

		double x = (b2 * c1 - b1 * c2) / determinant;
		double y = (a1 * c2 - a2 * c1) / determinant;
		return new Point2D(x, y);
	}

	/**
	 * The method accepts varags and checks if any of the object is null then it
	 * will throw IllegalArgumentException
	 * 
	 */
	private static void checkObjectNullable(Object... objects) throws IllegalArgumentException {
		for (Object obj : objects) {
			if (obj == null) {
				throw new IllegalArgumentException();
			}
		}
	}
}