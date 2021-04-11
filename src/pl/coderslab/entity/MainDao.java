package pl.coderslab.entity;


public class MainDao {
    User user = new User();

    public static void main(String[] args) {
        System.out.println("");

        // create user
        UserDao userDao = new UserDao();
        User user = new User();
        user.setUserName("Jordan Phil");
        user.setEmail("jordan.phil@gmail.com");
        user.setPassword("alabama987");
        userDao.createUser(user);

        // display user by id
        System.out.println("========================== Read - exist ========================");

        User read = userDao.readUser(10);
        if(read != null){
            System.out.println(read);
        } else {
            System.out.println("User does not exist!");
        }
        System.out.println("");

        System.out.println("========================== Read - do not exist ========================");
        User readUserDoNotExist = userDao.readUser(1);
        if(read != null){
            System.out.println(readUserDoNotExist);
        } else {
            System.out.println("User does not exist!");
        }
        System.out.println("");

        // update user
        User userDataAmendment = userDao.readUser(13);
        userDataAmendment.setUserName("Francesco Castillo");
        userDataAmendment.setEmail("framnky.castillo@gmail.com");
        userDataAmendment.setPassword("password1234");
        userDao.updateUser(userDataAmendment);

        // delete user
        userDao.deleteUser(9);
        userDao.deleteUser(10);
        userDao.deleteUser(11);


        // display all users
        System.out.println("========================== Display final list after deletion/update  ========================");
        User[] all = userDao.displayAll();
        for (int i = 0; i < all.length; i++) {
            System.out.println(all[i]);
        }


    }
}
