package inf101v22.grid;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import inf101v22.tetris.model.piece.PieceShape;

/**
 * Testing the class PieceShape
 * 
 * @author Jasmine Næss
 */
public class PieceShapeTest {
	PieceShape t = PieceShape.T;
	PieceShape s = PieceShape.S;
	PieceShape z = PieceShape.Z;
	PieceShape i = PieceShape.I;
	PieceShape j = PieceShape.J;
	PieceShape l = PieceShape.L;
	PieceShape o = PieceShape.O;

	@Test
	void testTileCharacter() {
		assertEquals('t', t.getTile().getCharacter());
		assertEquals('s', s.getTile().getCharacter());
		assertEquals('z', z.getTile().getCharacter());
		assertEquals('i', i.getTile().getCharacter());
		assertEquals('j', j.getTile().getCharacter());
		assertEquals('l', l.getTile().getCharacter());
		assertEquals('o', o.getTile().getCharacter());
	}
	
	@Test
	void testHeight() {
		assertEquals(2, t.getHeight());
		assertEquals(1, i.getHeight());
	}
	
	@Test
	void testWidth() {
		assertEquals(2, o.getWidth());
		assertEquals(3, j.getWidth());
		assertEquals(4, i.getWidth());
	}
	
}
