package com.trendyol.selector;

import com.trendyol.model.ElementInfo;
import org.openqa.selenium.By;

public class WebSelector implements Selector {

    @Override
    public By getElementInfoToBy(ElementInfo elementInfo) {
        By by = null;
        if (elementInfo.getWebType().equals("css")) {
            by = By.cssSelector(elementInfo.getWebValue());
        } else if (elementInfo.getWebType().equals("id")) {
            by = By.id(elementInfo.getWebValue());
        } else if (elementInfo.getWebType().equals("xpath")) {
            by = By.xpath(elementInfo.getWebValue());
        } else if (elementInfo.getWebType().equals("class")) {
            by = By.className(elementInfo.getWebValue());
        } else if (elementInfo.getWebType().equals("text")) {
            by = By.linkText(elementInfo.getWebValue());
        } else if (elementInfo.getWebType().equals("name")) {
            by = By.linkText(elementInfo.getWebValue());
        }
        return by;
    }

    @Override
    public int getElementInfoToIndex(ElementInfo elementInfo) {
        return elementInfo.getWebIndex();
    }
}
