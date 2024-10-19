package elementosDinamicos;

public class Jogador extends ElementoDinamico{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idJogador;
	
	public Jogador(int x, int y, int tamanhoIcone, int idJogador) {
		super(x, y, tamanhoIcone);
		this.idJogador = idJogador;
	}
	
	public void moverJogador(int moverX, int moverY) {
		this.x = this.x + moverX;
		this.y = this.y + moverY;
		this.setBounds(tamanhoIcone*x, tamanhoIcone*y, tamanhoIcone, tamanhoIcone);
	}
	
	@Override
	public String setUrlIcone() {
		String tempUrlIcone = new String();
		switch (this.idJogador) {
		case 0: tempUrlIcone = "/imagens/jogador0.png"; break;
		case 1: tempUrlIcone = "/imagens/jogador1.png"; break;
		}
		return tempUrlIcone;
	}
}