package floresta;

import javax.swing.ImageIcon;

import interfaceJogo.TelaInicial;
/**
 * A classe Arvore representa uma árvore na floresta e estende a classe Floresta. 
 * Cada árvore possui um tipo e um ícone correspondente. 
 * O ícone é ajustado de acordo com o tipo de árvore e o tamanho do ícone.
 */
public class Arvore extends Floresta{
	
	ImageIcon image;
	private String tipoArvore;
	
	/**
     * Construtor da classe Arvore que inicializa a árvore com um tipo e ajusta o tamanho do ícone.
     * Dependendo do tipo da árvore, um ícone correspondente será carregado e redimensionado.
     *
     * @param tipoArvore  O tipo da árvore (ex: "Laranja", "Abacate", "Coco", "Acerola", "Amora", "Goiaba")
     * @param tamanhoIcone  O tamanho do ícone da árvore
     */
	public Arvore(String tipoArvore, int tamanhoIcone){
		this.tipoArvore = tipoArvore;
		this.tamanhoIcone = tamanhoIcone;
		ImageIcon image = null;
		
		switch (this.tipoArvore) {
		case "Laranja": image = new ImageIcon(TelaInicial.class.getResource("/imagens/arvore_laranja.png")); break;
		case "Abacate": image = new ImageIcon(TelaInicial.class.getResource("/imagens/arvore_abacate.png")); break;
		case "Coco": image = new ImageIcon(TelaInicial.class.getResource("/imagens/arvore_coco.png")); break;
		case "Acerola": image = new ImageIcon(TelaInicial.class.getResource("/imagens/arvore_acerola.png")); break;
		case "Amora": image = new ImageIcon(TelaInicial.class.getResource("/imagens/arvore_amora.png")); break;
		case "Goiaba": image = new ImageIcon(TelaInicial.class.getResource("/imagens/arvore_goiaba.png")); break;
		}
		
		image.setImage(image.getImage().getScaledInstance(tamanhoIcone, tamanhoIcone, 1));
		this.setIcon(image);
	}


	private static final long serialVersionUID = 1L;

}
