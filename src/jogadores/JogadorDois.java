package jogadores;
import javax.swing.ImageIcon;
import interfaceJogo.TelaInicial;

public class JogadorDois extends Jogador{
	
	/**
     * Construtor da classe JogadorUm que inicializa a posição e o ícone do jogador no jogo.
     * O ícone é carregado a partir de um arquivo de imagem e redimensionado de acordo com o tamanho especificado.
     * A posição da laranja é determinada pelos valores de x e y multiplicados pelo tamanho do ícone.
     *
     * @param x  A coordenada horizontal (em unidades de grade) onde o jogador será colocada
     * @param y  A coordenada vertical (em unidades de grade) onde o jogador será colocada
     * @param tamanhoIcone  O tamanho do ícone do jogador
     */
	public JogadorDois(int x, int y, int tamanhoIcone) {
		this.tamanhoIcone = tamanhoIcone;
		this.x = x;
		this.y = y;
		this.setBounds(tamanhoIcone*x, tamanhoIcone*y, tamanhoIcone, tamanhoIcone);
		ImageIcon image = new ImageIcon(TelaInicial.class.getResource("/imagens/competidor2.png"));
		image.setImage(image.getImage().getScaledInstance(tamanhoIcone, tamanhoIcone, 1));
		this.setIcon(image);
	}
	/**
     * ID de versão para controle de serialização.
     */
	private static final long serialVersionUID = 1L;
}

