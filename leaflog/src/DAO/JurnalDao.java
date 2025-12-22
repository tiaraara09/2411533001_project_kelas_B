package DAO;

import java.util.List;
import model.Jurnal;
import model.MoodTracker;

public interface JurnalDao {

    void insert(Jurnal cctn);

    public boolean update(Jurnal cttn);

    public List<Jurnal> show();


}
