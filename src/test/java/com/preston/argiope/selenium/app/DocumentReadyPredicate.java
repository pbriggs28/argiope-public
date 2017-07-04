package com.preston.argiope.selenium.app;

import org.openqa.selenium.JavascriptExecutor;

import com.google.common.base.Predicate;

/**
 * @author Preston Briggs
 * @since 9/12/2016
 */
//@SuppressWarnings("rawtypes")
public class DocumentReadyPredicate<T> implements Predicate<JavascriptExecutor> {
    public static final String SCRIPT_DOC_READY = "return document.readyState;";
    public static final String COMPLETE = "complete";

	@Override
    public boolean apply(JavascriptExecutor webDriver) {
        JavascriptExecutor jsExecutor;

        // TODO: We should proabably be able to remove this check since we paramterized the 
        // class using "implements Predicate<JavascriptExecutor>"
        if(webDriver instanceof JavascriptExecutor) {
            jsExecutor = (JavascriptExecutor) webDriver;
        } else {
            throw new IllegalStateException("This driver does not implement the JavascriptExecutor interface!");
        }

        return jsExecutor.executeScript(SCRIPT_DOC_READY).equals(COMPLETE);
    }
}
