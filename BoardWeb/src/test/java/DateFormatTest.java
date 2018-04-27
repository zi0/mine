import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
 * Date --> String
 */

public class DateFormatTest {

	public static void main(String[] args) {

		Calendar cal = Calendar.getInstance();
		System.out.println(cal);
		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
		System.out.println(cal.get(Calendar.DAY_OF_WEEK));

		Calendar birth = Calendar.getInstance();
		birth.set(2018, 1, 1);
		//long a = cal.getTimeInMillis() - birth.getTimeInMillis();
		//System.out.println(a / 1000 / 60 / 60 / 24);
		// 2월 마지막 날 계산
		System.out.println("마지막날 : " + birth.getActualMaximum(Calendar.DAY_OF_MONTH) );

		DateFormat formatter = new SimpleDateFormat("MM/dd");
		System.out.println(formatter.format(cal.getTime()));
	}
}
