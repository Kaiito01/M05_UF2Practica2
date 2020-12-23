package com.joseugal.m05.uf2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import static com.joseugal.m05.uf2.mainActivity.changeFormat;
import static org.junit.jupiter.api.Assertions.*;

class mainActivityTest {

    private static String nom;
    private static String cognoms;
    private static String nombreCompleto;
    private static HashMap<String, String> listaUsuarios = new HashMap<String, String>();

    @org.junit.jupiter.api.BeforeEach
    void setUp () {
        nombreCompleto = "Ugal Luque, Jose María";
        String datos[] = nombreCompleto.split ( "," );
        nom = datos[1];
        cognoms = datos[0];
        System.out.println("SET UP");
        // Comprobacion formato
        listaUsuarios = changeFormat(nom, cognoms,listaUsuarios,nombreCompleto);
        System.out.println("SET UP DONE");

    }

    @org.junit.jupiter.api.Test
        public HashMap<String, String> changeFormat (String nom, String cognoms, HashMap<String, String> lista, String nombreCompleto) {
        System.out.println ( "CHANGE FORMAT" );
        // Cambiamos caracteres nombre & apellidos
        nom = specialChar(nom);
        cognoms = specialChar(cognoms);

        // Eliminamos caracteres extra de apellidos
        cognoms = deleteExtraChar ( cognoms );


        // Eliminaremos los espacios de los caracteres
        nom = deleteSpaceChar ( nom );
        String apellidosArr[] = cognoms.split ( " " );
        System.out.println ( "CHANGE FORMAT DONE" );
        return lista;
    }

    @org.junit.jupiter.api.Test
    public String specialChar (String cadena) {
        System.out.println("SPECIAL CHAR");
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
        System.out.println("SPECIAL CHAR DONE");
        return cadena;
    }


    @org.junit.jupiter.api.Test
    public String deleteSpaceChar (String cadena) {
        System.out.println("DELETE SPACE CHAR");
        String tmpCadena = "";
        for (int iCont = 0; iCont < cadena.length(); iCont++) {
            if (cadena.charAt(iCont) != ' ' ){
                tmpCadena += cadena.charAt ( iCont );
            }
        }
        System.out.println("DELETE SPACE CHAR DONE");
        return tmpCadena;
    }

    @org.junit.jupiter.api.Test
    public String deleteExtraChar (String cadena) {
        System.out.println("DELETE EXTRA CHAR");
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
        System.out.println("DELETE EXTRA CHAR DONE");
        return cadena;

    }

    @org.junit.jupiter.api.Test
    public HashMap<String,String> createNewUser (String nombre, String apellido, HashMap<String,String> lista, String nombreCompleto) {
        System.out.println("DELETE SPACE CHAR");
        Random randClass = new Random ();
        int randomNumber = randClass.nextInt (10 );
        if(randomNumber == 0){ randomNumber = randomNumber + 1; }
        System.out.println ( randomNumber );

        Usuari u = new Usuari (nombre, apellido, nombreCompleto);
        lista.put (u.generaCorreu ( randomNumber ), nombreCompleto);
        return lista;
    }

}