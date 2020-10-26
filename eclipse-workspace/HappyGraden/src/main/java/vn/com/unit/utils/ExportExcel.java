package vn.com.unit.utils;

public class ExportExcel { }

/*

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import vn.com.unit.entity.Account;
import vn.com.unit.entity.Role;

public class ExportExcel {

	public static XSSFWorkbook exportAccount(List<Account> accounts) {

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet("Sheet 1");

		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);

		cell.setCellValue("ID");
		CellUtil.setAlignment(cell, HorizontalAlignment.CENTER);

		cell = row.createCell(1);

		cell.setCellValue("Username");
		CellUtil.setAlignment(cell, HorizontalAlignment.CENTER);

		cell = row.createCell(2);

		cell.setCellValue("Roles");
		CellUtil.setAlignment(cell, HorizontalAlignment.CENTER);

		int rowIndex = 1;
		for (Account account : accounts) {
			row = sheet.createRow(rowIndex++);

			cell = row.createCell(0);

			cell.setCellValue(account.getId());

			cell = row.createCell(1);

			cell.setCellValue(account.getUsername());

			cell = row.createCell(2);

			List<String> roles = new ArrayList<String>();

			for (Role role : account.getRoles()) {
				roles.add(role.getRole());
			}

			cell.setCellValue(String.join(", ", roles));

		}

		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);

		return workbook;
	}
	
}
*/