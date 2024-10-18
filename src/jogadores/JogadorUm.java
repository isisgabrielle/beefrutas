package jogadores;

import javax.swing.ImageIcon;
import interfaceJogo.TelaInicial;

public class JogadorUm extends Jogador{
	
	
	public JogadorUm(int x, int y, int tamanhoIcone) {
		this.tamanhoIcone = tamanhoIcone;
		this.x = x;
		this.y = y;
		this.setBounds(tamanhoIcone*x, tamanhoIcone*y, tamanhoIcone, tamanhoIcone);
		ImageIcon image = new ImageIcon(TelaInicial.class.getResource("/imagens/competidor1.png"));
		image.setImage(image.getImage().getScaledInstance(tamanhoIcone, tamanhoIcone, 1));
		this.setIcon(image);
		
	}
	
	private static final long serialVersionUID = 1L;
}

