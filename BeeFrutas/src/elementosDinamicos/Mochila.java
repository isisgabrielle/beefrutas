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


    // Verifica se há espaço para mais frutas
    public boolean podeAdicionarFruta() {
        return frutas.size() < capacidade;
    }

    // Adiciona fruta à mochila se houver espaço
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

    // Remove uma fruta da mochila e a consome
    public Fruta consumirFruta() {
        if (!frutas.isEmpty()) {
            return frutas.remove(frutas.size() - 1); // Remove a última fruta
        }
        return null; // Se não houver frutas
    }

    // Retorna quantas frutas estão na mochila
    public int quantidadeFrutas() {
        return frutas.size();
    }

    // Lista os tipos de frutas na mochila
    public List<Fruta> listarFrutas() {
        return frutas;
    }
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
