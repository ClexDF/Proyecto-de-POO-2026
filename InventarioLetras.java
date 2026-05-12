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