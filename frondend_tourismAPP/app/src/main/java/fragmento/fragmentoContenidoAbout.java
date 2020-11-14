package fragmento;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yurihuallpavargasgmail.machupicchuyourself.R;

import java.util.ArrayList;
import java.util.List;


public class fragmentoContenidoAbout extends Fragment {

    private AppBarLayout appBar;
    private TabLayout pestanas;
    private ViewPager viewPager;
    public fragmentoContenidoAbout() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_contenido_about, container, false);

        if (savedInstanceState == null) {
            insertarTabs(container);

            // Setear adaptador al viewpager.
            viewPager = (ViewPager) view.findViewById(R.id.pager);
            poblarViewPager(viewPager);
            pestanas.setupWithViewPager(viewPager);
        }

        return view;
    }

    private void insertarTabs(ViewGroup container) {
        View padre = (View) container.getParent();
        appBar = (AppBarLayout) padre.findViewById(R.id.appbar);
        pestanas = new TabLayout(getActivity());
        pestanas.setBackgroundColor(getResources().getColor(R.color.orange));
        pestanas.setSelectedTabIndicatorColor(getResources().getColor(R.color.green1));
        pestanas.setTabTextColors(Color.parseColor("#FFFFFF"), getResources().getColor(R.color.textoSeleccionado));
        appBar.addView(pestanas);
    }

    private void poblarViewPager(ViewPager viewPager) {
        AdaptadorSecciones adapter = new AdaptadorSecciones(getFragmentManager());
        //fragmento itinerario
        adapter.addFragment(new fragmento_about_cusco(), getString(R.string.fragmentoCusco));
        adapter.addFragment(new fragmento_about_empresa(), getString(R.string.fragmentoEmpresa));
        adapter.addFragment(new fragmento_about_peru(), getString(R.string.fragmentoPeru));
        //fragmento descripcion
       /* fragmentoDescripcion descripcion=new fragmentoDescripcion();
        descripcion.Producto(product);
        adapter.addFragment(descripcion, getString(R.string.descripcion));

        //fragmento reservar
        fragmentoContenidoReservar reservar=new fragmentoContenidoReservar();
        reservar.Producto(product);
        adapter.addFragment(reservar, getString(R.string.shop));*/
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appBar.removeView(pestanas);
    }

    /**
     * Un {@link FragmentStatePagerAdapter} que gestiona las secciones, fragmentos y
     * titulos de las pestannas
     */
    public class AdaptadorSecciones extends FragmentStatePagerAdapter {
        private final List<Fragment> fragmentos = new ArrayList<>();
        private final List<String> titulosFragmentos = new ArrayList<>();

        public AdaptadorSecciones(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return fragmentos.get(position);
        }

        @Override
        public int getCount() {
            return fragmentos.size();
        }

        public void addFragment(android.support.v4.app.Fragment fragment, String title) {
            fragmentos.add(fragment);
            titulosFragmentos.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titulosFragmentos.get(position);
        }
    }
}
