package fragmento;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.yurihuallpavargasgmail.machupicchuyourself.R;

import java.io.File;

public class fragmento_redes_sociales extends Fragment {
    //creamos los botones paracompartir
    private Button botonCompartir, botonFacebook, botonTwitter, botonWhatsapp;
    private EditText To,Subjetc;
    private ImageView err1,err2;
    private MultiAutoCompleteTextView comentario;
    //Constantes para subir foto
    private static String APP_DIRECTORY = "MachupicchuYourSelf/";
    private static String MEDIA_DIRECTORY = APP_DIRECTORY + "imagen";

    private final int MY_PERMISSIONS = 100;
    private final int PHOTO_CODE = 200;
    private final int SELECT_PICTURE = 300;

   // private ImageView mSetImage;
    //private Button mOptionButton;

    private String mPath;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragmento_redes_sociales, container, false);
       // mOptionButton = (Button) view.findViewById(R.id.foto);
        //mSetImage = (ImageView) view.findViewById(R.id.imgfoto);

        //asignamos a los botones e la actividad los botones del diseño.
        //el error se corregir al parsear
        err1=(ImageView)view.findViewById(R.id.error1);
        err2=(ImageView)view.findViewById(R.id.error2);
        To=(EditText)view.findViewById(R.id.To);
        To.setKeyListener(null);
        Subjetc=(EditText)view.findViewById(R.id.Subjet);
        comentario=(MultiAutoCompleteTextView)view.findViewById(R.id.comentario);
        botonCompartir = (Button) view.findViewById(R.id.buttonShare);
        botonFacebook = (Button) view.findViewById(R.id.buttonFacebook);
        botonTwitter = (Button) view.findViewById(R.id.buttontwitter);
        botonWhatsapp = (Button) view.findViewById(R.id.buttonwhatsapp);
       /* botonChat=(Button)view.findViewById(R.id.chat);
        botonChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comentario.getVisibility()==View.VISIBLE)
                {
                    comentario.setVisibility(View.INVISIBLE);
                }else
                {
                    comentario.setVisibility(View.VISIBLE);
                }
            }
        });
        //ahora le asignamos la accion a relizar a los botones
        mOptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptions();
            }
        });*/

        //ahora le asignamos la accion a relizar a los botones
     /*   botonCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aqui asginamos la accion a realizar
                //importamos las librerias con alt+intro

                mSetImage.buildDrawingCache();
                Bitmap bitmap = mSetImage.getDrawingCache();

                /***** COMPARTIR IMAGEN *****/
             /*   try {
                    File file = new File(getContext().getCacheDir(), bitmap + ".png");
                    FileOutputStream fOut = null;
                    fOut = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                    fOut.flush();
                    fOut.close();
                    file.setReadable(true, false);
                    final Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                    intent.putExtra(Intent.EXTRA_TEXT, comentario.getText());
                    intent.setType("image/png");
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                /*Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, comentario.getText());
                startActivity(Intent.createChooser(intent, "Share with"));*/
       /*     }
        });*/
        botonCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Verificar();
           }
        });
        botonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.texto_compartir)+ " http://footpathperu.com/mobile");
                intent.setPackage("com.facebook.katana");
                try {
                    //Enviamos el Correo iniciando una nueva Activity con el emailIntent.
                    startActivity(intent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_cliente1), Toast.LENGTH_SHORT).show();
                }
            }
        });

        botonTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.texto_compartir)+ " http://footpathperu.com/mobile");
                //Para especificar la red social especifica se le asigna en esta parte
                intent.setPackage("com.twitter.android");
                try {
                    //Enviamos el Correo iniciando una nueva Activity con el emailIntent.
                    startActivity(intent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_cliente1), Toast.LENGTH_SHORT).show();
                }
            }
        });

        botonWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,getResources().getString(R.string.texto_compartir)+ " http://footpathperu.com/mobile");
                intent.setPackage("com.whatsapp");

                try {
                    //Enviamos el Correo iniciando una nueva Activity con el emailIntent.
                    startActivity(intent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_cliente1), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    private void Verificar()
    {
        if(To.getText().toString().equals("") || Subjetc.getText().toString().equals("")) {
            if (To.getText().toString().equals("")) {
                err1.setVisibility(getView().VISIBLE);

            }
            if (Subjetc.getText().toString().equals("")) {
                err2.setVisibility(getView().VISIBLE);
            }
        }else
        {
            err1.setVisibility(getView().INVISIBLE);
            err2.setVisibility(getView().INVISIBLE);
            Enviar(To.getText().toString(), Subjetc.getText().toString(),comentario.getText().toString());
        }
    }
    private void Enviar(String to,String asunto, String mensaje) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        //String[] to = direccionesEmail;
        //String[] cc = copias;
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String []{to});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, asunto);
        emailIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
        emailIntent.setType("message/rfc822");

        try {
            //Enviamos el Correo iniciando una nueva Activity con el emailIntent.
            startActivity(Intent.createChooser(emailIntent, "Email "));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), getResources().getString(R.string.error_cliente), Toast.LENGTH_SHORT).show();
        }
    }
    private void showOptions() {
        final CharSequence[] option = {"Tomar foto", "Elegir de galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Eleige una opci�n");
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(option[which] == "Tomar foto"){
                    openCamera();
                }else if(option[which] == "Elegir de galeria"){
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent, "Selecciona app de imagen"), SELECT_PICTURE);
                }else {
                    dialog.dismiss();
                }
            }
        });

        builder.show();
    }

    private void openCamera() {
        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        boolean isDirectoryCreated = file.exists();

        if(!isDirectoryCreated)
            isDirectoryCreated = file.mkdirs();

        if(isDirectoryCreated){
            Long timestamp = System.currentTimeMillis() / 1000;
            String imageName = timestamp.toString() + ".jpg";

            mPath = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY
                    + File.separator + imageName;

            File newFile = new File(mPath);

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
            startActivityForResult(intent, PHOTO_CODE);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("file_path", mPath);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore last state for checked position.
            mPath = savedInstanceState.getString("file_path");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       // super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == getActivity().RESULT_OK){
            switch (requestCode){
                case PHOTO_CODE:
                    MediaScannerConnection.scanFile(getActivity(),
                            new String[]{mPath}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String path, Uri uri) {
                                    Log.i("ExternalStorage", "Scanned " + path + ":");
                                    Log.i("ExternalStorage", "-> Uri = " + uri);
                                }
                            });


                    Bitmap bitmap = BitmapFactory.decodeFile(mPath);
                   // mSetImage.setImageBitmap(bitmap);
                    break;
                case SELECT_PICTURE:
                    Uri path = data.getData();
                    //mSetImage.setImageURI(path);
                    break;

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == MY_PERMISSIONS){
            if(grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getActivity(), "Permisos aceptados", Toast.LENGTH_SHORT).show();
               // mOptionButton.setEnabled(true);
            }
        }else{
            showExplanation();
        }
    }

    private void showExplanation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Permisos denegados");
        builder.setMessage("Para usar las funciones de la app necesitas aceptar los permisos");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getContext().getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                getActivity().finish();
            }
        });

        builder.show();
    }

}
