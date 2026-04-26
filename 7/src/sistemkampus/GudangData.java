
package sistemkampus;

import java.util.ArrayList;
import java.util.List;

public class GudangData<T> {
    private List<T> daftarData = new ArrayList<>();

    public void tambahData(T data) {
        daftarData.add(data);
    }

    // Agar MainAplikasi bisa mengambil list-nya untuk di-looping
    public List<T> getDaftarData() {
        return daftarData;
    }

    public void tampilkanSemua() {
        for (T data : daftarData) {
            System.out.println(data.toString());
        }
    }
}