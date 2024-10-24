package elementosDinamicos;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import interfaceJogo.TelaInicial;

public class Jogador extends JLabel {
	private static final long serialVersionUID = 1L;
	protected int tamanhoIcone;
	public int x;
	public int y;
	public Mochila mochila;
	public String nome;
	protected String urlIcone;
	
	// Construtor ajustado para inicializar a mochila
	public Jogador(int x, int y, int tamanhoIcone, int idJogador, String nome, int capacidadeMochila){
		if(idJogador == 0) {
			urlIcone = "/imagens/jogador0.png";
			mochila = new Mochila(capacidadeMochila, "Jogador0", tamanhoIcone);  // Inicializa a mochila do jogador 0
		}
		if(idJogador == 1) {
			urlIcone = "/imagens/jogador1.png";
			mochila = new Mochila(capacidadeMochila, "Jogador1", tamanhoIcone);  // Inicializa a mochila do jogador 1
		}
		this.tamanhoIcone = tamanhoIcone;
		this.x = x;
		this.y = y;
		this.nome = nome;
		this.setBounds(tamanhoIcone * x, tamanhoIcone * y, tamanhoIcone, tamanhoIcone);
		
		// Carrega o ícone do jogador
		ImageIcon image = new ImageIcon(TelaInicial.class.getResource(urlIcone));
		image.setImage(image.getImage().getScaledInstance(tamanhoIcone, tamanhoIcone, 1));
		this.setIcon(image);
	}

	// Adiciona fruta na mochila se possível
	public boolean pegarFruta(Fruta fruta) {
		return mochila.adicionarFruta(fruta);
	}

	// Consome uma fruta da mochila
	public Fruta consumirFruta() {
		return mochila.consumirFruta();
	}
	
	public Mochila getMochila() {
        return mochila;
    }
	
	// Método para mover o jogador dentro dos limites do mapa
	public void moverJogador(int moverX, int moverY, int limiteMovimento) {
		if (this.x + moverX < limiteMovimento && this.x + moverX >= 0) {
			this.x = this.x + moverX;
		}
		if (this.y + moverY < limiteMovimento && this.y + moverY >= 0) {
			this.y = this.y + moverY;
		}
		this.setBounds(tamanhoIcone * x, tamanhoIcone * y, tamanhoIcone, tamanhoIcone);
	}
}
