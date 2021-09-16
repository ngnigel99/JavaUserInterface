
import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class Main {
        public static void main(String[] args) throws MalformedURLException, URISyntaxException {
            Scanner in = new Scanner(System.in);
            while (true) {
                System.out.println("Hi, please enter a webpage link");
                System.out.println("...");
                URL webpageLink = stringToUrl();
                openWebpage(webpageLink);
            }
        }
        /*
        References:
        https://stackoverflow.com/questions/10967451/open-a-link-in-browser-with-java-button
         */
        public static boolean openWebpage(URI uri) {
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    desktop.browse(uri);
                    System.out.println("Opened " + uri);
                    return true;
                } catch (IllegalArgumentException e) {
                    System.out.println("Please follow the following format: https://<link>.com");
                } catch (IOException e) {
                    System.out.println("input error! Please try again");
                }
            }
            return false;
        }

        public static boolean openWebpage(URL url) {
            try {
                return openWebpage(url.toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return false;
        }

        public static URL stringToUrl() throws URISyntaxException, MalformedURLException {
            String baseCase = "http://google.com";
            URI uri_baseCase = new URI(baseCase);
            URL url_baseCase = uri_baseCase.toURL();

            URL url = null;
            try {
                Scanner keyboard = new Scanner(System.in);
                String link = keyboard.nextLine();
                if (!link.startsWith("https:/")) {
                    String link2 = "https:/" + link;
                    link = link2;
                }
                URI uri = new URI(link);
                url = uri.toURL();
                return url;
            } catch (Exception e) {
                System.out.println("hey, that's not a working link.. redirecting to google.com!");
                return url_baseCase;
            }
        }
}
