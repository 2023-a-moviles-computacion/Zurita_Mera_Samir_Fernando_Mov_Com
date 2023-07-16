import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.examenib.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnZapatos = findViewById<Button>(R.id.btnZapatos)
        btnZapatos.setOnClickListener {
            // Realizar acciones al hacer clic en el botón de Zapatos
        }

        val btnMarcas = findViewById<Button>(R.id.btnMarcas)
        btnMarcas.setOnClickListener {
            // Realizar acciones al hacer clic en el botón de Marcas
        }
    }
}
