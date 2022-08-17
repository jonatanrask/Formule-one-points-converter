package model.services;

import model.exception.SearchException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class ReadApiService {
    public ReadApiService() {
    }

    public int raceResult(int element, String uri) throws ParserConfigurationException, SAXException, IOException, NullPointerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();

        Document doc = documentBuilder.parse(uri);
        NodeList resultNodeList = doc.getElementsByTagName("Result");
        Node resultNode = resultNodeList.item(element);

        Element resultElement = (Element) resultNode;

        return Integer.parseInt(resultElement.getAttribute("position"));
    }

    public String driverName(int element, String uri) throws IOException, SAXException, ParserConfigurationException, NullPointerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();

        Document doc = documentBuilder.parse(uri);
        NodeList driversNodeList = doc.getElementsByTagName("Driver");
        Node driversNode = driversNodeList.item(element);
        Element driverElement = (Element) driversNode;

        return driverElement.getAttribute("driverId");
    }

    public int hotLap(int element, String uri) throws IOException, SAXException, ParserConfigurationException, NullPointerException {
        int lap;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();

        Document doc = documentBuilder.parse(uri);
        NodeList lapNodeList = doc.getElementsByTagName("FastestLap");
        Node lapNode = lapNodeList.item(element);
        Element lapElement = (Element) lapNode;
        if (lapElement == null) {
            lap = 0;
        } else {
            lap = Integer.parseInt(lapElement.getAttribute("rank"));
        }

        return lap;
    }

    public int lastRace(String uri) throws IOException, SAXException, ParserConfigurationException, NullPointerException {
        int result = 0;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();

        Document doc = documentBuilder.parse(uri);
        NodeList lastRaceNodeList = doc.getElementsByTagName("MRData");
        Node lastRaceNode = lastRaceNodeList.item(0);
        Element element = (Element) lastRaceNode;
        if (element != null) {
            result = Integer.parseInt(element.getAttribute("total"));;
        }
        return result;
    }
    public void errorRead(int year){
        if(year > 2018 && year < 2023){
            throw new SearchException("Temporada já está no regulamento atual!");
        }
        else if(year < 1950 || year > 2022){
            throw new SearchException("Temporada inexistente");
        }
    }
}
