package com.anish.calabashbros;

import asciiPanel.AsciiPanel;

public class  Letter extends Thing {

    public Letter(World world,int ty) {
        super(AsciiPanel.brightCyan, (char) ty, world);
    }

}
