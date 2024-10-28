package elementosDinamicos;

/**
 * A classe Amora representa uma amora como um elemento visual no jogo, estendendo a classe Fruta.
*/
public class Amora extends Fruta{

	private static final long serialVersionUID = 1L;
	
	/**
     * Construtor da classe Amora que inicializa a posição e o tamanho do ícone da amora no jogo.
     */
	public Amora(int x, int y, int tamanhoIcone) {
		super(x, y, tamanhoIcone);
		this.tipoFruta = "Amora";
	}
	
	@Override 
	public void setUrlIcone() {
		this.urlIcone = "/imagens/amora.png";
	}
}