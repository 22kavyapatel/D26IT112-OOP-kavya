interface Switchable {
    void on();
    void off();

    default void toggle() {
        on();
    }
}

class Fan implements Switchable {
    public void on() {
        System.out.println("Fan is ON");
    }

    public void off() {
        System.out.println("Fan is OFF");
    }
}

class Light implements Switchable {
    public void on() {
        System.out.println("Light is ON");
    }

    public void off() {
        System.out.println("Light is OFF");
    }
}

interface SwitchPermission {
    boolean canSwitchOn(String device, int hour);
}

public class RemoteControl {
    public static void main(String[] args) {

        Switchable[] devices = {new Fan(), new Light()};

        for (Switchable device : devices) {
            device.toggle();
        }

        // Anonymous class
        SwitchPermission permission = new SwitchPermission() {
            public boolean canSwitchOn(String device, int hour) {
                return hour >= 6 && hour <= 22;
            }
        };

        System.out.println("Fan allowed: "
                + permission.canSwitchOn("Fan", 10));

        // Lambda
        SwitchPermission permission2 =
                (device, hour) -> hour >= 6 && hour <= 22;

        System.out.println("Light allowed: "
                + permission2.canSwitchOn("Light", 23));
    }
}