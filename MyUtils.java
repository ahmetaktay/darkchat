import java.util.Calendar;
import java.util.Date;


public class MyUtils {
  public static Boolean debugFlag = false; 
  public static void dPrintLine(String toBePrinted){
    if (debugFlag) {
      System.out.println(" = " + toBePrinted);
    }
  }
  public static Date nowPlusSeconds(int seconds)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    c.add(Calendar.SECOND, seconds);
    Date d = c.getTime();
    return d;
  }
}
