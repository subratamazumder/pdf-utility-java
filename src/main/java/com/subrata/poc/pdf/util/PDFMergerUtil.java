package com.subrata.poc.pdf.util;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.subrata.poc.pdf.util.LoggerUtil.*;

public class PDFMergerUtil {
    private static final String TRAILING_FILE_NAME = "Auto-Merged.pdf";
    private static final String MERGED_OUTPUT_FILE = String.format("%s-%s",
            new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()),
            TRAILING_FILE_NAME);

    public static void merge(String dir) throws IOException {
        logInfo("Reading directory " + dir + System.lineSeparator() + System.lineSeparator());
        int totalFileCount = 0;
        int totalAddedFileCount = 0;
        int totalIgnoredFileCount = 0;
        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
        String ABSOLUTE_MERGED_OUTPUT_FILE_PATH = dir + FileSystems.getDefault().getSeparator() + MERGED_OUTPUT_FILE;
        pdfMergerUtility.setDestinationFileName(ABSOLUTE_MERGED_OUTPUT_FILE_PATH);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir), "*.{pdf}")) {
            for (Path entry : stream) {
                //ignore previously auto merged files
                if (!entry.getFileName().toString().contains(TRAILING_FILE_NAME)) {
                    logInfo("Adding " + entry.getFileName());
                    pdfMergerUtility.addSource(entry.toFile());
                    totalAddedFileCount++;
                } else {
                    logWarning("Ignoring !!! " + entry.getFileName());
                    totalIgnoredFileCount++;
                }

                totalFileCount++;
            }
            pdfMergerUtility.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
            logBanner(System.lineSeparator() + System.lineSeparator() + "************************* SUMMARY ****************************");
            logSuccess(System.lineSeparator() + ABSOLUTE_MERGED_OUTPUT_FILE_PATH + " created successfully :) :)");
            logInfo("Total no of pdf files-" + totalFileCount);
            logInfo("Total no of files merged-" + totalAddedFileCount);
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
            throw  ex;
        }
        return;
    }

}
