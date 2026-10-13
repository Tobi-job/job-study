import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class BcryptGen {
    public static void main(String[] args) {
        BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
        System.out.println(enc.encode("password123"));
        System.out.println(enc.encode("pass1"));
        System.out.println(enc.encode("pass2"));
        System.out.println(enc.encode("pass3"));
        System.out.println(enc.encode("pass4"));
        System.out.println(enc.encode("pass5"));
        System.out.println(enc.encode("pass6"));
        System.out.println(enc.encode("pass7"));
        System.out.println(enc.encode("pass8"));
        System.out.println(enc.encode("pass9"));
        System.out.println(enc.encode("pass10"));
    }
}
