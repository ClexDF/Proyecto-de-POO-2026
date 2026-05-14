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