package come.geekbrains.vitekm.mvpkotlinproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import come.geekbrains.vitekm.mvpkotlinproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding

    private lateinit var presenter: CountersPresenter

    private var counterModel = CountersModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPresenter()

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

    private fun initPresenter() {
        presenter = CountersPresenter(this, counterModel)
    }

    override fun setTextOne(counters: String) {
        binding.tvText1.text = counters
    }

    override fun setTextTwo(counters: String) {
        binding.tvText2.text = counters
    }

    override fun setTextThree(counters: String) {
        binding.tvText3.text = counters
    }
}