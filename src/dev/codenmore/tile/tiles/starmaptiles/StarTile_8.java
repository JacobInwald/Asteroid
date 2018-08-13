package dev.codenmore.tile.tiles.starmaptiles;

import java.awt.image.BufferedImage;

import dev.codenmore.tile.gfx.Assets;
import dev.codenmore.tile.tiles.Tile;

public class StarTile_8 extends Tile{

	public StarTile_8(int id) {
		super(Assets.starMap_Tile8, id);
	}

	public boolean isSolid() {
		return true;
	}
	
}
