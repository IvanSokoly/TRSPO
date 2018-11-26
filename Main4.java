import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

public class Main {
    public static void main(String[] args) throws Exception {
        String jsonSource = "{\"firstName\": \"Иван\",\n\"lastName\": \"Иванов\"}";
        String xmlSource = "<note><to>Tove</to><from>Jani</from><heading>Reminder</heading><body>Don't forget me this weekend!</body></note>";

        JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonSource);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = factory.newDocumentBuilder();
        InputSource inputSource = new InputSource(new StringReader(xmlSource));
        Document xmlDocument = db.parse(inputSource);

        String to = xmlDocument.getElementsByTagName("to").item(0).getTextContent();
        String from = xmlDocument.getElementsByTagName("from").item(0).getTextContent();
        String heading = xmlDocument.getElementsByTagName("heading").item(0).getTextContent();
        String body = xmlDocument.getElementsByTagName("body").item(0).getTextContent();
 
        System.out.println("First name: " + jsonObject.get("firstName") + "\nLast name: " + jsonObject.get("lastName"));
        System.out.println("\n\nNote.\nHeading: " + heading + "\nFrom: " + from + "\nTo: " + to + "\nMessage: " + body);
    }
}
