package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.World;
import com.anish.calabashbros.MazeGenerator;
import com.anish.calabashbros.Thing;
import com.anish.calabashbros.Wall;
import com.anish.calabashbros.Floor;
import com.anish.calabashbros.Floor2;
import com.anish.calabashbros.Floor3;
import com.anish.calabashbros.Floor4;
import com.anish.calabashbros.Letter;
import com.anish.calabashbros.Arm;
import java.util.Random;
import com.anish.calabashbros.Map;
import asciiPanel.AsciiPanel;
public class WorldScreen implements Screen{
    int over=1;
    int ct1=0;
    int ct2=0;
    int count=0;
    private World world;
    Color []c={AsciiPanel.yellow,AsciiPanel.blue,AsciiPanel.cyan,AsciiPanel.red};
    int [][]a={
                        {3,1,2,2,1},
                        {10,1,2,4,1},
                        {3,2,2,2,1},
                        {3,1,2,1,2}};
    int []w={20,0,0,0,0};
    int [][]b={{3,31},{4,31},{25,31},{26,31},{3,8},{4,8},{25,8},{26,8}};
    int [][]b1={{3,6},{4,6},{25,6},{26,6},{3,7},{4,7},{25,7},{26,7}};
    int [][]b2={{3,33},{4,33},{25,33},{26,33},{3,32},{4,32},{25,32},{26,32}};
    Arm []t=new Arm[1000];
    int num=0;
    String[] sortSteps;
    Color []monster =new Color[256];
    int []m=new int[1200];
    Wall wa=new Wall(world);
    Floor fo=new Floor(world);
    Floor2 fo2=new Floor2(world);
    Floor3 fo3=new Floor3(world);
    Floor4 fo4=new Floor4(world);
    Map map=new Map(); 
    String p="";
    Random r=new Random();
    public WorldScreen() {
        world = new World();
        for(int i=0;i<40;i++){
            for(int j=0;j<30;j++){
                switch(map.age[i][j]){
                    case 0:
                    world.put(fo, j, i);
                    break;
                    case 1:
                    world.put(fo2,j,i);
                    break;
                    case 2:
                    world.put(fo3, j, i);
                    break;
                    case 3:
                    world.put(fo4,j,i);
                    break;
                    case 4:
                    world.put(fo4, j, i);
                    break;
                }
            }
        }
        for(int k=0;k<8;k++){
            Arm s=new Arm(177, c[3], world, w, b1[k], 4);
            t[num++]=s;
        }
        for(int k=0;k<8;k++){
            Arm s=new Arm(177, c[3], world, w, b2[k], 3);
            t[num++]=s;
        }
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Calabash[] bros, String step) {
        String[] couple = step.split("<->");
        getBroByRank(bros, Integer.parseInt(couple[0])).swap(getBroByRank(bros, Integer.parseInt(couple[1])));
    }

    private Calabash getBroByRank(Calabash[] bros, int rank) {
        for (Calabash bro : bros) {
            if (bro.getRank() == rank) {
                return bro;
            }
        }
        return null;
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {
        if(ct1==8){
            paintOver(2);
            for(int i=0;i<7;i++){
                terminal.write(world.get(i, 0).getGlyph(), i, 0, world.get(i, 0).getColor());
            }
            over=0;
            
        }
        if(ct2==8){
            
             paintOver(1);
             for(int i=0;i<6;i++){
                terminal.write(world.get(i, 0).getGlyph(), i, 0, world.get(i, 0).getColor());
            }
            
           over=0;
        }
        if(over==1)
        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
        
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        int rt=r.nextInt(4);
        if(over==1)
        switch(key.getKeyCode()){

            case KeyEvent.VK_1:
                Arm s=new Arm(2,c[rt],world,a[rt],b[0],1);
                t[num++]=s;
                break;
            case KeyEvent.VK_2:
                Arm s1=new Arm(2,c[rt],world,a[rt],b[1],1);
                t[num++]=s1;
                break;
            case KeyEvent.VK_3:
                Arm s2=new Arm(2,c[rt],world,a[rt],b[2],1);
                t[num++]=s2;
                break;
            case KeyEvent.VK_4:
                Arm s3=new Arm(2,c[rt],world,a[rt],b[3],1);
                t[num++]=s3;
                break;
        }
        return this;
    }

    public Screen re(){
  
        int rt=r.nextInt(200);
        if(over==1)
        if(rt<5){
            int rs=r.nextInt(4);
            int rr=r.nextInt(4);
            Arm s=new Arm(2,c[rs],world,a[rs],b[4+rr],2);
            t[num++]=s;
        }
        count++;
        if(over==1)
        for(int i=0;i<num;i++){
            if(t[i]!=null&&t[i].isDead()==true){
                world.put(fo2, t[i].getX(), t[i].getY());
                if(t[i].getIndex()==3) ct1++;
                if(t[i].getIndex()==4) ct2++;
                t[i]=null;
            }
        }
        if(over==1)
        for(int i=0;i<num;i++){
            if(t[i]!=null){
                t[i].findAnemy(t);
                if(t[i].getMove()==1){
                    world.put(fo2, t[i].getX(), t[i].getY());
                    t[i].move(count);
                    world.put(t[i], t[i].getX(), t[i].getY());
            }
            }
        }
        return this;
    }
    
    public void paintOver(int a){
        Letter k1=new Letter(world, 89);//Y
        Letter k2=new Letter(world, 79);//O
        Letter k3=new Letter(world, 85);//U
        Letter k4=new Letter(world, 87);//W
        Letter k5=new Letter(world, 73);//I
        Letter k6=new Letter(world, 78);//N
        Letter k7=new Letter(world, 76);//L
        Letter k8=new Letter(world, 83);//s
        Letter k9=new Letter(world, 69);//e
        if(a==1){
            world.put(k1, 0, 0);
            world.put(k2, 1, 0);
            world.put(k3, 2, 0);
            world.put(k4, 3, 0);
            world.put(k5, 4, 0);
            world.put(k6, 5, 0);
        }
        else{
            world.put(k1, 0, 0);
            world.put(k2, 1, 0);
            world.put(k3, 2, 0);
            world.put(k7, 3, 0);
            world.put(k2, 4, 0);
            world.put(k8, 5, 0);
            world.put(k9, 6, 0);
        }
    }
}
