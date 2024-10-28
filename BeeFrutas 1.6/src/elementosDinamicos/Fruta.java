package elementosDinamicos;

import javax.swing.ImageIcon;

import javax.swing.JLabel;

import interfaceJogo.TelaInicial;
/**
 * Classe Fruta que representa uma fruta no jogo.
 * <p>
 * Esta classe estende a {@link JLabel} e inclui propriedades
 * para controlar o tamanho do ícone da fruta e sua posição
 * (coordenadas x e y) na interface gráfica.
 * </p>
 */
public class Fruta extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int tamanhoIcone;
	public int x;
	public int y;
	protected String urlIcone;
	public int estaNaMochila = 3;
	protected boolean foiColetada = false;
	public String tipoFruta;
	
	public Fruta(int x, int y, int tamanhoIcone){
		setUrlIcone();
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
	public void setUrlIcone(){
		this.urlIcone = "/imagens/fruta_generica.png";
	}
	
	public void moverFrutaPara(int moverX, int moverY) {
		this.x = moverX;
		this.y = moverY;
		this.setBounds(tamanhoIcone*x, tamanhoIcone*y, tamanhoIcone, tamanhoIcone);
	}
	public boolean isFoiColetada() {
	        return foiColetada;
	    }

	
	public void setFoiColetada(boolean foiColetada) {
	        this.foiColetada = foiColetada;
	}
	
	public void removerFruta() {
		this.estaNaMochila = 3;
		this.setIcon(null);
	}
	 
}



