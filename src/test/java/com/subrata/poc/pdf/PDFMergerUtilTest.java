package com.subrata.poc.pdf;

import org.junit.Test;

import java.io.IOException;

public class PDFMergerUtilTest {

    @Test (expected = IOException.class)
    public void mergeHandleErrorIfDirIsInvalid() throws IOException{
        PDFMergerUtil.merge("/some/invalid/dir");
    }
}