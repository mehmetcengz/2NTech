package data;

import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;

@Getter
@Setter

public class UserData {
    private String name;
    private String tcKimlikNo;
    private String phoneNumber;
    private String email;
    private String birthday;

    public static UserData generateUserData(){
        Faker faker = new Faker();

        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();

        int firstDigit = faker.number().numberBetween(1,10);
        String remainingDigits = faker.number().digits(10);
        String tcKimlikNo = firstDigit + remainingDigits;

        String phoneNumber = "05" + faker.number().numberBetween(0, 10) + faker.number().numberBetween(0, 10)
                + faker.number().numberBetween(100, 1000) + faker.number().numberBetween(1000, 10000);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String birthday = sdf.format(faker.date().birthday());

        UserData userData = new UserData();
        userData.setName(name);
        userData.setTcKimlikNo(tcKimlikNo);
        userData.setPhoneNumber(phoneNumber);
        userData.setEmail(email);
        userData.setBirthday(birthday);
        return userData;
    }

}
