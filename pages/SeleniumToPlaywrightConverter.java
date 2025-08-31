package org.example.pages;

import java.io.*;
import java.nio.file.*;
import java.util.regex.*;

public class SeleniumToPlaywrightConverter {

    public static void main(String[] args) throws IOException {
        Path inputFile = Paths.get("src\\main\\java\\org\\example\\pages\\TripSelection.java");
        Path outputFile = Paths.get("ConvertedPlaywrightPageObject.java");

        String content = Files.readString(inputFile);

        // Convert @FindBy annotations
        content = content.replaceAll("@FindBy\\(xpath\\s*=\\s*\"([^\"]+)\"\\)\\s*WebElement\\s+(\\w+);",
                "Locator $2 = page.locator(\"$1\");");
        content = content.replaceAll("@FindBy\\(css\\s*=\\s*\"([^\"]+)\"\\)\\s*WebElement\\s+(\\w+);",
                "Locator $2 = page.locator(\"$1\");");
        content = content.replaceAll("@FindBy\\(id\\s*=\\s*\"([^\"]+)\"\\)\\s*WebElement\\s+(\\w+);",
                "Locator $2 = page.locator(\"#$1\");");
        content = content.replaceAll("@FindBy\\(className\\s*=\\s*\"([^\"]+)\"\\)\\s*WebElement\\s+(\\w+);",
                "Locator $2 = page.locator(\".$1\");");

        // Convert By locators
        content = content.replaceAll("WebElement\\s+(\\w+)\\s*=\\s*driver\\.findElement\\(By\\.xpath\\(\"([^\"]+)\"\\)\\);",
                "Locator $1 = page.locator(\"$2\");");
        content = content.replaceAll("WebElement\\s+(\\w+)\\s*=\\s*driver\\.findElement\\(By\\.cssSelector\\(\"([^\"]+)\"\\)\\);",
                "Locator $1 = page.locator(\"$2\");");
        content = content.replaceAll("WebElement\\s+(\\w+)\\s*=\\s*driver\\.findElement\\(By\\.id\\(\"([^\"]+)\"\\)\\);",
                "Locator $1 = page.locator(\"#$2\");");
        content = content.replaceAll("WebElement\\s+(\\w+)\\s*=\\s*driver\\.findElement\\(By\\.className\\(\"([^\"]+)\"\\)\\);",
                "Locator $1 = page.locator(\".$2\");");

        // Remove import for WebElement and add import for Locator
        content = content.replaceAll("import org\\.openqa\\.selenium\\.WebElement;", "import com.microsoft.playwright.Locator;");
        content = content.replaceAll("WebElement", "Locator");

        Files.writeString(outputFile, content);
        System.out.println("Conversion complete. Output saved to: " + outputFile.toAbsolutePath());
    }
}
