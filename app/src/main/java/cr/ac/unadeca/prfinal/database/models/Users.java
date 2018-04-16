package cr.ac.unadeca.prfinal.database.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import cr.ac.unadeca.prfinal.database.DbGaleria;

/**
 * Created by Freddy on 4/15/2018.
 */

@Table(database = DbGaleria.class)
public class Users extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    public long id;

    @Column
    public String username;

    @Column
    public String password;

    @Column
    public String nombre;

    @Column
    public String Cometario;

}
