package location;

public enum Direction {
	cw, ccw; // clockwise,anticlockwise

	public static String toDirection(Direction direction) {
		String re = "";
		switch (direction) {
		case cw:
			re = "clockwise";
			break;
		case ccw:
			re = "counterclockwise";
			break;
		}
		return re;
	}

	public static Direction negative(Direction direction) {
		switch (direction) {
		case cw:
			return ccw;
		case ccw:
			return cw;
		default:
			return cw;// useless
		}
	}

}
