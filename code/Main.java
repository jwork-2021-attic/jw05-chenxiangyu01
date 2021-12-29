

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.anish.calabashbros.World;
import com.anish.screen.Screen;
import com.anish.screen.WorldScreen;

import java.io.*;
import asciiPanel.AsciiFont;
import asciiPanel.AsciiPanel;

public class Main extends JFrame implements KeyListener,Runnable{

    private AsciiPanel terminal;
    private Screen screen;

    public Main() {
        super();
        terminal = new AsciiPanel(World.WIDTH, World.HEIGHT, AsciiFont.TALRYTH_15_15);
        add(terminal);
        pack();
        screen = new WorldScreen();
        addKeyListener(this);
        repaint();
        
    }
    
    @Override
    public void repaint() {
        terminal.clear();
        screen.displayOutput(terminal);
        super.repaint();
    }
    private int exit=1;
    public void go(){
        //screen=screen.re();
        try{
        screen=screen.A();
        if(screen==null) exit=0;
        }catch(Exception a){}
        
        repaint();
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) { 
        //screen=screen.re();
        screen = screen.respondToUserInput(e);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public static void main(String[] args){
        
        Main app = new Main();
        Thread t=new Thread(app);
        t.start();
        
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }

    @Override
    public void run(){

        while(exit==1){
            try{
                Thread.sleep(200);
            }catch(Exception a){}
            go();
        }
    }
}
