package interfaceJogo;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Dados extends JPanel {
    private int ValorFace; 
    private Random random;

    public Dados() {
        random = new Random();
        ValorFace = 1;}

    void rolarDado() {
        ValorFace = random.nextInt(6) + 1;
        repaint();
    }
    public int getValorFace() {
        return ValorFace; 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int inset = 2; 
        int dadoTamanho = 25;
        g.setColor(Color.WHITE);
        g.fillRoundRect(inset, inset, dadoTamanho - inset * 2, dadoTamanho - inset * 2, 5, 5); 
        g.setColor(Color.BLACK);
        g.drawRoundRect(inset, inset, dadoTamanho - inset * 2, dadoTamanho - inset * 2, 5, 5); 
        drawDots(g, ValorFace);
    }

    private void drawDots(Graphics g, int ValorFace) {
        g.setColor(Color.BLACK);

        int[][] positions = {
            {12, 12},              
            {6, 6}, {18, 18},      
            {6, 18}, {18, 6},      
            {6, 12}, {18, 12}      
        };

        switch (ValorFace) {
            case 1:
                drawCircle(g, positions[0]); 
                break;
            case 2:
                drawCircle(g, positions[1]); 
                drawCircle(g, positions[2]); 
                break;
            case 3:
                drawCircle(g, positions[0]); 
                drawCircle(g, positions[1]); 
                drawCircle(g, positions[2]); 
                break;
            case 4:
                drawCircle(g, positions[1]); 
                drawCircle(g, positions[2]); 
                drawCircle(g, positions[3]); 
                drawCircle(g, positions[4]); 
                break;
            case 5:
                drawCircle(g, positions[0]);
                drawCircle(g, positions[1]);
                drawCircle(g, positions[2]); 
                drawCircle(g, positions[3]); 
                drawCircle(g, positions[4]); 
                break;
            case 6:
                drawCircle(g, positions[1]); 
                drawCircle(g, positions[2]); 
                drawCircle(g, positions[3]); 
                drawCircle(g, positions[4]); 
                drawCircle(g, positions[5]); 
                drawCircle(g, positions[6]); 
                break;
        }
    }


    private void drawCircle(Graphics g, int[] pos) {
        int diametro = 5; // 
        g.fillOval(pos[0] - diametro / 2, pos[1] - diametro / 2, diametro, diametro);
    }
}
