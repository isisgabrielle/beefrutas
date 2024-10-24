package elementosDinamicos;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import interfaceJogo.TelaInicial;

public class Mochila extends JButton {
	
	ImageIcon image;
	private String tipoMochila;
	private int tamanhoIcone;
    private int capacidade;
    private List<Fruta> frutas;

    public Mochila(int capacidade, String tipoMochila, int tamanhoIcone) {
        this.capacidade = capacidade;
        this.tipoMochila = tipoMochila;
		this.tamanhoIcone = tamanhoIcone;
		ImageIcon image = null;

        this.frutas = new ArrayList<>();
        
        switch (this.tipoMochila) {
		case "Jogador0": image = new ImageIcon(TelaInicial.class.getResource("/imagens/coco.png")); break;
		case "Jogador1": image = new ImageIcon(TelaInicial.class.getResource("/imagens/coco.png")); break;
		}
		image.setImage(image.getImage().getScaledInstance(tamanhoIcone, tamanhoIcone, Image.SCALE_SMOOTH));
		this.setIcon(image);
	}

    public boolean podeAdicionarFruta() {
        return frutas.size() < capacidade;
    }

    public boolean adicionarFruta(Fruta fruta) {
        System.out.println("Capacidade: " + capacidade + ", Frutas atuais: " + frutas.size());
        if (podeAdicionarFruta()) {
            frutas.add(fruta);
            System.out.println("Fruta adicionada: " + fruta);
            return true;
        }
        System.out.println("Não foi possível adicionar a fruta: " + fruta);
        return false;
    }

    public Fruta consumirFruta() {
        if (!frutas.isEmpty()) {
            return frutas.remove(frutas.size() - 1);
        }
        return null;
    }

    public int quantidadeFrutas() {
        return frutas.size();
    }

    public List<Fruta> listarFrutas() {
        return frutas;
    }
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
