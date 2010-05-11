
public class MyUtils {
	public static Boolean debugFlag = true; 
	public static void dPrintLine(String toBePrinted){
		if (debugFlag) {
			System.out.println(toBePrinted);
		}
	}
}
