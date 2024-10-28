package floresta;

import javax.swing.ImageIcon;

import elementosDinamicos.Abacate;
import elementosDinamicos.Acerola;
import elementosDinamicos.Amora;
import elementosDinamicos.Coco;
import elementosDinamicos.Fruta;
import elementosDinamicos.Goiaba;
import elementosDinamicos.Laranja;
import interfaceJogo.TelaInicial;
/**
 * A classe Arvore representa uma árvore na floresta e estende a classe Floresta. 
 * Cada árvore possui um tipo e um ícone correspondente. 
 * O ícone é ajustado de acordo com o tipo de árvore e o tamanho do ícone.
 */
public class Arvore extends Floresta{
	
	ImageIcon image;
	private String tipoArvore;
	private int x;
	private int y;
	
	/**
     * Construtor da classe Arvore que inicializa a árvore com um tipo e ajusta o tamanho do ícone.
     * Dependendo do tipo da árvore, um ícone correspondente será carregado e redimensionado.
     *
     * @param tipoArvore  O tipo da árvore (ex: "Laranja", "Abacate", "Coco", "Acerola", "Amora", "Goiaba")
     * @param tamanhoIcone  O tamanho do ícone da árvore
     */
	public Arvore(String tipoArvore, int tamanhoIcone, int x, int y){
		this.tipoArvore = tipoArvore;
		this.tamanhoIcone = tamanhoIcone;
		this.x = x;
		this.y = y;
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

	public Fruta derrubarFrutaArvore(int dono) {
		Fruta fruta = new Fruta(0, 0, 0);
		switch (this.tipoArvore) {
		case "Laranja": fruta = new Laranja(this.x, this.y, this.tamanhoIcone);
						fruta.estaNaMochila = dono; break;
		case "Abacate": fruta = new Abacate(this.x, this.y, this.tamanhoIcone);
						fruta.estaNaMochila = dono; break;				
		case "Coco":    fruta = new Coco(this.x, this.y, this.tamanhoIcone);
						fruta.estaNaMochila = dono; break;
		case "Acerola": fruta = new Acerola(this.x, this.y, this.tamanhoIcone);
						fruta.estaNaMochila = dono; break;
		case "Amora":   fruta = new Amora(this.x, this.y, this.tamanhoIcone);
						fruta.estaNaMochila = dono; break;
		case "Goiaba":  fruta = new Goiaba(this.x, this.y, this.tamanhoIcone);
						fruta.estaNaMochila = dono; break;
		}
		return fruta;
	}
	
	private static final long serialVersionUID = 1L;

}
