//Es la clase principal para el proyecto
public class InventarioLetras {
    //Guarda los datos en una "capsula"
    private int[] conteoLetras;     
    private int totalLetras;        
    private int letrasDiferentes;   

    //Se activa cuando se crea un objeto procesando el texto a colocar
    public InventarioLetras(String datos) {
        this.conteoLetras = new int[26]; 
        this.totalLetras = 0;
        this.letrasDiferentes = 0;

        //Verificamos que el texto no este nulo antes de empezar
        if (datos != null) {
            for (int i = 0; i < datos.length(); i++) {
                char caracter = datos.charAt(i);
                
                //Si la letra llegara a ser mayusucula la transformamos a minuscula
                if (caracter >= 'A' && caracter <= 'Z') {
                    caracter = (char)(caracter + 32);
                }
                
                //Se registra si esta entre 'a' y 'z'
                if (caracter >= 'a' && caracter <= 'z') {
                    int donde = caracter - 'a'; 
                    
                    //Si la casilla da 0 es porque es una letra diferente y nueva
                    if (conteoLetras[donde] == 0) {
                        letrasDiferentes++;
                    }
                    conteoLetras[donde]++; 
                    totalLetras++;         
                }
            }
        }
    }

    //Devuelve la cantidad de letras totales acumuladas
    public int size() {
        return this.totalLetras;
    }

    //Devuelve true si esta vacio el inventario
    public boolean isEmpty() {
        return this.totalLetras == 0;
    }

    //Devuelve cuantas veces aparece una letra especifica 
    public int get(char letra) {
        //Lo convertimos a minuscula 
        if (letra >= 'A' && letra <= 'Z') {
            letra = (char)(letra + 32);
        }
        //Si el caracter dado no es del alfabeto en ingles dara error
        if (letra < 'a' || letra > 'z') {
            throw new IllegalArgumentException("No es letra valida");
        }
        //Devuelve el valor que se guardo en la posicion de la letra
        return conteoLetras[letra - 'a'];
    }

    //Esto nos permite fijar o cambiar cuanto aparece una letra
    public void set(char letra, int valor) {
        //Convertir a minuscula
        if (letra >= 'A' && letra <= 'Z') {
            letra = (char)(letra + 32);
        }
        //Lo introducido debe ser letra y no un caracter negativo
        if (letra < 'a' || letra > 'z' || valor < 0) {
            throw new IllegalArgumentException("Error");
        }
        
        int donde = letra - 'a'; 
        
        //Dependiendo del cambio manipulamos las letras
        if (conteoLetras[donde] == 0 && valor > 0) {
            letrasDiferentes++; 
        } else if (conteoLetras[donde] > 0 && valor == 0) {
            letrasDiferentes--; 
        }
        
        //Restamos el valor viejo y sumando el nuevo como resultado
        totalLetras = totalLetras - conteoLetras[donde] + valor;
        conteoLetras[donde] = valor; 
    }

    //Inventario a String siendo ordenado alfabeticamente encerrado los corchetes
    @Override
    public String toString() {
        String texto = "[";
        //Las 26 casillas del alfabeto se recorren
        for (int i = 0; i < 26; i++) {
            //Se imprimira la letra tanto como se pida
            for (int j = 0; j < conteoLetras[i]; j++) {
                texto += (char) ('a' + i);
            }
        }
        return texto + "]";
    }

    //Se encriptara un caracter, se movera 3 posiciones adelante en el abcdario
    public char encriptarCesar(char letra) {
        if (letra >= 'a' && letra <= 'z') {
            return (char) ('a' + (letra - 'a' + 3) % 26); 
        } else if (letra >= 'A' && letra <= 'Z') {
            return (char) ('A' + (letra - 'A' + 3) % 26); 
        }
        return letra; 
    }

    //Se desencriptara un caracter, se movera 3 posiciones atras en el abcdario
    public char desencriptarCesar(char letra) {
        if (letra >= 'a' && letra <= 'z') {
            //Sumara +26 pero antes del modulo evitando numeros negativos
            return (char) ('a' + (letra - 'a' - 3 + 26) % 26);
        } else if (letra >= 'A' && letra <= 'Z') {
            return (char) ('A' + (letra - 'A' - 3 + 26) % 26);
        }
        return letra; 
    }

    //Se encriptara la cadena completa repitiendo el proceso de Cesar segun sea el numero de desplazamiento
    public String encriptarPalabra(String palabra, int desplazamiento) {
        String resultado = "";
        //Recorreremos letra usando un bucle
        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);
            
            //Avanzara los espacios de desplazamiento en el abecedario sin triplicar los saltos
            if (letra >= 'a' && letra <= 'z') {
                letra = (char) ('a' + (letra - 'a' + desplazamiento) % 26);
            } else if (letra >= 'A' && letra <= 'Z') {
                letra = (char) ('A' + (letra - 'A' + desplazamiento) % 26);
            }
            resultado += letra; 
        }
        return resultado;
    }

    //Desencriptara la cadena completa, se aplicara el desencriptado Cesar pero inverso
    public String desencriptarPalabra(String palabra, int desplazamiento) {
        String resultado = "";
        //Desarmara la palabra pasando posicion por posicion
        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);
            
            //Resta el desplazamiento en el abecedario 
            if (letra >= 'a' && letra <= 'z') {
                letra = (char) ('a' + (letra - 'a' - desplazamiento + 26 * desplazamiento) % 26);
            } else if (letra >= 'A' && letra <= 'Z') {
                letra = (char) ('A' + (letra - 'A' - desplazamiento + 26 * desplazamiento) % 26);
            }
            resultado += letra; 
        }
        return resultado;
    }

    //Se sumaran recuentos de las letras de este inventario con otro, creando asi uno nuevo
    public InventarioLetras add(InventarioLetras otro) {
        InventarioLetras nuevo = new InventarioLetras(""); 
        for (int i = 0; i < 26; i++) {
            char letraActual = (char)('a' + i);
            int suma = this.conteoLetras[i] + otro.conteoLetras[i]; 
            nuevo.set(letraActual, suma); 
        }
        return nuevo; 
    }

    //Restaremos los recuentos de letras del otro inventario yendo asi al inventario actual
    public InventarioLetras subtract(InventarioLetras otro) {
        InventarioLetras nuevo = new InventarioLetras(""); 
        for (int i = 0; i < 26; i++) {
            int resta = this.conteoLetras[i] - otro.conteoLetras[i]; 
            //Si alguna resta da un numero negativo se devolvera nulo
            if (resta < 0) {
                return null;
            }
            nuevo.set((char)('a' + i), resta); 
        }
        return nuevo;
    }

    //Multiplicara todos los conteos de todas las letras por un número "n"
    public InventarioLetras amplifies(int n) {
        InventarioLetras nuevo = new InventarioLetras(""); 
        for (int i = 0; i < 26; i++) {
            //Multiplicaremos la cantidad actual por el factor n asi asignandose al nuevo objeto
            nuevo.set((char)('a' + i), this.conteoLetras[i] * n);
        }
        return nuevo; 
    }
}