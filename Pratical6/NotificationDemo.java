interface Notifier {
    void send(String message);
}

interface Urgent {
}

class EmailNotifier implements Notifier, Urgent {
    public void send(String message) {
        System.out.println("Email: " + message);
    }
}

class SMSNotifier implements Notifier {
    public void send(String message) {
        System.out.println("SMS: " + message);
    }
}

public class NotificationDemo {
    public static void main(String[] args) {

        Notifier email = message ->
                System.out.println("Email: " + message);

        Notifier sms = message ->
                System.out.println("SMS: " + message);

        Notifier[] senders = {email, sms};

        String message = "Exam starts at 10 AM";

        for (Notifier sender : senders) {
            sender.send(message);
        }

        // Urgent sender
        Notifier urgentEmail = new EmailNotifier();

        if (urgentEmail instanceof Urgent) {
            urgentEmail.send(message);
            urgentEmail.send(message);
        }
    }
}