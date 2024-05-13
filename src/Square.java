import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Square extends JFrame{
    public static void main(String[] args){
        Square square = new Square();
    }

    int xMin = 7;
    int xMax = 1544;
    int yMin = 27;
    int yMax = 824;
    int xAxis = xMax/2;
    int yAxis = yMax/2;
    int height = 100;
    int width = 100;
    int xFruit = (int) ((Math.random() * (xMax)) + xMin);
    int yFruit = (int) ((Math.random() * (yMax)) + yMin);

    public Square(){
        setTitle("SQUARE");//Fixar fram rutan som man spelar i
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addKeyListener(new KeyAdapter() {//Kollar om en knapp trycks på
            public void keyPressed(KeyEvent e) {
                int movementSpeed = 15;
                int keyCode = e.getKeyCode();

                if (keyCode == 40 && yAxis < yMax) {//ner
                    yAxis += movementSpeed;
                }
                if (keyCode == 39 && xAxis < xMax) {//höger
                    xAxis += movementSpeed;
                }
                if (keyCode == 38 && yAxis > yMin) {//upp
                    yAxis -= movementSpeed;
                }
                if (keyCode == 37 && xAxis > xMin) {//vänster
                    xAxis -= movementSpeed;
                }
                if(xAxis < xMin){ //kollar så man inte går ut ur spel planen
                    xAxis = xMin;
                }
                if(xAxis + width> xMax){
                    xAxis = xMax - width;
                }
                if(yAxis + height < yMin){
                    yAxis = yMin + height;
                }
                if(yAxis + height > yMax){
                    yAxis = yMax - height;
                }
                repaint();
                if((xAxis) <= (xFruit) && (xAxis + width) >= (xFruit + 25) && (yAxis) <= (yFruit) && (yAxis + height) >= (yFruit + 25)){//Kollar om du är i en frukt
                    width += 10;
                    height += 10;
                    xFruit = (int) (Math.random() * (xMax - 10));
                    yFruit = (int) (Math.random() * (yMax - 10));
                }
            }
        });
    }

    public void paint(Graphics g) {
        remove(g);
        square(g);
        fruit(g);
    }
    public void square(Graphics g){//Ritar ut kvadraten
        g.setColor(Color.CYAN);
        g.fillRect(xAxis,yAxis,width,height);
    }
    public void remove(Graphics g){//resetar framen
        g.setColor(Color.WHITE);
        g.fillRect(0,0,10000,10000);
    }
    public void fruit(Graphics g){//Ritar ut frukten
        if(xFruit + 25 >= xMax || yFruit + 25 >= yMax){//Kollar så den inte hamnade utanför spelplanen
            xFruit = (int) ((Math.random() * (xMax)) + xMin);
            yFruit = (int) ((Math.random() * (yMax)) + yMin);
        }
        g.setColor(Color.RED);
        g.fillRect(xFruit,yFruit,25,25);
    }
}