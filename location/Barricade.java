package location;

import application.Point;

public class Barricade extends Location {

	public Barricade(String name, Point point) {
		super(name, point);
	}

	@Override
	public void operation(Player player) {
		// do nothing
	}

}
