package floresta;

import javax.swing.ImageIcon;

import interfaceJogo.TelaInicial;

public class Grama extends Floresta {	
	public Grama(int tamanhoIcone){
		this.tamanhoIcone = tamanhoIcone;
		ImageIcon image = new ImageIcon(TelaInicial.class.getResource("/imagens/grama.png"));
		image.setImage(image.getImage().getScaledInstance(tamanhoIcone, tamanhoIcone, 1));
		this.setIcon(image);
		}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

