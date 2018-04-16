package cr.ac.unadeca.prfinal.activities;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import cr.ac.unadeca.prfinal.R;
import cr.ac.unadeca.prfinal.subclases.RunPic;
import cr.ac.unadeca.prfinal.util.Functions;

/**
 * Created by Freddy on 4/15/2018.
 */

public class bigpic extends AppCompatActivity {
    MediaPlayer mp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bigpic);

        mp = MediaPlayer.create(this,R.raw.clic );



        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        ImageView imageView = findViewById(R.id.image);
        TextView textView = findViewById(R.id.descripcion);


        RunPic  image = new RunPic();
        mp.start();

        if(!getIntent().getExtras().isEmpty()) {
            image.name = getIntent().getStringExtra("name");
            image.author = getIntent().getStringExtra("author");
            image.url = getIntent().getStringExtra("url");


            Functions.loadImage(image.url, imageView, this);
            textView.setText("Autor : "+ image.author +"\n" + "Nombre: "+ image.name );
        }else{
            Functions.loadImage( imageView, this);
            textView.setText("No se envio ninguna información" );
        }


    }

}
