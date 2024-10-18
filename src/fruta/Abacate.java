package fruta;

import javax.swing.ImageIcon;

import interfaceJogo.TelaInicial;

public class Abacate extends Fruta{
	public Abacate(int x, int y, int tamanhoIcone) {
		this.tamanhoIcone = tamanhoIcone;
		this.x = x;
		this.y = y;
		this.setBounds(tamanhoIcone*x, tamanhoIcone*y, tamanhoIcone, tamanhoIcone);
		ImageIcon image = new ImageIcon(TelaInicial.class.getResource("/imagens/abacate.png"));
		image.setImage(image.getImage().getScaledInstance(tamanhoIcone, tamanhoIcone, 1));
		this.setIcon(image);
	}
}
