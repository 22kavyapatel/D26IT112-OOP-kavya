class Thermostat {
    private String location;
    private int temperature;

    private static final int MIN = 16;
    private static final int MAX = 30;
    private static int activeCount = 0;

    // Constructor with location and temperature
    Thermostat(String location, int startTemp) {
        this.location = location;

        if (startTemp >= MIN && startTemp <= MAX)
            this.temperature = startTemp;
        else
            this.temperature = 22;

        activeCount++;
    }

    // Constructor with only location
    Thermostat(String location) {
        this(location, 22);
    }

    // Increase temperature
    void raise() {
        if (temperature < MAX)
            temperature++;
        else
            System.out.println("Already at maximum (30)");
    }

    // Decrease temperature
    void lower() {
        if (temperature > MIN)
            temperature--;
        else
            System.out.println("Already at minimum (16)");
    }

    int getTemperature() {
        return temperature;
    }

    static int getActiveCount() {
        return activeCount;
    }

    // Main method
    public static void main(String[] args) {

        Thermostat t1 = new Thermostat("Room 1", 20);
        Thermostat t2 = new Thermostat("Room 2");

        // Raise 10 times
        for (int i = 0; i < 10; i++) {
            t1.raise();
            System.out.println("Temperature: " + t1.getTemperature());
        }

        // Lower 20 times
        for (int i = 0; i < 20; i++) {
            t1.lower();
            System.out.println("Temperature: " + t1.getTemperature());
        }

        // Active thermostat count
        System.out.println("Active thermostats: " + Thermostat.getActiveCount());
    }
}