package com.anish.calabashbros;
import java.io.*;
import java.awt.Color;
import asciiPanel.AsciiPanel;

public class Creature extends Thing implements Serializable{
    private static final long serialVersionUID = 1L;
    Creature(){}
    
    Creature(Color color, char glyph, World world) {
        super(color, glyph, world);
    }

    public void moveTo(int xPos, int yPos) {
        this.world.put(this, xPos, yPos);
    }

}
