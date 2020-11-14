package fragmento;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.yurihuallpavargasgmail.machupicchuyourself.R;

import java.util.HashMap;

import clases.Peru;

public class fragmento_about_peru extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener, View.OnClickListener {
    Peru peru=new Peru();
    private SliderLayout mDemoSlider;
    private TextView textoPeru;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private TextView txtImg1;
    private TextView txtImg2;
    private TextView txtImg3;
    private TextView txtImg4;
    private TextView txtPopup;

    Button btn_Cerrar;
    LayoutInflater layoutInflater;
    View popupView;
    PopupWindow popupWindow;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragmento_about_peru, container, false);
        mDemoSlider = (SliderLayout)view.findViewById(R.id.slider);
        HashMap<String,Integer> url_maps = new HashMap<String, Integer>();
        //url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        //url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        //url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        //url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        //HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        /*
                ejemplo:
                 file_maps.put("Hannibal",R.drawable.machupicchu);
                */
        for(int i=0;i<peru.getIdDrawable().length-4;i++)
        {
            url_maps.put(peru.getNombre()[i],peru.getIdDrawable()[i]);
        }

        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            // .setOnSliderClickListener(getActivity());

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.DepthPage);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        // mDemoSlider.addOnPageChangeListener(getActivity());
        textoPeru=(TextView)view.findViewById(R.id.txtPeru);
        textoPeru.setText(peru.getTexto());

        img1=(ImageView)view.findViewById(R.id.imgPeru1);
        img1.setImageResource(peru.getIdDrawable()[6]);
        txtImg1=(TextView)view.findViewById(R.id.textImgPeru1);
        txtImg1.setText(peru.getNombre()[6]);
        img1.setOnClickListener(this);


        img2=(ImageView)view.findViewById(R.id.imgPeru2);
        img2.setImageResource(peru.getIdDrawable()[7]);
        txtImg2=(TextView)view.findViewById(R.id.textImgPeru2);
        txtImg2.setText(peru.getNombre()[7]);
        img2.setOnClickListener(this);

        img3=(ImageView)view.findViewById(R.id.imgPeru3);
        img3.setImageResource(peru.getIdDrawable()[8]);
        txtImg3=(TextView)view.findViewById(R.id.textImgPeru3);
        txtImg3.setText(peru.getNombre()[8]);
        img3.setOnClickListener(this);

        img4=(ImageView)view.findViewById(R.id.imgPeru4);
        img4.setImageResource(peru.getIdDrawable()[9]);
        txtImg4=(TextView)view.findViewById(R.id.textImgPeru4);
        txtImg4.setText(peru.getNombre()[9]);
        img4.setOnClickListener(this);
        return view;
    }

    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(getActivity(), slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

    @Override
    public void onClick(View view) {
        if(view.findViewById(R.id.imgPeru1)==view){
            layoutInflater =(LayoutInflater)getActivity().getBaseContext().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
            popupView = layoutInflater.inflate(R.layout.popup_emergente, null);
            popupWindow = new PopupWindow(popupView, RadioGroup.LayoutParams.WRAP_CONTENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT);

            btn_Cerrar = (Button)popupView.findViewById(R.id.cerrar);
            btn_Cerrar.setOnClickListener(new Button.OnClickListener(){

                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }});

            txtPopup=(TextView)popupView.findViewById(R.id.txtPopup);
            txtPopup.setText(peru.getDescripcion()[0]);
            popupWindow.showAtLocation(view, Gravity.CENTER,0,0);
        }
        if(view.findViewById(R.id.imgPeru2)==view){
            layoutInflater =(LayoutInflater)getActivity().getBaseContext().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
            popupView = layoutInflater.inflate(R.layout.popup_emergente, null);
            popupWindow = new PopupWindow(popupView, RadioGroup.LayoutParams.WRAP_CONTENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT);

            btn_Cerrar = (Button)popupView.findViewById(R.id.cerrar);
            btn_Cerrar.setOnClickListener(new Button.OnClickListener(){

                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }});

            txtPopup=(TextView)popupView.findViewById(R.id.txtPopup);
            txtPopup.setText(peru.getDescripcion()[1]);
            popupWindow.showAtLocation(view, Gravity.CENTER,0,0);
        }
        if(view.findViewById(R.id.imgPeru3)==view){
            layoutInflater =(LayoutInflater)getActivity().getBaseContext().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
            popupView = layoutInflater.inflate(R.layout.popup_emergente, null);
            popupWindow = new PopupWindow(popupView, RadioGroup.LayoutParams.WRAP_CONTENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT);

            btn_Cerrar = (Button)popupView.findViewById(R.id.cerrar);
            btn_Cerrar.setOnClickListener(new Button.OnClickListener(){

                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }});

            txtPopup=(TextView)popupView.findViewById(R.id.txtPopup);
            txtPopup.setText(peru.getDescripcion()[2]);
            popupWindow.showAtLocation(view, Gravity.CENTER,0,0);
        }
        if(view.findViewById(R.id.imgPeru4)==view){
            layoutInflater =(LayoutInflater)getActivity().getBaseContext().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
            popupView = layoutInflater.inflate(R.layout.popup_emergente, null);
            popupWindow = new PopupWindow(popupView, RadioGroup.LayoutParams.WRAP_CONTENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT);

            btn_Cerrar = (Button)popupView.findViewById(R.id.cerrar);
            btn_Cerrar.setOnClickListener(new Button.OnClickListener(){

                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }});

            txtPopup=(TextView)popupView.findViewById(R.id.txtPopup);
            txtPopup.setText(peru.getDescripcion()[3]);
            popupWindow.showAtLocation(view, Gravity.CENTER,0,0);
        }
    }
}
