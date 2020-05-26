
import java.awt.Canvas;                 //il canvas è il coso su cui disegnamo
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;                    //la classe file
import java.io.FileNotFoundException;   //eccezzione nel caso in cui non esista il file che abbiamo provato ad aprire
import java.util.ArrayList;
import java.util.Scanner;               //lo scanner è la classe con cui leggiamo in input
import javax.swing.JFrame;              //il frame è la classe finestra
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stefano Pirarba & Diana Gafiuc
 */
public class Main {

    public static void main(String[] args) {
        
        // questo try controlla che ci sia il file
        try {            
             String fileMax = "temperature_max.csv";    //nome del file da aprire: inseriamo temperature_max nella stringa "fileMax"
             
             File file = new File(fileMax);             //crea un oggetto file da cui andremo a leggere
             Scanner inputStream = new Scanner(file);   //crea uno scanner che andrà a leggere dal file
             
             ArrayList<String> anno = new ArrayList<String>();
             ArrayList<String> inverno = new ArrayList<String>();
             ArrayList<String> primavera = new ArrayList<String>();
             ArrayList<String> estate = new ArrayList<String>();
             ArrayList<String> autunno = new ArrayList<String>();

                          
             inputStream.nextLine();        //legge tutta una riga. Dato che la prima riga non è da leggere la ignoriamo
             while(inputStream.hasNext())   //se c'è una riga dopo
             {  
                 String data = inputStream.nextLine();  //data legge tutta riga. -->data sta per dato
                 String[] cell = data.split(";");       //con split() posso dividere una stringa in più stringhe, salvate in un array. ; è il carattere di divisione perchè stiamo leggendo un file csv
                 
                 anno.add(cell[0]);         //nel file la prima cella, copiata in cell[0], contiene sempre l'anno delle misurazioni
                 inverno.add(cell[1]);      //la seconda cella contiene i dati relativi all'inverno e così via
                 primavera.add(cell[2]);
                 estate.add(cell[3]);
                 autunno.add(cell[4]);
                 
                                  
             }
             
             
            
//-----------------------------------------------------------------------------//             
            JFrame frame = new JFrame("");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Fa si che il programma si chiuda quando si preme la X
            frame.setVisible(true);                               //rende visibila la finestra
            frame.setLocation(400, 150);                          //è il punto dove apparirà la pagina
            
            
            
            JPanel pane =new JPanel();
            pane.setSize(500, 500);
            
            
            JComboBox<String> time = new JComboBox<String>(); //finestra con le varie opzioni
            time.addItem("Tutto"); //sono i valori che può assumere
            time.addItem("Inverni");
            time.addItem("Primavere");
            time.addItem("Estati");
            time.addItem("Autunni");
            time.setVisible(true); //serve per visualizzare il tutto
           
            
            
            pane.add(new JLabel("Visualizzazione:"));
            pane.add(time);
           
            
            JComboBox<String> graf = new JComboBox<String>(); //finestra con le varie opzioni
            graf.addItem("Lineare");
            graf.addItem("Barre");
            
            pane.add(new JLabel("Tipo di grafico"));
            pane.add(graf);
            
            JButton button = new JButton("OK");
            button.addActionListener(new ActionListener(){ //quando viene premuto crea la nuova pagina (quella con il grafico)
            
                   public void actionPerformed(ActionEvent e) //metodo dell'ActionListener che andiamo ad implementare
                   {
                        JFrame graph = new JFrame("Media temperatura massima varie stagioni dal 1991 al 2017");   //la striga passata al costruttore sarà il nome della nuova finestra (quello che appare in alto a sinistra)
                        Canvas canvas = new Drawing(anno, inverno, primavera, estate, autunno, time.getSelectedItem().toString(), graf.getSelectedItem().toString()); //stiamo passando tutte le cose che possiamo leggere nella canvas
                        
                        canvas.setSize(800, 650); //indica la grandezza iniziale della finestra. Il primo paramentro è la lunghezza orizzontale e la seconda quella verticale
                        graph.add(canvas); //aggiungiamo il canvas alla finestra
                        graph.pack(); //mette la finestra alla dimensione indicata
                        graph.setVisible(true); //rende la finestra visibile
                   }
                
            });
            
            pane.add(button);
            
            frame.add(pane);
            
            frame.setSize(400, 150);
            frame.setResizable(false);//fa si che la prima finestra che vediamo non possa essere ingrandita o diminuita
//-----------------------------------------------------------------------------//            
             
             
        } catch (FileNotFoundException ex) {  //nel caso in cui il file non venga trovato
            System.out.println("File non trovato!");
        }
        
        
        
    }
     
    
}
