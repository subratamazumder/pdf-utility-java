package com.subrata.poc.pdf;

import java.io.IOException;

import java.time.Duration;
import java.time.Instant;

import static com.subrata.poc.pdf.LoggerUtil.logBanner;
import static com.subrata.poc.pdf.LoggerUtil.logWarning;

public class Merger {

    public static void main(String[] args) throws IOException {
        logBanner(System.lineSeparator() + System.lineSeparator() + "************************* Welcome To PDF Merging Utility ****************************");
        logBanner(System.lineSeparator() + "Developed by Subrata Mazumder @ https://subratamazumder.github.io" + System.lineSeparator());
        Instant start = Instant.now();
        if (args.length == 0) {
            logWarning("Supply valid input directory; e.g.; java -jar build/libs/pdf-utility-1.0-SNAPSHOT.jar /mydir/pdffiles/");
            return;
        }
        PDFMergerUtil.merge(args[0]);
        System.out.println("Total Execution Time (ms)-" + Duration.between(start, Instant.now()).toMillis());
        return;
    }

}
