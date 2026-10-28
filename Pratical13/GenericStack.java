class Stack<T> {
    private Object[] items;
    private int top;

    public Stack(int size) {
        items = new Object[size];
        top = -1;
    }

    public void push(T value) {
        if (top == items.length - 1) {
            System.out.println("Stack is full");
            return;
        }

        items[++top] = value;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (top == -1) {
            System.out.println("Stack is empty");
            return null;
        }

        return (T) items[top--];
    }
}

public class GenericStack {
    public static void main(String[] args) {
        Stack<Integer> s1 = new Stack<>(5);
        s1.push(10);
        s1.push(20);

        System.out.println("Integer popped: " + s1.pop());

        Stack<String> s2 = new Stack<>(5);
        s2.push("Java");
        s2.push("Python");

        System.out.println("String popped: " + s2.pop());
        System.out.println("String popped: " + s2.pop());
        System.out.println("String popped: " + s2.pop());
    }
}