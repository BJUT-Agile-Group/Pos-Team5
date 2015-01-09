package mmm;
import m.mm;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2015/1/7.
 */
public class m {
    public static void main(String args[])
    {
        String order[]=new String[10];
        order=mm.test();
        int point = Integer.parseInt(order[1]);
        String string=new String();
        string=order[0];
        String user=order[2];
        System.out.println("***商店购物清单***");
        System.out.print("会员编号" + user + "  ");
        System.out.println("会员积分："+point);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//);//设置日期格式
        System.out.println("打印时间："+df.format(new Date()));
        System.out.println(string);
    }
}
