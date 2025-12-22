package Enum;

public enum MoodType {
    DEFAULT,     
    MARAH,
    KECEWA,
    SEDIH,
    BOSAN,
    BIASA_SAJA,
    SENANG;

    @Override
    public String toString() {
     
        switch(this) {
            case MARAH: return "Marah";
            case KECEWA: return "Kecewa";
            case SEDIH: return "Sedih";
            case BOSAN: return "Bosan";
            case BIASA_SAJA: return "Biasa Saja";
            case SENANG: return "Senang";
            case DEFAULT: return "Pilih Mood";
            default: return this.name();
        }
    }
}
