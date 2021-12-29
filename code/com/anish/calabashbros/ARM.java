package com.anish.calabashbros;

import java.awt.Color;
import java.io.*;



public class ARM extends Creature implements Serializable{
    private static final long serialVersionUID = 1L; 
    private int index;
    private int health;
    private int attack;
    private int defence;
    private int speed;
    private int range;
    private int xPo;
    private int yPo;
    private int isMove;
    public ARM(int ty,Color color, World world,int []property,int []Po,int in) {
        super(color, (char) ty, world);
        health=property[0];
        attack=property[1];
        defence=property[2];
        speed=property[3];
        range=property[4];
        xPo=Po[0];
        yPo=Po[1];
        isMove=1;
        index=in;
    }
    //受到攻击
    public void beAttacked(int at){
        health=health+defence-at;
        if(defence>0){
            defence--;
        }
    }
    //施加攻击
    public int attacking(){
        return attack;
    }
    //移动
    public void move(int c){
        if(speed==0) return;
        if(isMove==1){
            if(index==1){
                if(yPo>6&&c%speed==0)
                    yPo--;
            }
            else{
                if(yPo<33&&c%speed==0)
                    yPo++;
            }
        }
    }

    public int getX(){
        return xPo;
    }

    public int getY(){
        return yPo;
    }
    //判断死亡
    public boolean isDead(){
        if(health<=0)
            return true;
        else return false;
    }
    //索敌
    public void findAnemy(ARM []a1){//有敌人，置isMove为0
        int k;
        if(index==1) k=1;
        else k=-1;
        int i=0;
        for(;i<a1.length;i++){
            if(a1[i]==null||index==a1[i].index) continue;
            for(int j=0;j<range;j++){
                if(a1[i].equal(xPo, yPo-j*k)==true||a1[i].equal(xPo+1, yPo-j*k)==true||a1[i].equal(xPo-1, yPo-j*k)==true){
                    isMove=0;
                    a1[i].beAttacked(attack);
                    break;
                }
            }
            if(a1[i].equal(xPo, yPo-range*k)==true){
                isMove=0;
                a1[i].beAttacked(attack);
                break;   
            }
        }
        if(i==a1.length)
            isMove=1;
    }
    //匹配
    public boolean equal(int x,int y){
        if(x==xPo&&y==yPo)
            return true;
        else return false;
    }
    //
    public int getMove(){
        return isMove;
    }
    public int getIndex(){
        return index;
    }

    private void writeObject(ObjectOutputStream oos)  throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(this);
    }
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        ARM k=(ARM)ois.readObject();
        
    }
}