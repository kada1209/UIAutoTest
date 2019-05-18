package operatFile;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import module.Config;
import module.ElementExist;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wangxinqi
 * @date 2019-03-28 00:38
 *
 * 操作Excel用例表
 */
public class AutoOperatExcelFile {
    private POIFSFileSystem fileSystem;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private XSSFRow row;
    private XSSFCell cell;
    ElementExist elementExist = new ElementExist();

    /**
     * 得到列表中的标题
     * @param inputStream
     * @return
     */
    public String[] readExcelTitleContent(InputStream inputStream){
        try {
            //载入Excel文件(得到文档对象)
            //fileSystem = new POIFSFileSystem(inputStream);
            //得到表单
            workbook = new XSSFWorkbook(new FileInputStream(Config.getInstance().getCfg("filePath")));


        } catch (IOException e) {
            e.printStackTrace();
        }
        //读取配置文件中对应的Excel中的sheet
        sheet = workbook.getSheet(Config.getInstance().getCfg("My"));

        row = sheet.getRow(0);//默认得到第一列
        int colNum = row.getPhysicalNumberOfCells(); // 标题总列数
        String title[] = new String[colNum];
        for (int i=0;i<colNum;i++){
            title[i] = getStringCellValue(row.getCell((short)i)); //得到标题的内容
        }
        return title; //返回列表标题内容数组
    }

    /**
     * 读取Excel中的数据内容
     * @param inputStream
     * @return
     */
    public Map<Integer,String> readExcelContent(InputStream inputStream){
        Map<Integer,String> content = new HashMap<Integer,String>();
        String str = "";
        try {
//            fileSystem = new POIFSFileSystem(inputStream);
            workbook = new XSSFWorkbook(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = workbook.getSheet(Config.getInstance().getCfg("My"));// 得到Excel为“MY”的sheet表格
        int rowNum = sheet.getLastRowNum(); // 得到总行数
        row= sheet.getRow(0);//默认得到第一行
        int colNum = row.getPhysicalNumberOfCells();
        for (int i=1;i<=rowNum;i++){
            row = sheet.getRow(i);
            int j =0;
            while (j<=colNum){
                str += getStringCellValue(row.getCell((short)j)).trim()+"-";
                j++;
            }
            content.put(i,str);
            str = "";
        }
        return content;
    }

    /**
     * 获取Excel单元格数据内容中为字符串类型的数据
     * @param cell
     * @return
     */
    private String getStringCellValue(XSSFCell cell){
        String strCell= "";
        if (cell==null){
            return "";
        }else {
            switch (cell.getCellType()){
                case STRING:
                    strCell = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    String strCellNumber = String.valueOf(cell.getNumericCellValue());
                    DecimalFormat decimalFormat = new DecimalFormat("0");//将数据转化为整数
                    strCell = decimalFormat.format(cell.getNumericCellValue());
                    break;
                case BOOLEAN:
                    strCell = String.valueOf(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    strCell = "";
                    break;
                default:
                    strCell = "";
                    break;
            }
            if (strCell.equals("")|| strCell==null){
                return "";
            }
            return strCell;
        }
    }

    /**
     * 获取Excel中单元格中数据内容为日期类型的数据
     * @param cell
     * @return
     */
    private String getDateCellValue(HSSFCell cell){
         String result = "";
         try {
             CellType cellType = cell.getCellType();
             if (cellType==CellType.NUMERIC){
                 Date date = cell.getDateCellValue();
                 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                 result = simpleDateFormat.format(date);
             }else if (cellType==CellType.BLANK){
                 result ="";
             }

         }catch (Exception e){
             System.out.println("日期格式不正确");
             e.printStackTrace();
         }
         return result;

    }

    /**
     * 读取Excle标题内容
     * @return
     */
    public List<String> readTitle() throws FileNotFoundException {
        List<String> list = new ArrayList<String>();
        InputStream inputStream = new FileInputStream(Config.getInstance().getCfg("filePath"));
        AutoOperatExcelFile excelFile = new AutoOperatExcelFile();
        String[] titles = excelFile.readExcelTitleContent(inputStream);
        for (String title :titles ){
            list.add(title);
        }
        return list;
    }

    /**
     * 读取Excel表格中的测试用例的内容
     * @return
     */
    public List<String> ReadContent() throws FileNotFoundException {
        List<String> list = new ArrayList<String>();
        InputStream inputStream = new FileInputStream(Config.getInstance().getCfg("filePath"));
        AutoOperatExcelFile excelFile = new AutoOperatExcelFile();
        Map<Integer,String> map = excelFile.readExcelContent(inputStream);
        for (int i =1;i<=map.size();i++){
            list.add(map.get(i));
        }
        return list;
    }
    public String ReadTitleContent(int i,int j) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(Config.getInstance().getCfg("filePath"));
        try{
//            fileSystem = new POIFSFileSystem(inputStream);
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = workbook.getSheet(Config.getInstance().getCfg("My"));
        row = sheet.getRow(i);
        String content = getStringCellValue(row.getCell((short)j));
        return content;
    }
    private void saveWorkBook(XSSFWorkbook workbook){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(Config.getInstance().getCfg("filePath"));
            workbook.write(fileOutputStream);
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    private XSSFCell getCell(XSSFSheet sheet,int rowIndex,int columnIndex){
        XSSFRow row = sheet.getRow(rowIndex);
        if (row==null){
            row = sheet.createRow(rowIndex);
        }
        XSSFCell cell = row.getCell(columnIndex);
        if (cell==null){
            cell = row.createCell(columnIndex);
        }
        return cell;
    }

    /**
     * 纪录测试结果，当元素存在时为pass,否则为空
     * @param i
     * @param j
     */
    public void WriteTitleContent(int i,short j) throws IOException {
        try {
            InputStream inputStream = new FileInputStream(Config.getInstance().getCfg("filePath"));
//            fileSystem = new POIFSFileSystem(inputStream);
            workbook = new XSSFWorkbook(inputStream);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        sheet = workbook.getSheet(Config.getInstance().getCfg("My"));
        row = sheet.getRow(i);
        XSSFCell cell = getCell(sheet,i,j);
        XSSFRichTextString hssfRichTextString = new XSSFRichTextString(elementExist.result);
        cell.setCellValue(hssfRichTextString);
        saveWorkBook(workbook);
    }

    /**
     * 在执行测试之前将测试结果设置为空
     * @param j
     */
    public void SetContentInit(short j){
        try {
            InputStream inputStream = new FileInputStream(Config.getInstance().getCfg("filePath"));
            Map<Integer, String> map = readExcelContent(inputStream);
            for (int k =1;k<map.size();k++){
                sheet = workbook.getSheet(Config.getInstance().getCfg("My"));
                row = sheet.getRow(k);
                XSSFCell cell = getCell(sheet,k,j);
                XSSFRichTextString hssfRichTextString = new XSSFRichTextString("");
                cell.setCellValue(hssfRichTextString);
            }
            saveWorkBook(workbook);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}
