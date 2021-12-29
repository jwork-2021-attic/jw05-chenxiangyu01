package com.anish.calabashbros;

import asciiPanel.AsciiPanel;
import java.io.*;
public class Floor2 extends Thing implements Serializable{
    private static final long serialVersionUID = 1L; 
    public Floor2(World world) {
        super(AsciiPanel.yellow, (char) 177, world);
    }

}
