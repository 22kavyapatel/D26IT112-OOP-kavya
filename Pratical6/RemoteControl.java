interface Switchable {
    void on();
    void off();

    default void toggle() {
        System.out.println("Device Toggled");
    }
}

interface Permission {
    boolean canSwitch(int hour);
}

class Fan implements Switchable {

    public void on() {
        System.out.println("Fan ON");
    }

    public void off() {
        System.out.println("Fan OFF");
    }
}

class Light implements Switchable {

    public void on() {
        System.out.println("Light ON");
    }

    public void off() {
        System.out.println("Light OFF");
    }
}

public class RemoteControl {

    public static void main(String[] args) {

        Switchable[] devices = {
            new Fan(),
            new Light()
        };

        System.out.println("Remote Control:");

        for (Switchable d : devices) {
            d.on();
            d.toggle();
            d.off();
        }

        // Anonymous Class
        Permission p1 = new Permission() {
            public boolean canSwitch(int hour) {
                return hour >= 6 && hour <= 22;
            }
        };

        // Lambda
        Permission p2 = hour -> hour >= 6 && hour <= 22;

        System.out.println("Anonymous Class: " + p1.canSwitch(10));
        System.out.println("Lambda: " + p2.canSwitch(23));
    }
}
