package clases;

import com.yurihuallpavargasgmail.machupicchuyourself.R;

/**
 * Created by YURI VLADIMIR on 13/03/2017.
 */
public class Cusco {
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

    private static int [] idDrawable;
    private static String [] nombre;
    private static String [] descripcion;

    static
    {
        idDrawable=new int[7];
        //imagenes grandes
        idDrawable[0]= R.drawable.cusco_machupicchu;
        idDrawable[1]= R.drawable.cusco_montana_colores;
        idDrawable[2]= R.drawable.cusco_moray;
        idDrawable[3]= R.drawable.cusco_sacsayhuaman;
        idDrawable[4]= R.drawable.cusco_urubamba;

        //imagenes pequenas de la danza, cultura y comida
        idDrawable[5]= R.drawable.cusco_gente;
        idDrawable[6]= R.drawable.cusco_comida;

        //nombre de cada lugar
        nombre =new String[7];
        nombre[0]="Machupicchu";
        nombre[1]="Montaña de colores";
        nombre[2]="Moray";
        nombre[3]="Sacsayhuamán";
        nombre[4]="Urubamba";
        //nombre de cada imagen pequena
        nombre[5]="Culture";
        nombre[6]="Our food";

        //descripcion de cada imagen pequena
        descripcion=new String[2];
        descripcion[0]="What can you find in Cusco?\n" +
                " Colors, kindness, joy and an endless variety of usages and customs, clothes and dances, like that of Chunchos, Qollas, Carnaval Cusqueño, la Danza de los Doctorcitos, la Danza de los Negritos, la Contradanza, la Saqra K'achampa, el Panadero, just to mention some of them.\n" +
                "The Cusco popular art is incomparable. The natural creativity of native people has been shown throughout the history of this city that was enriched with the Spaniards' arrival who brought new ideas and techniques. \n" +
                "Cusco is famous for its textiles. Its skilled weavers astonish travelers when they weave their alpaca, llama or vicuna wool garments. The clay sculpture also stands out since it transmits the Andean view of the world and its vision of the west world. The unpredictable altarpieces and the particular religious images making are two good examples.\n" +
                "Gastronomy.\n" +
                "Your travel to Cusco will not be complete if you do not taste the unmistakable flavoring of the Andes. The \"adobo\", cuy (guinea pig), cordero (lamb)or chicharrones (cracklings) will impress your palate and you'll remember the taste forever. \n" +
                "\n" +
                "Folklore.\n" +
                "Cusco is characterized by the richness and diversity of its traditions. It stands out the dances of Chunchos, Qollas, Carnaval Cusqueño, Danza de los Doctorcitos, Danza de los Negritos, Contradanza, Saqra K'achampa and el Panadero, among others.";
        descripcion[1]="A delicious dish made with guinea pig, cheese, sausage, chicken, cecina, corn tortilla, egg of fish, among other six ingredients occupies the main tables of the Imperial City of Cusco throughout June. The chiriuchu (chilli or cold spicy in Quechua), a plateau of the Inca region that is served at Jubilee celebrations by Corpus Christi.";
    }
}
