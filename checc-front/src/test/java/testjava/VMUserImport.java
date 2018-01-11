package testjava;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.checc.domain.AuctionRecordDO;
import com.checc.domain.CheccUserDO;
import com.checc.enums.AuctionRecordTypeEnum;
import com.checc.service.AuctionRecordService;
import com.checc.service.CheccUserService;
import com.checc.service.UserCurrencyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/spring-context.xml" })
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

			Cell cellPwd = sheet.getRow(1).getCell(1);
			String pwd = cellPwd.getStringCellValue();
			Cell cellCurr = sheet.getRow(1).getCell(2);
			Integer currency = Double.valueOf(cellCurr.getNumericCellValue()).intValue();

			Date date = new Date();

			List<AuctionRecordDO> list = new ArrayList<AuctionRecordDO>();
			for (int i = 1; i < rows; i++) { // 从1开始,忽略sheel表头标题行
				Row row = sheet.getRow(i);
				Cell cellMobile = row.getCell(0);
				cellMobile.setCellType(Cell.CELL_TYPE_STRING);
				String mobile = cellMobile.getStringCellValue();

				CheccUserDO userDO = new CheccUserDO();
				userDO.setCreateTime(date);
				userDO.setModifyTime(date);
				userDO.setLastLoginTime(date);
				userDO.setMobile(mobile);
				userDO.setPassword(pwd);
				userDO.setNickname("虚拟账号");

				long userId = userService.register(userDO);

				userCurrencyService.increaseTotalCurrency(userId, currency);

				AuctionRecordDO recordDO = new AuctionRecordDO();
				recordDO.setDepositAmount(currency);
				recordDO.setRecordType(AuctionRecordTypeEnum.DEPOSIT.code);
				recordDO.setMobile(mobile);
				recordDO.setUserId(userId);
				recordDO.setDiscount(1D);
				recordDO.setDiscountId(0L);
				recordDO.setCreateTime(date);
				list.add(recordDO);
			}

			auctionRecordService.insertBatch(list);

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

			Cell cellPwd = sheet.getRow(1).getCell(1);
			System.out.println(cellPwd.getCellType());
			String pwd = cellPwd.getStringCellValue();
			System.out.println(pwd);

			Cell cellCurr = sheet.getRow(1).getCell(2);
			Object o = cellCurr.getNumericCellValue();
			System.out.println(o);

		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
	}

}
