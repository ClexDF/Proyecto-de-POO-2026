public class PruebaInventario {
    public static void main(String[] args) {
        InventarioLetras inv = new InventarioLetras("Hola Mundo");
        System.out.println("size: " + inv.size());            // 9
        System.out.println("isEmpty: " + inv.isEmpty());      // false
        System.out.println("get('o'): " + inv.get('o'));      // 2
        System.out.println(inv);                              // 
            // [adhlnmou]

        System.out.println(inv.encriptarCesar('a'));          // 'd'
        System.out.println(inv.encriptarPalabra("play", 3)); // "sodb"
    }
}