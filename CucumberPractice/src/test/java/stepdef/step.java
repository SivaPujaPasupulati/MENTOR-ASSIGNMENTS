package stepdef;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.Thread.sleep;


public class step{
    WebDriver driver;
    @Given("open URL to read table")
    public void open_url_to_read_table() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pa.puja\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_table_intro");
        driver.manage().window().maximize();
        sleep(3000);

        WebElement iframe = driver.findElement(By.id("iframeResult"));
        driver.switchTo().frame(iframe);

    }


    @Then("open and write in excel")
    public void open_and_write_in_excel() throws IOException {
        File file = new File("C:\\Users\\pa.puja\\Desktop\\tableData.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sh = wb.createSheet("W3SCHOOLS TABLE");
        WebElement table =	 driver.findElement(By.xpath("//table"));
        List<WebElement> totalRows = table.findElements(By.tagName("tr"));
        List<WebElement> totalHeaders = table.findElements(By.tagName("th"));
        System.out.println(totalHeaders.size());

        for(int row=0; row<totalRows.size(); row++)
        {
            XSSFRow rowValue = sh.createRow(row);
            List<WebElement> totalColumns = totalRows.get(row).findElements(By.tagName("td"));
            for(int col=0; col<totalColumns.size(); col++)
            {
                String cellValue = totalColumns.get(col).getText();
                System.out.print(cellValue + "\t");
                rowValue.createCell(col).setCellValue(cellValue);
            }
            System.out.println();
        }
        FileOutputStream fos = new FileOutputStream(file);
        wb.write(fos);
        wb.close();
    }

}


