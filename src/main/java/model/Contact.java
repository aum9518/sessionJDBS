package model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

@ToString
public class Contact {
    private long id;
    private String contactName;
    private String phoneNumber;
    private Long phone_id;

    public Contact(String contactName, String phoneNumber, Long phone_id) {
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.phone_id = phone_id;
    }
}
