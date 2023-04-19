package service.serviceImpl;

import dao.daoImpl.PhoneDaoImpl;
import model.Phone;
import service.PhoneService;

import java.util.List;

public class PhoneServiceImpl implements PhoneService {
PhoneDaoImpl phoneDao = new PhoneDaoImpl();
    @Override
    public void createPhoneTable() {
        phoneDao.createPhoneTable();

    }

    @Override
    public void savePhoneTable(Phone phone) {
        phoneDao.savePhoneTable(phone);
    }

    @Override
    public void updatePhone(Long id, Phone phone) {
        phoneDao.updatePhone(id,phone);
    }

    @Override
    public List<Phone> getAllUsersPhone(Long userId) {
        return phoneDao.getAllUsersPhone(userId);
    }

    @Override
    public List<Phone> getAllUserSortedUserPhone(Long userId, String ascOrDesc) {
        return phoneDao.getAllUserSortedUserPhone(userId,ascOrDesc);
    }

    @Override
    public Phone getYoungerUserPhone() {
        return phoneDao.getYoungerUserPhone();
    }

    @Override
    public Phone getPhoneById(Long id) {
        return phoneDao.getPhoneById(id);
    }

    @Override
    public void deletedPhoneById(Long id) {
phoneDao.deletedPhoneById(id);
    }
}
