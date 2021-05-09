//--------------------------------------------
//Part: 2 (ShowList) assignment 4
//Written by: Yason Bedoshvili ID No: 40058829
//

import java.util.NoSuchElementException;

public class ShowList {
	public class ShowNode { // possible privacy leak
		private TVShow tvShow;
		private ShowNode next;

		public ShowNode() {
			tvShow = null;
			next = null;
		}

		/**
		 * 
		 * @param tvShow Object of TVShow
		 * @param next   node
		 */
		public ShowNode(TVShow tvShow, ShowNode next) {
			this.tvShow = tvShow;
			this.next = next;
		}

		/**
		 * 
		 * @param c copy constructor of ShowNode
		 */
		public ShowNode(ShowNode c) {
			this.tvShow = c.tvShow.clone();
			this.next = c.next;
		}

		/**
		 * clone method
		 */
		public ShowNode clone() {
			return new ShowNode(this);
		}

		/**
		 * 
		 * @return returns TVShow object
		 */
		public TVShow getTVShow() {
			this.getNext();
			return this.tvShow;
		}

		/**
		 * 
		 * @param tvShow sets TVShow object
		 */
		public void setTVShow(TVShow tvShow) {
			this.tvShow = tvShow;
		}

		/**
		 * 
		 * @return returns pointer
		 */
		public ShowNode getNext() {
			return this.next;
		}

		/**
		 * 
		 * @param next sets pointer
		 */
		public void setNext(ShowNode next) {
			this.next = next;
		}
	}

	private ShowNode head;
	private int size;
	private static int counter = 0;

	public ShowList() {
		head = null;
		size = 0;
	}

	/**
	 * 
	 * @param sl Copy constructor of ShowList
	 */
	public ShowList(ShowList sl) {
		if (sl.head == null)
			head = null;
		else {
			head = null;
			ShowNode t1, t2, t3; // create 3 temporary pointers

			t1 = sl.head;
			t2 = t3 = null;
			while (t1 != null) {
				if (head == null) // this happens only once
				{
					t2 = new ShowNode(t1);
					head = t2;
				} else {
					t3 = new ShowNode(t1);
					t2.next = t3;
					t2 = t3;
				}
				t1 = t1.next;
			}
			t2 = t3 = null; // t1 is already null by now
		}
	}

	/**
	 * 
	 * @return returns counter
	 */
	public static int getCounter() {
		return counter;
	}

	/**
	 * 
	 * @param c sets counter
	 */
	private static void setCounter(int c) {
		counter = c;
	}

	/**
	 * static method that increments counter
	 */
	private static void incCounter() {
		counter++;
	}

	/**
	 * 
	 * @return returns size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 
	 * @param tv method that adds a node to start
	 */
	public void addToStart(TVShow tv) {
		head = new ShowNode(tv, head);
		size++;
	}

	/**
	 * 
	 * @param tv    Object of TVShow
	 * @param index index at which to insert
	 */
	public void insertAtIndex(TVShow tv, int index) {
		if (index < 0 || index >= size) {
			throw new NoSuchElementException();
		}
		if (index == 0) {
			addToStart(tv);
			return;
		}
		index--;

		ShowNode start = head;
		while (index != 0) {
			start = start.next;
			index--;
		}
		start.next = new ShowNode(tv, start.next);
		size++;
	}

	/**
	 * 
	 * @param index deletes node from the given index
	 */
	public void deleteFromIndex(int index) {
		if (index < 0 || index >= size) {
			throw new NoSuchElementException();
		}
		if (index == 0) {
			deleteFromStart();
			return;
		}
		index--;
		ShowNode start = head;
		while (index != 0) {
			start = start.next;
			index--;
		}
		start.next = start.next.next;
		size--;
	}

	/**
	 * method that deletes a node from start
	 */
	public void deleteFromStart() {
		if (head != null) {
			head = head.next;
			size--;
		}
	}

	/**
	 * 
	 * @param tv    Object of TVShow
	 * @param index index at which to replace a node
	 */
	public void replaceAtIndex(TVShow tv, int index) {
		if (index < 0 || index >= size)
			return;
		index--;
		ShowNode start = head;
		while (index != 0) {
			start = start.next;
			index--;
		}
		start.next.setTVShow(tv);
	}

	/**
	 * 
	 * @param tv Object of TVShow
	 * @return returns a node if finds it
	 */
	public ShowNode find(TVShow tv) {
		if (head == null)
			return null;
		ShowNode t = head;
		setCounter(0);
		while (t != null) {
			incCounter();
			if (t.tvShow.equals(tv))
				return t;
			t = t.next;
		}
		return null;
	}

	/**
	 * 
	 * @param id ShowID String
	 * @return returns a node if finds it
	 */
	public ShowNode find(String id) {
		if (head == null)
			return null;
		ShowNode t = head;
		setCounter(0);
		while (t != null) {
			incCounter();
			if (t.tvShow.getShowID().equals(id))
				return t;
			t = t.next;
		}
		return null;
	}

	/**
	 * 
	 * @param show Object of TVShow
	 * @return returns boolean if object TVShow is there
	 */
	public boolean contains(TVShow show) {
		return (find(show) != null);
	}

	/**
	 * 
	 * @param showID showID String
	 * @return returns boolean if object TVShow is there
	 */
	public boolean contains(String showID) {
		return (find(showID) != null);
	}

	/**
	 * 
	 * @param other ShowList's object
	 * @return returns true if objects of ShowList are equal
	 */
	public boolean equals(ShowList other) {
		if (other == null)
			return false;
		else if (getClass() != other.getClass())
			return false;
		else if (size != other.size)
			return false;
		else {
			ShowNode t = head;
			ShowNode tOther = other.head;
			while (t != null) {
				if (!(t.tvShow.equals(tOther.tvShow)))
					return false;

				t = t.next;
				tOther = tOther.next;
			}
			return true;
		}
	}

	/**
	 * method toString
	 */
	public String toString() {
		ShowNode t = head;
		String result = "";
		while (t != null) {
			result += t.tvShow.toString() + "\n";
			t = t.next;
		}
		return result;
	}
}
