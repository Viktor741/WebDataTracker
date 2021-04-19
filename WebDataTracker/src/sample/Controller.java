package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Controller {
    // pomocná hodnota pro ukládání ceny
    String price;
    String OBJ;
    //tlačítka a textová pole
    @FXML
    Label valueLabel;
    @FXML
    Label trackerLabel;
    @FXML
    public TextField pageField;
    @FXML
    public TextField ObjectField;
    @FXML
    public TextField trackingValue;
    @FXML
    public TextField limit;
    @FXML
    public void getValue(MouseEvent mouseEvent) throws IOException {
        String URL = pageField.getText();
        String Object = ObjectField.getText();
        // tato část kódu čte html stránku
        URL url = new URL(URL);
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        // zde procházíme String řádek po řádku
        String line = null;
        while ((line = br.readLine()) != null) {
            //pole pro porovnání pozdějšího řetězce které použijeme na vytáhnutí hodnot týkající se ceny
            char[] ch1 = new char[] { '0','1','2','3','4','5','6','7','8','9','.'};
            if (line.contains(Object)) {
                if (line.contains("cena")) {
                    String[] splitString = line.split("cena");
                    OBJ = splitString[1];
                    char[] ch = new char[OBJ.length()];
                    char[] ch2 = new char[OBJ.length()];
                    for (int i = 0; i < 15; i++) {
                        ch[i] = OBJ.charAt(i);
                        for(int j=0;j<ch1.length;j++){
                            if(ch[i] == ch1[j]){
                                ch2[i]=ch[i];
                            }
                        }
                    }
                    price = String.valueOf(ch2);
                } else {
                    if (line.contains("Cena")) {
                        String[] splitString = line.split("Cena");
                        OBJ = splitString[1];
                        char[] ch = new char[OBJ.length()];
                        char[] ch2 = new char[OBJ.length()];
                        for (int i = 0; i < 15; i++) {
                            ch[i] = OBJ.charAt(i);
                            for(int j=0;j<ch1.length;j++){
                                if(ch[i] == ch1[j]){
                                    ch2[i]=ch[i];
                                }
                            }
                        }
                        price = String.valueOf(ch2);
                    } else {
                        if (line.contains("price")) {
                            String[] splitString = line.split("price");
                            OBJ = splitString[1];
                            char[] ch = new char[OBJ.length()];
                            char[] ch2 = new char[OBJ.length()];
                            for (int i = 0; i < 15; i++) {
                                ch[i] = OBJ.charAt(i);
                                for(int j=0;j<ch1.length;j++){
                                    if(ch[i] == ch1[j]){
                                        ch2[i]=ch[i];
                                    }
                                }
                            }
                            price = String.valueOf(ch2);
                        } else {
                            if (line.contains("Price")) {
                                String[] splitString = line.split("price");
                                OBJ = splitString[1];
                                char[] ch = new char[OBJ.length()];
                                char[] ch2 = new char[OBJ.length()];
                                for (int i = 0; i < 15; i++) {
                                    ch[i] = OBJ.charAt(i);
                                    for(int j=0;j<ch1.length;j++){
                                        if(ch[i] == ch1[j]){
                                            ch2[i]=ch[i];
                                        }
                                    }
                                }
                                price = String.valueOf(ch2);
                            }
                            }
                        }
                    }
                valueLabel.setText("ProduktNalezen "+price);
                }
            }
        }
        // Kód co zapisuje hodnotu nalezeného objektu do souboru
    @FXML
    public void LaunchTracker(MouseEvent mouseEvent) {
        try {
               String holder = limit.getText();
            int l=Integer.parseInt(holder);
            for(int i=0;i<=l;i++) {
                TimeUnit.SECONDS.sleep(Long.parseLong(trackingValue.getText()));
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                getValue(mouseEvent);
                File log = new File(ObjectField.getText() + ".txt");
                FileOutputStream fo = new FileOutputStream(log,true);
                OutputStreamWriter osw = new OutputStreamWriter(fo);
                Writer w = new BufferedWriter(osw);
                w.write("\n");
                w.write(dtf.format(now));
                w.write(price);
                w.close();
            }
        } catch (IOException | InterruptedException e) {
        e.printStackTrace();
    }
        trackerLabel.setText("Tracker started");
    }
}
