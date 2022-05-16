import lombok.Data;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PracticeScrapper {

    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; // 드라이버 ID
    public static final String WEB_DRIVER_PATH = "C:\\Users\\effir\\chromedriver.exe"; // 드라이버 경로

    public static void main(String[] args) throws Exception {
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        WebDriver driver = new ChromeDriver(capabilities);

//        ChromeOptions options = new ChromeOptions();
//        WebDriver driver = new ChromeDriver(options);
//        options.addArguments("disable-infobars");

        String mainUrl = "https://www.cjthemarket.com/pc/ctg/ctgList?ctgrId=0018";
        driver.get(mainUrl);

        // 브라우저 닫기
        if (driver == null) {
            driver.close();
            driver.quit();
        }

        Thread.sleep(5000);

        List<Product> productObjs = new ArrayList<>();
        List<Product> totalObjs = new ArrayList<>();
        File downloadsFolder = new File("C:\\CrawlingTest\\imgDownloads");

        if(downloadsFolder.exists()==false){
            downloadsFolder.mkdir();
        }

        int pageNum = driver.findElement(By.className("paging")).findElements(By.tagName("li")).size();

        for(int i = 1; i <= pageNum; i++){
            driver.findElement(By.xpath("//*[@id=\"prodListPageArea\"]/a[" + i + "]")).sendKeys("\n");
            Thread.sleep(3000);

            List<WebElement> productList = driver.findElement(By.id("prodListArea")).findElements(By.className("product-item"));

            productList.forEach(productLi -> {
                Product product = new Product();
                String aTagData = productLi.findElement(By.tagName("a")).getAttribute("data-gatraceinfo");

                int underBar = aTagData.indexOf("_");
                String prdNum = aTagData.substring(underBar - 8, underBar);
                String prdName = aTagData.substring(underBar + 1, aTagData.lastIndexOf("\""));

                product.setNum(prdNum);
                product.setName(prdName);
                productObjs.add(product);

            }); // productList
        } // for

        System.out.println(productObjs);
        System.out.println(productObjs.size());
        System.out.println("===============================================");

        Thread.sleep(2000);

        for(int i=0; i < productObjs.size(); i++) {
            driver.get("https://www.cjthemarket.com/pc/prod/prodDetail?prdCd=" + productObjs.get(i).getNum() + "&ctgrId=0018&plnId=&giftSetEvntId=");
            Thread.sleep(1500);

            while(true){
            try {
                driver.findElement(By.xpath("//*[@id=\"prdInfoArea\"]/ul/li[1]/a")).click();

                Thread.sleep(3000);

                List<WebElement> allergyInfo = driver.findElement(By.className("table-default")).findElements(By.tagName("tr"));
                String[] temp1 = allergyInfo.get(5).getText().split("\n");
                String result = temp1[0].toString();
                String temp2 = allergyInfo.get(6).getText();

                String ingredient = result.substring(9, result.length());
                String allergy = temp2.substring(13, temp2.length());
                productObjs.get(i).setIngredients(ingredient);
                productObjs.get(i).setAllergy(allergy);

                List<WebElement> imgElements = driver.findElement(By.className("product-images"))
                        .findElement(By.className("product-images-item"))
                        .findElements(By.tagName("img"));

                Thread.sleep(1500);

                for(WebElement img : imgElements){
                    String src = img.getAttribute("src");

                    if (src.contains("//img.cjthemarket.com/images/file/product/")==false){
                        continue;
                    }

                    productObjs.get(i).setImgUrl(src);

                    BufferedImage saveImage = null;

                    try{
                        saveImage = ImageIO.read(new URL(src));
                    } catch (IOException e){
                        e.printStackTrace();
                    }

                    if(saveImage != null) {
                        try {
                            ImageIO.write(saveImage, "jpg", new File("C:\\CrawlingTest\\imgDownloads\\Photo_" + productObjs.get(i).getNum() + ".jpg"));
                        } catch (IOException e) {
                        }
                    }//if
                }//for

                System.out.println(productObjs.get(i));
                break;

            } catch (UnhandledAlertException f) {
                Thread.sleep(3000);

                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                System.out.println("Alert data: " + alertText);
                alert.accept();
            }
            }//while

        }//for

        Thread.sleep(3000);
        System.out.println(productObjs.size());

    } // main
} // class


@Data
class Product {
    private String name;
    private String num;
    private String ingredients;
    private String allergy;
    private String imgUrl;

    private static final String pageUrl = "https://www.cjthemarket.com/pc/prod/prodDetail?prdCd=&ctgrId=0018&plnId=&giftSetEvntId=";

    public String getURL() {
        return pageUrl;
    }
}
