package com.xmlexercise.utils;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface XMLParser {

    <T> void marshalToFile(String filePath, T rootDto) throws JAXBException;

    <T> T unmarshalFromFile(String filePath, Class<T> tClass) throws JAXBException, FileNotFoundException;
}
