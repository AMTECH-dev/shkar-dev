package amtech.tools;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PassportChecker {
    private static String uName, uPasSeria, uPasNumber, uBirthDate, uAddress, uPhonePrefix, uPhoneMobile, uPhoneHome, uMail, uSex;
    private static HashMap<String, String> fDataMap;

    @SuppressWarnings("serial")
	public static HashMap<String, String> check(String passportFormData) {
        String[] formDataArray = passportFormData.split("&");

        for (int i = 0; i < formDataArray.length; i++) {
            try{
                formDataArray[i] = URLDecoder.decode(formDataArray[i], StandardCharsets.UTF_8);
            } catch(Exception e) {
                e.printStackTrace();
                continue;
            }
        }

        uName       = formDataArray[0].contains("fioField=") ? formDataArray[0].replace("fioField=", "") : "";
        uPasSeria   = formDataArray[1].contains("passSField=") ? formDataArray[1].replace("passSField=", "") : "";
        uPasNumber  = formDataArray[2].contains("passField=") ? formDataArray[2].replace("passField=", "") : "";
        uBirthDate  = formDataArray[3].contains("birthField=") ? formDataArray[3].replace("birthField=", "") : "";
        uAddress    = formDataArray[4].contains("addrField=") ? formDataArray[4].replace("addrField=", "") : "";
        uPhonePrefix= formDataArray[5].contains("phoneFieldPrefix=") ? formDataArray[5].replace("phoneFieldPrefix=", "") : "";
        uPhoneMobile= formDataArray[6].contains("phoneFieldBody=") ? formDataArray[6].replace("phoneFieldBody=", "") : "";
        uPhoneHome  = formDataArray[7].contains("phoneHFieldBody=") ? formDataArray[7].replace("phoneHFieldBody=", "") : "";
        uMail       = formDataArray[8].contains("mailFieldBody=") ? formDataArray[8].replace("mailFieldBody=", "") : "";
        uSex        = formDataArray[9].contains("sex=") ? formDataArray[9].replace("sex=", "") : "";

        if (uName.isBlank() || uPasSeria.isBlank() || uPasNumber.isBlank() || uBirthDate.isBlank() || uAddress.isBlank() || uPhoneMobile.isBlank() || uSex.isBlank()) {
            return null;
        } else {
            fDataMap = new HashMap<>() {
                {
                    put("uName", uName);
                    put("uPasSeria", uPasSeria);
                    put("uPasNumber", uPasNumber);
                    put("uBirthDate", uBirthDate);
                    put("uAddress", uAddress);
                    put("uPhonePrefix", uPhonePrefix);
                    put("uPhoneMobile", uPhoneMobile);
                    put("uPhoneHome", uPhoneHome);
                    put("uMail", uMail);
                    put("uSex", uSex);
                }
            };

            // 'for' (для отладки. Можно удалить)
            for (Map.Entry<String, String> uFormLine : fDataMap.entrySet()) {
                System.out.println(Arrays.asList(uFormLine));
            }
            // 'end for'

            return fDataMap;
        }
    }
}
