
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author hugog
 */
public class clasesJava {

/**
     * @param args the command line arguments
     */
    //Declarar variables.
    static int jugadores;
    static int contadorPartida = 0;
    static int ronda = 0;
    static int partida, solucion;
    static int puntuacion1, ganador;
    static int matriz[][];
    static int cadenaString, nickname;
    static String operadore;
    static String cadenaNombres[];

    public static void inicioPartida() {

        //Entrada.
        Scanner entrada = new Scanner(System.in);

        //Pregunta el n�mero de rondas, es decir, el modo de partida a seleccionar. Hasta que la respuesta no se ajuste a lo mostrado no se avanzar�.
        partida = 0;
        while (partida != 3 && partida != 5 && partida != 10 && partida != 20) {
            System.out.println("�Cu�ntas rondas deseas jugar? Partida r�pida: 3 // Partida corta: 5 //Partida normal: 10 //Partida larga: 20");
            partida = entrada.nextInt();
        }

        //Pregunta sobre el n�mero de jugadores. Hasta que no se ajuste a lo programado no se podr� continuar.
        jugadores = 0;
        while (jugadores < 1 || jugadores > 6) {
            System.out.println("�Cu�ntos jugadores participar�n? El n�mero de los mismos es de 1 a 6:");
            jugadores = entrada.nextInt();
        }

        //Creaci�n del array nombres.
        String cadenaNombres[] = new String[jugadores];

        for (int nickname = 0; nickname < jugadores; nickname++) {
            //Introducci�n del nombre del jugador en la matriz.
            System.out.println("�Cu�l es el nombre del jugador " + (nickname + 1) + "?: ");
            cadenaNombres[nickname] = entrada.next();

        }

        //Mostrar el n�mero correspondiente a cada nombre.
        //desordenar();
        List<String> lista_jug = Arrays.asList(cadenaNombres);
        Collections.shuffle(lista_jug);
        lista_jug.toArray(cadenaNombres);
        System.out.println(Arrays.toString(cadenaNombres));

        System.out.println("Se jugar� en el orden indicado en la lista de nombres anterior"
                + "de izquierda a derecha. Asignando a cada jugador un n�mero: 0, 1, 2, ...");

        //Creaci�n de la matriz.
        matriz = new int[jugadores][2];

        //Llamar a la funci�n ronda para iniciar las preguntas.
        ronda();
        //Llamar a la funci�n ganador.
        ganador();

    }

    public static void ronda() {

        for (int k = 0; k < partida; k++) {
            for (int g = 0; g < jugadores; g++) {
                System.out.println("Jugador " + g + " : ");

                //Pasar la cadena String a String para hacerla visible.
                String[] preguntas = preguntaAleatoria();
                String cadenaOperacion = Arrays.toString(preguntas);
                System.out.println("Resuelva la siguiente pregunta: " + cadenaOperacion);
                int respuestaCorrecta = evaluarExpresionArray(preguntas);
                System.out.println("Evaluada y es " + respuestaCorrecta);
                //Entrada.
                Scanner entrada = new Scanner(System.in);

                //Leer variable.
                solucion = entrada.nextInt();

                //Comparar la soluci�n dada por teclado con el resultado calculado.
                if (respuestaCorrecta == solucion) {
                    matriz[g][1] = matriz[g][1] + 1;
                } else {
                    //Se muestra la respuesta en el caso de fallar.
                    System.out.println("La respuesta correcta ser�a " + respuestaCorrecta);
                }

            }
        }

        //Llamar a la funci�n resultados. Se mostrar�n los resultados acumulados.
        resultados();
    }

    public static String operadorAleatorio() {

        //Generaci�n de n�mero aleatorio.
        int operador = 1;
                //(int) (1 + Math.random() * (3 - 1 + 1));
        //Mostrar n�mero random.

        //Switch para escoger uno de los operadores al azar.
        switch (operador) {
            case 1:
                operadore = "+";
                break;
            case 2:
                operadore = "-";
                break;
            case 3:
                operadore = "*";
                break;

        }
        return operadore;
    }
   

    public static String[] preguntaAleatoria() {

        //Declarar array cadenaString
        int longitudOpera = (int) (4 + Math.random() * (8 - 4 + 1));
        String cadenaString[] = new String[longitudOpera + longitudOpera - 1];
        int numAleatorio;
        String numAleatorioS;

        //Convierte los n�meros int en String.
        //Bucle encargado de rellenar el array con operandos y operadores.
        for (int p = 0; p < (longitudOpera - 1) * 2; p = p + 2) {

            //A�adir n�mero aleatorio en una posici�n del array.
            numAleatorio = (int) (2 + Math.random() * (12 - 2 + 1));
            numAleatorioS = Integer.toString(numAleatorio);

            //A�adirlo con tipo String.
            cadenaString[p] = numAleatorioS;

            //A�adir operador en una posici�n del array.
            cadenaString[p + 1] = operadorAleatorio();

        }

        //A�adir n�mero aleatorio en una posici�n del array.
        numAleatorio = (int) (2 + Math.random() * (12 - 2 + 1));
        numAleatorioS = Integer.toString(numAleatorio);

        cadenaString[cadenaString.length - 1] = numAleatorioS;

        return cadenaString;

    }

    public static int evaluarExpresionArray(String[] exp) {

        int valor = 0;
        String[] expRed = new String[exp.length];
        expRed = Arrays.copyOf(exp, exp.length);

        for (int i = 0; i < expRed.length; i++) {
            if (expRed[i] == "*") {
                expRed[i + 1] = multiplicar(expRed[i - 1], expRed[i + 1]);
                expRed[i - 1] = "0";
                if (i - 2 > 0 && expRed[i - 2] == "-") {
                    expRed[i] = "-";
                } else {
                    expRed[i] = "+";
                }
            }
        }

        System.out.print("La expresi�n matem�tica reducida es: ");
        for (int i = 0; i < expRed.length; i++) {
            System.out.print(expRed[i]);
        }
        System.out.println();

        String op = "";
        for (int i = 0; i < expRed.length; i++) {
            if (i == 0) {
                valor = Integer.parseInt(expRed[i]);
            } else if (expRed[i] == "+") {
                op = "suma";
            } else if (expRed[i] == "-") {
                op = "resta";
            } else {
                if (op == "suma") {
                    valor = valor + Integer.parseInt(expRed[i]);
                    op = "";
                } else if (op == "resta") {
                    valor = valor - Integer.parseInt(expRed[i]);
                    op = "";
                }
            }
        }
        return valor;
    }

    private static String multiplicar(String num1, String num2) {
        int num1Int, num2Int, resInt;
        String resString;

        num1Int = Integer.parseInt(num1);
        num2Int = Integer.parseInt(num2);
        resInt = num1Int * num2Int;
        resString = Integer.toString(resInt);
        return resString;

        // Tambi�n se podr�a poner en una sola l�nea:
        // Integer.toString (Integer.parseInt (num1) * Integer.parseInt (num2));
    }

    public static void resultados() {

        for (int i = 0; i < jugadores; i++) {

            //Se muestran los resultados junto al nombre.
            System.out.print("Jugador" + i + ":" + matriz[i][1] + "\n");
        }

    }

    public static void ganador() {

        puntuacion1 = matriz[0][1];
        ganador = matriz[0][0];

        for (int i = 0; i < jugadores - 1; i++) {

            /*Comparaci�n de todas las puntuaciones referentes a los resultados finales tanto en ronda
            como en el fin de la partida.*/
            if (puntuacion1 < matriz[i + 1][1]) {

                puntuacion1 = matriz[i + 1][1];
                ganador = matriz[i + 1][0];

            }
        }

        //Tenemos en puntuacion1 los puntos ganadores y en ganador el nombre (id).
        String result_f = ""; //Se utilizar� para mostrar al ganador y en caso de empate.
        int ganadores = 0;

        for (int i = 0; i < jugadores; i++) {

            /*Comparaci�n de todas las puntuaciones referentes a los resultados finales.*/
            if (puntuacion1 == matriz[i][1]) {
                ganadores++;
                result_f = result_f + "\n Ha ganado el jugador " + i + " y su puntuaci�n es: " + puntuacion1;

            }
        }
        //Comprobamos empate.
        if (ganadores > 1) {

            result_f = "Ha habido empate:\n" + result_f;
        }
        System.out.println(result_f);
    }
    }

