package test;
import m.mm;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
/**
 * Created by Administrator on 2015/1/7.
 */
public class testok{
    public static void main(String args[])
    {
        String order[]=new String[10];
        order=mm.test();
        String string=new String();
        string=order[0];

        String expectedShoppingList =
                         "名称：电池，数量：1个，单价：5.0，小计：5.0\n"
                        +"名称：雪碧，数量：2瓶，单价：3.0，小计：4.56\n"
                        +"名称：可口可乐，数量：5瓶，单价：3.0，小计：13.5\n"
                        + "总计：23.06\n"
                        +"节省：2.9400005\n"
                        + "*************\n";
        assertThat(string.toString(),is(expectedShoppingList));
    }
}
