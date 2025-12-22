package UI;

import java.awt.Color;
import Enum.MoodType;

public class Theme {

    // ===== WARNA DASAR =====
    public static final Color BG_CALENDAR = new Color(80, 115, 53);   // #507335
    public static final Color BTN_DATE = new Color(232, 220, 193);    // #e8dcc1
    public static final Color TEXT_WHITE = Color.WHITE;

    // ===== WARNA MOOD =====
    public static Color moodColor(MoodType mood) {
        if (mood == null) return BTN_DATE;

        switch (mood) {
            case SENANG:
                return new Color(255, 217, 102); // #FFD966
            case SEDIH:
                return new Color(142, 197, 252); // #8EC5FC
            case MARAH:
                return new Color(242, 139, 130); // #F28B82
            case BIASA_SAJA:
                return new Color(168, 230, 207); // #A8E6CF
            case BOSAN:
                return new Color(214, 214, 214); // #D6D6D6
            case KECEWA:
                return new Color(205, 180, 219); // #CDB4DB
            default:
                return BTN_DATE;
        }
    }
}
