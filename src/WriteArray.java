import java.io.FileWriter;
import java.io.IOException;

/**
 * @author chen zhen
 * @version 创建时间：2018年4月13日 下午6:30:11
 * @value 类说明: 将一个数组读取到 txt 里面或 excel 里
 *
 */
public class WriteArray {

    static void writeArrayToTxt(Object[][] data, String string) {
        int rowNum = data.length;
        int columnNum = data[0].length;
        try {
            FileWriter fw = new FileWriter(string);
            for (int i = 0; i < rowNum; i++) {
                for (int j = 0; j < columnNum; j++)
                    fw.write(data[i][j]+ "\t");
                fw.write("\n");
            }
            fw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    // 导出到 excel的代码其实跟导出到 txt 的代码一样
    public void writeArrayToExcel(Object[][] data, String string) {
        int rowNum = data.length;
        int columnNum = data[0].length;
        try {
            FileWriter fw = new FileWriter(string);
            for (int i = 0; i < rowNum; i++) {
                for (int j = 0; j < columnNum; j++)
                    fw.write(data[i][j]+ "\t"); // tab 间隔
                fw.write("\n"); // 换行
            }
            fw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }


//    public static void main(String[] args) {
//
//        Object[][] demands = {{7,7,7,7,7,7},
//                {2,3,4,5,6,7},
//                {8,7,6,5,4,3},
//                {5,6,7,8,7,6},
//                {8,5,2,1,2,5},
//                {8,4,1,3,1,3},
//                {1,3,8,4,8,7},
//                {1,4,7,3,5,8},
//                {3,8,4,4,6,2},
//                {3,1,5,8,4,4}
//        };
//
//        WriteArray wa = new WriteArray();
//        wa.writeArrayToTxt(demands, "Score.txt");
//        wa.writeArrayToTxt(demands, "Score.txt");
//        //wa.writeArrayToTxt(demands, "D:\\desk\\mytxt.xls");
//    }
}