package com.digisky.liuwei2.tinyexample.serialize;

import java.io.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class Login implements Serializable {
    private Date date = new Date();
    private String username;
    private transient String pasword;

    public Login(String username, String pasword) {
        this.username = username;
        this.pasword = pasword;
    }

    @Override
    public String toString() {
        return "Login{" +
                "date=" + date +
                ", username='" + username + '\'' +
                ", pasword='" + pasword + '\'' +
                '}';
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Login a = new Login("vv", "123456");
        print("login a = " + a);

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("login.out"));
        out.writeObject(a);

        TimeUnit.SECONDS.sleep(1);

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("login.out"));
        a = (Login) in.readObject();
        print(a);
    }
}
