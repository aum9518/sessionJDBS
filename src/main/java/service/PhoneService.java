package service;

import model.Phone;

import java.util.List;

public interface PhoneService {
    void createPhoneTable();
    void savePhoneTable(Phone phone);
    void updatePhone(Long id,Phone phone);
    List<Phone> getAllUsersPhone(Long userId);
    List<Phone> getAllUserSortedUserPhone(Long userId, String ascOrDesc);
    Phone getYoungerUserPhone();
    Phone getPhoneById(Long id);
    void deletedPhoneById(Long id);
}
