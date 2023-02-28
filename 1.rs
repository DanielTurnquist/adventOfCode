use std::fs;

fn main() {
    let input = fs::read_to_string("input.txt").expect("Failed to read input file");
    let inventories: Vec<&str> = input.trim().split("\n\n").collect();

    let mut max_calories = 0;
    let mut max_calories_elf = 0;

    for (i, inventory) in inventories.iter().enumerate() {
        let calories: Vec<u32> = inventory.lines().map(|line| line.parse().unwrap()).collect();
        let total_calories = calories.iter().sum();

        if total_calories > max_calories {
            max_calories = total_calories;
            max_calories_elf = i + 1;
        }
    }

    println!("Elf {} is carrying the most Calories: {}", max_calories_elf, max_calories);
}
