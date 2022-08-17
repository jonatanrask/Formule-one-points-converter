package application;

import model.entities.Driver;
import model.exception.SearchException;
import model.services.ReadApiService;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Program {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        Scanner sc = new Scanner(System.in);
        ReadApiService readApiService = new ReadApiService();
        List<Driver> driverList = new ArrayList<>();

        System.out.println("Qual o ano da temporada que gostaria de fazer a conversão?");
        int year = sc.nextInt();
        int gpNumber = 1;
        try {
            readApiService.errorRead(year);
            while (true) {

                String uri = "http://ergast.com/api/f1/" + year + "/" + gpNumber + "/";
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = factory.newDocumentBuilder();
                Document doc = documentBuilder.parse(uri);
                NodeList qntdrivers = doc.getElementsByTagName("Driver");

                if (readApiService.lastRace(uri) == 0) {
                    break;
                }
                for (int i = 0; i < qntdrivers.getLength(); i++) {
                    int points = readApiService.raceResult(i, uri);
                    String name = readApiService.driverName(i, uri);
                    int hotLap = readApiService.hotLap(i, uri);
                    Driver driver = new Driver();
                    Driver hasDriver = driver.hasDriver(driverList, name);
                    if (hasDriver == null) {
                        driverList.add(new Driver(name, driver.newPoints(points, hotLap)));
                    } else {
                        hasDriver.totalPoint(points, hotLap);
                    }
                }
                gpNumber++;
            }

        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        } catch (SearchException searchException){
            System.out.println(searchException.getMessage());
        }

        Collections.sort(driverList);
        int placement = 1;
        for (Driver driver : driverList) {
            System.out.print(placement + "°, ");
            System.out.print(driver.getName() + ", ");
            System.out.println(driver.getPoints() + " Pontos.");
            placement++;
        }
    }
}
