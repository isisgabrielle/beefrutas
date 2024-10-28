package elementosDinamicos;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import floresta.Floresta;
import floresta.Pedra;
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
	public int forca = 0;
	
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
	
	public static void wait(int ms)
	{
	    try
	    {
	        Thread.sleep(ms);
	    }
	    catch(InterruptedException ex)
	    {
	        Thread.currentThread().interrupt();
	    }
	}

	
	private boolean conferePedra(int novoX, int novoY, Floresta[][] ladrilho, int pontosMovimento){
		boolean requisitosPedra = false;
		if(ladrilho[novoY][novoX] instanceof Pedra == false || (ladrilho[novoY][novoX] instanceof Pedra == true && pontosMovimento >= 3)) {
			requisitosPedra = true;
		}
		else {
			JOptionPane.showMessageDialog(null, "Você não tem pontos suficientes para pular a pedra. Tente outra jogada.", "ERRO", JOptionPane.WARNING_MESSAGE);
		}
		return requisitosPedra;
	}
	
	public boolean moverJogadorParaCima(Floresta[][] ladrilho, int pontosMovimento) {
		boolean movimentacaoConcluida = false;
		int novoY = this.y - 1;
		if (novoY >= 0 && conferePedra(this.x, novoY, ladrilho, pontosMovimento)) {
			this.y = novoY;
			movimentacaoConcluida = true;}
		this.setBounds(tamanhoIcone*x, tamanhoIcone*y, tamanhoIcone, tamanhoIcone);
		return movimentacaoConcluida;
	}
	
	public boolean moverJogadorParaBaixo(Floresta[][] ladrilho, int pontosMovimento) {
		boolean movimentacaoConcluida = false;
		int limiteMovimento = ladrilho.length;
		int novoY = this.y + 1;
		if (novoY < limiteMovimento && conferePedra(this.x, novoY, ladrilho, pontosMovimento)) {
			this.y = novoY;
			movimentacaoConcluida = true;}
		this.setBounds(tamanhoIcone*x, tamanhoIcone*y, tamanhoIcone, tamanhoIcone);
		return movimentacaoConcluida;
	}
	
	public boolean moverJogadorParaDireita(Floresta[][] ladrilho, int pontosMovimento) {
		boolean movimentacaoConcluida = false;
		int limiteMovimento = ladrilho.length;
		int novoX = this.x + 1;
		if (novoX < limiteMovimento && conferePedra(novoX, this.y, ladrilho, pontosMovimento)) {
			this.x = novoX;
			movimentacaoConcluida = true;}
		this.setBounds(tamanhoIcone*x, tamanhoIcone*y, tamanhoIcone, tamanhoIcone);
		return movimentacaoConcluida;
	}
	
	public boolean moverJogadorParaEsquerda(Floresta[][] ladrilho, int pontosMovimento) {
		boolean movimentacaoConcluida = false;
		int novoX = this.x - 1;
		if (novoX >= 0 && conferePedra(novoX, this.y, ladrilho, pontosMovimento)) {
			this.x = novoX;
			movimentacaoConcluida = true;}
		this.setBounds(tamanhoIcone*x, tamanhoIcone*y, tamanhoIcone, tamanhoIcone);
		return movimentacaoConcluida;
	}
} 