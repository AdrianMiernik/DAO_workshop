package pl.coderslab.entity;

import org.mindrot.jbcrypt.BCrypt;

public class MainDao {
    User user = new User();

    public static void main(String[] args) {

        // createUSer - dodawanie nowego użytkownika do bazy danych
        UserDao userDao = new UserDao();
        User user = new User();
        user.setUserName("magelan177");
        user.setEmail("magelan.l@gmaik.com");
        user.setPassword("kwiatek2020");
        //userDao.createUser(user);

        // readUser - sprawdzanie metody
        User read = userDao.readUser(7);
        //System.out.println(read);

        User readUserDoNotExist = userDao.readUser(1);
        //System.out.println(readUserDoNotExist);

        // updateUser - zmiana danych użytkownika
      /*  User userDataAmendment = userDao.readUser(10);
        userDataAmendment.setUserName("magelan77");
        userDataAmendment.setEmail("magelan@gmail.com");
        userDataAmendment.setPassword("flower2020");
        userDao.updateUser(userDataAmendment);
*/
        // deleteUser - usunięcie użytkownika
        //userDao.deleteUser(9);

        // displayAllUsers - wyświetla wszytkich użytkowników w bazie
        User[] all = userDao.displayAll();
        for (int i = 0; i < all.length; i++) {
            System.out.println(all[i]);
        }

        // sprawdzanie czy


    }
}
