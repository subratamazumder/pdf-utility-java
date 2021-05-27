package com.subrata.poc.pdf.util;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static com.subrata.poc.pdf.util.LoggerUtil.*;

public class PDFDecrypterUtil {
    private static final String TRAILING_FILE_NAME = "decrypted.pdf";

    public static void decryptBulk(String dir, String password) throws IOException {
        logInfo("Reading directory " + dir);
        int totalFileCount = 0;
        int totalDecryptedFileCount = 0;
        int totalIgnoredFileCount = 0;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir), "*.{pdf}")) {
            for (Path entry : stream) {
                //ignore previously decrypted files
                if (!entry.getFileName().toString().contains(TRAILING_FILE_NAME)) {
                    PDDocument pdDocument = PDDocument.load(entry.toFile(), password);
                    if (pdDocument.isEncrypted()) {
                        String FILE_NAME_WITHOUT_EXTN = entry.getFileName().toString().substring(0,entry.getFileName().toString().lastIndexOf("."));
                        String DECRYPTED_OUTPUT_FILE = String.format("%s%s%s-%s", dir, FileSystems.getDefault().getSeparator(), FILE_NAME_WITHOUT_EXTN, TRAILING_FILE_NAME);
                        pdDocument.setAllSecurityToBeRemoved(true);
                        pdDocument.save(DECRYPTED_OUTPUT_FILE);
                        totalDecryptedFileCount++;
                        logSuccess(entry.getFileName() + " decrypted successfully :) :)");
                    } else {
                        totalIgnoredFileCount++;
                        logWarning("Ignoring !!! " + entry.getFileName() + ", not an encrypted pdf");
                    }
                    pdDocument.close();
                } else {
                    logWarning("Ignoring !!! " + entry.getFileName() + ", previously decrypted pdf");
                    totalIgnoredFileCount++;
                }
                totalFileCount++;
            }
            logBanner(System.lineSeparator() + System.lineSeparator() + "************************* SUMMARY ****************************");
            logInfo("Total no of pdf files-" + totalFileCount);
            logInfo("Total no of files decrypted-" + totalDecryptedFileCount);
            logInfo("Total no of files ignored-" + totalIgnoredFileCount);
            logBanner(System.lineSeparator() + "*************************************************************" + System.lineSeparator());

        } catch (DirectoryIteratorException ex) {
            logError("Unable to parse given directory", ex);
            throw ex.getCause();
        } catch (NoSuchFileException ex) {
            logError("Invalid directory, verify input", ex);
            throw ex;
        } catch (Exception ex) {
            logError("General error ", ex);
            throw ex;
        }
        return;
    }
}
