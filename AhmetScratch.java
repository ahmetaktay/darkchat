import java.net.InetSocketAddress;


public class AhmetScratch {
public static void main(String[] args) throws InterruptedException {
	User u = new User("ahmet");
	Session s1 = new Session(u, new InetSocketAddress("localhost",6789));
	Thread.sleep(2000);
	Session s2 = new Session(u, new InetSocketAddress("localhost",6789));
	System.out.println(s1.compareTo(s2));
	System.out.println(s1.compareTo(s1));
	System.out.println(s2.compareTo(s1));
	
	
}
}
