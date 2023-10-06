import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter the Username:");
        String username = readFromConsole();
        String url = "https://www.ecs.soton.ac.uk/people/" + username;
        System.out.println("The URL is: " + url);

        try {
            String charset = "UTF-8";
            URLConnection connection = new URL(url).openConnection();
            // connection.addRequestProperty("User-Agent", 
            //             "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            // connection.setRequestProperty("Accept-Charset", charset);
            InputStream response = connection.getInputStream();
            BufferedReader br = new  BufferedReader(new InputStreamReader(response,charset));
        
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("heading-m inline-block text-prussianDark")) {
                    String name = line.substring(line.indexOf("heading-m inline-block text-prussianDark") + 42);
                    name = name.substring(0, name.indexOf("<"));
                    System.out.println("The name is: " + name);
                }
            }
            

        } catch (IOException e) {
            System.out.println("Error reading from URL");
        }



    }

    private static String readFromConsole() {

        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        try {
            return br.readLine();
        } catch (IOException e) {
            System.out.println("Error reading from console");
        }
        return null;
        
    }
}