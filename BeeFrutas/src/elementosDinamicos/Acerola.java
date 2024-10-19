package elementosDinamicos;

/**
 * A classe Acerola representa uma acerola como um elemento visual no jogo, estendendo a classe Fruta.
*/
public class Acerola extends Fruta{

	private static final long serialVersionUID = 1L;

	/**
     * Construtor da classe Acerola que inicializa a posição e o tamanho do ícone da acerola no jogo.
     */
	public Acerola(int x, int y, int tamanhoIcone) {
		super(x, y, tamanhoIcone);
	}
	
	@Override 
	public void setUrlIcone() {
		this.urlIcone = "/imagens/acerola.png";
	}
}