package com.subrata.poc.pdf;

import com.subrata.poc.pdf.util.PDFDecrypterUtil;
import com.subrata.poc.pdf.util.PDFMergerUtil;

import java.io.IOException;

import java.time.Duration;
import java.time.Instant;

import static com.subrata.poc.pdf.util.LoggerUtil.logBanner;
import static com.subrata.poc.pdf.util.LoggerUtil.logWarning;

public class Main {

    public static void main(String[] args) throws IOException {
        logBanner(System.lineSeparator() + System.lineSeparator() + "************************* Welcome To PDF Utility ****************************");
        logBanner(System.lineSeparator() + "Developed by Subrata Mazumder @ https://subratamazumder.github.io" + System.lineSeparator());
        Instant start = Instant.now();
        if (args.length == 0) {
            logWarning("Supply valid input ; e.g.; TO MERGE  : java -jar build/libs/pdf-utility-1.0-SNAPSHOT.jar MERGE /mydir/pdffiles/");
            logWarning("Supply valid input ; e.g.; TO DECRYPT: java -jar build/libs/pdf-utility-1.0-SNAPSHOT.jar DECRYPT /mydir/pdffiles/ password");
            return;
        }
        if (args[0].equalsIgnoreCase("MERGE")){
            logBanner(System.lineSeparator() + System.lineSeparator() + "************************* MERGE UTILITY ****************************");
            PDFMergerUtil.merge(args[1]);
        } else if(args[0].equalsIgnoreCase("DECRYPT")){
            logBanner(System.lineSeparator() + System.lineSeparator() + "************************* DECRYPT UTILITY ****************************");
            PDFDecrypterUtil.decryptBulk(args[1], args[2]);
        }
        System.out.println("Total Execution Time (ms)-" + Duration.between(start, Instant.now()).toMillis());
        return;
    }

}
