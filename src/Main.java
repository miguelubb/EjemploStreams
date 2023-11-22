import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ArrayList<Libro> libros=new ArrayList<>();
        libros.add(new Libro("Papelucho", "Marcela Paz"));
        libros.add(new Libro("Papelucho detective", "Marcela Paz"));
        libros.add(new Libro("Papelucho y mi hermana Ji", "Marcela Paz"));
        libros.add(new Libro("Papelucho historiador", "Marcela Paz"));
        libros.add(new Libro("Como programar en Java", "Deitel & Deitel"));
        for (int i = 0; i < libros.size(); i++) {
             libros.get(i).setYear(1970+i*2);
        }
        libros.get(0).setYear(2001);
        int numLibros= (int) libros.stream().count();
        int numLibrosMP= (int) libros
                .stream()
                .filter(l->l.getAutor().contains("Marcela"))
                .count();
        System.out.println("total de libros:"+numLibros);
        System.out.println("total de libros Marcela:"+numLibrosMP);
        libros.stream()
                .filter(l->l.getAutor().contains("Marcela"))
                .map(l->l.getTitulo())
                .sorted()
                .forEach(t-> System.out.println(t));

        Optional<Libro> antiguo=libros.stream()
                .min((l1,l2)->Integer.compare(l2.getYear(),l1.getYear()));
        if(antiguo.isPresent()){
            System.out.println("Libro más antiguo"+antiguo.get());
        }else{
            System.out.println("No hay libro");
        }

        Optional<Libro> deitel=libros.parallelStream()
                .filter(l->l.getAutor().contains("Deitel"))
                .findAny();
        System.out.println(deitel.get());

        List<Libro> librosMarcela=libros.stream()
                .filter(l->l.getAutor().contains("Marcela"))
                .sorted((l1,l2)->l1.getTitulo().compareTo(l2.getTitulo()))
                .toList();
        System.out.println(librosMarcela);
        
    }


}
