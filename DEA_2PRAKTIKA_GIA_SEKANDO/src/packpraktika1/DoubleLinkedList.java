package packpraktika1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class DoubleLinkedList<T extends Comparable<T>> implements ListADT<T> {

	// Atributuak
	protected Node<T> first; // lehenengoaren erreferentzia
	protected String deskr;  // deskribapena
	protected int count;     // Susmoa: zerrendan dagoen elementu kopurua (Galdetu Koldori)

	public DoubleLinkedList() { //Super deiaren bidez inplementatuko da, baina ez dira mota honetako klaseak inplementatuko
		
		first = null;
		deskr = "";
		count = 0;
	}
	
	public void setDeskr(String pDeskribapena) { //Ez da abstraktua izango, berdina izango delako azpiklaseetan
		
		//Aurre-Baldintza:	--------
		//Post-Baldinta: 	Emandako deskribapena klasearen "deskr" aldagaian eskuragarri egongo da.
		//Kostua:			O(1);
		
		
	  this.deskr = pDeskribapena;
	}

	public String getDeskr() { //Ez da abstraktua izango, berdina izango delako azpiklaseetan
		
		//Aurre-Baldintza:	--------
		//Post-Baldinta: 	Klasearen deskribapena bueltatuko da
		//Kostua:			O(1);
		
		return this.deskr;
	}

	public T removeFirst() {  //Ez da abstraktua izango, berdina izango delako azpiklaseetan
	
		//Aurre-Baldintza:	Zerrenda ez da hutsa izango.
		//Post-Baldinta: 	Zerrendaren lehenengo elementua kenduta egongo da, eta horren datua bueltatuko da.
		//Kostua:			O(1);
		
		/* Proba kasuak:
		 	- 1. Elementu bakarreko zerrenda denean.
		 	- 2. Elementu anitzeko zerrenda denean.
		 */
		
		Node unekoa = this.first;
		
		if( this.count < 2 ){ //elementu bakarrekoa ez bada
			
			this.first		= unekoa.next;    //Lehenengoaren hurrengoa orain lehenengoa izango da.
			this.first.prev	= unekoa.prev;	  //Ezarritako lehenengoaren aurrekoa ezabatuko denaren aurrekoa izango da.
			this.count		= this.count - 1; //Nodo bat gutxiago egongo delako.
		}
		
		else{
			
			this.first	= null;				   //Zerrenda hutsik geratuko da.			
		}
		
		
		return  (T) unekoa.data;			   //Nodoaren datua bueltatzea eskatzen delako 
	}

	public T removeLast() {   //Ez da abstraktua izango, berdina izango delako azpiklaseetan
		
		//Aurre-Baldintza:	Zerrenda ez da hutsa izango.
		//Post-Baldinta: 	Zerrendaren azkenengo elementua kenduta egongo da, eta horren datua bueltatuko da.
		//Kostua:			O(1);
		
		/* Proba kasuak:
		 	- 1. Elementu bakarreko zerrenda denean.
		 	- 2. Elementu anitzeko zerrenda denean.
		 */
		
		
		Node unekoa = this.first.prev; //Lehenengoaren aurrekoa azkenengoa izango da zerrenda zirkularrean.
		
		if( this.count < 2 ){ //elementu bakarrekoa ez bada
			
			//Kodea ulertzeko errazagoa izateko, beste bi erakusle erabiliko dira: aurrrekoa eta hurrengoa
			Node aurrekoa	= unekoa.prev;
			Node hurrengoa	= unekoa.next;
			
			aurrekoa.next	= unekoa.next;    //Azkenengoaren hurrengoa bere aurrekoaren hurrengoa izango da orain.
			hurrengoa.prev	= unekoa.prev;	  //Azkenengoaren aurrekoa bere hurrenglaren aurrekoa izango da orain.
			this.count		= this.count - 1; //Nodo bat gutxiago egongo delako.
		}
		
		else{
			
			this.first	= null;				   //Zerrenda hutsik geratuko da.			
		}
		
		
		return  (T) unekoa.data;			   //Nodoaren datua bueltatzea eskatzen delako 
	}


	public abstract T remove(T pElem);
	/* Aurrebaldintza: zerrenda ez da hutsa
	// Balio hori listan baldin badago, bere lehen agerpena ezabatuko dut. Kendutako objektuaren erreferentzia 
    //  bueltatuko du (null ez baldin badago)
	// KODEA OSATU ETA KOSTUA KALKULATU
    */ 

	public T first() {

		//Aurre baldintza: -------
		//Post baldintza:  Listako lehen elementua ematen du
		//Kostua: O(1)

		      if (this.isEmpty()) {return null;}         
		      
		      else {return first.data;}
		}

	public T last() {
		// Aurrebaldintza: ------
		// Postbaldintza: azkenengo elementuaren data itzuli da.
		// listako azken elementua ematen du
	      if (isEmpty())
	          return null;
	      else return first.prev.data;
	}

	public boolean contains(T pElem) {

		//Aurre-Baldintza:	--- (NULL KASUA FIND-REN INPLEMENTAZIOAN TRATATUKO DA)
		//Post-Baldinta: 	Zerrendaren barnean badago true bueltatuko du, false bestela.
		//Kostua:			O(n);
		
		/* Proba kasuak:
		 
		 	- 1. Zerrenda hutsa izatea.
		 	- 2. Zerrenda ez hutsa izatea eta elementua zerrendan egotea.
		 	- 3. Zerrenda ez hutsa izatea eta elementua zerrendan ez egotea.
		 */
		if (isEmpty()){
			
			return false;
		}
		else{
    	  
			T bilatutakoa = this.find(pElem);
			return (bilatutakoa != null);
		}
	}

	public abstract T find(T pElem);

	public boolean isEmpty(){

		//Aurre baldintza: --------
		//Post baldintza: Listan elementurik ez dagoen konfirmatzen duen boolean bat itzultzen du
		//Kostua: O(1)

		return first == null;
	}
	
	public int size(){
		
		// Aurrebaldintza: ----
		// Postbaldintza: zerrendak duen luzeera(nodo kopurua) itzuliko da.
		 return this.count;
	}
	

   public Iterator<T> iterator(){
	   
		//Aurre-Baldintza:	---
		//Post-Baldinta: 	Zerrendaren Iteradorea bueltatuko du.
		//Kostua:			O(1);
		
		/* Proba kasuak:
		 * 
		 	- 1. Zerrenda hutsik egotea.
		 	- 2. Zerrenda ez-hutsa izatea.
		 */
	   return new ListIterator();
   }
   
   /*Nested klase bat inplementatzen iteradorea emateko */
   private class ListIterator implements Iterator<T> { 

		//Aurre baldintza: -------
		//Post baldintza: Iteradore baten funtzio erabilgarrienak inplementatuta eukitea (hasNext, add...)
			   
		private int indizea = 0 ;
		private Node<T> unekoa ;
		private Node<T> aurrekoa ;

		ListIterator ( ) {

			unekoa = first;
			aurrekoa = null;
			indizea = 0 ;
		}

		@Override
		public boolean hasNext ( ) {

			//Aurre baldintza: -------
			//Post baldintza: Iteradorearen bidez hurrengo elementua dagoen ala ez bueltatzen du boolean bidez
			//Kostua: O(1)
			    	
			return indizea < count;
		}

		@Override
		public T next ( ) {	

			//Aurre baldintza: -------
			//Post baldintza: Iteradorearen bidez hurrengo elementua bueltatzen du
			//Kostua: O(1)	
				   
			if( ! hasNext ( ) ) {  
					   
				throw new NoSuchElementException ( ) ;
					   
			}
			   
			aurrekoa = unekoa ;
			T objetua = unekoa.data ;
			unekoa = unekoa.next ;
			indizea++;
			return objetua ;
				   
		}


	
			    
		public boolean hasPrev ( ) {

			//Aurre baldintza: -------
			//Post baldintza: Iteradorearen bidez aurreko elementua dagoen ala ez bueltatzen du boolean bidez
			//Kostua: O(1)
			    	
			return indizea > 0 ;
			    
		}

		
		
		public T prev ( ) {

			//Aurre baldintza: -------
			//Post baldintza: Iteradorearen bidez aurreko elementua bueltatzen du
			//Kostua: O(1)
			    	
			if ( ! hasPrev ( ) ) {
			    		
			    	throw new NoSuchElementException ( ) ;
			    			
			}
			    	
			unekoa = unekoa.prev ;
			aurrekoa = unekoa ;
			indizea--;
			return unekoa.data ;
			    	
		}
	   
	   
   }


	
	public void adabegiakInprimatu() {
		System.out.println(this.toString());
	}

	
	@Override
	public String toString() {
		String result = new String();
		Iterator<T> it = iterator();
		while (it.hasNext()) {
			T elem = it.next();
			result = result + "[" + elem.toString() + "] \n";
		}	
		return "SimpleLinkedList " + result + "]";
	}

}
