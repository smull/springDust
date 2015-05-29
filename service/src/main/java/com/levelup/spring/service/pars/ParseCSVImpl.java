package com.levelup.spring.service.pars;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ParseCSVImpl {
    public List<List<String>> parseCSV(String fileName, int... param) {

        BufferedReader fileReader = null;
        List<List<String>> cells = new ArrayList<>();
        //Delimiter used in CSV file
        String delimiter = ",";
        try {
            String line = "";
            //Create the file reader
            fileReader = new BufferedReader(new FileReader(fileName));
            int count = 0;
            //Read the file line by line
            while ((line = fileReader.readLine()) != null) {
                count++;
                if (count > 2) {
                    //Get all tokens available in line
                    String[] tokens = line.split(delimiter);
                    // cells.add(tokens);
                    ArrayList<String> myString = new ArrayList<String>();
                    for (int i = 0; i < param.length; i++) {
                        myString.add(tokens[param[i]]);
                    }
                    cells.add(myString);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cells;
    }

    public List<List<String>> parseCSV(String fileName) {

        BufferedReader fileReader = null;
        List<List<String>> cells = new ArrayList<>();
        String delimiter = ",";
        try {
            String line = "";
            fileReader = new BufferedReader(new FileReader(fileName));
            int count = 0;
            while ((line = fileReader.readLine()) != null) {
                count++;
                if (count > 2) {
                    ArrayList<String> myString = new ArrayList<String>();
                    String[] tokens = line.split(delimiter);
                    for (String token : tokens) {
                        // if (!(token.equals("") || token.equals(" "))) {
                        myString.add(token);
                        //  }
                    }
                    cells.add(myString);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cells;
    }

    public List<List<String>> parseCSV(String fileName, String Delimiter, int... param) {
        BufferedReader fileReader = null;
        List<List<String>> cells = new ArrayList<>();
        try {
            String line = "";
            fileReader = new BufferedReader(new FileReader(fileName));
            int count = 0;
            while ((line = fileReader.readLine()) != null) {
                count++;
                if (count > 2) {
                    String[] tokens = line.split(Delimiter);
                    ArrayList<String> myString = new ArrayList<String>();
                    for (int i = 0; i < param.length; i++) {
                        myString.add(tokens[param[i]]);
                    }
                    cells.add(myString);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cells;
    }

    public List<List<String>> parseCSV(String fileName, String Delimiter) {
        BufferedReader fileReader = null;
        List<List<String>> cells = new ArrayList<>();
        try {
            String line = "";
            fileReader = new BufferedReader(new FileReader(fileName));
            int count = 0;
            while ((line = fileReader.readLine()) != null) {
                count++;
                if (count > 2) {
                    ArrayList<String> myString = new ArrayList<String>();
                    String[] tokens = line.split(Delimiter);

                    for (String token : tokens) {
                        //     if (!(token.equals("") || token.equals(" "))) {
                        myString.add(token);
                        //    }
                    }
                    cells.add(myString);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cells;
    }
}
