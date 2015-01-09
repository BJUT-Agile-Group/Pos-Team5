import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
/**
 * Created by Administrator on 2015/1/3.
 */
public class m {
    public static void main(String[] args) {
        String barCode = null;
        int i = 0;
        //int k=0;
        int c=1;
        int v=0;
        System.out.println("***商店购物清单***");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//);//设置日期格式
        System.out.println("打印时间："+df.format(new Date()));
        System.out.println("----------------");
        float tot=0;
        float free=0;
        float total=0;
        Statement st=null;
        ResultSet rs=null;
        Connection con=null ;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rooooo", "root", "shsy1994");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String kt[] =new String[10];
        String tempkt[]=new String[10];
        String user=null;
        for(int h=0;h<10;h++) {
            tempkt[h]="0";
        }
        int q=0;
        String tem[]=new String[10];
        String temp=null;
        String sql = "select * from user";
        try {
            st=con.createStatement();
            rs=st.executeQuery(sql);
            user=rs.getString("USERID");
            while (rs.next())
            {
                kt[i] = rs.getString("items");
                i++;
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int k = 0; k <= i; k++)
        {
            c=1;
            if(kt[k] == null || kt[k].length() <= 0) {
            }
            else
            {
                for (int j = k+1; j <= i; j++) {
                    if(kt[k] == null || kt[k].length() <= 0) {
                        if(temp.equals(kt[j]))
                        {
                            c++;
                            kt[j] = null;
                            tempkt[q]="0";
                            tempkt[c] =temp;
                            q=c;
                        }
                    }
                    else if (kt[k].equals(kt[j]))
                    {
                        c++;
                        kt[j] = null;
                        tempkt[c] = kt[k];
                        temp=kt[k];
                        kt[k] = null;
                        q=c;
                    }
                }
            }
        }
        for(int k=0;k<=i;k++) {
            if(kt[k] == null || kt[k].length() <= 0) {

            }else{
                tem[v]=kt[k];
                v++;
            }
        }
        for(int g=0;g<10;g++)
        {
            try {
                st=con.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(tem[g] == null || tem[g].length() <= 0)
            {
            }
            else{
                try {
                    String sql1="select * from shop where pid='"+tem[g]+"'";
                    rs=st.executeQuery(sql1);
                    rs.next();
                    tot=1*rs.getFloat("pr")*rs.getFloat("dis");
                    free=free+1*rs.getFloat("pr")-tot;
                    total=total+tot;
                    System.out.println("名称："+ rs.getString("na")+"，数量："+"1" + rs.getString("un")+"，单价："+rs.getDouble("pr")+"，小计："+tot);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        for(int f=0;f<10;f++)
        {
            try {
                st = con.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(tempkt[f] == "0" ) {

            }
            else
            {
                try {
                    {
                        String sql1 = "select * from shop where pid='" + tempkt[f]+"'";
                        rs = st.executeQuery(sql1);
                        rs.next();
                        tot=rs.getFloat("pr")*f*rs.getFloat("dis");
                        free=free+f*rs.getFloat("pr")-tot;
                        total=total+tot;
                        System.out.println("名称："+ rs.getString("na")+"，数量："+f + rs.getString("un")+"，单价："+rs.getDouble("pr")+"，小计："+tot);
                        if (rs.getString("pro").equals("true") ){
                            System.out.println("挥泪赠送商品：");
                            System.out.println("名称：" + rs.getString("na") + "，数量：" + "1 " + rs.getString("un"));
                            System.out.println("----------------");
                            free=free+rs.getFloat("pr");
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("总计：" + total);
        System.out.println("节省：" + free);
        System.out.println("*************");

    }


}

