package packpraktika2;

public class UnorderedDoubleLinkedList<T extends Comparable<T>> extends DoubleLinkedList<T> implements UnorderedListADT<T> {
	
	public void addToFront(T elem) {
		
		// Aurrebaldintza: ----
		// Postbaldintza: elem zerrendaren hasieran gehitu da.
		// hasieran gehitu

				Node datua = new Node(elem);
				datua.next = this.first;
				datua.prev = this.first.prev;
				this.first = datua;

		
	}

	public void addToRear(T pElem) {
		
		//Aurre baldintza: -------
		//Post baldintza: elementu bat bukaeran gehituko da
		//Kostua: O(1)
			
			Node<T> berria = new Node(pElem);		
			
			if (this.isEmpty()) {
				
				first = berria;
				berria.prev  = berria;				
					
			}
			
			else {
				
				first.prev.next  = berria;			
				berria.next = first;
				berria.prev = first.prev;	
				first.prev = berria;
				
			}		

		}
	
	public void addAfter(T elem, T target) {
		// Aurrebaldintza: zerrendak gutxienez elementu bat dauka.
		// Postbaldintza: elem zerrendan gehitu da, target atzean.
		// KODEA OSATU ETA KOSTUA KALKULATU (AUKERAZKOA)
				Node<T> sartu = new Node(target);
				Node<T> lag;
				lag=first;		
				boolean topatua = false;	
				int kont = 0;
				
				while(kont != this.count && !topatua) {
					
					if (lag.data==elem) {
							
						topatua=true;
						sartu.next = lag.next;
						sartu.prev = lag;
						lag.next.prev = sartu;
						lag.next = sartu;			
					}
					lag=lag.next;	
					kont++;
				}	
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
				
					if (lag.data.equals(pElem)) {
						
						topatua=true;
						return lag.data;
						
					}
					
					lag=lag.next;	
					kont++;
				
				}
				
			}
							
			return null;	
			
		}

	public T remove(T pElem) {

		//Aurre-Baldintza:	---
		//Post-Baldinta: 	Elementua badago, zerrendatik ezabatuko da eta datua bueltatuko da, bestela null.
		//Kostua:			O(n);
		
		/* Proba kasuak:
		  
		 	- 1. Zerrenda hutsa izatea
		 	- 2. Elementua zerrendan dago eta elementu bakarreko zerrenda denean.
		 	- 3. Elementua zerrendan ez dago eta elementu bakarreko zerrenda denean.
		 	- 4. Elementua zerrendan dago eta elementu anitzeko zerrenda denean: Lehenengoa denean.
		 	- 5. Elementua zerrendan dago eta elementu anitzeko zerrenda denean: Erdialdean dagoenean.
		 	- 6. Elementua zerrendan dago eta elementu anitzeko zerrenda denean: Amaieran dagoenean.
		 	- 7. Elementua zerrendan ez dagoenean eta elementu anitzeko zerrenda denean.
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
				super.count--;            	//Elementuen kopurua txikitu egin da.
			}
			
			else { //Edo ez da elementu bakarrekoa edo ez dago zerrendan
				
				boolean	topatua		= false;
				int		kontatutakoPosizio	= 0; //Zerrenda zirkularrean behin eta berriro ez zeharkatzeko
				
				while( kontatutakoPosizio < super.count && !topatua){
					
					if(unekoa.data.equals(pElem)){ //Aurkituta izan da.
						
						ezabatutakoa	= unekoa.data;
						topatua			= true;
						aurrekoa.next	= hurrengoa;
						hurrengoa.prev	= aurrekoa;
						super.count--;            //Elementuen kopurua txikitu egin da.
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
