package packpraktika1;

import java.util.ArrayList;

public class PelikulaGuztiak {
	
	private static PelikulaGuztiak nirePelikulak;
	private ListaPelikulak zerrenda;
	
	private PelikulaGuztiak(){
			
			this.zerrenda = new ListaPelikulak();
		}
	public static synchronized PelikulaGuztiak getNirePelikulak(){
			
		if(PelikulaGuztiak.nirePelikulak == null){
			
			PelikulaGuztiak.nirePelikulak = new PelikulaGuztiak();
		}
		
		return PelikulaGuztiak.nirePelikulak;
	}
	
	
	
	
	public Pelikula pelikulaBilatu(String pPelikulaIzena){//METODO BERRIA, JUNIT BEHAR DU
		
		
		return this.zerrenda.pelikulaBilatu(pPelikulaIzena);
	}

	
	
	
 	public void gehituPelikula(Pelikula pFilma){
		
		//Aurre-Baldintza:	----------------------------------------------------------
		//Post-Baldinta: 	Pelikula ez bada null eta ez badago zerrendan, gehituko da.
		//Kostua:			O(m); Non m pelikulen kopurua da.
		
		this.zerrenda.gehituPelikula(pFilma);
	}
	
	public void ezabatuFilma(Pelikula pFilma){
		
		//Aurre-Baldintza:	----------------------------------------------------------
		//Post-Baldinta: 	Pelikula ez bada null eta pelikulen zerrendan  badago, zerrendatik ezabatuko da.
		//Kostua:			O(m); Non m pelikulen kopurua da.
		
		this.zerrenda.ezabatuFilma(pFilma);
	}
	
	public void inprimatuGuztiak(){//Frogak egiteko programatu da bakarrik, ez da memorian idatzi behar
		
		this.zerrenda.inprimatuGuztiak();
	}

	
	public void pelikulaKargatu(Pelikula pPelikula){//Pelikula zerrendan sartzeko errepikapena konprobatu gabe.
		
		this.zerrenda.pelikulaKargatu(pPelikula);
	}
	
	public int luzera(){ //Frogak egiteko programatu da bakarrik, ez da memorian idatzi behar
		
		return this.zerrenda.luzera();
	}
	
	public void erreseteatu(){ //Frogak egiteko programatu da bakarrik, ez da memorian idatzi behar
		
		PelikulaGuztiak.nirePelikulak = null;
	}
}
