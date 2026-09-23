class MyResource implements AutoCloseable {

    public void use() {
        System.out.println("Resource is used");
    }

    public void close() {
        System.out.println("Resource closed");
    }
}

public class ResourceDemo {
    public static void main(String[] args) {

        try (MyResource r = new MyResource()) {
            r.use();
            throw new RuntimeException("Original error");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
