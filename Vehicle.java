// Abstract base Vehicle class
public abstract class Vehicle {
    private String make;
    private String model;
    private int year;
    private String color;
    private int speed;
    private boolean engineRunning;

    public Vehicle(String make, String model, int year, String color) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.speed = 0;
        this.engineRunning = false;
    }

    // Abstract methods that must be implemented by all vehicles
    public abstract String getVehicleType();
    public abstract int getPassengerCapacity();
    public abstract double getCargoCapacity();
    public abstract double getMaxSpeed();
    public abstract String performSpecialFeature();

    // Concrete methods shared by all vehicles
    public String startEngine() {
        engineRunning = true;
        return year + " " + make + " " + model + "'s engine is now running";
    }

    public String stopEngine() {
        engineRunning = false;
        speed = 0;
        return year + " " + make + " " + model + "'s engine is now off";
    }

    public String accelerate(int speedIncrease) {
        if (!engineRunning) {
            return "Cannot accelerate - engine is off";
        }
        if (speed + speedIncrease > getMaxSpeed()) {
            speed = (int) getMaxSpeed();
            return "Maximum speed reached: " + speed + " mph";
        }
        speed += speedIncrease;
        return "Current speed: " + speed + " mph";
    }

    // Getters
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public String getColor() { return color; }
    public int getSpeed() { return speed; }
}

// Coupe class
class Coupe extends Vehicle {
    private boolean convertibleTopDown;

    public Coupe(String make, String model, int year, String color) {
        super(make, model, year, color);
        this.convertibleTopDown = false;
    }

    @Override
    public String getVehicleType() {
        return "Coupe";
    }

    @Override
    public int getPassengerCapacity() {
        return 4;  // Typical coupe capacity
    }

    @Override
    public double getCargoCapacity() {
        return 13.0;  // Average coupe trunk space in cubic feet
    }

    @Override
    public double getMaxSpeed() {
        return 155.0;  // Top speed in mph
    }

    @Override
    public String performSpecialFeature() {
        convertibleTopDown = !convertibleTopDown;
        return convertibleTopDown ? "Convertible top lowered" : "Convertible top raised";
    }
}

// Van class
class Van extends Vehicle {
    private boolean slidingDoorOpen;

    public Van(String make, String model, int year, String color) {
        super(make, model, year, color);
        this.slidingDoorOpen = false;
    }

    @Override
    public String getVehicleType() {
        return "Van";
    }

    @Override
    public int getPassengerCapacity() {
        return 7;  // Typical van capacity
    }

    @Override
    public double getCargoCapacity() {
        return 150.0;  // Average van cargo space in cubic feet
    }

    @Override
    public double getMaxSpeed() {
        return 112.0;  // Top speed in mph
    }

    @Override
    public String performSpecialFeature() {
        slidingDoorOpen = !slidingDoorOpen;
        return slidingDoorOpen ? "Sliding door opened" : "Sliding door closed";
    }
}

// SUV class
class SUV extends Vehicle {
    private boolean fourWheelDriveEngaged;

    public SUV(String make, String model, int year, String color) {
        super(make, model, year, color);
        this.fourWheelDriveEngaged = false;
    }

    @Override
    public String getVehicleType() {
        return "SUV";
    }

    @Override
    public int getPassengerCapacity() {
        return 5;  // Typical SUV capacity
    }

    @Override
    public double getCargoCapacity() {
        return 70.0;  // Average SUV cargo space in cubic feet
    }

    @Override
    public double getMaxSpeed() {
        return 130.0;  // Top speed in mph
    }

    @Override
    public String performSpecialFeature() {
        fourWheelDriveEngaged = !fourWheelDriveEngaged;
        return fourWheelDriveEngaged ? "4WD engaged" : "4WD disengaged";
    }
}

// Main class to demonstrate the vehicle hierarchy
class VehicleDemo {
    public static void main(String[] args) {
        // Create instances of each vehicle type
        Vehicle mustang = new Coupe("Ford", "Mustang", 2024, "Red");
        Vehicle sienna = new Van("Toyota", "Sienna", 2024, "Silver");
        Vehicle rav4 = new SUV("Toyota", "RAV4", 2024, "Blue");

        // Test each vehicle 
        testVehicle(mustang);
        testVehicle(sienna);
        testVehicle(rav4);
    }

    public static void testVehicle(Vehicle vehicle) {
        System.out.println("\n=== Testing " + vehicle.getVehicleType() + 
                         " (" + vehicle.getYear() + " " + vehicle.getMake() + 
                         " " + vehicle.getModel() + ") ===");
        System.out.println(vehicle.startEngine());
        System.out.println(vehicle.accelerate(50));
        System.out.println("Max Speed: " + vehicle.getMaxSpeed() + " mph");
        System.out.println("Passenger Capacity: " + vehicle.getPassengerCapacity());
        System.out.println("Cargo Capacity: " + vehicle.getCargoCapacity() + " cubic feet");
        System.out.println(vehicle.performSpecialFeature());
        System.out.println(vehicle.performSpecialFeature());  // Toggle feature back
    }
}