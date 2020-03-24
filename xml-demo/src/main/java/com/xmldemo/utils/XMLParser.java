package com.xmldemo.utils;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface XMLParser {

    <O> void exportToXML(O object, String path) throws JAXBException;

    <O> O importFromXML(Class<O> klass, String path) throws JAXBException, FileNotFoundException;
}
