package connectfour.views.subpanel.components;

import java.awt.Color;
import java.awt.Graphics;

class DiskField {
    private int diameter;
    private Color color;
    private boolean played;

    public DiskField(int diameter){
        this.diameter = diameter;
        played = false;
    }

    private void diskPlayed(){
        played = true;
    }
    private void setColor(Color color){
        this.color = color;
    }
    public void diskPlayed(Color color){
        diskPlayed();
        setColor(color);
    }
    public boolean isPlayed(){
        return played;
    }
    
    public void draw(int x, int y, Graphics g){
        if(played){
            g.setColor(color);
        }else{
            g.setColor(Color.WHITE);
        }
        g.fillOval(x, y, diameter, diameter);
    }
}
