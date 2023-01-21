package org.webscrape;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main {

    //make method
    //if method is non static then need object for called
    //if static then called
    //take variable string c for country
    //return string
    public static String getData(String c) throws Exception {
        //make variable for easy append
        StringBuffer br = new StringBuffer();

        br.append("<html>" +
                "<body style='text-align:center;color:red;'>");
        br.append(c.toUpperCase()+"<br>");
        //make local variable
        //place dynamic "c"
        //fetch data from this website
        String url = "https://www.worldometers.info/coronavirus/country/"+c+"/";
        //that return html (document)
        Document doc = Jsoup.connect(url).get();
        //#maincounter-wrap
        // for cssQuery
        Elements elements = doc.select("#maincounter-wrap");

        //get three numbers
        // use lambda
        elements.forEach((e)->{
            //return text
            String text = e.select("h1").text();
            String count = e.select(".maincounter-number>span").text();
            br.append(text).append(count).append("<br>");
        });
        br.append("</body>" +
                "</html>");


        //return br.toString because we can use up string
        return br.toString();
    }

    //catch exception
    public static void main(String[] args) throws Exception
    {
        System.out.println("Hello world!");
        //take user value
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter country: ");
        //store co for country
        String co= scanner.nextLine();
        System.out.println(getData(co));

        //Creating GUl
        //make gui in java that class is JFrame
        JFrame root = new JFrame("Details of Country");
        //setSize
        root.setSize(500,500);
        //make font
        Font f = new Font("Poppins",Font.BOLD,30);

        //textfield
        JTextField field = new JTextField();
        //label
        JLabel dataL = new JLabel();
        //set font
        field.setFont(f);
        dataL.setFont(f);
        field.setHorizontalAlignment(SwingConstants.CENTER);
        dataL.setHorizontalAlignment(SwingConstants.CENTER);
        //Button
        JButton button = new JButton("Get");
        button.setFont(f);
        //for click button
        //use lambda
        button.addActionListener((event)->{
            //click
           try{
               String maindata = field.getText();
               String result = getData(maindata);
               dataL.setText(result);
           } catch (Exception e) {
               e.printStackTrace();
           }
        });
        root.setLayout(new BorderLayout());
        //to add text field for top
        root.add(field, BorderLayout.NORTH);
        //center
        root.add(dataL, BorderLayout.CENTER);
        //bottom
        root.add(button,BorderLayout.SOUTH);
        //for visible
        root.setVisible(true);
    }
}