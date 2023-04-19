package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private long id;
    private String fullName;
    private int age;

    public User(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }
}
