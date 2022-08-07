package come.geekbrains.vitekm.mvpkotlinproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import come.geekbrains.vitekm.mvpkotlinproject.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding

    private val presenter by moxyPresenter { CountersPresenter(CountersModel()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        with(binding) {
            btnNumber1.setOnClickListener {
                presenter.onCounterClickOne()

            }
            btnNumber2.setOnClickListener {
                presenter.onCounterClickTwo()

            }
            btnNumber3.setOnClickListener {
                presenter.onCounterClickThree()
            }

        }
    }


    override fun setTextOne(counters: String) = with(binding) {
      tvTextOne.text = counters
    }

    override fun setTextTwo(counters: String) = with(binding) {
       tvTextTwo.text = counters
    }

    override fun setTextThree(counters: String) = with(binding){
        tvTextThree.text = counters
    }
}