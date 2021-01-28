import com.sun.corba.se.impl.orb.ParserTable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author tangjing
 * @date 2020/11/13 17:12
 */
public class Test {
    // 通过传递日期获得上周周一到周日的日期
    public static List<String> getLastWeekDateList(String dateString){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(date);
        calendar2.setTime(date);
        int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
        if(dayOfWeek <= 0){
            dayOfWeek = 7;
        }
        int offset1 = 1 - dayOfWeek;
        int offset2 = 7 - dayOfWeek;
        calendar1.add(Calendar.DATE, offset1 - 7);
        calendar2.add(Calendar.DATE, offset2 - 7);
        // last Monday
        String lastBeginDate = sdf.format(calendar1.getTime());
        // last Sunday
        String lastEndDate = sdf.format(calendar2.getTime());
        List<String> dateList = new ArrayList<>();
        dateList.add(lastBeginDate);
        dateList.add(lastEndDate);
        return dateList;
    }
    // 通过传递日期，获得过去60天的日期的时间段
    public static List<String> getLastSixTyDateList(String dateString){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE,-60);
        String startTime= sdf.format(cal.getTime());
        List<String> dateList = new ArrayList<>();
        dateList.add(startTime);
        dateList.add(dateString);
        return dateList;
    }

    public static String getNowTime(){
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return format.format(date);
    }
    // 判断周报、月报、季报
    public static List<String> getDateList(String date,int reportType){
        List<String> dateList = new ArrayList<>();
        // 月报
        if(reportType == 2){
            dateList.add(date.substring(0,7)+"-01");
        }
        // 季报
        if(reportType == 3){
            String startDate="";
            String judge=date.substring(5,7);
            switch (judge){
                case "03":startDate="01-01";break;
                case "06":startDate="04-01";break;
                case "09":startDate="07-01";break;
                case "12":startDate="10-01";break;
                default:startDate="01-01";
            }
            dateList.add(date.substring(0,5)+startDate);
        }
        dateList.add(date);
        return dateList;
    }
    public static void main(String[] args) {
        Test a = new Test();
        String date="2020-03-30";
        System.out.println(getLastWeekDateList(date).toString());
        System.out.println(getLastSixTyDateList(date).toString());
        System.out.println(getNowTime());
        System.out.println(getDateList("2021-12-31",3));

    }
}
