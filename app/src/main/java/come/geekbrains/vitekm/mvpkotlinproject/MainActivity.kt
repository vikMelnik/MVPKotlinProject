package come.geekbrains.vitekm.mvpkotlinproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import come.geekbrains.vitekm.mvpkotlinproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val counters = mutableListOf(0, 0, 0)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        with(binding){
            btnNumber1.setOnClickListener {
                tvText1.text = (++ counters[0]).toString()
            }
            btnNumber2.setOnClickListener {
                tvText2.text =  counters[1].toString()
            }
            btnNumber3.setOnClickListener {
                tvText3.text = counters[2].toString()
            }

        }
    }

    private fun initViews() {
        with(binding){
            tvText1.text = counters[0].toString()
            tvText2.text = counters[1].toString()
            tvText3.text = counters[2].toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntArray("counters", counters.toIntArray())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val array = savedInstanceState.getIntArray("counters")
        counters.let { list->
            list.clear()
            array?.toList()?.let {
                list.addAll(it)
            }
        }
        initViews()
    }
}