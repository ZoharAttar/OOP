public class Zoo {

	private String name;
	private List<Animal> animals;
	// constructor in case of empty list - just an zoo name
	public Zoo(String name) {
		this.animals = new List <Animal>();
		if (name == "") { //in case of an empty input
			this.name = "Temp";
		} else {
			this.name = name;
		}
	}
	// constructor in case of exist list
	public Zoo(String name, List<Animal> animals) {
		this.animals = copyList(animals);
		orderAnimals();
		if (name == "") { //in case of an empty input
			this.name = "Temp";
		} else {
			this.name = name;
		}
	}
	//doing a deep copy of the list to another
	private List<Animal> copyList (List<Animal> ls){
		List <Animal> ans = new List <Animal>();
		if (ls == null) {
			return null;
		}
		Node<Animal> pos = null;
		for (Node<Animal> p = ls.getFirst(); p != null ; p = p.getNext()) {
			pos = ans.insert(pos, p.getData());	
		}
		return ans;
	}
	//getters
	public String getName() {
		return name;
	}
	public List<Animal> getAnimals(){
		return copyList(animals);
	}
	//name setter
	public boolean setName(String name) {
		if (name == "") { //in case of an empty input
			return false;
		}
		this.name = name;
		return true;
	}
	//converting to string
	public String toString () {
		return ""+this.name+": \n"+animals.toString();
	}
	public int animalListLength () {
		int count = 0;
		if (animals==null) {
			return 0;
		}
		for (Node <Animal> p = animals.getFirst() ; p!=null ; p=p.getNext()) {
			count++;
		}
		return count;
	}
	//check if an animal in the zoo list
	public boolean isAnimalInZoo (Animal animal) {
		for (Node <Animal> p = animals.getFirst() ; p!=null ; p=p.getNext()) {
			if (animal.toString().equals(p.toString())) {
				return true;
			}
		}
		return false;
	}
	//inserting a new animal to the zoo list
	public void insertAnimal (Animal animal) {

		if (!isAnimalInZoo(animal)) {
			Node <Animal> prev = animals.getFirst();
			if (animals.isEmpty()) {
				animals.insert(null, animal);
			}
			else 
				if (prev.getNext()!=null) {
					while (!animal.getType().equals(prev.getNext().getData().getType()) && prev.getNext().getNext()!=null) {
						prev = prev.getNext();
					}
					this.animals.insert(prev,animal);
					this.orderAnimals();
				}
		}
	}
	//remove an animal from the zoo list
	public void removeAnimal (Animal animal) {
		if (isAnimalInZoo(animal)) {
			Node <Animal> prev = animals.getFirst();
			while (!prev.getData().toString().equals(animal.toString())) {
				prev = prev.getNext();
			}
			animals.remove(prev);
		}
	}
	//return a list of all the animals who have bad BMI
	public List<Animal> badAnimalBMI (){
		List<Animal> badAnimalBMI = new List<Animal>();
		Node<Animal> pos = badAnimalBMI.getFirst();
		double animalBMI = 0;
		if(animalListLength()>1) {
			for (Node<Animal> p = animals.getFirst() ; p != null ; p = p.getNext()) {
				animalBMI = ( p.getData().getWeight() ) / ( p.getData().getHeight() );
				if (animalBMI>80) {
					pos = badAnimalBMI.insert(pos, p.getData());
				}
			}
		}
		animalBMI = ( pos.getData().getWeight() ) / ( pos.getData().getHeight() );
		return badAnimalBMI;
	}
	//return an animal to it original cage, by they diet : 1- carnivore , 2 - herbivore
	public void backToMainCage (Animal animal) {
		if (isAnimalInZoo(animal)) {
			if (animal.getDiet().equals("carnivore")) {
				animal.setCage(1);
			}
			else {
				animal.setCage(2);
			}
		}
	}
	//return all the animals to there cages, by they diet : 1- carnivore , 2 - herbivore
	public void returnAllToBaseCages () {
		Node <Animal> temp = animals.getFirst();
		while (temp !=null) {
			if (temp.getData().getDiet().equals("carnivore")) {
				temp.getData().setCage(1); 
			}
			else {
				temp.getData().setCage(2);
			}
			temp = temp.getNext();	
		}
	}
	//giving a list of all animals in a cage
	public List<Animal> cageList (int cage){
		List<Animal> cageList = new List<Animal>();
		if (cage>0 && cage<6) {
			Node<Animal> pos = cageList.getFirst() ;
			for (Node<Animal> p = animals.getFirst() ; p != null ; p = p.getNext()) {
				if (cage == p.getData().getCage()) {
					pos = cageList.insert(pos, p.getData());
				}
			}
			return cageList;
		}
		return null;
	}
	//return all animal in a cage to there original cage 1/2 by they diet
	public void returnAnimalsFromACage (int cage) {
		Node <Animal> temp = animals.getFirst();
		while(temp!=null) {
			if (temp.getData().getCage() == cage && temp.getData().getDiet().equals("carnivore")) {
				temp.getData().setCage(1);
			}
			else if (temp.getData().getCage() == cage && temp.getData().getDiet().equals("herbivore"))	{
				temp.getData().setCage(2);
			}
			temp = temp.getNext();
		}
	}
	//getting a list of animals in a cage and return how many animals there are in this cage
	public int cageListLength (List <Animal> cageList) {
		int count = 0;
		for (Node <Animal> p = cageList.getFirst() ; p!=null ; p=p.getNext()) {
			count++;
		}
		return count;
	}
	//checking if there is a animal who lonely in a cage
	public List<Animal> lonelyAnimals () {
		List<Animal> lonelyAnimals = new List<Animal>();
		Node <Animal> pos = lonelyAnimals.getFirst();
		for(int i = 1; i < 6; i++) {
			int capacity = cageListLength(cageList(i));
			if(capacity == 1) {
				Node <Animal> p2= cageList(i).getFirst();
				pos = lonelyAnimals.insert(pos, p2.getData());
			}
		}
		return lonelyAnimals;
	}
	//
	private int emptyCage () { 
		//calls the function who counts capacity in cages. returns number of cage which is empty, or -1 if all cages are occupied.
		for(int i = 3; i < 6; i ++) {
			int capacity = cageListLength(cageList(i));
			if(capacity == 0) {
				return i;
			}
		}
		int occupied = -1;
		return occupied;
	}
	public List<Animal> findPartners (Animal animal){  
		List<Animal> PotentialPartners = new List<Animal>();
		if (!isAnimalInZoo(animal)) {
			return null;
		}
		Node<Animal> pos = PotentialPartners.getFirst();
		for(Node<Animal> i = animals.getFirst() ; i != null; i= i.getNext()) {
			if (i.getData().possibleBaby(animal)) {
				pos = PotentialPartners.insert(pos, i.getData());
			}
		}
		return PotentialPartners;
	}
	public Animal mating (Animal animal1, Animal animal2, String babyName) {
		if (isAnimalInZoo(animal1) == false || isAnimalInZoo(animal2) == false) {
			return null;
		}
		if (animal1.possibleBaby(animal2) == true && emptyCage() != -1) {
			animal1.setCage(emptyCage());
			animal2.setCage(animal1.getCage());
			Animal baby = animal1.mating(animal2, babyName);
			//making a new baby for animal1 using previous Animal mating (with only 2 parameters, because the first partner is
			//the one we call the function from. after this, previous Animal mating return a baby we insert to the zoo using previous function//
			baby.setCage(animal1.getCage());
			insertAnimal(baby);
			return baby;
		}
		return null;
	}
	private void orderAnimals() { // sort the animal list by the type (abc order)

		int check = 0; // help var

		do { // sorting the list
			Node<Animal> pos = this.animals.getFirst();
			check = 0; // zero out the help var
			while (pos != null) {
				if (pos.getNext() != null) {
					for (int i = 0; i < pos.getData().getType().length() && i < pos.getNext().getData().getType().length(); i++) { // check order loop
						if (pos.getNext().getData().getType().charAt(i) < pos.getData().getType().charAt(i)) { // wrong order
							Animal temp = pos.getData(); // save the animal
							this.animals.insert(pos.getNext(), temp); // switch the animals
							this.animals.remove(pos); // delete the extra one
							check++; // add to the help var
							break;
						}
						if (pos.getNext().getData().getType().charAt(i) == pos.getData().getType().charAt(i)) { // same letter
							i++;
						} else // right order
							break;
					}
				}
				pos = pos.getNext();
			}
		} while (check != 0); // if checker == 0 that mean that all of the switches were done
	}
}