interface Notifier {
    void send(String message);
}

interface Urgent {
}

class Email implements Notifier {

    public void send(String message) {
        System.out.println("Email: " + message);
    }
}

class SMS implements Notifier {

    public void send(String message) {
        System.out.println("SMS: " + message);
    }
}

class UrgentEmail implements Notifier, Urgent {

    public void send(String message) {
        System.out.println("Urgent Email: " + message);
    }
}

class UrgentSMS implements Notifier, Urgent {

    public void send(String message) {
        System.out.println("Urgent SMS: " + message);
    }
}

public class Notification {

    public static void main(String[] args) {

        // Lambda senders
        Notifier email = message ->
            System.out.println("Email: " + message);

        Notifier sms = message ->
            System.out.println("SMS: " + message);

        Notifier[] senders = {email, sms};

        System.out.println("Normal Message:");

        for (Notifier n : senders) {
            n.send("Hello Students!");
        }

        // Urgent senders
        UrgentEmail ue = new UrgentEmail();
        UrgentSMS us = new UrgentSMS();

        System.out.println("\nUrgent Message:");

        ue.send("Important Notice!");
        ue.send("Important Notice!");

        us.send("Important Notice!");
        us.send("Important Notice!");
    }
}