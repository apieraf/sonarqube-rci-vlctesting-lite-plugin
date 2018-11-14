package es.vlctesting.lite.rci.utils;

public class RciRatings {
	private final int a;
	private final int b;
	private final int c;
	private final int d;

	public RciRatings(int a, int b, int c, int d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	public int getRating(double rci) {
		int result = 5;

		if (rci >= a) {
			result = 1;
		} else if (rci >= b) {
			result = 2;
		} else if (rci >= c) {
			result = 3;
		} else if (rci >= d) {
			result = 4;
		}

		return result;
	}

}
