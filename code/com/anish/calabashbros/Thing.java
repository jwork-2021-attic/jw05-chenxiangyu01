package com.anish.calabashbros;

import java.awt.Color;
import java.io.*;
public class Thing implements Serializable{
    private static final long serialVersionUID = 1016524813918100547L; 
    protected World world;

    public Tile<? extends Thing> tile;

    public int getX() {
        return this.tile.getxPos();
    }

    public int getY() {
        return this.tile.getyPos();
    }

    public void setTile(Tile<? extends Thing> tile) {
        this.tile = tile;
    }

    Thing(Color color, char glyph, World world) {
        this.color = color;
        this.glyph = glyph;
        this.world = world;
    }
    Thing(){
        this.color=null;
        this.glyph = (char)176;
        this.world = null;
    }
    private final Color color;

    public Color getColor() {
        return this.color;
    }

    private final char glyph;

    public char getGlyph() {
        return this.glyph;
    }

}
