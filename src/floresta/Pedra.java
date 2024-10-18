package floresta;

import javax.swing.ImageIcon;

import interfaceJogo.TelaInicial;

public class Pedra extends Floresta {
	public Pedra(int tamanhoIcone){
		this.tamanhoIcone = tamanhoIcone;
		ImageIcon image = new ImageIcon(TelaInicial.class.getResource("/imagens/pedra.png"));
		image.setImage(image.getImage().getScaledInstance(tamanhoIcone, tamanhoIcone, 1));
		this.setIcon(image);
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
