package elementosDinamicos;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import interfaceJogo.TelaInicial;

public class ElementoDinamico extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int tamanhoIcone;
	public int x;
	public int y;
	protected String urlIcone;
	
	public ElementoDinamico(int x, int y, int tamanhoIcone){
		this.urlIcone = setUrlIcone();
		this.tamanhoIcone = tamanhoIcone;
		this.x = x;
		this.y = y;
		this.setBounds(tamanhoIcone*x, tamanhoIcone*y, tamanhoIcone, tamanhoIcone);
		ImageIcon image = new ImageIcon(TelaInicial.class.getResource(urlIcone));
		image.setImage(image.getImage().getScaledInstance(tamanhoIcone, tamanhoIcone, 1));
		this.setIcon(image);

	}
	/**
	 * Getter do local onde a imagem da fruta está armazenada.
	 */
	public String setUrlIcone(){
		return "/imagens/goiaba.png";
	}
}