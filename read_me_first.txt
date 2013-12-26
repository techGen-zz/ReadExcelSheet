1) First created the CreateExcelAnnotation.Java
2) Then ReadExcelAnnotation.java
3) Then ReturnExcelSheetData.java

Add these three file into webdriver project or you can also create jar for these three class.

Here is an example to utilise this feature.

Suppose you have excel sheet which has two column of "Username" and "Password"



public class TestClass{

@Test(dataProviderClass=khan.tarique.ReturnExcelSheetData.class, dataProvider="dataProvider")
@ReadExcelSheet(filePath="D:/WebDriver/Training/TestData", fileName="TestFile.xls",sheetName="Login")

public void login(String user, String pwd){

System.out.println("Username = " +user);
System.out.println("Username = " +pwd);
System.out.println("-------------------");

}

}

Note: First row of excel sheet will not be read. So first row should be your column header.

Important: Both parameter "dataProviderClass" and "dataProvider" inside @Test annotation, without this it will not work.
