package com.subrata.poc.pdf.util;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.IOException;
import java.nio.file.*;
import java.util.Iterator;
import java.util.List;

import static com.subrata.poc.pdf.util.LoggerUtil.*;

public class PDFSplitterUtil {
    private static final String TRAILING_FILE_NAME = "Splitted.pdf";

    public static void split(String dir, int splitAtPage) throws IOException {
        logInfo("Reading directory " + dir + System.lineSeparator() + System.lineSeparator());
        int totalFileCount = 0;
        int totalSplittedFileCount = 0;
        int totalIgnoredFileCount = 0;
        Splitter splitter = new Splitter();
        splitter.setSplitAtPage(splitAtPage);
        logInfo("Reading directory " + dir);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir), "*.{pdf}")) {
            for (Path entry : stream) {
                //ignore previously splitted files
                if (!entry.getFileName().toString().contains(TRAILING_FILE_NAME)) {
                    PDDocument pdfDocument = PDDocument.load(entry.toFile());
                    //ignore if input pdf is already having pages less than splitAtPage
                    if (pdfDocument.getNumberOfPages() > splitAtPage) {
                        String FILE_NAME_WITHOUT_EXTN = entry.getFileName().toString().substring(0, entry.getFileName().toString().lastIndexOf("."));
                        List<PDDocument> splittedPagesList = splitter.split(pdfDocument);
                        Iterator<PDDocument> iterator = splittedPagesList.iterator();
                        int newFileCounter = 1;
                        while (iterator.hasNext()) {
                            PDDocument splittedPdfDocument = iterator.next();
                            String SPLITTED_OUTPUT_FILE = String.format("%s%s%s-%d-%s", dir, FileSystems.getDefault().getSeparator(), FILE_NAME_WITHOUT_EXTN, newFileCounter, TRAILING_FILE_NAME);
                            splittedPdfDocument.save(SPLITTED_OUTPUT_FILE);
                            newFileCounter++;
                        }
                        totalSplittedFileCount++;
                        logSuccess(entry.getFileName() + " splitted successfully :) :)");
                    } else {
                        totalIgnoredFileCount++;
                        logWarning("Ignoring !!! " + entry.getFileName() + ", no of pages less than split size");
                    }
                    pdfDocument.close();
                } else {
                    logWarning("Ignoring !!! " + entry.getFileName() + ", previously splitted pdf");
                    totalIgnoredFileCount++;
                }
                totalFileCount++;
            }
            logBanner(System.lineSeparator() + System.lineSeparator() + "************************* SUMMARY ****************************");
            logInfo("Total no of pdf files-" + totalFileCount);
            logInfo("Total no of files splitted-" + totalSplittedFileCount);
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
