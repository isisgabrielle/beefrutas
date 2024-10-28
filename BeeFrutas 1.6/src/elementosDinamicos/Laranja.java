package elementosDinamicos;

/**
 * A classe Laranja representa uma laranja como um elemento visual no jogo, estendendo a classe Fruta.
*/
public class Laranja extends Fruta{

	private static final long serialVersionUID = 1L;

	/**
     * Construtor da classe Laranja que inicializa a posição e o tamanho do ícone da laranja no jogo.
     */
	public Laranja(int x, int y, int tamanhoIcone) {
		super(x, y, tamanhoIcone);
		this.tipoFruta = "Laranja";
	}
	
	@Override 
	public void setUrlIcone() {
		this.urlIcone = "/imagens/laranja.png";
	}
}