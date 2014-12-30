package mm;
import domains.Item;
import domains.Pos;
import domains.ShoppingChart;

import java.util.Scanner;
/**
 * Created by Administrator on 2014/12/29.
 */
public class m {
    public static  void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("请输入一个字符串:");
        ShoppingChart shoppingChart = new ShoppingChart();
        String a[]=new String[10];
        int j=0;
        String Str[]=new String[10];
        int k=0;
        double tot=0;
        double free=0;
        for(int i=0;i<5;i++){

            String tmp = in.nextLine();
            String strSp[] = tmp.split(" ");
            String barCode = strSp[0];
            Str[j]=barCode;
            String name = strSp[1];
            String unit = strSp[2];
            double price;
            price = Double.parseDouble(strSp[3]);
            double discount=Float.parseFloat(strSp[4]);

            // when
            if(j>=1) {
                if (!Str[j].equals(Str[j-1])) {
                    if(k==0) {
                        System.out.println("***商店购物清单***\n");
                        k = 1;
                    }
                    Pos pos = new Pos();
                    a= pos.getShoppingList(shoppingChart,tot,free);
                    String actualShoppingList=a[1];
                    tot=Double.parseDouble(a[2]);
                    free=Double.parseDouble(a[3]);
                    System.out.println(actualShoppingList);

                }
            }
            shoppingChart.add(new Item(barCode, name, unit, price,discount));
            j++;
        }
        if(k!=1) {

            System.out.println("***商店购物清单***\n");
        }
        Pos pos = new Pos();
        a= pos.getShoppingList(shoppingChart,tot,free);
        String actualShoppingList=a[1];
        tot=Double.parseDouble(a[2]);
        free=Double.parseDouble(a[3]);
        System.out.println(actualShoppingList);
        System.out.println("----------------------\n");
        System.out.println("总计："+tot+"(元)\n");
        System.out.println("节省："+free+"(元)\n");
        System.out.println("**********************\n");
    }
}
