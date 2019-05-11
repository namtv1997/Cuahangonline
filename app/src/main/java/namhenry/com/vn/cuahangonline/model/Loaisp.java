package namhenry.com.vn.cuahangonline.model;

public class Loaisp {
    public int Id;
    public String TenLoaisp;
    public String Hinhanhloaisp;

    public Loaisp(int id, String tenLoaisp, String hinhanhloaisp) {
        Id = id;
        TenLoaisp = tenLoaisp;
        Hinhanhloaisp = hinhanhloaisp;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenLoaisp() {
        return TenLoaisp;
    }

    public void setTenLoaisp(String tenLoaisp) {
        TenLoaisp = tenLoaisp;
    }

    public String getHinhanhloaisp() {
        return Hinhanhloaisp;
    }

    public void setHinhanhloaisp(String hinhanhloaisp) {
        Hinhanhloaisp = hinhanhloaisp;
    }
}
