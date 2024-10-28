package interfaceJogo;

import javax.swing.ImageIcon;

import interfaceJogo.TelaInicial;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class BotaoFruta extends JButton{
	
	ImageIcon image;
	private String tipoFruta;
	private int tamanhoIcone = 20;

	public BotaoFruta(String tipoFruta){
		this.tipoFruta = tipoFruta;
		ImageIcon image = null;
		
		switch (this.tipoFruta) {
		case "Maracuja": image = new ImageIcon(TelaInicial.class.getResource("/imagens/maracuja.png")); break;
		case "Laranja": image = new ImageIcon(TelaInicial.class.getResource("/imagens/laranja.png")); break;
		case "Abacate": image = new ImageIcon(TelaInicial.class.getResource("/imagens/abacate.png")); break;
		case "Coco": image = new ImageIcon(TelaInicial.class.getResource("/imagens/coco.png")); break;
		case "Acerola": image = new ImageIcon(TelaInicial.class.getResource("/imagens/acerola.png")); break;
		case "Amora": image = new ImageIcon(TelaInicial.class.getResource("/imagens/amora.png")); break;
		case "Goiaba": image = new ImageIcon(TelaInicial.class.getResource("/imagens/goiaba.png")); break;
		}
		
		image.setImage(image.getImage().getScaledInstance(tamanhoIcone, tamanhoIcone, 1));
		this.setIcon(image);
	}


	private static final long serialVersionUID = 1L;

}
