package tt;

import java.text.SimpleDateFormat;
import java.util.Date;

public class time {
public static void main(String[] args) {
	long time = 1415766273L;
	String dateFormat = "MM/ dd/yyyy HH:mm:ss";
	SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

    Date date= new Date(1435048167L);
	System.out.println(sdf.format(date));
}
}
