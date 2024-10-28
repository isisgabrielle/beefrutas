package interfaceJogo;

import java.util.ArrayList;

import elementosDinamicos.Fruta;

public class Mochila {
	ArrayList<Fruta> maracujasNaMochila = new ArrayList<Fruta>();
	ArrayList<Fruta> laranjasNaMochila = new ArrayList<Fruta>();
	ArrayList<Fruta> abacatesNaMochila = new ArrayList<Fruta>();
	ArrayList<Fruta> cocosNaMochila = new ArrayList<Fruta>();
	ArrayList<Fruta> acerolasNaMochila = new ArrayList<Fruta>();
	ArrayList<Fruta> amorasNaMochila = new ArrayList<Fruta>();
	ArrayList<Fruta> goiabasNaMochila = new ArrayList<Fruta>();

	public int tamanho;
	public int capacidadeUtilizada = 0;
	public int dono;
	
	Mochila(int tamanho, int dono, Fruta[] elementosDinamicos){
		this.dono = dono;
		this.tamanho = tamanho;
	}
	
	public void adicionarFrutaMochila(Fruta fruta){
		if (this.capacidadeUtilizada < this.tamanho) {
			if ((fruta == null) == false){
				if (fruta.estaNaMochila == this.dono) {
					switch (fruta.tipoFruta) {
					case "Maracuja": this.maracujasNaMochila.add(fruta); this.capacidadeUtilizada++; break;
					case "Laranja": this.laranjasNaMochila.add(fruta); this.capacidadeUtilizada++; break;
					case "Abacate": this.abacatesNaMochila.add(fruta); this.capacidadeUtilizada++; break;
					case "Coco": this.cocosNaMochila.add(fruta); this.capacidadeUtilizada++; break;
					case "Acerola": this.acerolasNaMochila.add(fruta); this.capacidadeUtilizada++; break;
					case "Amora": this.amorasNaMochila.add(fruta); this.capacidadeUtilizada++; break;
					case "Goiaba": this.goiabasNaMochila.add(fruta); this.capacidadeUtilizada++; break;
					}
					}
				}
		}
	}
	
	public void removerFrutaMochila(String fruta){
		switch (fruta) {
		case "Maracuja": this.maracujasNaMochila.remove(maracujasNaMochila.size()-1); this.capacidadeUtilizada--; break;
		case "Laranja": this.laranjasNaMochila.remove(laranjasNaMochila.size()-1); this.capacidadeUtilizada--; break;
		case "Abacate": this.abacatesNaMochila.remove(abacatesNaMochila.size()-1); this.capacidadeUtilizada--; break;
		case "Coco": this.cocosNaMochila.remove(cocosNaMochila.size()-1); this.capacidadeUtilizada--; break;
		case "Acerola": this.acerolasNaMochila.remove(acerolasNaMochila.size()-1); this.capacidadeUtilizada--; break;
		case "Amora": this.amorasNaMochila.remove(amorasNaMochila.size()-1); this.capacidadeUtilizada--; break;
		case "Goiaba": this.goiabasNaMochila.remove(goiabasNaMochila.size()-1); this.capacidadeUtilizada--; break;
		}
	}
	
	
}
