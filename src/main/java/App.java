import config.Config;
import model.Contact;
import model.Phone;
import model.User;
import service.serviceImpl.ContactServiceImpl;
import service.serviceImpl.PhoneServiceImpl;
import service.serviceImpl.UserServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
User user = new User(2,"bek",13);
       // System.out.println(Config.connectionToDataBase());
        UserServiceImpl userService = new UserServiceImpl();
        PhoneServiceImpl phoneService = new PhoneServiceImpl();
        ContactServiceImpl contactService = new ContactServiceImpl();
       // userService.createUserTable();
        //userService.saveUser(user);
       // System.out.println(userService.getAllUsers());
      //  userService.updateUserInfo(1L,user);
      //  userService.cleanUserTable();
        //userService.deleteById(4L);
      //  userService.dropUserTable();



      //  phoneService.createPhoneTable();
       // phoneService.savePhoneTable(new Phone("S22 ultra","Samsung",80000,1L));
       // phoneService.updatePhone(3L,new Phone ("Iphone 13 pro","Iphone",90000));
//        System.out.println(phoneService.getAllUsersPhone(1L));
       // System.out.println(phoneService.getAllUserSortedUserPhone(1L, "asc"));
       // System.out.println(phoneService.getYoungerUserPhone());
       // System.out.println(phoneService.getPhoneById(3L));
       // phoneService.deletedPhoneById(3L);
       //


        // contactService.createContactTable();
           // contactService.saveContact(new Contact("Manas","0221010101",5L));
       // System.out.println(contactService.getContactById(4L));
       // System.out.println(contactService.getAllContacts());
       // System.out.println(contactService.getAllPhoneContacts(4L));
       // System.out.println(contactService.getAllUserContacts(1L));
//        contactService.getPhoneContactsCount(5L);
//        contactService.getUserContactsCount(1L);
//        contactService.updateContactInfo(5L,new Contact("Bolot","0221020202",4L));
//        contactService.deleteAllPhoneContactsByPhoneId(5L);
    }
}
