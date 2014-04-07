/**
 * 系统数据导出Excel 生成器
 * @version 1.0
 */
package rml.util;

import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;


public class ExcelFileGenerator {

	private final int SPLIT_COUNT = 1500; //Excel每个工作簿的行数

	private ArrayList fieldName = null; //excel标题数据集

	private ArrayList fieldData = null; //excel数据内容	

	private HSSFWorkbook workBook = null;

	/**
	 * 构造器
	 * @param fieldName 结果集的字段名
	 * @param data
	 */
	public ExcelFileGenerator(ArrayList fieldName, ArrayList fieldData) {

		this.fieldName = fieldName;
		this.fieldData = fieldData;
	}

	/**
	 * 创建HSSFWorkbook对象
	 * @return HSSFWorkbook
	 */
	public HSSFWorkbook createWorkbook() {

		workBook = new HSSFWorkbook();
		int rows = fieldData.size();
		int sheetNum = 0;

		if (rows % SPLIT_COUNT == 0) {
			sheetNum = rows / SPLIT_COUNT;
		} else {
			sheetNum = rows / SPLIT_COUNT + 1;
		}

		for (int i = 1; i <= sheetNum; i++) {
			HSSFSheet sheet = workBook.createSheet("Page " + i);
			HSSFRow headRow = sheet.createRow((short) 0); 
			for (int j = 0; j < fieldName.size(); j++) {
				HSSFCell cell = headRow.createCell((short) j);
				//添加样式
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			//	cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				//添加样式
				//设置所有单元格的宽度
				sheet.setColumnWidth((short)j, (short)6000);
				//创建样式(使用工作本的对象创建)
				HSSFCellStyle cellStyle = workBook.createCellStyle();
				//创建字体的对象
				HSSFFont font = workBook.createFont();
				//将字体加粗
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				//设置字体的颜色
				short color = HSSFColor.RED.index;
				font.setColor(color);
				//将新设置的字体属性放置到样式中
				cellStyle.setFont(font);
				if(fieldName.get(j) != null){
					cell.setCellStyle(cellStyle);
					cell.setCellValue((String) fieldName.get(j));
				}else{
					cell.setCellStyle(cellStyle);
					cell.setCellValue("-");
				}
			}

			for (int k = 0; k < (rows < SPLIT_COUNT ? rows : SPLIT_COUNT); k++) {
				HSSFRow row = sheet.createRow((short) (k + 1));
				//将数据内容放入excel单元格
				ArrayList rowList = (ArrayList) fieldData.get((i - 1)
						* SPLIT_COUNT + k);
				for (int n = 0; n < rowList.size(); n++) {
					HSSFCell cell = row.createCell((short) n);
			//		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					if(rowList.get(n) != null){
						cell.setCellValue((String) rowList.get(n).toString());
					}else{
						cell.setCellValue("");
					}
				}
			}
		}
		return workBook;
	}

	public void expordExcel(OutputStream os) throws Exception {
		workBook = createWorkbook();
		workBook.write(os);
		os.close();
	}

}
