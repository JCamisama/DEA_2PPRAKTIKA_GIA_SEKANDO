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

	public T remove(T elem) {
		// TODO Auto-generated method stub
		return null;
	}
}
