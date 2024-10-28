package elementosDinamicos;

/**
 * A classe Coco representa um coco como um elemento visual no jogo, estendendo a classe Fruta.
*/
public class Coco extends Fruta{

	private static final long serialVersionUID = 1L;
	
	/**
     * Construtor da classe Coco que inicializa a posição e o tamanho do ícone do coco no jogo.
     */
	public Coco(int x, int y, int tamanhoIcone, int probBichada) {
		super(x, y, tamanhoIcone, probBichada);
		this.tipoFruta = "Coco";
	}
	
	@Override 
	public void setUrlIcone() {
		this.urlIcone = "/imagens/coco.png";
	}
}