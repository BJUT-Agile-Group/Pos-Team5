package m;
import java.sql.*;


/**
 * Created by Administrator on 2015/1/3.
 */
public class mm {
    public static String [] test() {
        int i = 0;
        //int k=0;
        int c=1;
        int v=0;
        StringBuilder stringBuilder = new StringBuilder();
        //stringBuilder.append("***商店购物清单***\n");


        int point=0;
        float tot=0;
        float free=0;
        float total=0;
        int key=0;
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
        String re[]=new String[10];
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
            while (rs.next())
            {
                user=rs.getString("USERID");
                kt[i] = rs.getString("items");
                i++;
            }

            String sql2 = "select * from useritem where username='"+user+"'";
            rs=st.executeQuery(sql2);
            rs.next();
            point=rs.getInt("point");
            if(rs.getString("isvip").equals("1"))
            {
                key=1;
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
                    if(key==0) {
                        tot = 1 * rs.getFloat("pr") * rs.getFloat("dis");
                        free = free + 1 * rs.getFloat("pr") - tot;
                        total = total + tot;
                        stringBuilder.append("名称：" + rs.getString("na") + "，数量：" + "1" + rs.getString("un") + "，单价：" + rs.getDouble("pr") + "，小计：" + tot + "\n");
                    }
                    else
                    {
                        tot = 1 * rs.getFloat("pr") * rs.getFloat("dis")*rs.getFloat("vipdis");
                        free = free + 1 * rs.getFloat("pr") - tot;
                        total = total + tot;
                        stringBuilder.append("名称：" + rs.getString("na") + "，数量：" + "1" + rs.getString("un") + "，单价：" + rs.getDouble("pr") + "，小计：" + tot + "\n");
                        if(point<=200)
                        {
                            point=(int)tot%5*1+point;
                        }
                        else if(point<=500)
                        {
                            point=(int)tot%5*3+point;
                        }
                        else if(point>500)
                        {
                            point=(int)tot%5*5+point;
                        }
                    }

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
                        if(key==0) {
                            tot = rs.getFloat("pr") * f * rs.getFloat("dis");
                            free = free + f * rs.getFloat("pr") - tot;
                            total = total + tot;
                            stringBuilder.append("名称：" + rs.getString("na") + "，数量：" + f + rs.getString("un") + "，单价：" + rs.getDouble("pr") + "，小计：" + tot + "\n");

                            if (rs.getString("pro").equals("1")) {
                                stringBuilder.append("挥泪赠送商品：\n");

                                stringBuilder.append("名称：" + rs.getString("na") + "，数量：" + "1 " + rs.getString("un") + "\n");

                                stringBuilder.append("----------------\n");

                                free = free + rs.getFloat("pr");
                            }
                        }
                        else
                        {
                            tot = rs.getFloat("pr") * f * rs.getFloat("dis") * rs.getFloat("vipdis");
                            free = free + f * rs.getFloat("pr") - tot;
                            total = total + tot;
                            stringBuilder.append("名称：" + rs.getString("na") + "，数量：" + f + rs.getString("un") + "，单价：" + rs.getDouble("pr") + "，小计：" + tot + "\n");
                            if(point<=200)
                            {
                                point=(int)tot%5*1+point;
                            }
                            else if(point<=500)
                            {
                                point=(int)tot%5*3+point;
                            }
                            else if(point>500)
                            {
                                point=(int)tot%5*5+point;
                            }
                          /*  if (rs.getString("pro").equals("1")) {
                                stringBuilder.append("挥泪赠送商品：\n");

                                stringBuilder.append("名称：" + rs.getString("na") + "，数量：" + "1 " + rs.getString("un") + "\n");

                                stringBuilder.append("----------------\n");

                                free = free + rs.getFloat("pr");
                            }*/
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
        stringBuilder.append("总计："+total+"\n");

        stringBuilder.append("节省：" + free + "\n");

        stringBuilder.append("*************\n");


       // System.out.println(stringBuilder);
        re[0]=stringBuilder.toString();
        re[1]= String.valueOf(point);
        re[2]=user;
        return re;
    }


    }

