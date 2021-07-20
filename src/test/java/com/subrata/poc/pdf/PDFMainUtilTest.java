package com.subrata.poc.pdf;

import com.subrata.poc.pdf.util.PDFMergerUtil;
import org.junit.Test;

import java.io.IOException;

public class PDFMainUtilTest {

    @Test (expected = IOException.class)
    public void mergeHandleErrorIfDirIsInvalid() throws IOException{
        PDFMergerUtil.merge("/some/invalid/dir");
    }
}