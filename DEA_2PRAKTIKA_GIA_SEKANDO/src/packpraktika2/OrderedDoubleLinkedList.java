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
		
	    int kontX = this.count; //this.count-aren laguntzaile bat
	    int kontY = pZerrenda.count; //pZerrenda.count-aren laguntzaile bat	 	    
	    
	    while (kontX !=0 && kontY !=0) {

		if (this.first.data.equals(pZerrenda.first.data)) { //elementu biak berdinak badira, pZerrendako elemntua sartuko da lista berrian eta lista biak desplazatuko dira
	    		
	    		linkedListBerria.add(pZerrenda.first.data);
	    		
	    		pZerrenda.first.next.prev = pZerrenda.first.prev;	        	
	        	pZerrenda.first = pZerrenda.first.next;	             
	            	kontY--;            
	        	
	        	this.first.next.prev = this.first.prev;	      	
	        	this.first = this.first.next;	        	
	            	kontX--;	    		
	    		
	    	}
	    	
	        else if (this.first.data.toString().compareToIgnoreCase(pZerrenda.first.data.toString()) <= 0 ) { //this.first-ren data string bezela, pZerrendarena baino handiagoa bada, orduan pZerrendaren lehenengo elementua sartu egingo da gure zerrenda berrian
	        	
	        	linkedListBerria.add(pZerrenda.first.data);
	        	
	        	pZerrenda.first.next.prev = pZerrenda.first.prev;	        	
	        	pZerrenda.first = pZerrenda.first.next;
	             
	            	kontY--;
	            
	        } 
	        
	        else { 
	        	
	        	linkedListBerria.add(this.first.data);
	        	
	        	this.first.next.prev = this.first.prev;	      	
	        	this.first = this.first.next;	        	
	        	
	            	kontX--;	        	
	            
	        }  	        
	        
	    }
	    
	    if (kontX == 0){ //this zerrenda hutsa bada, orduan pZerrendan zerrendan dagoen objektu guztiak linkedListBerria-n gehitu behar dira
	    	
	    	linkedListBerria.first.prev.next = pZerrenda.first;
	    	pZerrenda.first.prev = linkedListBerria.first.prev;	    	
	    	
	    }
	    
	    if (kontY == 0) { //pZerrenda zerrenda hutsa bada, orduan this zerrendan dagoen objektu guztiak linkedListBerria-n gehitu behar dira
	    	
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
	public T remove(T elem) {
		// TODO Auto-generated method stub
		return null;
	}


}
