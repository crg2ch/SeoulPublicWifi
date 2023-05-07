import com.mission1.mission1.Haversine;
import com.mission1.mission1.db.Wifi;

import java.text.SimpleDateFormat;
import java.util.*;

public class TestWifi {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = new Date();
        String str = sdf.format(date);
        System.out.println(str);

        date = new Date();
        str = sdf.format(date);
        System.out.println(str);


//        SeoulAPI api = new SeoulAPI();
//        int rowCnt = api.getData();
//        System.out.println(rowCnt + "개의 WIFI 정보를 정상적으로 저장하였습니다.");
//        Wifi wifi = new Wifi();
//        System.out.println(wifi);
//        WifiService wifiService = new WifiService();
//        wifiService.dbCreate();
//        List<Wifi> wifis = wifiService.get20Wifi(127.1, 37.2);
//        System.out.println(wifis);
//
//        System.out.println(String.format("select pow(LAT-%f, 2) + pow(LNT-%f, 2) as dist_square, * " +
//                "from WIFI " +
//                "order by dist_square " +
//                "limit 20", 127.5, 234.1));
    }
}
