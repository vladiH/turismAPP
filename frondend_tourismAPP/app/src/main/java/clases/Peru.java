package clases;

import com.yurihuallpavargasgmail.machupicchuyourself.R;

/**
 * Created by YURI VLADIMIR on 14/03/2017.
 */
public class Peru {
    public int[] getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(int[] idDrawable) {
        this.idDrawable = idDrawable;
    }

    public String[] getNombre() {
        return nombre;
    }

    public void setNombre(String[] nombre) {
        this.nombre = nombre;
    }

    public String[] getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String[] descripcion) {
        this.descripcion = descripcion;
    }

    public static String getTexto() {
        return texto;
    }

    public static void setTexto(String texto) {
        Peru.texto = texto;
    }
    private static int [] idDrawable;
    private static String [] nombre;
    private static String [] descripcion;
    private static String texto;
    static
    {
        texto="Our country offers you the chance to awaken unfulfilled dreams, connect with yourself and explore five thousand years of living history. Travel through Peru and savor its exquisite food, enjoy its art and discover places where you can enjoy a time of peace and pleasure. Explore its countless natural wonders and the archaeological monuments of its ancient cultures. We invite you to travel and discover the Peru you still have not seen.";
        idDrawable=new int[10];
        //imagenes grandes
        idDrawable[0]= R.drawable.arequipal_peru;
        idDrawable[1]= R.drawable.cuscol_peru;
        idDrawable[2]= R.drawable.ical_peru;
        idDrawable[3]= R.drawable.mancoral_peru;
        idDrawable[4]= R.drawable.puertomaldonadol_peru;
        idDrawable[5]= R.drawable.punol_peru;

        //imagenes pequenas de la danza, cultura y comida
        idDrawable[6]= R.drawable.cusco_peru;
        idDrawable[7]= R.drawable.lima_peru;
        idDrawable[8]= R.drawable.ica_peru;
        idDrawable[9]= R.drawable.arequipa_peru;
        //nombre de cada lugar
        nombre =new String[10];
        nombre[0]="Arequipa";
        nombre[1]="Cusco";
        nombre[2]="Ica";
        nombre[3]="Mancora";
        nombre[4]="Puerto maldonado";
        nombre[5]="Puno";
        //nombre de cada imagen pequena
        nombre[6]="Cusco";
        nombre[7]="Lima";
        nombre[8]="Ica";
        nombre[9]="Arequipa";
        //descripcion de cada imagen pequena
        descripcion=new String[4];
        descripcion[0]="Seductive, striking and natural, Cusco's history lives in its streets, squares, valleys and towns.Stunning destinations and examples of fine engineering by Inca stonemasons can be seen in Choquequirao, Saysayhuaman, Kenko, Tambomachay, Ollantaytambo and Machu Picchu, the Inca jewel built with the wisdom of the ancient Peruvians in an ecological environment. Captivating landscapes such as the Sacred Valley, where the mountains are clothed by terraces.\n" +
                "Pictures villages where the past forms part of the present. Cusco really is the birthplace of the world.\n" +
                "Location: Andes mountain range in the south of Peru\n" +
                "Extension: 72,104 km2.";
        descripcion[1]="In the north is the city of Caral, the oldest civilization of America, and to the south is the Archaeological Complex of Pachacamac. Lima's history predates the colonial presence in the country. The establishment of the viceroyalty transformed the city into the main political and administrative center of South America. During this period, significant churches, monasteries, mansions and balconies were built. The arrival of modernity didn't transform the historic center, which is recognized as a World Heritage Site.\n" +
                "Museums with great works of art, archaeological sites, beaches, the boardwalk, valleys, natural reserves, the nightlife, the thrill of adventure sports, and the exquisite cuisine gives Peru's capital an authentic personality and makes tourism in Lima a unique experience in the country.\n" +
                "Location: On the west central coast of Peru, on the shores of the Pacific Ocean.\n" +
                "Extension: Metropolitan Lima: 2,817 km2.";
        descripcion[2]="Ica boasts characteristic dunes and a vast desert etched with enigmatic figures, the Nazca Lines, transformed into fertile fields by ancient cultures: The Paracas and the Nazca. Land of valleys, sun, beaches and a Natural Reserve inhabited by a lush variety of flora and fauna, it is also a mysterious land of villages, home of fine wine and \"pisco\", Afro-Peruvian music, and an oasis like the Huacachina, a patch of life in the heart of a blanket of sand.\n" +
                "Ica: more than one reason to enjoy it.\n" +
                "Location: Central coast of Peru.\n" +
                "Size: 21,305 km2 (8,226 sq.miles).";
        descripcion[3]="Peru's ashlar (white stone) city with snow-capped mountains, volcanoes (e.g. Misti), deep canyons (e.g. Cotahuasi and Colca), renowned gastronomy, small coves and beaches.\n" +
                "Location: Nestling between the coast and the southern highlands of Peru, Arequipa is the land of great destinations with heights on snow-capped peaks such as Ampato (6,288 m), volcanoes like Chachani (6,075 m) and El Misti (5,825 m).\n" +
                "Extension: 63.345 km2";
    }
}
