import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

class SignupForm {
    @NotBlank
    String username;

    @MaxLength(10)
    String city;

    SignupForm(String username, String city) {
        this.username = username;
        this.city = city;
    }
}

public class FormValidator {

    public static ArrayList<String> validate(Object obj)
            throws IllegalAccessException {

        ArrayList<String> errors = new ArrayList<>();

        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(obj);

            if (field.isAnnotationPresent(NotBlank.class)) {
                if (value == null || value.toString().trim().isEmpty()) {
                    errors.add(field.getName() + " must not be blank");
                }
            }

            if (field.isAnnotationPresent(MaxLength.class)) {
                MaxLength rule = field.getAnnotation(MaxLength.class);

                if (value instanceof String &&
                        ((String) value).length() > rule.value()) {
                    errors.add(field.getName() + " exceeds maximum length");
                }
            }
        }

        return errors;
    }

    public static void main(String[] args) throws IllegalAccessException {
        SignupForm form = new SignupForm("", "AhmedabadCity");

        ArrayList<String> errors = validate(form);

        for (String error : errors) {
            System.out.println(error);
        }
    }
}