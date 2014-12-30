import domains.Item;
import domains.Pos;
import domains.ShoppingChart;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Administrator on 2014/12/28.
 */
public class PosTest {
    @Test
    public void testGetCorrectShoppingListForSingleItem() throws Exception {
        // given
        Item cokeCola = new Item("ITEM000000", "可口可乐", "瓶", 3.00,1);
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(cokeCola);
        String a[]={};
        double tot=0;
        double free=0;
        // when
        Pos pos = new Pos();
        a= pos.getShoppingList(shoppingChart,tot,free);
        String actualShoppingList=a[0];
        tot=Double.parseDouble(a[1]);

        // then
        String expectedShoppingList =
                          "***商店购物清单***\n"
                        + "名称：可口可乐，数量：1瓶，单价：3.00(元)，小计：3.00(元)\n"
                        + "----------------------\n"
                        + "总计：3.00(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }

    @Test
    public void testGetCorrectShoppingListForTwoSameItems() throws Exception {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00,1));
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00,1));
        double tot=0;
        double free=0;
        // when
        Pos pos = new Pos();
        String a[]={};
        a= pos.getShoppingList(shoppingChart,tot,free);
        String actualShoppingList=a[0];
        tot=Double.parseDouble(a[1]);

        // then
        String expectedShoppingList =
                          "***商店购物清单***\n"
                        + "名称：可口可乐，数量：2瓶，单价：3.00(元)，小计：6.00(元)\n"
                        + "----------------------\n"
                        + "总计：6.00(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
}