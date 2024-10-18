package fruta;

import java.awt.Color;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import interfaceJogo.TelaInicial;

public class Maracuja extends Fruta{
	public Maracuja(int x, int y, int tamanhoIcone) {
		this.tamanhoIcone = tamanhoIcone;
		this.x = x;
		this.y = y;
		this.setBounds(tamanhoIcone*x, tamanhoIcone*y, tamanhoIcone, tamanhoIcone);
		ImageIcon image = new ImageIcon(TelaInicial.class.getResource("/imagens/maracuja.png"));
		image.setImage(image.getImage().getScaledInstance(tamanhoIcone, tamanhoIcone, 1));
		this.setIcon(image);
	}
}
