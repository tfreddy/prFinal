package cr.ac.unadeca.prfinal.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import cr.ac.unadeca.prfinal.R;
import cr.ac.unadeca.prfinal.database.models.Users;
import cr.ac.unadeca.prfinal.database.models.Users_Table;
import cr.ac.unadeca.prfinal.util.Functions;
import cr.ac.unadeca.prfinal.util.Session;

/**
 * Created by Freddy on 4/15/2018.
 */

public class RegisterActivity extends AppCompatActivity {
    MediaPlayer mp;
    private EditText username;
    private EditText password;
    private EditText Comentario;
    private Session session;
    private ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        mp = MediaPlayer.create(this,R.raw.clic );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registry_view );
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Comentario = findViewById(R.id.Comentario);
        image = findViewById(R.id.imageLogin);

        session = new Session(this);



        Button registrar = findViewById(R.id.register);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();

                try{
                    validar(username.getText().toString(),password.getText().toString(), Comentario.getText().toString());
                    goToRegistrar(username.getText().toString(),password.getText().toString(), Comentario.getText().toString());

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }

        });


        Functions.loadImage("https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-643387.jpg", image, this);

    }

    private  void goToMain(){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }

    private void validar(String username, String password, String roll)throws Exception{
        if(username.isEmpty())
            throw new Exception("El nombre del usuario esta vacio");
        if(password.isEmpty())
            throw new Exception("La contraseña esta vacia");
        if(roll.isEmpty())
            throw new Exception("El rol del usuario esta vacio");
    }


    private boolean goToRegistrar(String username, String password, String Comentario)throws Exception{
        boolean isLoggedIn= false;
        isLoggedIn = isLoggedIn = SQLite.selectCountOf().from(Users.class).where(Users_Table.username.eq(username)).and(Users_Table.password.eq(Functions.md5(password))).hasData();

        if (isLoggedIn){
            throw new Exception("EL usuario ya existe");
        }else{
            Users user = new Users();
            user.nombre=username;
            user.username=username;
            user.password=Functions.md5(password);
            user.Cometario=Comentario;
            user.save();
            Session session = new Session(getApplicationContext());
            session.createLoginSession(user.id,user.nombre,user.Cometario);
            goToMain();
        }
        return isLoggedIn;
    }


}
