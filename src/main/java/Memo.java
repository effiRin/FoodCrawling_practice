//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.remote.CapabilityType;
//import org.openqa.selenium.remote.DesiredCapabilities;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.io.File;
//import java.io.IOException;
//
//public class Memo {
//
//    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; // 드라이버 ID
//    public static final String WEB_DRIVER_PATH = "C:\\Users\\effir\\chromedriver.exe"; // 드라이버 경로
//
//    public static void main(String[] args) throws Exception {
//
//        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
//        WebDriver driver = new ChromeDriver(capabilities);
//
//        String mainUrl = "https://www.cjthemarket.com/pc/ctg/ctgList?ctgrId=0018";
//        driver.get(mainUrl);
//
//        if (driver == null) {
//            driver.close();
//            driver.quit();
//        }
//
//        Thread.sleep(5000);
//
//        List<Scrapper.Product> productObjs = new ArrayList<>();
//        List<Scrapper.Product> totalObjs = new ArrayList<>();
//        File downloadsFolder = new File("imgDownloads");
//
//        if(downloadsFolder.exists()==false){
//            downloadsFolder.mkdir();
//        }
//
//        for(int i=0; i < productObjs.size(); i++) {
//            driver.get("https://www.cjthemarket.com/pc/prod/prodDetail?prdCd=" + productObjs.get(i).getNum() + "&ctgrId=0018&plnId=&giftSetEvntId=");
//            Thread.sleep(1500);
//
//            while(true){
//                try {
//                    driver.findElement(By.xpath("//*[@id=\"prdInfoArea\"]/ul/li[1]/a")).click();
//
//                    Thread.sleep(1000);
//
//                    List<WebElement> allergyInfo = driver.findElement(By.className("table-default")).findElements(By.tagName("tr"));
//                    String[] temp1 = allergyInfo.get(5).getText().split("\n");
//                    String result = temp1[0].toString();
//                    String temp2 = allergyInfo.get(6).getText();
//
//                    String ingredient = result.substring(9, result.length());
//                    String allergy = temp2.substring(13, temp2.length());
//                    productObjs.get(i).setIngredients(ingredient);
//                    productObjs.get(i).setAllergy(allergy);
//
//                    List<WebElement> imgElements = driver.findElement(By.className("product-images-paper"))
//                            .findElement(By.className("slick-track"))
//                            .findElements(By.tagName("img"));
//
//                    Thread.sleep(1000);
//
//                    for(WebElement img : imgElements){
//                        String src = img.getAttribute("src");
//
//                        if (src.contains("//img.cjthemarket.com/images/file/product/")==false){
//                            continue;
//                        }
//
//                        System.out.println(src);
//                    }//for
//
//                    System.out.println(productObjs.get(i));
//                    break;
//
//                } catch (UnhandledAlertException f) {
//                    Alert alert = driver.switchTo().alert();
//                    String alertText = alert.getText();
//                    System.out.println("Alert data: " + alertText);
//                    alert.accept();
//                }
//            }//while
//
//        }//for
//
//
//    }
//
////        String ingredient = "원재료 및 함량 멥쌀 78.9%, 찹쌀 11.9%, 흑미 4.6%, 기장 4.3%, 쌀미강추출물\n" +
////                "*생산 및 유통 시점에 따라 원산지 변경 전 제품이 혼용되어 발송 가능한 점 양해 부탁드립니다.";
////        String allergy = "알레르기 유발물질 함유 해당사항 없음";
////
////        int ingredientStart = ingredient.indexOf("량");
////
////        String[] ingredientSplited = ingredient.split("\n");
////        String result = ingredientSplited[0].toString();
////        int ingredientEnd = result.length();
////
////        int allergyStart = allergy.lastIndexOf("유");
////        int allergyEnd = allergy.length();
////
////        System.out.println(ingredientStart); // 7+2
////        System.out.println(result);
////        System.out.println(allergyStart); // 11+2
////        System.out.println(allergyEnd);
////
////        System.out.println(result.substring(ingredientStart+2,ingredientEnd));
////        System.out.println(allergy.substring(allergyStart+2, allergyEnd));
//
////        String aTagData = "category\":\"\",\"action\":\" - 상품 클릭\",\"label\":\"40092905_[공장직송][54%할인] 햇반 매일찰잡곡밥 210gx24개(1box)\"";
////
////        int underBar = aTagData.lastIndexOf("_");
////        int prdNumStart = underBar-8;
////        int prdNumEnd = underBar;
////
////        int prdNameStart = underBar + 1;
////        int prdNameEnd = aTagData.lastIndexOf("\"");
////
////        String prdNum = aTagData.substring(prdNumStart, prdNumEnd);
////        String prdName = aTagData.substring(prdNameStart, prdNameEnd);
////
////        System.out.println(prdNum);
////        System.out.println(prdName);
////    }
//}