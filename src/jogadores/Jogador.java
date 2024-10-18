package jogadores;

import javax.swing.JLabel;

public class Jogador extends JLabel {
    int tamanhoIcone;
    int x;
    int y;
    
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
        this.setBounds(x * this.tamanhoIcone, this.y * this.tamanhoIcone, this.tamanhoIcone, this.tamanhoIcone); // Atualiza a posição do ícone
    }

    public void setY(int y) {
        this.y = y;
        this.setBounds(this.x * this.tamanhoIcone, y * this.tamanhoIcone, this.tamanhoIcone, this.tamanhoIcone); // Atualiza a posição do ícone
    }
    public int getTamanhoIcone() {
        return tamanhoIcone;
    }

    
    private static final long serialVersionUID = 1L;
}
