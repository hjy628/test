package Seven.ch03;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hjy on 15-9-tt.
 */
public class DateParser {
    private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public Date parse(String s)throws ParseException{
        return format.parse(s);
    }
}
