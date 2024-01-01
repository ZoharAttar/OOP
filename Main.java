public class Main {
	public static void main(String[] args) {
		
		
		Animal a1 = new Animal ("Asaf", "Tiger", "carnivore", "male", 3, 250, 2.5);
		Animal a2 = new Animal ("Ehud", "Giraffe", "herbivore", "male", 2, 272, 4.6);
		Animal a3 = new Animal ("Dana", "Giraffe", "herbivore", "female", 3, 250, 6.1);
		Animal a4 = new Animal ("Bella", "Tiger", "herbivore", "female", 1, 221, 3.9);
		Animal a5 = new Animal ("Gigi", "Bear", "carnivore", "male", 3, 200, 1.8);
		
		List<Animal> animals = new List<Animal>();
		animals.insert(null,a1);
		animals.insert(null,a2);
		animals.insert(null,a3);
		animals.insert(null,a4);
		animals.insert(null,a5);
		
		System.out.println("List of animals:");
		System.out.println(animals);
		
		Zoo zoo = new Zoo ("Safari",animals);
		System.out.println("How many animals does the zoo have? "+zoo.animalListLength());
		System.out.println(zoo.toString());
		

		Animal a6 = new Animal ("Tamar", "Tiger", "carnivore", "female", 5, 165, 2.8);
		zoo.insertAnimal(a6); //Tamar
		zoo.removeAnimal(a3); //Dana
		System.out.println("Without Dana and with Tamar:");
		System.out.println(zoo.toString());
		
		System.out.println("Animals with a bad BMI score:");
		System.out.println(zoo.badAnimalBMI());
		
		System.out.println("Partners for Asaf (tiger, male):");
		System.out.println(zoo.findPartners(a1));
		
		System.out.println("Is the lonely list empty? "+zoo.lonelyAnimals().isEmpty()+"\n");
		a2.setCage(3); //Ehud
		System.out.println("And now... are there any lonely animals? \n"+zoo.lonelyAnimals());
		System.out.println(zoo.toString());
		
		
		Animal a7 = zoo.mating(a1,a4,"Gal");
		System.out.println("New baby: \n"+zoo);
		
		System.out.println("Cage 4: \n"+zoo.cageList(4));
		
		zoo.returnAllToBaseCages();
		System.out.println("After return all:\n"+zoo);
		
		System.out.print("The End");

	}
}
