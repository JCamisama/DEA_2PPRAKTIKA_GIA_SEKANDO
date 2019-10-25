package packpraktika2;

public class OrderedDoubleLinkedList<T extends Comparable<T>> extends DoubleLinkedList<T> implements OrderedListADT<T> {
	
	public void add(T pElem){
		

		//Aurre-Baldintza:	---
		//Post-Baldinta: 	Datua posizio egokia duen nodo berri batean txertatuta egongo da, zerrendaren parte bilakatzen.
		//Kostua:			O(n);
		
		/* Proba kasuak:
		  
		 	- 1. Zerrenda hutsa izatea
		 	- 2. Elementu bakarreko zerrenda denean.
		 	- 3. Elementu berria zerrendan daudenak baino txikiagoa izatea (lehenengo posizioan gehitzea).
		 	- 4. Elementu berria zerrendako erdialdeko posizio batean joatea.
		 	- 5. Elementu berria zerrendan daudenak baino handiagoa izatea (azkenengo posizioan gehitzea).
		 */

		//Nodo berria eta unekoari apuntatzeko erakusleak sortzen:
		
		Node berria = new Node((Comparable) pElem); //COMPARABLE EZ BADUT IDAZTEN, EZ DU FUNTZIONATZEN T-REKIN!!!!!*****
		
		Node unekoa	= super.first; //Bere atributuak ama klasean definituta daudenez, "super" erreferentzia erabili da.
		
		if( unekoa == null ){ //Zerrenda hutsik dagoenean
			
			super.first = berria;
			berria.next	= berria;
			berria.prev	= berria;
		}
		
		else{
			
			int		kontatutakoPosizio	= 0;
			boolean	txikiagoDa			= false;
			
			while( (kontatutakoPosizio < super.count)&&!txikiagoDa ){ //Guztiekin konparatu arte edo elementu baino 
																		// txikiagoa den jakin arte
				
				if( berria.data.compareTo(unekoa.data) < 0 ){ //Negatiboa bada, txiagoa izango da, bestela berdina edo handiagoa
					
					txikiagoDa = true;
				}
				
				else{
					
					unekoa = unekoa.next;
					kontatutakoPosizio++; // Zerrendako elementu bat gehiago konprobatu dela 
				}	
			}
			
			//Unekoaren aurrekoa eta hurrengoen erakusleak egiten, ulermena errazteko
			Node aurrekoa	= unekoa.prev;
			Node hurrengoa	= unekoa.next;
			
			aurrekoa.next	= berria;
			berria.prev		= aurrekoa;
			unekoa.prev		= berria;
			berria.next		= unekoa;
			
			if( kontatutakoPosizio == 0 ){ //Zerrendaren lehenengoa baino txikiagoa izatekotan
				
				super.first = berria;
			}
			
			super.count++;
		}
	}

	public void merge(DoubleLinkedList<T> pZerrenda){
		
		//Aurre baldintza: DoubleLinkedList<T> ordenatu bat sartuko da
		//Post baldintza: Ez du ezer itzuliko, baina daugakun DoubleLinkedList<T>-rekin (this.first...) eta sartutako DoubleLinkedList<T>-arekin (pZerrenda), OrderedDoubleLinkedList<T> berri ordenatu bat sortuka da
		//Kostua: 0(n)
		
			
	    OrderedDoubleLinkedList<T> linkedListBerria = new OrderedDoubleLinkedList();
	    OrderedDoubleLinkedList<T> zerrendaLag		= (OrderedDoubleLinkedList<T>) pZerrenda; //Do
		
	    int kontX = this.count; //this.count-aren laguntzaile bat
	    int kontY = zerrendaLag.count; //zerrendaLag.count-aren laguntzaile bat	 	    
	    
	    while (kontX !=0 && kontY !=0) {

	    	
	        if (this.first.data.compareTo(zerrendaLag.first.data) <= 0 ) { //this.first-ren data string bezela, zerrendaLagrena baino handiagoa bada, orduan zerrendaLagren lehenengo elementua sartu egingo da gure zerrenda berrian
	        	

	        	linkedListBerria.add(this.first.data);
	        	
	        	this.first.next.prev = this.first.prev;	      	
	        	this.first = this.first.next;	        	
	        	
	            	kontX--;
	        } 
	        
	        else { 
	        	
	        	linkedListBerria.add(zerrendaLag.first.data);
	        	
	        	zerrendaLag.first.next.prev = zerrendaLag.first.prev;	        	
	        	zerrendaLag.first = zerrendaLag.first.next;
	             
	            	kontY--;          
	        }  	        
	        
	    }
	    
	    if (kontX == 0){ //this zerrenda hutsa bada, orduan zerrendaLagn zerrendan dagoen objektu guztiak linkedListBerria-n gehitu behar dira
	    	
	    	linkedListBerria.first.prev.next = zerrendaLag.first;
	    	zerrendaLag.first.prev = linkedListBerria.first.prev;	    	
	    	
	    }
	    
	    if (kontY == 0) { //zerrendaLag zerrenda hutsa bada, orduan this zerrendan dagoen objektu guztiak linkedListBerria-n gehitu behar dira
	    	
	    	linkedListBerria.first.prev.next = this.first;
	    	this.first.prev = linkedListBerria.first.prev;	
	    	
	    }

	    this.first = linkedListBerria.first;
	    
	}

	public T find(T pElem) {

		
		 
		// Aurre Baldintza : ------
		// Post Baldintza : Elementua bueltatuko du aurkituz gero, eta null bestela
		// Kostua: O(n)
			
			Node<T> lag;
			lag=first;		
			boolean topatua = false;	
			int kont = 0;
			
			if (this.isEmpty()){return null;}		
			
			else {
			
				while(kont != this.count && !topatua) {


	                      		if (lag.data.toString().compareToIgnoreCase(pElem.toString()) <= 0 ){
	  
	                            		return null;

	                      		}
				
					else if (lag.data.equals(pElem)) {
						
						topatua=true;
						return lag.data;
						
					}
					
					lag=lag.next;	
					kont++;
				
				}
				
			}
							
			return null;	
			
		}

	@Override
	public T remove(T pElem) {

		//Aurre-Baldintza:	---
		/*Post-Baldinta: 	Elementua badago, zerrendatik ezabatuko da eta datua bueltatuko da, bestela null.
		 					era eraginkorrean ezabatuko da elementua*/
		//Kostua:			O(n);
		
		/* Proba kasuak:
		  
		 	- 1. Zerrenda hutsa izatea
		 	- 2. Elementua zerrendan dago eta elementu bakarreko zerrenda denean.
		 	- 3. Elementua zerrendan ez dago eta elementu bakarreko zerrenda denean.
		 	- 4. Elementua zerrendan dago eta elementu anitzeko zerrenda denean: Lehenengoa denean.
		 	- 5. Elementua zerrendan dago eta elementu anitzeko zerrenda denean: Erdialdean dagoenean.
		 	- 6. Elementua zerrendan dago eta elementu anitzeko zerrenda denean: Amaieran dagoenean.
		 	- 7. Elementua zerrendan ez dagoenean eta elementu anitzeko zerrenda denean: Lehenengoa baino txikiagoa denean.
		 	- 8. Elementua zerrendan ez dagoenean eta elementu anitzeko zerrenda denean: Erdialdeko bat baino txikiagoa denean.
		 	- 9. Elementua zerrendan ez dagoenean eta elementu anitzeko zerrenda denean: Guztiak baino handiagoa denean.
		 */
		
		Node unekoa	= super.first; //Bere atributuak ama klasean definituta daudenez, "super" erreferentzia erabili da.
		Comparable ezabatutakoa = null;
		
		if(unekoa != null){
			
			//Unekoaren aurrekoa eta hurrengoen erakusleak egiten, ulermena errazteko
			Node aurrekoa	= unekoa.prev;
			Node hurrengoa	= unekoa.next;
			
			//Elementu bakarreko zerrenda denean
			if(unekoa == aurrekoa && unekoa.data.equals(pElem)){
				
				ezabatutakoa	= unekoa.data;
				this.first		= null;		//Zerrenda hutsik geratuko da
				super.count--;              //Elementuen kopurua txikitu egin da.
			}
			
			else { //Edo ez da elementu bakarrekoa edo ez dago zerrendan
				
				boolean	topatua		= false;
				boolean txikiagoaDa	= false;
				int		kontatutakoPosizio	= 0; //Zerrenda zirkularrean behin eta berriro ez zeharkatzeko
				
				while( kontatutakoPosizio < super.count && !topatua && !txikiagoaDa){
					
					if(unekoa.data.equals(pElem)){ //Aurkituta izan da.
						
						ezabatutakoa	= unekoa.data;
						topatua			= true;
						aurrekoa.next	= hurrengoa;
						hurrengoa.prev	= aurrekoa;
						super.count--;            //Elementuen kopurua txikitu egin da.
					}
					
					else if(unekoa.data.compareTo(pElem) > 0){ //Ez da aurkitu eta gainera oraingoa baino txikiagoa da
						
						txikiagoaDa = true;
					}
					
					else{ //Hurrengoa aztertu ahal izateko
						
						aurrekoa	= aurrekoa.next;
						unekoa		= unekoa.next;
						hurrengoa	= hurrengoa.next;
					}
				}
			}
		}
		
		return (T) ezabatutakoa;
	}
}

