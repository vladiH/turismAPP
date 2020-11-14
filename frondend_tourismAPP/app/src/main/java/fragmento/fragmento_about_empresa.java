package fragmento;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yurihuallpavargasgmail.machupicchuyourself.R;

public class fragmento_about_empresa extends Fragment implements View.OnClickListener {
    TextView numero;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragmento_about_empresa, container, false);
        numero=(TextView)view.findViewById(R.id.numero);
        numero.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        if(view.findViewById(R.id.numero)==view){
            String number = "+51 984 696924";
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + number));
            startActivity(intent);
        }
    }

}
