package com.joseugal.m05.uf2;

import java.io.*;
import java.util.*;


public class mainActivity {
    private static final String path = "./src/com/joseugal/m05/uf2/listaNombres.csv";
    private static final String newCSVFile = "namesToEmail.csv";
    private static final String separador = ",";
    private static HashMap<String, String> listaUsuarios = new HashMap<String, String>();

    public static void main ( String[] args ) throws IOException {

        String nom;
        String cognoms;
        String nombreCompleto;

        // Abro el .csv en buffer de lectura
        BufferedReader bufferLectura = new BufferedReader(new FileReader (path));

        // Leo una línea del archivo
        String linea = bufferLectura.readLine();

        while (linea != null) {
            // Separa la línea leída con el separador definido previamente
            nombreCompleto = linea;
            String[] tmpUser = (linea.split(separador));
            System.out.println( Arrays.toString(tmpUser) );
            nom = tmpUser[1];
            cognoms = tmpUser[0];


            // Comprobacion formato
            listaUsuarios = changeFormat(nom, cognoms,listaUsuarios,nombreCompleto);


            // Vuelvo a leer del fichero
            linea = bufferLectura.readLine();
        }

        // CIerro el buffer de lectura
        bufferLectura.close();

        // Comprobamos lista
        // checkUserList(listaUsuarios);

        // Creamos fichero CSV
        makeCSVFIle(listaUsuarios, newCSVFile);

    }

    public static HashMap<String,String> changeFormat(String nombre, String apellidos,HashMap<String,String> lista, String nombreCompleto){

        // Cambiamos caracteres nombre & apellidos
        nombre = specialChar(nombre);
        apellidos = specialChar(apellidos);

        // Eliminamos caracteres extra de apellidos
        apellidos = deleteExtraChar ( apellidos );


        // Eliminaremos los espacios de los caracteres
        nombre = deleteSpaceChar ( nombre );
        String apellidosArr[] = apellidos.split ( " " );
        System.out.println ( "NOM: " + nombre + " | COGNOM: " + apellidosArr[0] ) ;

        return createNewUser(nombre,apellidosArr[0],lista,nombreCompleto);

    }


    public static String specialChar(String cadena){
        // Eliminar caracteres raros
        String CaractersProhibits = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖØÙÚÛÜÝßàáâãäåæçèéêëìíîïðñòóôõöøùúûüýÿÑñ&";
        String CaractersRemplazar = "AAAAAAACEEEEIIIIDNOOOOOOUUUUYBaaaaaaaceeeeiiiionoooooouuuuyyNn*";

        for (int i = 0; i < cadena.length(); i++) {
            for (int j = 0; j < CaractersProhibits.length(); j++) {
                if (cadena.charAt(i) == CaractersProhibits.charAt(j)) {
                    cadena = cadena.replace(cadena.charAt(i), CaractersRemplazar.charAt(j));

                }
            }
        }

        return cadena;
    }

    public static String deleteExtraChar(String cadena){
        ArrayList<String> preposicio = new ArrayList<>();
        preposicio.add("de ");
        preposicio.add("del ");
        preposicio.add("d' ");
        preposicio.add("la ");
        preposicio.add("el ");
        preposicio.add("l' ");

        for (int i = 0; i < cadena.length(); i++) {
            for (int j = 0; j < preposicio.size(); j++) {
                if (cadena.startsWith(preposicio.get(j))) {
                    cadena = cadena.replaceFirst(preposicio.get(j), "");
                }
            }
        }

        return cadena;
    }

    public static String deleteSpaceChar(String cadena){
        String tmpCadena = "";
        for (int iCont = 0; iCont < cadena.length(); iCont++) {
            if (cadena.charAt(iCont) != ' ' ){
                tmpCadena += cadena.charAt ( iCont );
            }
        }
        return tmpCadena;
    }

    public static HashMap<String,String> createNewUser(String nombre, String apellido, HashMap<String,String> lista, String nombreCompleto){
        Random randClass = new Random ();
        int randomNumber = randClass.nextInt (10 );
        if(randomNumber == 0){ randomNumber = randomNumber + 1; }
        System.out.println ( randomNumber );

        Usuari u = new Usuari (nombre, apellido, nombreCompleto);
        lista.put (u.generaCorreu ( randomNumber ), nombreCompleto);
        return lista;
    }

    /*
    public static void checkUserList(HashMap<String,String> lista){
        Iterator it = lista.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getValue() + " > " +pair.getKey());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }
    */

    public static void makeCSVFIle(HashMap<String,String> lista, String file) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File (file));
        StringBuilder sb = new StringBuilder();
        Iterator it = lista.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            //System.out.println(pair.getValue() + " > " +pair.getKey());
            sb.append(pair.getValue());
            sb.append(" > ");
            sb.append(pair.getKey());
            sb.append("\n");
        }
        pw.write(sb.toString());
        pw.close();
        System.out.println("Creación de fichero realizada con éxito!");
    }

}
