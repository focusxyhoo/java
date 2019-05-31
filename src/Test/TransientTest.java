import java.io.*;

/**
 * 使用 transient 关键字不序列化某个变量
 * 注意：读取数据的顺序一定要和存放数据的顺序保持一致。
 *
 * @author focusxyhoo
 * @date 2019-05-27 13:19
 */
public class TransientTest {
    public static void main(String[] args) {

        User user = new User();
        user.setUserName("focusxyhoo");
        user.setPasswd("abcdefg");

        System.out.println("在序列化之前的读取结果：");
        System.out.println("user name: " + user.getUserName());
        System.out.println("pass word: " + user.getPasswd());

        try {
            ObjectOutputStream os = new ObjectOutputStream(
                    new FileOutputStream("/Users/huxiaoyang/Desktop/javaTest/src/resource/TransientTest.txt")
            );
            os.writeObject(user);
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ObjectInputStream is = new ObjectInputStream(
                    new FileInputStream("/Users/huxiaoyang/Desktop/javaTest/src/resource/TransientTest.txt")
            );
            user = (User) is.readObject();
            is.close();

            System.out.println("\n序列化之后读取的结果：");
            System.out.println("user name: " + user.getUserName());
            System.out.println("pass word: " + user.getPasswd());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userName;
    private transient String passwd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
