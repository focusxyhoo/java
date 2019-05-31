import java.io.*;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-05-29
 * time        : 09:12
 * description :
 */
public class SerialVersionUIDTest {

    /**
     * 测试
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        serializeCustomer();
        Customer customer = deserializeCustomer();
        System.out.println(customer);
    }

    /**
     * 序列化 Customer 对象
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void serializeCustomer() throws FileNotFoundException, IOException {
        Customer customer = new Customer("xiaoming", 21, "male");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                new File("/Users/huxiaoyang/Desktop/javaTest/src/resource/serialVersionUIDTest.txt")));
        oos.writeObject(customer);
        System.out.println("Customer 对象序列化成功");
        oos.close();
    }

    /**
     * 反序列化 Customer 对象
     *
     * @return
     * @throws Exception
     * @throws IOException
     */
    private static Customer deserializeCustomer() throws Exception, IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                new File("/Users/huxiaoyang/Desktop/javaTest/src/resource/serialVersionUIDTest.txt")));
        Customer customer = (Customer) ois.readObject();
        System.out.println("Customer 对象反序列化成功");
        return customer;
    }
}

/**
 * Customer 类实现了 Serializable 接口，可以被序列化
 */
class Customer implements Serializable {
    // Customer 类中没有指定 serialVersionUID
    private String name;
    private int age;

    // 当添加一项属性时，由于没有指定 serialVersionUID，会导致程序报错
    private String sex;

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 添加新的构造方法
    public Customer(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", age=" + age + '\'' +
                ", sex=" + sex +
                '}';
    }
}
