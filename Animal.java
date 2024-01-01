public class Animal {
	private String name;
	private String type;
	private String diet;
	private String gender;
	private int age;
	private double weight;
	private double height;
	private int cage;
	// constructor
	public Animal (String name, String type,String diet, String gender, int age, double weight, double height) {
		this.name = name;
		this.type = type;
		this.diet = diet;
		this.gender=gender;
		this.age=age;
		this.weight=weight;
		this.height=height;

		if (diet.equals("carnivore")) {
			cage = 1;
		}
		else if (diet.equals("herbivore")) {
			cage = 2;
		}
	}
	// getters
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public String getDiet() {
		return diet;
	}
	public String getGender() {
		return gender;
	}
	public int getAge() {
		return age;
	}
	public double getWeight(){
		return weight;
	}
	public double getHeight() {
		return height;
	}
	public int getCage() {
		return cage;
	}
	// setters
	public boolean setName(String name) {
		if (name=="") {
			return false;
		}
		this.name = name;
		return true;
	}
	public boolean setAge(int age) {
		if (age<=this.age) {
			return false;
		}
		this.age = age;
		return true;
	}
	public boolean setWeight(double weight) {
		if (weight<0) {
			return false;
		}
		this.weight = weight;
		return true;
	}
	public boolean setHeight (double height) {
		if (height<=this.height) {
			return false;
		}
		this.height = height;
		return true;
	}
	public boolean setCage(int cage) {
		if (cage>0 && cage<6) {//check if the cage input is valid
			if ((this.diet == "carnivore" && cage == 2) || (this.diet == "herbivore" && cage == 1) ) {//check that carnivore animal do not be with herbivore
				return false;
			}
			else {
				this.cage = cage;
				return true;
			}
		}
		return false;
	}
	//converting to String
	public String toString () {
		return ""+this.name+", "+this.type+", "+this.diet+", "
				+this.gender+", age: "+this.age+", weight: "
				+this.weight+", height: "+this.height+", cage: "
				+this.cage+"\n";
	}
	//check if 2 animal can make a baby
	public boolean possibleBaby (Animal other) {
		if (this.gender.equals(other.gender)) { //check if the gender is equal
			return false;
		}
		else if (this.type.equals(other.type)) { //check if the type is equal
			return true;
		}
		return false;
	}
	//create a new baby animal in case of possible mating
	public Animal mating (Animal other, String babyName) {
		if (possibleBaby(other)) {
			String BabyDiet; //to save the mother's diet
			if (other.gender.equals("female")) {//give BabyDiet his mother diet
				BabyDiet = other.diet;
			}
			else {
				BabyDiet = this.diet; 
			}
			return new Animal (babyName, this.type, BabyDiet, "female", 0 , (other.weight + this.weight)/6, (other.height + this.height)/6);//creating the new baby
		}
		return null; // in case the animals can not make a baby
	}
}
