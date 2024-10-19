package elementosDinamicos;

/**
 * A classe Goiaba representa uma goiaba como um elemento visual no jogo, estendendo a classe Fruta.
*/
public class Goiaba extends Fruta{
	
	private static final long serialVersionUID = 1L;
		
	/**
     * Construtor da classe Goiaba que inicializa a posição e o tamanho do ícone da goiaba no jogo.
     */
	public Goiaba(int x, int y, int tamanhoIcone) {
		super(x, y, tamanhoIcone);
	}
	
	@Override 
	public void setUrlIcone() {
		this.urlIcone = "/imagens/goiaba.png";
	}
}