package com.training.dataproviders;

import java.util.List;
import org.testng.annotations.DataProvider;
import com.training.readexcel.ApachePOIExcelRead;

public class RolesDataProviders {

	@DataProvider(name = "excel-ChangeRole")
	public static Object[][] getExcelData() {
		String fileName = "C:\\Users\\PREETHIVijayKumar\\git\\repository\\git\\Selenium-Automation-Elearning-Framework-TestNG\\DataProviders\\excel-ChangeRole.xlsx";

		List<List<Object>> retVal = ApachePOIExcelRead.getExcelContent(fileName);
		System.out.println("size" + retVal.size());

		Object[][] result = new Object[retVal.size()][retVal.size()];
		int count = 0;

		for (List<Object> temp : retVal) {
			if (temp != null) {
				Object[] obj = new Object[2];
				System.out.println(temp.get(0));
				System.out.println(temp.get(1));

				obj[0] = temp.get(0);
				obj[1] = temp.get(1);

				result[count++] = obj;

			}
		}
		return result;

	}

}
