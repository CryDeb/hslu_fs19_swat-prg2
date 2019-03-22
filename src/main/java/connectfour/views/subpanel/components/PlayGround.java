package connectfour.views.subpanel.components;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Arc2D;
import javax.swing.*;

public class PlayGround extends JComponent{
    private static final int Y_SIZE =200;
    private static final int X_SIZE =250;
    private static final double GAP_SIZE_PERCENTAGE =0.02;
    private int currentLocation;
    private int xCount;
    private int yCount;
    private Color backgroundColor;
    private Color playerColor;
    private Color opponentColor;
    private int spaceX;
    private int spaceY;
    private int diskSize;
    private boolean mouseEventEnabled;
    private int currentPlayer;
    private double gap;
    private DiskField[][] diskFields;

    public PlayGround(int rowCount, int columnCount){
        this(new Dimension(X_SIZE, Y_SIZE), rowCount, columnCount, GAP_SIZE_PERCENTAGE);
    }

    private PlayGround(Dimension size, int xCount, int yCount, double gapPercent){
        this.initialize(size, xCount, yCount, gapPercent);
    }

    private void initialize(Dimension size, int xCount, int yCount, double gapPercent){
        gap = gapPercent;
        this.xCount = xCount;
        this.yCount = yCount;
        spaceX = (int) (size.width * gapPercent);
        diskSize = (size.width - ((xCount+1)*this.spaceX))/xCount;
        spaceY = (size.height-(this.diskSize*yCount)) /(yCount+1);
        spaceY = (this.spaceY<0)?2:this.spaceY;
        mouseEventEnabled = true;
        currentPlayer = 0;
        size.width = (this.spaceX*(xCount+1))+(this.diskSize*xCount);
        size.height = (this.spaceY*(yCount+1))+(this.diskSize*yCount)+(this.diskSize/2);
        setPreferredSize(size);
        currentLocation = 0;
        diskFields = new DiskField[xCount][yCount];
        start();
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e){
                mouseMovedInComponent(e);
            }        
        });
        initPlayGroundColors();
    }

    private void initPlayGroundColors(){
        backgroundColor = new Color(51, 153, 255);
        playerColor = new Color(255,255,51);
        opponentColor = new Color(255,51,51);
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        if(this.isEnabled()){
            graphics.setColor(currentPlayer==0 ? playerColor : opponentColor);
            ((Graphics2D) (graphics)).fill(getArc2DPie());
        }
        
        graphics.setColor(this.backgroundColor);
        graphics.fillRect(0,this.diskSize/2, this.getWidth(), this.getHeight()-(this.diskSize/2));
        int x=this.spaceX;
        for(DiskField[] disks : this.diskFields){
            int y=this.spaceY+this.diskSize/2;
            for(DiskField disk:disks){
                disk.draw(x, y, graphics);
                y+=this.spaceY+this.diskSize;
            }
            x+=this.spaceX+this.diskSize;
        }
    }

    private Arc2D getArc2DPie(){
        return new Arc2D.Double((double) currentLocation * (spaceX + diskSize) + (spaceX), 0, this.diskSize, this.diskSize, 0, 180, Arc2D.PIE);
    }

    private void mouseMovedInComponent(MouseEvent event){
        int loc = calcLocation(event.getX());
        if(this.currentLocation != loc  && this.mouseEventEnabled && this.isEnabled()){
            this.currentLocation = loc;
            this.repaint(0, 0, this.getWidth(),this.diskSize/2);
        }
    }

    private int calcLocation(int x){
        int ret = (x/(this.diskSize+this.spaceX));
        if(ret >= this.xCount)
            return ret-1;
        return ret;
    }

    public int getColumn(){
        if(this.isEnabled())
            return this.currentLocation;
        else
            return -1;
    }

    private void playerDiskPlayed(int x, int y){
        this.diskFields[x][y].diskPlayed(this.playerColor);
        repaint();
    }

    private void opponentDiskPlayed(int x, int y){
        this.diskFields[x][y].diskPlayed(this.opponentColor);
        repaint();
    }

    private void start() {
        for(int i=0;i<xCount;i++){
            for(int i2=0; i2<yCount;i2++){
                this.diskFields[i][i2] = new DiskField(this.diskSize);
            }
        }
    }

    public void start(int x, int y){
        this.initialize(this.getSize(), x, y, this.gap);
    }

    public void playerDiskPlayedUpsideDown(int x, int y) {
        this.playerDiskPlayed(x, (this.yCount-1)-y);
    }

    public void opponentDiskPlayedUpsideDown(int x, int y) {
       this.opponentDiskPlayed(x, (this.yCount-1)-y);
    }

    public boolean notFull() {
        return (!this.diskFields[this.currentLocation][0].isPlayed()) && this.isEnabled();
    }

    @Override
    public Dimension getSize(){
        return new Dimension((xCount+1)*spaceX+xCount*diskSize, (yCount+1)*spaceY+yCount*diskSize);
    }
}
    
