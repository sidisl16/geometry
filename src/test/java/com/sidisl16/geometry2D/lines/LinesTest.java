package com.sidisl16.geometry2D.lines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.sidisl16.geometry2D.lines.models.Line;
import com.sidisl16.geometry2D.lines.models.Point2D;
import com.sidisl16.geometry2D.lines.models.SlopeYintercept;

public class LinesTest {

	@Test
	public void testGetDistanceInt() {
		double actualDist = Lines.getDistance(new Point2D(1, 4), new Point2D(-2, 3));
		assertEquals(3.1622776601683795, actualDist);
	}

	@Test
	public void testGetDistanceZero() {
		double actualDist = Lines.getDistance(new Point2D(1, 4), new Point2D(1, 4));
		assertEquals(0.0, actualDist);
	}

	@Test
	public void testGetDistanceFloat() {
		double actualDist = Lines.getDistance(new Point2D(1.04f, 4.22f), new Point2D(-2.2f, 3.03f));
		assertEquals(3.4516227532200854, actualDist);
	}

	@Test
	public void testGetDistanceDouble() {
		double actualDist = Lines.getDistance(new Point2D(3.04d, 4.22d), new Point2D(-2.2d, -3.03d));
		assertEquals(8.945395463589074, actualDist);
	}

	@Test
	public void testGetYintercept() {
		SlopeYintercept expected = new SlopeYintercept(0.3333333333333333, 4.0);
		SlopeYintercept actual = Lines.getSlopeIntercept(new Line(new Point2D(1, 4), new Point2D(-2, 3)));
		assertEquals(expected, actual);
	}

	@Test
	public void testGetSlopeInterceptLineParallelToYaxis() {
		SlopeYintercept expected = new SlopeYintercept(0.0, Double.MAX_VALUE);
		SlopeYintercept actual = Lines.getSlopeIntercept(new Line(new Point2D(1, 3), new Point2D(1, -2)));
		assertEquals(expected, actual);
	}

	@Test
	public void testGetSlopeInterceptLineParallelToXaxis() {
		SlopeYintercept expected = new SlopeYintercept(0.0, 3);
		SlopeYintercept actual = Lines.getSlopeIntercept(new Line(new Point2D(-2, 3), new Point2D(1, 3)));
		assertEquals(expected, actual);
	}

	@Test
	public void testIsLinesParallel() {
		boolean actual = Lines.isParallel(new Line(new Point2D(1, 3), new Point2D(1, -2)),
				new Line(new Point2D(3, 3), new Point2D(3, -2)));
		assertTrue(actual);
	}

	@Test
	public void testIsLinesNotParallel() {
		boolean actual = Lines.isParallel(new Line(new Point2D(-2, 3), new Point2D(1, -2)),
				new Line(new Point2D(3, 3), new Point2D(3, -2)));
		assertFalse(actual);
	}

	@Test
	public void testIsLinePerpendicular() {
		boolean actual = Lines.isPerpendicular(new Line(new Point2D(-1, -3), new Point2D(-3, -1)),
				new Line(new Point2D(0, 0), new Point2D(-2, -2)));
		assertTrue(actual);
	}

	@Test
	public void testIsLinePerpendicularBothLinesInfiniteSlope() {
		boolean actual = Lines.isPerpendicular(new Line(new Point2D(1, 3), new Point2D(1, -2)),
				new Line(new Point2D(3, 3), new Point2D(3, -2)));
		assertFalse(actual);
	}

	@Test
	public void testIsLinePerpendicularFirstLineInfiniteSlope() {
		boolean actual = Lines.isPerpendicular(new Line(new Point2D(0, 4), new Point2D(0, -9)),
				new Line(new Point2D(2, 0), new Point2D(-1, 0)));
		assertTrue(actual);
	}

	@Test
	public void testIsLinePerpendicularSecondtLineInfiniteSlope() {
		boolean actual = Lines.isPerpendicular(new Line(new Point2D(-2, 1), new Point2D(3, 1)),
				new Line(new Point2D(-1, -1), new Point2D(-1, 4)));
		assertTrue(actual);
	}

	@Test
	public void testIsLineNotPerpendicular() {
		boolean actual = Lines.isPerpendicular(new Line(new Point2D(-2, 3), new Point2D(1, -2)),
				new Line(new Point2D(3, 3), new Point2D(3, -2)));
		assertFalse(actual);
	}

	@Test
	public void testIsLineNotPerpendicularInfinitySlopes() {
		boolean actual = Lines.isPerpendicular(new Line(new Point2D(-2, 3), new Point2D(1, -2)),
				new Line(new Point2D(3, 3), new Point2D(3, -2)));
		assertFalse(actual);
	}

	@Test
	public void testGetIncidentPoint() {
		Point2D expected = new Point2D(2.4, 2.4);
		Point2D actual = Lines.getIncidentPoint(new Line(new Point2D(1, 1), new Point2D(4, 4)),
				new Line(new Point2D(1, 8), new Point2D(2, 4)));
		assertEquals(expected, actual);
	}

	@Test
	public void testGetIncidentPointForParallelLines() {
		Point2D expected = new Point2D(Double.MAX_VALUE, Double.MAX_VALUE);
		Point2D actual = Lines.getIncidentPoint(new Line(new Point2D(1, 3), new Point2D(1, -2)),
				new Line(new Point2D(3, 3), new Point2D(3, -2)));
		assertEquals(expected, actual);
	}

	@Test
	public void testGetDistanceException() {
		assertThrows(IllegalArgumentException.class, () -> Lines.getDistance(null, null));
	}

	@Test
	public void testGetSlopeInterceptException() {
		assertThrows(IllegalArgumentException.class, () -> Lines.getSlopeIntercept(null));
	}

	@Test
	public void testGetSlopeInterceptPointsException() {
		assertThrows(IllegalArgumentException.class, () -> Lines.getSlopeIntercept(null, null));
	}

	@Test
	public void testIsParallelException() {
		assertThrows(IllegalArgumentException.class, () -> Lines.isParallel(null, null));
	}

	@Test
	public void testIsParallelNullPointException() {
		assertThrows(IllegalArgumentException.class,
				() -> Lines.isParallel(new Line(null, null), new Line(null, null)));
	}

	@Test
	public void testIsPerpendicularException() {
		assertThrows(IllegalArgumentException.class, () -> Lines.isPerpendicular(null, null));
	}

	@Test
	public void testIsPerpendicularNullPointException() {
		assertThrows(IllegalArgumentException.class,
				() -> Lines.isPerpendicular(new Line(null, null), new Line(null, null)));
	}

	@Test
	public void testGetIncidentPointException() {
		assertThrows(IllegalArgumentException.class, () -> Lines.getIncidentPoint(null, null));
	}

	@Test
	public void testGetIncidentPointNullPointException() {
		assertThrows(IllegalArgumentException.class,
				() -> Lines.getIncidentPoint(new Line(null, null), new Line(null, null)));
	}
}