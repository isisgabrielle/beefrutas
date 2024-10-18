package floresta;

import javax.swing.ImageIcon;

import interfaceJogo.TelaInicial;

public class Arvore extends Floresta{
	
	ImageIcon image;
	private String tipoArvore;
	int tamanhoIcone = 65;
	public Arvore(String tipoArvore, int tamanhoIcone){
		this.tipoArvore = tipoArvore;
		this.tamanhoIcone = tamanhoIcone;
		if (this.tipoArvore == "Laranja") {
			ImageIcon image = new ImageIcon(TelaInicial.class.getResource("/imagens/arvore_laranja.png"));
			image.setImage(image.getImage().getScaledInstance(tamanhoIcone, tamanhoIcone, 1));
			this.setIcon(image);}
		if (this.tipoArvore == "Abacate") {
			ImageIcon image = new ImageIcon(TelaInicial.class.getResource("/imagens/arvore_abacate.png"));
			image.setImage(image.getImage().getScaledInstance(tamanhoIcone, tamanhoIcone, 1));
			this.setIcon(image);}
		if (this.tipoArvore == "Coco") {
			ImageIcon image = new ImageIcon(TelaInicial.class.getResource("/imagens/arvore_coco.png"));
			image.setImage(image.getImage().getScaledInstance(tamanhoIcone, tamanhoIcone, 1));
			this.setIcon(image);}
		if (this.tipoArvore == "Acerola") {
			ImageIcon image = new ImageIcon(TelaInicial.class.getResource("/imagens/arvore_acerola.png"));
			image.setImage(image.getImage().getScaledInstance(tamanhoIcone, tamanhoIcone, 1));
			this.setIcon(image);}
		if (this.tipoArvore == "Amora") {
			ImageIcon image = new ImageIcon(TelaInicial.class.getResource("/imagens/arvore_amora.png"));
			image.setImage(image.getImage().getScaledInstance(tamanhoIcone, tamanhoIcone, 1));
			this.setIcon(image);}
		if (this.tipoArvore == "Goiaba") {
			ImageIcon image = new ImageIcon(TelaInicial.class.getResource("/imagens/arvore_goiaba.png"));
			image.setImage(image.getImage().getScaledInstance(tamanhoIcone, tamanhoIcone, 1));
			this.setIcon(image);}
		
		
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
