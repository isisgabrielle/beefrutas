package elementosDinamicos;

/**
 * A classe Abacate representa um abacate como um elemento visual no jogo, estendendo a classe Fruta.
*/
public class Abacate extends Fruta{
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Construtor da classe Abacate que inicializa a posição e o tamanho do ícone do abacate no jogo.
     */
	public Abacate(int x, int y, int tamanhoIcone, int probBichada) {
		super(x, y, tamanhoIcone, probBichada);
		this.tipoFruta = "Abacate";
	}
	
	@Override 
	public void setUrlIcone() {
		this.urlIcone = "/imagens/abacate.png";
	}
}