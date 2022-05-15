import java.io.IOException;

public class memo {

//    public static void main(String[] args) throws IOException {
//
//        String ingredient = "원재료 및 함량 멥쌀 78.9%, 찹쌀 11.9%, 흑미 4.6%, 기장 4.3%, 쌀미강추출물\n" +
//                "*생산 및 유통 시점에 따라 원산지 변경 전 제품이 혼용되어 발송 가능한 점 양해 부탁드립니다.";
//        String allergy = "알레르기 유발물질 함유 해당사항 없음";
//
//        int ingredientStart = ingredient.indexOf("량");
//
//        String[] ingredientSplited = ingredient.split("\n");
//        String result = ingredientSplited[0].toString();
//        int ingredientEnd = result.length();
//
//        int allergyStart = allergy.lastIndexOf("유");
//        int allergyEnd = allergy.length();
//
//        System.out.println(ingredientStart); // 7+2
//        System.out.println(result);
//        System.out.println(allergyStart); // 11+2
//        System.out.println(allergyEnd);
//
//        System.out.println(result.substring(ingredientStart+2,ingredientEnd));
//        System.out.println(allergy.substring(allergyStart+2, allergyEnd));

//        String aTagData = "category\":\"\",\"action\":\" - 상품 클릭\",\"label\":\"40092905_[공장직송][54%할인] 햇반 매일찰잡곡밥 210gx24개(1box)\"";
//
//        int underBar = aTagData.lastIndexOf("_");
//        int prdNumStart = underBar-8;
//        int prdNumEnd = underBar;
//
//        int prdNameStart = underBar + 1;
//        int prdNameEnd = aTagData.lastIndexOf("\"");
//
//        String prdNum = aTagData.substring(prdNumStart, prdNumEnd);
//        String prdName = aTagData.substring(prdNameStart, prdNameEnd);
//
//        System.out.println(prdNum);
//        System.out.println(prdName);
//    }
}