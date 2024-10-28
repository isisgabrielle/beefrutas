package floresta;

import javax.swing.ImageIcon;

import interfaceJogo.TelaInicial;
/**
 * A classe Pedra representa uma pedra na floresta e estende a classe Floresta. 
 * Ela define um ícone de pedra que é redimensionado de acordo com o tamanho fornecido.
 */
public class Pedra extends Floresta {
	/**
	 * A classe Pedra representa uma pedra na floresta e estende a classe Floresta. 
	 * Ela define um ícone de pedra que é redimensionado de acordo com o tamanho fornecido.
	 */
	public Pedra(int tamanhoIcone){
		this.tamanhoIcone = tamanhoIcone;
		ImageIcon image = new ImageIcon(TelaInicial.class.getResource("/imagens/pedra.png"));
		image.setImage(image.getImage().getScaledInstance(tamanhoIcone, tamanhoIcone, 1));
		this.setIcon(image);
	}

	private static final long serialVersionUID = 1L;

}
