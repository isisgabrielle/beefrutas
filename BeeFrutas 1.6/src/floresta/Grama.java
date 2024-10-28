package floresta;

import javax.swing.ImageIcon;

import interfaceJogo.TelaInicial;
/**
 * A classe Grama representa um pedaço de grama na floresta e estende a classe Floresta. 
 * Ela define um ícone de grama que é redimensionado de acordo com o tamanho fornecido.
 */
public class Grama extends Floresta {	
	/**
     * Construtor da classe Grama que inicializa o ícone de grama com o tamanho especificado.
     * O ícone é carregado a partir de um arquivo de imagem e redimensionado de acordo com o tamanho do ícone.
     *
     * @param tamanhoIcone  O tamanho do ícone da grama
     */
	public Grama(int tamanhoIcone){
		this.tamanhoIcone = tamanhoIcone;
		ImageIcon image = new ImageIcon(TelaInicial.class.getResource("/imagens/grama.png"));
		image.setImage(image.getImage().getScaledInstance(tamanhoIcone, tamanhoIcone, 1));
		this.setIcon(image);
		}
	

	private static final long serialVersionUID = 1L;

}

