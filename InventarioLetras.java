public class InventarioLetras {
    private int[] conteoLetras; 
    private int totalLetras;
    private int letrasDiferentes;

    public InventarioLetras(String datos) {
        this.conteoLetras = new int[26];
        this.totalLetras = 0;
        this.letrasDiferentes = 0;

        if (datos != null) {
            for (int i = 0; i < datos.length(); i++) {
                char caracter = datos.charAt(i);
                
                if (caracter >= 'A' && caracter <= 'Z') {
                    caracter = (char)(caracter + 32);
                }
                
                if (caracter >= 'a' && caracter <= 'z') {
                    int donde = caracter - 'a';
                    if (conteoLetras[donde] == 0) {
                        letrasDiferentes++;
                    }
                    conteoLetras[donde]++;
                    totalLetras++;
                }
            }
        }
    }

    public int size() {
        return this.totalLetras;
    }

    public boolean isEmpty() {
        return this.totalLetras == 0;
    }

    public int get(char letra) {
        if (letra >= 'A' && letra <= 'Z') {
            letra = (char)(letra + 32);
        }
        if (letra < 'a' || letra > 'z') {
            throw new IllegalArgumentException("No es letra");
        }
        return conteoLetras[letra - 'a'];
    }

    public void set(char letra, int valor) {
        if (letra >= 'A' && letra <= 'Z') {
            letra = (char)(letra + 32);
        }
        if (letra < 'a' || letra > 'z' || valor < 0) {
            throw new IllegalArgumentException("Error");
        }
        
        int donde = letra - 'a';
        
        if (conteoLetras[donde] == 0 && valor > 0) {
            letrasDiferentes++;
        } else if (conteoLetras[donde] > 0 && valor == 0) {
            letrasDiferentes--;
        }
        
        totalLetras = totalLetras - conteoLetras[donde] + valor;
        conteoLetras[donde] = valor;
    }

    @Override
    public String toString() {
        String texto = "[";
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < conteoLetras[i]; j++) {
                texto += (char) ('a' + i);
            }
        }
        return texto + "]";
    }

    public char encriptarCesar(char letra) {
        if (letra >= 'a' && letra <= 'z') {
            return (char) ('a' + (letra - 'a' + 3) % 26);
        } else if (letra >= 'A' && letra <= 'Z') {
            return (char) ('A' + (letra - 'A' + 3) % 26);
        }
        return letra;
    }

    public String encriptarPalabra(String palabra, int desplazamiento) {
        String resultado = "";
        for (int i = 0; i < palabra.length(); i++) {
            resultado += encriptarCesar(palabra.charAt(i));
        }
        return resultado;
    }

    public InventarioLetras add(InventarioLetras otro) {
        InventarioLetras nuevo = new InventarioLetras("");
        for (int i = 0; i < 26; i++) {
            char letraActual = (char)('a' + i);
            int suma = this.conteoLetras[i] + otro.conteoLetras[i];
            nuevo.set(letraActual, suma);
        }
        return nuevo;
    }

    public InventarioLetras subtract(InventarioLetras otro) {
        InventarioLetras nuevo = new InventarioLetras("");
        for (int i = 0; i < 26; i++) {
            int resta = this.conteoLetras[i] - otro.conteoLetras[i];
            if (resta < 0) {
                return null;
            }
            nuevo.set((char)('a' + i), resta);
        }
        return nuevo;
    }

    public InventarioLetras amplifies(int n) {
        InventarioLetras nuevo = new InventarioLetras("");
        for (int i = 0; i < 26; i++) {
            nuevo.set((char)('a' + i), this.conteoLetras[i] * n);
        }
        return nuevo;
    }
}