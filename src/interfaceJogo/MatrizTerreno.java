package interfaceJogo;

import java.util.Random;

/**
 * A classe MatrizTerreno cria o terreno do jogo BeeFrutas como uma matriz de Strings
 * que contém elementos estáticos (como árvores, pedras, grama) e elementos dinâmicos (como frutas).
 * A classe permite a inicialização dos elementos em posições aleatórias no terreno seguindo as quantidades
 * definidas pelo jogador,  além de mostrar o terreno no console através de Strings.
 */
public class MatrizTerreno {		
	
	Random random = new Random();
	
	public int dimensao;
	private int totalPedras;
	private int maracuja;
	private int arvoreLaranja, arvoreAbacate, arvoreCoco, arvoreAcerola, arvoreAmora, arvoreGoiaba;
	private int laranja, abacate, coco, acerola, amora, goiaba;
	public String elementosEstaticos[][];	
	public String elementosDinamicos[][];
	
	
	/**
     * Construtor da classe MatrizTerreno.
     * Inicializa os atributos do terreno de acordo com os parâmetros passados.
     *
     * @param dimensao A dimensão da matriz (n x n) que representa a floresta.
     * @param totalPedras O número total de pedras no terreno.
     * @param arvoreLaranja O número de árvores de laranja no terreno.
     * @param arvoreAbacate O número de árvores de abacate no terreno.
     * @param arvoreCoco O número de árvores de coco no terreno.
     * @param arvoreAcerola O número de árvores de acerola no terreno.
     * @param arvoreAmora O número de árvores de amora no terreno.
     * @param arvoreGoiaba O número de árvores de goiaba no terreno.
     * @param maracuja A quantidade de maracujás no terreno.
     * @param laranja A quantidade de laranjas no terreno.
     * @param abacate A quantidade de abacates no terreno.
     * @param coco A quantidade de cocos no terreno.
     * @param acerola A quantidade de acerolas no terreno.
     * @param amora A quantidade de amoras no terreno.
     * @param goiaba A quantidade de goiabas no terreno.
     */
	public MatrizTerreno(int dimensao, int totalPedras, int arvoreLaranja, int arvoreAbacate, int arvoreCoco, int arvoreAcerola, int arvoreAmora, int arvoreGoiaba,
						 int maracuja, int laranja, int abacate, int coco, int acerola, int amora, int goiaba) {
		this.dimensao = dimensao;
		this.totalPedras = totalPedras;
		this.arvoreLaranja = arvoreLaranja;
		this.arvoreAbacate = arvoreAbacate;
		this.arvoreCoco = arvoreCoco;
		this.arvoreAcerola = arvoreAcerola;
		this.arvoreAmora = arvoreAmora;
		this.arvoreGoiaba = arvoreGoiaba;
		this.maracuja = maracuja;
		this.laranja = laranja;
		this.abacate = abacate;
		this.coco = coco;
		this.acerola = acerola;
		this.amora = amora;
		this.goiaba = goiaba;}
	
	
	//Insere um elemento no terreno em uma célula aleatória que tenha grama e esteja vazia.
	private void eventoAleatorio(String[][] matriz, String nome, int quantidade){
		for (int i = 0; i < quantidade; i++) {
			boolean temGrama = false;
			while (temGrama == false) {
				int randomX = random.nextInt(this.dimensao);
				int randomY = random.nextInt(this.dimensao);
				if (elementosEstaticos [randomX][randomY] == "g " && elementosDinamicos [randomX][randomY] == "  ") {
					matriz [randomX][randomY] = nome;
					temGrama = true; }
				else {
					temGrama = false; } } } }
	
	
	protected void inicializarElementos() {
		elementosEstaticos = new String[dimensao][dimensao];	
		for (int i = 0; i < elementosEstaticos.length; i++) {
			for (int j = 0; j < elementosEstaticos[i].length; j++) {
				elementosEstaticos[i][j] = "g "; } } 
		
		elementosDinamicos = new String[dimensao][dimensao];	
		for (int i = 0; i < elementosDinamicos.length; i++) {
			for (int j = 0; j < elementosDinamicos[i].length; j++) {
				elementosDinamicos[i][j] = "  "; } } 
		
		
		eventoAleatorio(elementosEstaticos, "P ", totalPedras);
		eventoAleatorio(elementosEstaticos, "AL", arvoreLaranja);
		eventoAleatorio(elementosEstaticos, "AA", arvoreAbacate);
		eventoAleatorio(elementosEstaticos, "AC", arvoreCoco);
		eventoAleatorio(elementosEstaticos, "AR", arvoreAcerola);
		eventoAleatorio(elementosEstaticos, "AO", arvoreAmora);
		eventoAleatorio(elementosEstaticos, "AG", arvoreGoiaba);
		
		eventoAleatorio(elementosDinamicos, "m ", maracuja);
		eventoAleatorio(elementosDinamicos, "l ", laranja);
		eventoAleatorio(elementosDinamicos, "a ", abacate);
		eventoAleatorio(elementosDinamicos, "c ", coco);
		eventoAleatorio(elementosDinamicos, "r ", acerola);
		eventoAleatorio(elementosDinamicos, "o ", amora);
		eventoAleatorio(elementosDinamicos, "g ", goiaba);
		eventoAleatorio(elementosDinamicos, "ju ", 1);
		eventoAleatorio(elementosDinamicos, "jd ", 1);

		}
	
	/**
     * Exibe o terreno criado no console, mostrando tanto os elementos estáticos quanto os dinâmicos.
     */
	public void mostrarTerreno() {
		for (int i = 0; i < elementosEstaticos.length; i++) {
			System.out.print("\n\n");
			for (int j = 0; j < elementosEstaticos[i].length; j++) {
				System.out.print(elementosEstaticos[i][j] + elementosDinamicos[i][j] + (" "));} } }
	
}
