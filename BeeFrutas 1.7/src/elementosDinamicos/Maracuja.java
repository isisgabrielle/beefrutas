package elementosDinamicos;

/**
 * A classe Maracuja representa um maracuja como um elemento visual no jogo, estendendo a classe Fruta.
*/
public class Maracuja extends Fruta{

	/**
     * Construtor da classe Maracuja que inicializa a posição e o tamanho do ícone do maracujá no jogo.
     */
	private static final long serialVersionUID = 1L;

	public Maracuja(int x, int y, int tamanhoIcone, int probBichada) {
		super(x, y, tamanhoIcone, probBichada);
		this.tipoFruta = "Maracuja";
	}
	
	@Override 
	public void setUrlIcone() {
		this.urlIcone = "/imagens/maracuja.png";
	}
}