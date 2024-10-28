package floresta;

import javax.swing.JButton;

import elementosDinamicos.Fruta;

/**
 * A classe Floresta estende JButton e serve como uma base para objetos relacionados à floresta. 
 */
public abstract class Floresta extends JButton{
	/** O tamanho do ícone que será utilizado nos botões da floresta */
	protected int tamanhoIcone;
	
	private static final long serialVersionUID = 1L;
	public abstract Fruta derrubarFrutaArvore(int dono, int probBichada);
	
}
