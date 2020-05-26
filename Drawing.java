
import java.awt.Canvas; 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stefano Pirarba & Diana Gafiuc
 */
public class Drawing extends Canvas {
    
    private ArrayList<String> Anno;  
    private ArrayList<String> Inverno;
    private ArrayList<String> Primavera;
    private ArrayList<String> Estate;
    private ArrayList<String> Autunno;
    private String Time;
    private String Type;
    
    
    private final Color NERO = new Color(0, 0, 0);
    private final Color AZZURRO = new Color(51, 153, 255);     //per l'inverno
    private final Color ROSA = new Color(255, 102, 255);       //per la primavera
    private final Color ROSSO = new Color(255, 71, 26);        //per l'estate  
    private final Color MARRONE = new Color(172, 115, 57);     //per l'autunno
    
    
    /**
     * Crea un oggetto di classe Drawing
     * @param anno segna l'anno della rilevazione
     * @param inverno segna le rilevazioni nei mesi invernali
     * @param primavera segna le rilevazioni nei mesi primaverili
     * @param estate segna le rilevazioni nei mesi estivi
     * @param autunno segna le rilevazioni nei mesi autunnali
     * @param time serve a decidere quali rilevazioni mostrare
     * @param type indica il tipo di grafico
     */
    public Drawing(ArrayList<String> anno, ArrayList<String> inverno, ArrayList<String> primavera, ArrayList<String> estate, ArrayList<String> autunno, String time, String type)
    {
        Anno = anno;
        Inverno = inverno;
        Primavera = primavera;
        Estate = estate;
        Autunno = autunno;
        Time = time;
        Type = type;
    }
    
   
    
    @Override
    public void paint(Graphics g) 
    {
         
        if(Type.equals("Lineare"))
            drawLinear(g);
        
        if(Type.equals("Barre"))
            drawBars(g);
        
        drawLegenda(g); //non appare se arriva l'exception per colpa dell'autunno
    }
    
    
    /**
     * Disegna una legenda per rendere più comprensibile il grafico
     * @param g è una sorta di pennello per disegnare sul canvas
     */
    private void drawLegenda(Graphics g)
    {
        g.setColor(new Color(255, 255, 255));   //bianco
        Polygon sfondoLegenda = new Polygon();  //crea un poligono
        sfondoLegenda.addPoint(5, 5);           //con addPoint() si aggiungono punti al poligono che vengono automaticamente collegati da una retta
        sfondoLegenda.addPoint(110, 5);
        sfondoLegenda.addPoint(110, 110);
        sfondoLegenda.addPoint(5, 110);
        g.fillPolygon(sfondoLegenda);      //riempie il poligono con il colore attivo
        
        g.setColor(NERO);
        g.drawString("LEGENDA:", 10, 20);  
        g.setColor(AZZURRO);
        g.drawString(" - Inverno", 10, 40);
        g.setColor(ROSA);
        g.drawString(" - Primavera", 10, 60);
        g.setColor(ROSSO);
        g.drawString(" - Estate", 10, 80);
        g.setColor(MARRONE);
        g.drawString(" - Autunno", 10, 100);
        g.setColor(NERO);
    }
    
    
    /**
     * Disegna il grafico lineare, che cambia in base all'attributo Time
     * @param g è una sorta di pennello per disegnare sul canvas
     */
    private void drawLinear(Graphics g)
    {
        int deltaX = 0;    //inizialamente è 0 per far si che la prima linea sia in realtà un punto, poi diventa 50 (distanza tra le x)
        int lastX = 50;    //è la X dell'ultimo punto disegnato. Inizia da 50 così c'è un po' di spazio dal margine
        double lastY = 0;  //è la Y dell'ultimo punto disegnato
        
        //In base all'attributo Time lastY assumerà diversi valori, per far si che il grafico inizi con un punto (ovvero una linea lunga 0)
        if(Time.equals("Inverni") || Time.equals("Tutto"))
            lastY = Double.parseDouble(Inverno.get(0));
        
        if(Time.equals("Primavere"))
            lastY = Double.parseDouble(Primavera.get(0));
        
        if(Time.equals("Estati"))
            lastY = Double.parseDouble(Estate.get(0));
        
        if(Time.equals("Autunni"))
            lastY = Double.parseDouble(Autunno.get(0));
        
        
        for(int i = 0; i < Inverno.size(); i++)
        {
            if(Time.equals("Inverni"))
            {
                try
                {
                    drawLinea(g, lastX , lastY, lastX + deltaX, Double.parseDouble(Inverno.get(i)), AZZURRO, Inverno.get(i));
                    lastX += deltaX;                            //dopo aver disegnato la linea bisogna aggiornare la X aggiungendo deltaX, ovvero la distanza orizzontale tra i due punti
                    lastY = Double.parseDouble(Inverno.get(i)); //anche lastY va aggiornata, ma la Y dipende dai valori degli arrayList da cui la andiamo a leggere
                    deltaX = 70;                                //la distanza orizzontale tra i punti che formano la retta. La prima retta deve essere un punto quindi inizialmente era 0

                    drawAnnoLinear(g, lastX, Anno.get(i));      //disegna una linea verticale, con al fondo segnato l'anno
                }
                catch(Exception e)                              //nel caso venga riscontrato un errore stampiamo un messaggio di errore
                {
                    System.out.println("ERRORE!");
                }
            }
              
            
            if(Time.equals("Primavere"))
            {
                try
                {
                    drawLinea(g, lastX , lastY , lastX + deltaX, Double.parseDouble(Primavera.get(i)), ROSA, Primavera.get(i));
                    lastX += deltaX;                                //dopo aver disegnato la linea bisogna aggiornare la X aggiungendo deltaX, ovvero la distanza orizzontale tra i due punti
                    lastY = Double.parseDouble(Primavera.get(i));   //anche lastY va aggiornata, ma la Y dipende dai valori degli arrayList da cui la andiamo a leggere
                    deltaX = 70;                                    //la distanza orizzontale tra i punti che formano la retta. La prima retta deve essere un punto quindi inizialmente era 0

                    drawAnnoLinear(g, lastX, Anno.get(i));          //disegna una linea verticale, con al fondo segnato l'anno
                }
                catch(Exception e)
                {
                    System.out.println("ERRORE!");                  //nel caso venga riscontrato un errore stampiamo un messaggio di errore
                }
            }
            
            if(Time.equals("Estati"))
            {
                try
                {
                    drawLinea(g, lastX , lastY , lastX + deltaX, Double.parseDouble(Estate.get(i)), ROSSO, Estate.get(i));
                    lastX += deltaX;                                //dopo aver disegnato la linea bisogna aggiornare la X aggiungendo deltaX, ovvero la distanza orizzontale tra i due punti
                    lastY = Double.parseDouble(Estate.get(i));      //anche lastY va aggiornata, ma la Y dipende dai valori degli arrayList da cui la andiamo a leggere
                    deltaX = 70;                                    //la distanza orizzontale tra i punti che formano la retta. La prima retta deve essere un punto quindi inizialmente era 0

                    drawAnnoLinear(g, lastX, Anno.get(i));          //disegna una linea verticale, con al fondo segnato l'anno
                }
                catch(Exception e)
                {
                    System.out.println("ERRORE!");                  //nel caso venga riscontrato un errore stampiamo un messaggio di errore
                }
                
            }
                                                        
            if(Time.equals("Autunni"))
            {
                try
                {
                    drawLinea(g, lastX , lastY , lastX + deltaX, Double.parseDouble(Autunno.get(i)), MARRONE, Autunno.get(i));
                    lastX += deltaX;                                //dopo aver disegnato la linea bisogna aggiornare la X aggiungendo deltaX, ovvero la distanza orizzontale tra i due punti
                    lastY = Double.parseDouble(Autunno.get(i));     //anche lastY va aggiornata, ma la Y dipende dai valori degli arrayList da cui la andiamo a leggere
                    deltaX = 70;                                    //la distanza orizzontale tra i punti che formano la retta. La prima retta deve essere un punto quindi inizialmente era 0
                
                    drawAnnoLinear(g, lastX, Anno.get(i));          //disegna una linea verticale, con al fondo segnato l'anno
                }
                catch(Exception e)
                {
                    System.out.println("ERRORE!");                  //nel caso venga riscontrato un errore stampiamo un messaggio di errore
                }
            }      
        }   
        
        
       
        if(Time.equals("Tutto")) 
        {
           for(int j = 0; j < 4; j++) //facciamo 4 cicli, e in ognuno di essi stampiamo una diversa stagione in base al valore di j
           {
               deltaX = 0;
               lastX = 50;
               if(j == 0)
                   lastY = Double.parseDouble(Inverno.get(0));
               if(j == 1)
                   lastY = Double.parseDouble(Primavera.get(0));
               if(j == 2)
                   lastY = Double.parseDouble(Estate.get(0));
               if(j == 3)
                   lastY = Double.parseDouble(Autunno.get(0));
               
               
               
                for(int i = 0; i < Inverno.size(); i++)
                {
                    try
                    {
                        if(j == 0)
                        {
                            drawLinea(g, lastX , lastY, lastX + deltaX, Double.parseDouble(Inverno.get(i)), AZZURRO);
                            drawTemp(g, lastX + deltaX, 612, Inverno.get(i), AZZURRO); //scrive il valore della temperatura a fondo della pagina, vicino all'anno
                        }
                        if(j == 1)
                        {
                            drawLinea(g, lastX , lastY, lastX + deltaX, Double.parseDouble(Primavera.get(i)), ROSA);
                            drawTemp(g, lastX + deltaX, 624, Primavera.get(i), ROSA);  //scrive il valore della temperatura a fondo della pagina, vicino all'anno
                        }
                            
                        if(j == 2)
                        {
                            drawLinea(g, lastX , lastY, lastX + deltaX, Double.parseDouble(Estate.get(i)), ROSSO);
                            drawTemp(g, lastX + deltaX, 636, Estate.get(i), ROSSO);    //scrive il valore della temperatura a fondo della pagina, vicino all'anno
                        }
                            
                        if(j == 3)
                        {
                            drawLinea(g, lastX , lastY, lastX + deltaX, Double.parseDouble(Autunno.get(i)), MARRONE);
                            drawTemp(g, lastX + deltaX, 648, Autunno.get(i), MARRONE); //scrive il valore della temperatura a fondo della pagina, vicino all'anno
                        }
                            
                        drawAnnoLinear(g, lastX + deltaX, Anno.get(i));
                        
                        lastX += deltaX;
                        
                        if(j == 0)
                            lastY = Double.parseDouble(Inverno.get(i));
                        if(j == 1)
                            lastY = Double.parseDouble(Primavera.get(i));
                        if(j == 2)
                            lastY = Double.parseDouble(Estate.get(i));
                        if(j == 3)
                            lastY = Double.parseDouble(Autunno.get(i));
                        
                        deltaX = 70;
                        
                    }
                    catch(Exception e)
                    {
                
                    }
                    
                    
                    
                }
           }
                
        }
    }
    
    /**
     * Disegna una linea e cerchia il secondo punto, scrivendo vicino la stringa passata come parametro
     * @param g è una sorta di pennello per disegnare sul canvas
     * @param x1 le coordinate x del primo punto della linea
     * @param y1 le coordinate y del primo punto della linea
     * @param x2 le coordinate x del secondo punto della linea
     * @param y2 le coordinate x del secondo punto della linea
     * @param c il colore della linea e del cerchio
     * @param s la stringa sa scrivere
     */
    private void drawLinea(Graphics g, int x1, double y1, int x2, double y2, Color c, String s)
    {   
        g.setColor(c);
        g.drawLine(x1, 500 - (int)Math.round(y1*20), x2, 500 - (int)Math.round(y2*20)); 
        g.drawOval(x2-4, 500 - (int)(y2*20) - 4, 8, 8);
        g.drawString(s + " °C", x2+10, 500 - (int)Math.round(y2*20));
        g.setColor(new Color(0, 0, 0));
    }
    
    
    /**
     * Disegna una linea e cerchia il secondo punto
     * @param g
     * @param x1 le coordinate x del primo punto della linea
     * @param y1 le coordinate y del primo punto della linea
     * @param x2 le coordinate x del secondo punto della linea
     * @param y2 le coordinate x del secondo punto della linea
     * @param c il colore della linea e del cerchio
     */
    private void drawLinea(Graphics g, int x1, double y1, int x2, double y2, Color c)
    {
        g.setColor(c);
        g.drawLine(x1, 500 - (int)Math.round(y1*20), x2, 500 - (int)Math.round(y2*20));
        g.drawOval(x2-4, 500 - (int)(y2*20) - 4, 8, 8);
        g.setColor(NERO);
    }
    
    
    /**
     * Fa una linea verticale e scrive l'anno in basso
     * @param g è una sorta di pennello per disegnare sul canvas
     * @param x le coordinate x dei due punti della linea
     * @param year la stringa che verrà scritta
     */
    private void drawAnnoLinear(Graphics g, int x, String year)
    {
        g.drawLine(x, 0, x, 10000);
        g.drawString(year, x + 2, 600);
    }
    
    
    /**
     * Scrive la stringa passata per parametro alle coordinate passate per parametro con il colore passato per parametro
     * @param g
     * @param x le coordinate x a cui verrà scritta la stringa
     * @param y le coordinate x a cui verrà scritta la stringa
     * @param s la stringa che verrà scritta
     * @param c il colore con sui sarà scritta la stringa
     */
    private void drawTemp(Graphics g, int x, int y, String s, Color c)
    {
        g.setColor(c);
        g.drawString(s + " °C", x + 2, y);
        g.setColor(NERO);
    }
   
    
    
    
    
    
    
    /**
     * Disegna il grafico a barre, che cambia in base all'attributo Time
     * @param g è una sorta di pennello per disegnare sul canvas
     */
    private void drawBars(Graphics g)
    {
        int deltaX = 50;    //deltaX e lastX sono simili a prima, ma senza la preoccupazione di fare un punto inziale
        int lastX = 5; //primo punto della barra, all'inizio è 5 perchè si distanzia di 5 punti dal margine
        
        for(int i = 0; i < Inverno.size(); i++) //i contatore 
        {
            if(Time.equals("Inverni") || Time.equals("Tutto"))
            {
                drawBarra(g, lastX, (int)(Double.parseDouble(Inverno.get(i))*20), Inverno.get(i), AZZURRO);
                
                drawAnnoBars(g, lastX, Anno.get(i));
                
                lastX += deltaX;    //aggiorna lastX per distanziare le barre
            }  
            
            
            if(Time.equals("Primavere") || Time.equals("Tutto"))
            {
                drawBarra(g, lastX, (int)(Double.parseDouble(Primavera.get(i))*20), Primavera.get(i), ROSA);
                
                //mi mette l'anno sotto la barra primavera
                if(Time.equals("Primavere"))
                    drawAnnoBars(g, lastX, Anno.get(i));
                
                lastX += deltaX;    //aggiorna lastX per distanziare le barre
            } 
            
            
            if(Time.equals("Estati") || Time.equals("Tutto"))
            {
                drawBarra(g, lastX, (int)(Double.parseDouble(Estate.get(i))*20), Estate.get(i), ROSSO);
               
                if(Time.equals("Estati"))
                    drawAnnoBars(g, lastX,  Anno.get(i));
                
                 lastX += deltaX;   //aggiorna lastX per distanziare le barre
            } 
            
            
            if(Time.equals("Autunni") || Time.equals("Tutto"))
            {
                try
                {
                    drawBarra(g, lastX, (int)(Double.parseDouble(Autunno.get(i))*20), Autunno.get(i), MARRONE);
                    
                    if(Time.equals("Autunni"))
                        drawAnnoBars(g, lastX, Anno.get(i));
                    
                    lastX += deltaX;    //aggiorna lastX per distanziare le barre
                }
                catch(Exception e)
                {
                
                }   
            } 
                
        }           
    }
    
    /**
     * Disegna una barra(istogramma) di altezza y e larghezza 45
     * @param g è una sorta di pennello per disegnare sul canvas
     * @param x il primo punto del rettangolo che sarà disegnato
     * @param y l'altezza della barra
     * @param s la stringa che verrà scritta sopra la barra
     * @param c il colore della barra
     */
    private void drawBarra(Graphics g, int x, int y, String s, Color c)
    {
        g.setColor(c);
        
        //se y è positivo facciamo un normale rettangolo alto y, altrimenti facciamo un rettangolo alto y e abbassato di y, poichè l'altezza di un rettangolo non può essere negativa, e in questo modo otteniamo lo stesso risultato
        if(y >= 0)
             g.fillRect(x, 500 - y, 45, y); //x coordinata x; 500-y coordinata y; 45 spessore; y altezza
        if(y < 0)
            g.fillRect(x, 500, 45, Math.abs(y));//math.abs(y)--> valore assoluto della y
        
        //se y è positiva scriviamo la stringa sopra, se è negativa la scriviamo sotto
        if(y >= 0)
            g.drawString(s + " °C", x + 7, 500 - y - 2);//s stringa; x+7; 500-y-2 mi serve per sollevare la scritta dalla barra
        if(y < 0)
            g.drawString(s + " °C", x + 7, 500 - y + 12);
        
        g.setColor(NERO);
    }
    
    /**
     * Scrive l'anno sotto la barra
     * @param g è una sorta di pennello per disegnare sul canvas
     * @param x la coordinata a cui scrivere l'anno
     * @param year la stringa contenente l'anno
     */
    private void drawAnnoBars(Graphics g, int x, String year)
    {
       g.drawString(year, x + 5 , 570);
    }
    
}