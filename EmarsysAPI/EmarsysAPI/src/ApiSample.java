import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.xml.bind.DatatypeConverter;

public class ApiSample {

    final protected static char[] hexArray = "0123456789abcdef".toCharArray();

    private static Properties props = new Properties();
    private String environment;
    private String apiUsername;
    private String apiSecretKey;


    public static void main(String[] args) throws Exception {
        do {
        System.out.println("Which endpoint? (help for options): ");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        if (sc.equals("help")) {
            FileOutputStream fileOutputStream = new FileOutputStream("help");
            System.out.println(fileOutputStream);
        } else if (sc.equals("delete lists")){
            deleteContactLists();
        }
        }



    }

    private static void deleteContactLists() {
        try {

            props.load(new FileInputStream("/Users/dannymadell/Documents/code/SDP-2017/exercises/EmarsysAPI/EmarsysAPI/prop.properties"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        List<String> ids = ERMHelper.getIDs();

        for (String id : ids) {
            //int intid = Integer.parseInt(id);
            ApiSample apiSample = new ApiSample(
                    props.getProperty("environment".toString()),
                    props.getProperty("apiUsername".toString()),
                    props.getProperty("apiSecretKey".toString()));
            apiSample.send("POST", (String.format("contactlist/%s/add", id)), null);
            System.out.println((String.format("contactlist/%s/deletelist", id)));

        }
    }



    public ApiSample(String environment, String apiUsername, String apiSecretKey) {
        this.environment = environment;
        this.apiUsername = apiUsername;
        this.apiSecretKey = apiSecretKey;
    }

    public void send(String method, String urlText, String data) {
        try {
            URL url = new URL(environment + "api/v2/" + urlText);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod(method);
            connection.addRequestProperty("X-WSSE", getSignature());
            connection.setRequestProperty("Content-Type", "application/json");

            if (data != null) {
                byte[] postDataBytes = data.getBytes("UTF-8");
                connection.setDoOutput(true);
                connection.setUseCaches(false);
                connection.getOutputStream().write(postDataBytes);
            }

            StringBuffer response = new StringBuffer();
            InputStream inputStream;

            if (connection.getResponseCode() == 200) {
                System.out.println("OK (200)");
                inputStream = connection.getInputStream();
            } else {
               /* error from server */
                System.out.println("ERROR (" + Integer.toString(connection.getResponseCode()) + ")");
                inputStream = connection.getErrorStream();
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    inputStream));
            String inputLine;

            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();

            System.out.println(response.toString());
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private String getSignature() {
        String timestamp = getUTCTimestamp();
        String nonce = getNonce();
        String digest = getPasswordDigest(nonce, timestamp);
        return String.format("UsernameToken Username=\"%s\", PasswordDigest=\"%s\", Nonce=\"%s\", Created=\"%s\"", apiUsername, digest, nonce, timestamp);

    }

    private String getUTCTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        return sdf.format(new Date());
    }

    private String getNonce() {
        byte[] nonceBytes = new byte[16];
        new Random().nextBytes(nonceBytes);

        return bytesToHex(nonceBytes);
    }

    private String getPasswordDigest(String nonce, String timestamp) {
        String digest = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.reset();
            String hashedString = String.format("%s%s%s", nonce, timestamp, apiSecretKey);
            messageDigest.update(hashedString.getBytes("UTF-8"));
            String sha1Sum = bytesToHex(messageDigest.digest());

            digest = DatatypeConverter.printBase64Binary(sha1Sum.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("No SHA-1 algorithm was found!");
        } catch (UnsupportedEncodingException ex) {
            System.out.println("Cannot use UTF-8 encoding.");
        }

        return digest;
    }

    private String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }



}