//The LetterInventory takes input and keeps track of the number of times each
//letter was used. Also allows user to manipulate and retrieve information from
//the object.
public class LetterInventory {
	public static final int ALPHASIZE = 26;
	
	private int[] inventory;
	private int size;
	
	//Constructs LetterInventory object out of string that was passed
	public LetterInventory(String data) {
		this.inventory = new int[ALPHASIZE];
		this.size = 0;
		
		for(int i = 0; i < data.length(); i++) {
			if(isLetter(data.charAt(i))) {
				this.inventory[difference(data.charAt(i))]++;
				this.size++;
			}
		}
	}
	
	//Helper method that returns an integer representing the number of letters away from A
	//the given character is. Ex: difference(d) returns 3
	private int difference(char x) {
		return Character.toLowerCase(x) - 'a';
	}
	
	//Helper method that returns a boolean determining if the given character
	//is alphabetic
	private boolean isLetter(char x) {
		return difference(x) >= 0 && difference(x) < 26;
	}
	
	//Throws an IllegalArgumentException if given a non alphabetic character
	//Returns the current count of the letter passed as a parameter
	public int get(char letter) {
		if(!isLetter(letter)) {
			throw new IllegalArgumentException("Letter given not valid letter");
		}
		
		return this.inventory[difference(letter)];
	}
	
	//Throws an IllegalArgumentException if given a non alphabetic character,
	//or a negative number
	//Sets the count of the given letter to the given value
	public void set(char letter, int value) {
		if(!isLetter(letter)) {
			throw new IllegalArgumentException("Letter given not valid");
		}
		
		this.size -= this.inventory[difference(letter)] - value;
		this.inventory[difference(letter)] = value;
	}
	
	//Returns the number of occurrences of all the letters in the inventory
	public int size() {
		return this.size;
	}
	
	//Returns a boolean determining whether or not the inventory is empty
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	//Returns a string representing the letters in alphabetical order repeated
	//as many times as they occurred in the inventory.
	//Ex: challenge printed from a letter inventory would print [aceeghlln]
	public String toString() {
		String s = "[";
		
		for(int i = 0; i < this.inventory.length; i++) {
			for(int j = 0; j < this.inventory[i]; j++) {
				s += (char) ('a' + i);
			}
		}
		
		return s + "]";
	}
	
	public double getLetterPercentage(char letter) {
		if(!isLetter(letter)) {
			throw new IllegalArgumentException("Letter given not valid");
		}
		
		return (double) this.inventory[letter - 'a']/size;
	}
	
	//Given a LetterInventory object, this method returns a new LetterInventory object who's
	//data represents the sum of all the counts from the LetterInventory object the method is
	//being called on added to the corresponding counts from the given LetterInventory's counts
	public LetterInventory add(LetterInventory other) {
		LetterInventory sum = new LetterInventory("");
		
		for(int i = 0; i < ALPHASIZE; i++) {
			sum.set((char) ('a' + i), this.inventory[i] + other.get((char) ('a' + i)));
		}
		
		return sum;
	}
	
	//Throws IllegalArgumentexception if any count results in a negative
	//Given a LetterInventory object, this method returns a new LetterInventory object who's
	//data represents all the counts from the LetterInventory object the method is
	//being called on subtracted by the corresponding counts from the given LetterInventory's counts
	public LetterInventory subtract(LetterInventory other) {
		LetterInventory difference = new LetterInventory("");
		
		for(int i = 0; i < ALPHASIZE; i++) {
			if(this.inventory[i] - other.get((char) ('a' + i)) < 0){
	           return null;
	        }
			difference.set((char) ('a' + i), this.inventory[i] - other.get((char) ('a' + i)));
			if(difference.get((char) ('a' + i)) < 0) {
				return null;
			}
		}
		
		return difference;
	}
}







