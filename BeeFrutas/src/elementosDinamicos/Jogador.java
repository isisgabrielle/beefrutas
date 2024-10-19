package elementosDinamicos;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import interfaceJogo.TelaInicial;

public class Jogador extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int tamanhoIcone;
	public int x;
	public int y;
	public String nome;
	protected String urlIcone;
	
	public Jogador(int x, int y, int tamanhoIcone, int idJogador, String nome){
		if(idJogador == 0) {
			urlIcone = "/imagens/jogador0.png";}
		if(idJogador == 1) {
			urlIcone = "/imagens/jogador1.png";}
		this.tamanhoIcone = tamanhoIcone;
		this.x = x;
		this.y = y;
		this.nome = nome;
		this.setBounds(tamanhoIcone*x, tamanhoIcone*y, tamanhoIcone, tamanhoIcone);
		ImageIcon image = new ImageIcon(TelaInicial.class.getResource(urlIcone));
		image.setImage(image.getImage().getScaledInstance(tamanhoIcone, tamanhoIcone, 1));
		this.setIcon(image);

}
	
	public void moverJogador(int moverX, int moverY, int limiteMovimento) {
		if (this.x+1 < limiteMovimento && moverX >= 0) {
			this.x = this.x + moverX;}
		if (this.x > 0 && moverX <= 0) {
			this.x = this.x + moverX;}
		if (this.y+1 < limiteMovimento && moverY >= 0) {
			this.y = this.y + moverY;}
		if (this.y > 0 && moverY <= 0) {
			this.y = this.y + moverY;}
		this.setBounds(tamanhoIcone*x, tamanhoIcone*y, tamanhoIcone, tamanhoIcone);
	}
} 