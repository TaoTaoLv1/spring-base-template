import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author: YwT
 * @create: 2019-01-03 15:20
 **/
public class test {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));
    }
}
