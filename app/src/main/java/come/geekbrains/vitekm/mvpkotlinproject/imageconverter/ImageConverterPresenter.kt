package come.geekbrains.vitekm.mvpkotlinproject.imageconverter

import android.net.Uri
import com.github.terrakok.cicerone.Router
import come.geekbrains.vitekm.mvpkotlinproject.model.ConverterJpgToPng
import come.geekbrains.vitekm.mvpkotlinproject.schedulers.MySchedulers
import come.geekbrains.vitekm.mvpkotlinproject.allinterface.ImageConverterView
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class ImageConverterPresenter(
    private val converter: ConverterJpgToPng,
    private val schedulers: MySchedulers,
    private val router: Router
) : MvpPresenter<ImageConverterView>() {

    var disposables = CompositeDisposable()


    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    override fun onDestroy() {
        disposables.clear()
    }


    // Процесс конвертации изображения

    fun startConvertingPressed(imageUri: Uri) {
        viewState.showProgressBar()
        viewState.signWaitingShow()
        viewState.signGetStartedHide()
        viewState.btnAbortConvertEnabled()
        converter
            .convertRx(imageUri)
            .subscribeOn(schedulers.computation())
            .observeOn(schedulers.main())
            .subscribe(object : SingleObserver<Uri> {
                override fun onSubscribe(d: Disposable?) {
                    disposables.add(d)
                }

                override fun onSuccess(t: Uri?) {
                    if (t != null) {
                        onConvertingSuccess(t)
                    }
                }

                override fun onError(e: Throwable?) {
                    onConvertingError(e)
                }
            })
    }


     //  Обработка успеха от источника

    private fun onConvertingSuccess(uri: Uri) {
        viewState.showConvertedImage(uri)
        viewState.hideProgressBar()
        viewState.btnAbortConvertDisabled()
        viewState.signAbortConvertHide()
        viewState.signWaitingHide()
    }


    // Обработка ошибки от источника

    private fun onConvertingError(e: Throwable?) {
        viewState.showErrorBar()
        viewState.hideProgressBar()
        viewState.btnAbortConvertDisabled()
        viewState.signWaitingHide()
    }



     //Прерывание процесса конвертации изображения

    fun abortConvertImagePressed() {
        schedulers.shutdown()
        viewState.hideProgressBar()
        viewState.signWaitingHide()
        viewState.btnAbortConvertDisabled()
        viewState.signAbortConvertShow()
        schedulers.start()
    }


    fun originalImageSelected(imageUri: Uri) {
        viewState.showOriginImage(imageUri)
        viewState.btnStartConvertEnable()
        viewState.signAbortConvertHide()
        viewState.signGetStartedHide()
        viewState.hideErrorBar()
        viewState.signWaitingShow()
    }
}