package service.serviceImpl;

import dao.ContactDao;
import dao.daoImpl.ContactDaoImpl;
import model.Contact;

import java.util.List;

public class ContactServiceImpl implements ContactDao {
    ContactDaoImpl contactDao = new ContactDaoImpl();

    @Override
    public void createContactTable() {
        contactDao.createContactTable();
    }

    @Override
    public void saveContact(Contact contact) {
        contactDao.saveContact(contact);
    }

    @Override
    public Contact getContactById(Long id) {

        return contactDao.getContactById(id);
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }

    @Override
    public List<Contact> getAllPhoneContacts(Long contactId) {
        return contactDao.getAllPhoneContacts(contactId);
    }

    @Override
    public List<Contact> getAllUserContacts(Long userId) {
        return contactDao.getAllUserContacts(userId);
    }

    @Override
    public void getPhoneContactsCount(Long phoneId) {
contactDao.getPhoneContactsCount(phoneId);
    }

    @Override
    public void getUserContactsCount(Long userId) {
        contactDao.getUserContactsCount(userId);
    }

    @Override
    public void updateContactInfo(Long id, Contact contact) {
        contactDao.updateContactInfo(id,contact);
    }

    @Override
    public void deleteAllPhoneContactsByPhoneId(Long phoneId) {
        contactDao.deleteAllPhoneContactsByPhoneId(phoneId);
    }
}
