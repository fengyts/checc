package testjava;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.checc.service.AuctionRecordService;
import com.checc.service.CheccUserService;
import com.checc.service.UserCurrencyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*: spring/spring-context.xml" })
public class VMUserImport {

	@Autowired
	private CheccUserService userService;
	@Autowired
	private UserCurrencyService userCurrencyService;
	@Autowired
	private AuctionRecordService auctionRecordService;

	@Test
	public void vmUserImport() {
		try {
			InputStream is = VMUserImport.class.getResourceAsStream("/template/虚拟用户.xlsx");
			Workbook book = WorkbookFactory.create(is);
			Sheet sheet = book.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			System.out.println(rows);
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			// File file = new File("src/main/resources/template/虚拟用户.xlsx");
			InputStream is = VMUserImport.class.getResourceAsStream("/template/虚拟用户.xlsx");

			Workbook book = WorkbookFactory.create(is);
			Sheet sheet = book.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			System.out.println(rows);
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
	}

}
